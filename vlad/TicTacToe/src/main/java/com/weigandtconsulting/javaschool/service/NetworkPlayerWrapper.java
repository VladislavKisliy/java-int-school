/*
 * Copyright (C) 2015 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.service;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.weigandtconsulting.javaschool.api.BaseTicTacToe;
import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.api.Observer;
import com.weigandtconsulting.javaschool.api.Referee;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.NetworkRequest;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.network.Network;
import com.weigandtconsulting.javaschool.network.Network.NetworkMode;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class NetworkPlayerWrapper extends BaseTicTacToe {

    private static final Logger LOG = Logger.getLogger(NetworkPlayerWrapper.class.getName());
    private static final int TIMEOUT = 5000;
//    private final TicTacToe player;
    private final BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Request> responseQueue = new LinkedBlockingQueue<>();
    private final Object lockObject = new Object();
//    private final NetworkService networkService;
    private final Client client = new Client();

    public NetworkPlayerWrapper() {
//        this.player = player;
        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof Request) {
                    Request response = (Request) object;
                    lastTurn = response.getGameField();
                    notifyObservers();
                    synchronized (lockObject) {
                        lockObject.notifyAll();
                    }
                }
            }
        });
    }

//    public void init() throws IOException {
//        client.start();
//        // timeout, ip, tcpPort, udpPort
//        client.connect(TIMEOUT, "127.0.0.1", Network.TCP_PORT, Network.UDP_PORT);
//        Network.register(client);
//    }
    @Override
    public boolean hasNextStep(List<CellState> gameField) {
        return false;
    }

    @Override
    public String getPlayerName() {
        return "Network player";
    }

    @Override
    public Request getRequest(List<CellState> gameField) {
        LOG.log(Level.INFO, "Send request to server");
        client.start();
        try {
            // timeout, ip, tcpPort, udpPort
            client.connect(TIMEOUT, "127.0.0.1", Network.TCP_PORT, Network.UDP_PORT);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        Network.register(client);

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                System.out.println("~~ Something from server");
                if (object instanceof Request) {
                    Request response = (Request) object;
                    System.out.println("Answer from server: " + response.getRefereeRequest());
                    System.out.println("Answer from server: " + response.getGameField());
                }
            }
        });
        GameFieldHelper gameFieldHelper = new GameFieldHelperImpl();
        Request request = new Request(CellState.TIC);
        request.setGameField(gameFieldHelper.getNewField());
        request.setRefereeRequest(RefereeRequest.EMPTY);
        System.out.println("-- request 1");
        client.sendTCP(request);
//        TimeUnit.SECONDS.sleep(1);
        if (client.isConnected()) {
            System.out.println("I am connected");
        } else {
            System.out.println("I was disconnected");
        }
//        client.close();

//        Request result = new Request(this);
//        result.setRefereeRequest(RefereeRequest.EMPTY);
//        result.setGameField(gameField);
//        System.out.println("-- request 1");
//        client.sendTCP(result);
//        if (client.isConnected()) {
//            System.out.println("I am connected");
//        } else {
//            System.out.println("I was disconnected");
//        }
//        client.close();
////        TimeUnit.SECONDS.sleep(1);
//
//        synchronized (lockObject) {
//            try {
//                lockObject.wait();
//            } catch (InterruptedException ex) {
//                LOG.log(Level.SEVERE, "Human Player is interrupted", ex);
//            }
//        }
        return request;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        Request request = getRequest(gameField);
        return request.getGameField();
    }
}

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
package com.weigandtconsulting.javaschool.network;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import com.weigandtconsulting.javaschool.players.Player;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Send responses from Player class. Created for test purpose
 * @author vlad
 */
public class GameClient {

    private final String serverHost;
    private final Client client;
    private final TicTacToe player = new Player(CellState.TAC);
    private int timeout = 5000;

    public GameClient(String host) {
        this.serverHost = host;
        client = new Client();
    }

    public GameClient(String host, int timeout) {
        this(host);
        this.timeout = timeout;
    }

    public void connectToServer() throws IOException {
        client.start();
        // timeout, ip, tcpPort, udpPort
        client.connect(timeout, serverHost, Network.TCP_PORT, Network.UDP_PORT);
        Network.register(client);

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof Request) {
                    Request response = (Request) object;
                    System.out.println("Answer from server: " + response.getRefereeRequest());
                    System.out.println("Answer from server: " + response.getGameField());
                    Request request = player.getRequest(response.getGameField());
                    System.out.println("Send to server: " + request);
                    connection.sendTCP(request);
                }
            }
        });
        GameFieldHelper gameFieldHelper = new GameFieldHelperImpl();
        Request request = new Request(CellState.TAC);
        request.setGameField(gameFieldHelper.getNewField());
        request.setRefereeRequest(RefereeRequest.EMPTY);
        System.out.println("-- request 1 ="+request);
        client.sendTCP(request);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        GameClient networkClient = new GameClient("127.0.0.1");
        networkClient.connectToServer();
        TimeUnit.SECONDS.sleep(17);
//        GameClient networkClient1 = new GameClient("127.0.0.1");
//        networkClient1.connectToServer();
//        GameClient networkClient2 = new GameClient("127.0.0.1");
//        networkClient2.connectToServer();
    }
}

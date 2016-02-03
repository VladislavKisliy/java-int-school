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
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.network.Network;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class ClientPlayer extends BaseTicTacToe {

    private static final Logger LOG = Logger.getLogger(ClientPlayer.class.getName());
    private static final int TIMEOUT = 5000;
    private final String hostname;
    private final Client client = new Client();

    public ClientPlayer() {
        this.hostname = "127.0.0.1";
        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof Request) {
                    Request response = (Request) object;
                    lastTurn = response.getGameField();
                    notifyObservers();
                }
            }
        });
    }

    public ClientPlayer(String hostname) {
        this.hostname = hostname;
    }

    public ClientPlayer(String hostname, CellState playerSign) {
        super(playerSign);
        this.hostname = hostname;
    }

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
        if (!client.isConnected()) {
            client.start();
            try {
                // timeout, ip, tcpPort, udpPort
                client.connect(TIMEOUT, hostname, Network.TCP_PORT, Network.UDP_PORT);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "", ex);
            }
            Network.register(client);
        }
        Request request = new Request(CellState.TIC);
        request.setGameField(gameField);
        request.setRefereeRequest(RefereeRequest.EMPTY);
        client.sendTCP(request);
        return request;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        Request request = getRequest(gameField);
        return request.getGameField();
    }
}

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
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import com.weigandtconsulting.javaschool.players.Player;
import java.io.IOException;

/**
 * Send responses from Player class. Created for test purpose
 * @author vlad
 */
public class GameClient {

    private final String serverHost;
    private final Client client;
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
                }
            }
        });
        GameFieldHelper gameFieldHelper = new GameFieldHelperImpl();
        Request request = new Request(CellState.TIC);
        request.setGameField(gameFieldHelper.getNewField());
        request.setRefereeRequest(RefereeRequest.EMPTY);
        System.out.println("-- request 1");
        client.sendTCP(request);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        GameClient networkClient = new GameClient("127.0.0.1");
        networkClient.connectToServer();
        GameClient networkClient1 = new GameClient("127.0.0.1");
        networkClient1.connectToServer();
        GameClient networkClient2 = new GameClient("127.0.0.1");
        networkClient2.connectToServer();
    }
}

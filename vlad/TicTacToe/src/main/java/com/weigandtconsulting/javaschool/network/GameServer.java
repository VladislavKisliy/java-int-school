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

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.players.Player;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Send responses from Player class. Created for test purpose
 * @author vlad
 */
public class GameServer {

    private static final Logger LOG = Logger.getLogger(GameServer.class.getName());

    private final Server server;
    private final TicTacToe player = new Player();

    public GameServer() throws IOException {
        server = new Server() {
        };

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                LOG.log(Level.INFO, "New connection to server ={0}, recieved ={1}", new Object[]{connection, object});
                // We know all connections for this server are actually ChatConnections.
                if (object instanceof Request) {
                    Request request = (Request) object;
                    System.out.println("Get from client: " + request);
                    Request response = player.getRequest(request.getGameField());
                    System.out.println("Send to client: " + response);
                    connection.sendTCP(response);
                }
            }
        });
    }

    public void init() throws IOException {
        server.start();
        server.bind(Network.TCP_PORT, Network.UDP_PORT);

        // For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(server);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        GameServer networkServer = new GameServer();
        networkServer.init();
    }
}

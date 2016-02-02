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
import com.weigandtconsulting.javaschool.beans.Request;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class NetworkServer{
    private static final Logger LOG = Logger.getLogger(NetworkServer.class.getName());
    
    private final Server server;

    public NetworkServer() throws IOException {
        server = new Server() {
        };

		// For consistency, the classes to be sent over the network are
        // registered by the same method for both the client and server.
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                // We know all connections for this server are actually ChatConnections.
                if (object instanceof Request) {
                    Request response = (Request) object;
                    System.out.println("Answer from server: " + response.getRefereeRequest());
                    System.out.println("Answer from server: " + response.getGameField());
                }
            }
        });
        server.bind(Network.TCP_PORT, Network.UDP_PORT);
        server.start();

    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        NetworkServer networkServer = new NetworkServer();
    }
}
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
import com.esotericsoftware.kryonet.Server;
import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.network.Network;
import com.weigandtconsulting.javaschool.network.Network.NetworkMode;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class NetworkService {

    private static final Logger LOG = Logger.getLogger(NetworkService.class.getName());
    private static final int TIMEOUT = 5000;

    private final NetworkMode mode;
    private final AtomicBoolean bindServer = new AtomicBoolean(false);
    private final Server server = new Server();
    private final Client client = new Client();

    private final Queue<Request> requestQueue;
    private final Queue<Request> responseQueue;

    private String serverHost;

    public NetworkService(NetworkMode mode, Queue<Request> requestQueue, Queue<Request> responseQueue) {
        this.mode = mode;
        this.requestQueue = requestQueue;
        this.responseQueue = responseQueue;
    }

    public NetworkService(NetworkMode mode, Queue<Request> requestQueue, Queue<Request> responseQueue, String serverHost) {
        this(mode, requestQueue, responseQueue);
        this.serverHost = serverHost;
    }

    public void sendResponse() {
        if (responseQueue.isEmpty()) {
            throw new IllegalArgumentException("Nothing to do. responseQueue is empty");
        } else {
            Request response = responseQueue.remove();
            if (mode == NetworkMode.SERVER) {
                server.sendToAllTCP(response);
            } else {
                client.sendTCP(response);
            }
        }
    }

    public void stop() {
        server.close();
    }

    public void initServer() {
        Network.register(server);
        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                processingMessage(object);
            }
        });
        try {
            server.bind(Network.TCP_PORT, Network.UDP_PORT);
            bindServer.set(true);
        } catch (IOException ex) {
            Logger.getLogger(NetworkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        server.start();
    }

    public void initClient() throws IOException {
        if (serverHost == null) {
            throw new IllegalArgumentException("Client mode need to set up serverHost");
        }
        client.start();
        // timeout, ip, tcpPort, udpPort
        client.connect(TIMEOUT, serverHost, Network.TCP_PORT, Network.UDP_PORT);
        Network.register(client);

        client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                processingMessage(object);
            }
        });
    }

    private void processingMessage(Object object) {
        if (object instanceof Request) {
            Request request = (Request) object;
            if (requestQueue.isEmpty()) {
                requestQueue.add(request);
            } else {
                LOG.log(Level.INFO, "Request ignored");
            }
            LOG.log(Level.INFO, "Answer from server: {0}", request);
        }
    }
}

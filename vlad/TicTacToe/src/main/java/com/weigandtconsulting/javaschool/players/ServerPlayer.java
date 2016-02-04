/*
 * Copyright (C) 2016 Weigandt Consulting
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
package com.weigandtconsulting.javaschool.players;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.network.Network;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vlad
 */
public class ServerPlayer extends BaseTicTacToe {

    private static final Logger LOG = Logger.getLogger(ServerPlayer.class.getName());
    CountDownLatch startSignal = new CountDownLatch(1);
    private final Server server = new Server();
    private Request userResponse;

    public ServerPlayer(CellState playerSign) throws IOException {
        super(playerSign);
        server.start();
        server.bind(Network.TCP_PORT, Network.UDP_PORT);

        Network.register(server);
        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                super.received(connection, object);
                LOG.log(Level.INFO, "Recieved from client ={0}, recieved ={1}", new Object[]{connection, object});
                if (object instanceof Request) {
                    Request request = (Request) object;
                    System.out.println("Get from client: " + request);
                    if (userResponse != null) {
                        LOG.log(Level.INFO, "Send to client: {0}", userResponse);
                        connection.sendTCP(userResponse);
                        lastTurn = request.getGameField();
                        notifyObservers();
                        userResponse = null;
                    }
                }
            }

//            @Override
//            public void connected(Connection connection) {
//                super.connected(connection);
//                LOG.log(Level.INFO, "New connection to server ={0}", new Object[]{connection});
//                startSignal.countDown();
//                if (userResponse != null) {
//                    LOG.log(Level.INFO, "Send to client: {0}", userResponse);
//                    connection.sendTCP(userResponse);
//                }
//            }
        });

    }

    @Override
    public boolean hasNextStep(List<CellState> gameField) {
        return false;
    }

    @Override
    public String getPlayerName() {
        return "Network server player";
    }

    @Override
    public Request getRequest(List<CellState> gameField) {
        LOG.log(Level.INFO, "Send request to client");
        Request request = new Request(CellState.TIC);
        request.setGameField(gameField);
        request.setRefereeRequest(RefereeRequest.EMPTY);
        userResponse = request;
        if (server.getConnections().length > 0) {
            server.sendToAllTCP(request);
        } else {
//            server.start();
//            try {
//                server.bind(Network.TCP_PORT, Network.UDP_PORT);
//                Network.register(server);
//                startSignal.await();
////                server.sendToAllTCP(request);
//            } catch (IOException | InterruptedException ex) {
//                LOG.log(Level.SEVERE, "Connection problem", ex);
//                request.setRefereeRequest(RefereeRequest.ERROR);
//                request.setMessage(ex.getLocalizedMessage());
//                notifyObservers(request);
//            }

        }
        LOG.log(Level.INFO, "Request ={0}", request);
        return request;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        Request request = getRequest(gameField);
        return request.getGameField();
    }

}

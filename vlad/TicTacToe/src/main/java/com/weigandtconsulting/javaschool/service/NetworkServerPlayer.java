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

import com.weigandtconsulting.javaschool.api.Observer;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.NetworkRequest;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.network.Network;
import com.weigandtconsulting.javaschool.network.Network.NetworkMode;
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
public class NetworkServerPlayer implements TicTacToe {

    private final TicTacToe player = new Player(CellState.TIC);
    private final BlockingQueue<Request> requestQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<Request> responseQueue = new LinkedBlockingQueue<>();
    private final NetworkService networkService;

    public NetworkServerPlayer() {
        this.networkService = new NetworkService(NetworkMode.SERVER, requestQueue, responseQueue);
        this.networkService.initServer();
    }

    @Override
    public boolean hasNextStep(List<CellState> gameField) {
        return player.hasNextStep(gameField);
    }

    @Override
    public String getPlayerName() {
        return "Network player";
    }

    @Override
    public Request getRequest(List<CellState> gameField) {
        Request result = null;
        Request request = new Request(this);
        request.setRefereeRequest(RefereeRequest.EMPTY);
        request.setGameField(nextStep(gameField));
        responseQueue.add(request);
        Request take = null;
        try {
            take = requestQueue.take();
        } catch (InterruptedException ex) {
            Logger.getLogger(NetworkServerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (take == null) {
            System.out.println("Nothing");
        } else {
            System.out.println("take =" + take);
        }
        return result;
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unregisterObserver(Observer observer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

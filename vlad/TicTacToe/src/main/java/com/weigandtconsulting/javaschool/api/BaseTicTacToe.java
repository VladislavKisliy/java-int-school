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
package com.weigandtconsulting.javaschool.api;

import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vlad
 */
public abstract class BaseTicTacToe implements TicTacToe {

    protected List<CellState> lastTurn;
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public Request getRequest(List<CellState> gameField) {
        Request request = new Request(this);
        request.setRefereeRequest(RefereeRequest.EMPTY);
        request.setGameField(nextStep(gameField));
        return request;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        if (lastTurn != null) {
            for (Observer observer : observers) {
                Request request = new Request(this);
                request.setRefereeRequest(RefereeRequest.EMPTY);
                request.setGameField(lastTurn);
                observer.update(request);
            }
        } else {
            throw new IllegalArgumentException("lastTurn is null");
        }
    }
}

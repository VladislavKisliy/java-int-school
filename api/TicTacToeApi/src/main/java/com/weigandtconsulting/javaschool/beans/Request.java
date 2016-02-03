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
package com.weigandtconsulting.javaschool.beans;

import java.util.List;

/**
 *
 * @author vlad
 */
public class Request {

    private String message;
    private CellState playerSign;
    private List<CellState> gameField;
    private RefereeRequest refereeRequest;

    public Request() {
    }

    public Request(CellState playerSign) {
        this.playerSign = playerSign;
    }

    public Request(RefereeRequest refereeRequest) {
        this.refereeRequest = refereeRequest;
    }
    
    public List<CellState> getGameField() {
        return gameField;
    }

    public void setGameField(List<CellState> gameField) {
        this.gameField = gameField;
    }

    public RefereeRequest getRefereeRequest() {
        return refereeRequest;
    }

    public void setRefereeRequest(RefereeRequest refereeRequest) {
        this.refereeRequest = refereeRequest;
    }

    public CellState getPlayerSign() {
        return playerSign;
    }

    public void setPlayerSign(CellState playerSign) {
        this.playerSign = playerSign;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Request{" + "message=" + message + ", playerSign=" + playerSign + ", gameField=" + gameField + ", refereeRequest=" + refereeRequest + '}';
    }
}

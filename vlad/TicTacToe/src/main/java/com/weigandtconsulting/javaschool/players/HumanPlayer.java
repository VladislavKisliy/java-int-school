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

import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javafx.scene.control.Button;

/**
 *
 * @author vlad
 */
public class HumanPlayer extends BaseTicTacToe {

    private static final String PLAYER_NAME = "Human Player";

    public HumanPlayer() {
        super();
    }

    public HumanPlayer(CellState playerSign) {
        super(playerSign);
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        return null;
    }

    @Override
    public String getPlayerName() {
        return PLAYER_NAME;
    }

    @Override
    public String toString() {
        return "HumanPlayer{" + "playerSymbol=" + playerSign + '}';
    }

    public void setLastTurn(List<CellState> lastTurn) {
        this.lastTurn = lastTurn;
    }

    @Override
    public Request getRequest(List<CellState> gameField) {
        Request request = new Request(this.getPlayerSign());
        request.setGameField(nextStep(gameField));
        request.setRefereeRequest(RefereeRequest.EMPTY);
        return request;
    }

}

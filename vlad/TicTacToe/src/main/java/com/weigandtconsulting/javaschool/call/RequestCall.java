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
package com.weigandtconsulting.javaschool.call;

import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.controllers.FXMLController;
import java.util.List;
import javafx.application.Platform;

/**
 *
 * @author vlad
 */
public class RequestCall implements Runnable {

    private final TicTacToe activePlayer;
    private final List<CellState> gameField;
    private final Showable view;

    public RequestCall(TicTacToe activePlayer, List<CellState> gameField, Showable view) {
        this.activePlayer = activePlayer;
        this.gameField = gameField;
        this.view = view;
    }

    @Override
    public void run() {
        System.out.println("Start from =" + activePlayer);
        if (!(activePlayer instanceof FXMLController.HumanPlayer)) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    view.lockBattleField();
                }
            });
        }
        activePlayer.getRequest(gameField);
        System.out.println("Referee died.");
    }

}

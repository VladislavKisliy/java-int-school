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

import com.weigandtconsulting.javaschool.api.BaseTicTacToe;
import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.beans.CellState;
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
    private final GameFieldHelper innerGameField = new GameFieldHelperImpl();
    private Button[] buttons = new Button[9];

    public HumanPlayer() {
        super();
    }

    public HumanPlayer(CellState playerSign) {
        super(playerSign);
    }

    @Override
    public List<CellState> nextStep(List<CellState> gameField) {
        lastTurn = generateGameField();
//        notifyObservers();
        return lastTurn;
    }

    @Override
    public String getPlayerName() {
        return PLAYER_NAME;
    }

    @Override
    public String toString() {
        return "HumanPlayer{" + "playerSymbol=" + playerSign + '}';
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }
    
    private List<CellState> generateGameField() {
        List<CellState> gameField = innerGameField.getNewField();
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            CellState cellState = CellState.getCellState(button.getText());
            gameField.set(i, cellState);
        }
        return gameField;
    }

}

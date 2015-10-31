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
package com.weigandtconsulting.javaschool.controllers;

import com.weigandtconsulting.javaschool.api.BaseTicTacToe;
import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class FXMLController implements Initializable, Showable {

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;

    private final Object lockObject = new Object();
    private RefereeRequest refereeRequest = RefereeRequest.EMPTY;
    private CellState playerSign = CellState.TOE;
    private boolean waitStepDone = true;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me! event source" + event.getSource());
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            applyCellButton(playerSign, button);
            synchronized (lockObject) {
                waitStepDone = false;
                lockObject.notifyAll();
            }
        }
    }

    @FXML
    private void handleConnectToAction(ActionEvent event) {
        System.out.println("handleConnectToAction = You clicked me! event source" + event.getSource());
    }

    @FXML
    private void handleCreateServerAction(ActionEvent event) {
        System.out.println("handleCreateServerAction = You clicked me! event source" + event.getSource());
    }

    @FXML
    private void handleRestartAction(ActionEvent event) {
        System.out.println("handleRestartAction = You clicked me! event source" + event.getSource());
        refereeRequest = RefereeRequest.RESTART;
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            synchronized (lockObject) {
                waitStepDone = false;
                lockObject.notifyAll();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void refreshBattleField(List<CellState> battleField) {
        int counterCell = 0;
        for (CellState cellState : battleField) {
            switch (counterCell) {
                case 0:
                    applyCellButton(cellState, button0);
                    break;
                case 1:
                    applyCellButton(cellState, button1);
                    break;
                case 2:
                    applyCellButton(cellState, button2);
                    break;
                case 3:
                    applyCellButton(cellState, button3);
                    break;
                case 4:
                    applyCellButton(cellState, button4);
                    break;
                case 5:
                    applyCellButton(cellState, button5);
                    break;
                case 6:
                    applyCellButton(cellState, button6);
                    break;
                case 7:
                    applyCellButton(cellState, button7);
                    break;
                case 8:
                    applyCellButton(cellState, button8);
                    break;

            }
            counterCell++;
        }
    }

    private void applyCellButton(CellState cellState, Button button) {
        switch (cellState) {
            case TOE:
                button.setText("");
                button.setDisable(false);
                break;
            default:
                button.setText(cellState.toString());
                button.setDisable(true);
                break;
        }
    }

    @Override
    public void lockBattleField() {
        button0.setDisable(true);
        button1.setDisable(true);
        button2.setDisable(true);
        button3.setDisable(true);
        button4.setDisable(true);
        button5.setDisable(true);
        button6.setDisable(true);
        button7.setDisable(true);
        button8.setDisable(true);
    }

    public class HumanPlayer extends BaseTicTacToe {

        private final CellState playerSymbol;
        private final GameFieldHelper innerGameField = new GameFieldHelperImpl();

        public HumanPlayer(CellState mySymbol) {
            this.playerSymbol = mySymbol;
            playerSign = playerSymbol;
        }

        @Override
        public List<CellState> nextStep(List<CellState> gameField) {
            while (waitStepDone) {
                synchronized (lockObject) {
                    try {
                        lockObject.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            waitStepDone = true;
            return generateGameField();
        }

        @Override
        public boolean hasNextStep(List<CellState> gameField) {
            return !(innerGameField.isGameOver(gameField));
        }

        @Override
        public String getPlayerName() {
            return "Human Player";
        }

        private List<CellState> generateGameField() {
            List<CellState> gameField = innerGameField.getNewField();
            Button[] buttons = new Button[]{button0, button1, button2,
                button3, button4, button5,
                button6, button7, button8};
            for (int i = 0; i < buttons.length; i++) {
                Button button = buttons[i];
                CellState cellState = CellState.getCellState(button.getText());
                gameField.set(i, cellState);
            }
            return gameField;
        }

        @Override
        public Request getRequest(List<CellState> gameField) {
            Request request = new Request();
            // Reset old value
            if (refereeRequest != RefereeRequest.EMPTY) {
                refereeRequest = RefereeRequest.EMPTY;
            }
            
            request.setGameField(nextStep(gameField));
            request.setRefereeRequest(refereeRequest);
            return request;
        }
    }
}

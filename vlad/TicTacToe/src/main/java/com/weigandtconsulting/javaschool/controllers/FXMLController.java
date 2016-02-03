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
import com.weigandtconsulting.javaschool.api.Observer;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

public class FXMLController implements Initializable, Showable {

    private static final Logger LOG = Logger.getLogger(FXMLController.class.getName());

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

    private Alert alertDialog;

    private final Object lockObject = new Object();
    private RefereeRequest refereeRequest = RefereeRequest.EMPTY;
    private TicTacToe player;

    private final List<Observer> observers = new ArrayList<>();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me! event source" + event.getSource());
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            applyCellButton(player.getPlayerSign(), button);
            refereeRequest = RefereeRequest.EMPTY;
            player.notifyObservers();
            synchronized (lockObject) {
                lockObject.notifyAll();
            }
        }
    }

    @FXML
    private void handleConnectToAction(ActionEvent event) {
        System.out.println("handleConnectToServer = You clicked me! event source" + event.getSource());
        TextInputDialog dialog = new TextInputDialog("localhost");
        dialog.setTitle("Connect to");
        dialog.setHeaderText("Network information");
        dialog.setContentText("Please enter server ");

        // The Java 8 way to get the response value (with lambda expression).
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("dialog get =" + result.get());
        }
//        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    @FXML
    private void handleCreateServerAction(ActionEvent event) {
        System.out.println("handleCreateServerAction = You clicked me! event source" + event.getSource());
    }

    @FXML
    private void handleRestartAction(ActionEvent event) {
        System.out.println("handleRestartAction = You clicked me! event source" + event.getSource());
        refereeRequest = RefereeRequest.RESTART;
        player.notifyObservers();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public synchronized void refreshBattleField(List<CellState> battleField) {
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
                button.setId("button");
                button.setDisable(false);
                break;
            case TIC:
                button.setText(cellState.toString());
                button.setId("button-tic");
                button.setDisable(true);
                break;
            case TAC:
                button.setText(cellState.toString());
                button.setId("button-tac");
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

    @Override
    public void showWaitingDailog(boolean visibleStatus) {
        if (alertDialog == null) {
            alertDialog = new Alert(AlertType.INFORMATION);
            alertDialog.setTitle("Waiting Dialog");
            alertDialog.setHeaderText(null);
            alertDialog.setContentText("Please wait. Your opponent is still thinking");
        }
        if (visibleStatus) {
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }
        } else {
            alertDialog.hide();
        }
    }

    public TicTacToe getPlayer(CellState mySymbol) {
        if (player == null) {
            player = new HumanPlayer(mySymbol);
        }
        return player;
    }

    public class HumanPlayer extends BaseTicTacToe {

        private static final String PLAYER_NAME = "Human Player";
        private final GameFieldHelper innerGameField = new GameFieldHelperImpl();

        public HumanPlayer() {
            super();
        }

        public HumanPlayer(CellState playerSign) {
            super(playerSign);
        }

        @Override
        public List<CellState> nextStep(List<CellState> gameField) {
            synchronized (lockObject) {
                try {
                    lockObject.wait();
                } catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, "Human Player is interrupted", ex);
                }
            }
            return generateGameField();
        }

        @Override
        public boolean hasNextStep(List<CellState> gameField) {
            return !(innerGameField.isGameOver(gameField));
        }

        @Override
        public String getPlayerName() {
            return PLAYER_NAME;
        }

        @Override
        public String toString() {
            return "HumanPlayer{" + "playerSymbol=" + playerSign + '}';
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
            Request request = new Request(this.getPlayerSign());
            // Reset old value
            if (refereeRequest != RefereeRequest.EMPTY) {
                refereeRequest = RefereeRequest.EMPTY;
            }

            request.setGameField(nextStep(gameField));
            request.setRefereeRequest(refereeRequest);
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
            for (Observer observer : observers) {
                Request request = new Request(player.getPlayerSign());
                request.setRefereeRequest(refereeRequest);
                request.setGameField(generateGameField());
                observer.update(request);
            }
        }
    }
}

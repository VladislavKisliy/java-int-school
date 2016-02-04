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

import com.weigandtconsulting.javaschool.api.GameFieldHelper;
import com.weigandtconsulting.javaschool.api.Referee;
import com.weigandtconsulting.javaschool.api.Showable;
import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.beans.RefereeRequest;
import com.weigandtconsulting.javaschool.beans.Request;
import com.weigandtconsulting.javaschool.players.ClientPlayer;
import com.weigandtconsulting.javaschool.players.HumanPlayer;
import com.weigandtconsulting.javaschool.players.Player;
import com.weigandtconsulting.javaschool.players.ServerPlayer;
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import com.weigandtconsulting.javaschool.service.RefereeAsyncWrapper;
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
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

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

    private static final Logger LOG = Logger.getLogger(FXMLController.class.getName());
    private final GameFieldHelper innerGameField = new GameFieldHelperImpl();
    private final TicTacToe[] players = new TicTacToe[2];
    private Alert alertDialog;
    private HumanPlayer player;
    private Referee referee;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        LOG.log(Level.INFO, "You clicked me! event source{0}", event.getSource());
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            if (player != null) {
                applyCellButton(player.getPlayerSign(), button);
                player.setLastTurn(generateGameField());
                player.notifyObservers();
            }
        }
    }

    @FXML
    private void handleLocalGameAction(ActionEvent event) {
        LOG.log(Level.INFO, "You clicked handleLocalGameAction! event source{0}", event.getSource());
        String firstPlayerTic = "First player - X";
        String secondPlayerTac = "Second player - 0";
        List<String> choices = new ArrayList<>();
        choices.add(firstPlayerTic);
        choices.add(secondPlayerTac);

        ChoiceDialog<String> dialog = new ChoiceDialog<>(firstPlayerTic, choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your player:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            TicTacToe playerTac;
            TicTacToe playerTic;
            System.out.println("Your choice: " + result.get());
            if (firstPlayerTic.equals(result.get())) {
                playerTic = getPlayer(CellState.TIC);
                playerTac = new Player(CellState.TAC);

            } else {
                playerTic = new Player(CellState.TIC);
                playerTac = getPlayer(CellState.TAC);
            }
            if (referee != null) {
                shutdownGame();
            }
            setupNewGame(playerTic, playerTac);
        }
    }

    @FXML
    private void handleConnectToAction(ActionEvent event) {
        LOG.log(Level.INFO, "handleConnectToServer = You clicked me! event source{0}", event.getSource());
        TextInputDialog dialog = new TextInputDialog("localhost");
        dialog.setTitle("Connect to");
        dialog.setHeaderText("Network information");
        dialog.setContentText("Please enter server ");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            LOG.log(Level.INFO, "dialog get ={0}", result.get());
            TicTacToe playerTac = getPlayer(CellState.TAC);
            TicTacToe playerTic = new ClientPlayer(result.get());
            setupNewGame(playerTic, playerTac);
        }
    }

    @FXML
    private void handleCreateServerAction(ActionEvent event) {
        LOG.log(Level.INFO, " ! handleCreateServer Action = You clicked me! event source{0}", event.getSource());
        TicTacToe playerTac = getPlayer(CellState.TAC);
        TicTacToe playerTic = new ServerPlayer(CellState.TIC);
        setupNewGame(playerTic, playerTac);
    }

    @FXML
    private void handleRestartAction(ActionEvent event) {
        LOG.log(Level.INFO, "handleRestartAction = You clicked me! event source{0}", event.getSource());
        player.notifyObservers(new Request(RefereeRequest.RESTART));

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
    public void showWaitingDialog(boolean visibleStatus) {
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
        player = new HumanPlayer(mySymbol);
        return player;
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
    public void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Something wrong!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setupNewGame(TicTacToe playerTic, TicTacToe playerTac) {
        referee = new RefereeAsyncWrapper(playerTic, playerTac, this);
        players[0] = playerTac;
        players[1] = playerTic;
        playerTic.registerObserver(referee);
        playerTac.registerObserver(referee);
        lockBattleField();
        referee.startGame(CellState.TIC);
    }

    private void shutdownGame() {
        for (TicTacToe localPlayer : players) {
            localPlayer.unregisterAllObservers();
        }
        referee.stopGame();
    }
}

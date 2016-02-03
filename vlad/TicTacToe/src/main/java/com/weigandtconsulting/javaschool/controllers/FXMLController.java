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
import com.weigandtconsulting.javaschool.service.GameFieldHelperImpl;
import com.weigandtconsulting.javaschool.service.RefereeAsyncWrapper;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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

    private Alert alertDialog;
    private HumanPlayer player;
    private final GameFieldHelper innerGameField = new GameFieldHelperImpl();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me! event source" + event.getSource());
        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            applyCellButton(player.getPlayerSign(), button);
            player.setLastTurn(generateGameField());
            player.notifyObservers();

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
            // Game settings
//        TicTacToe playerTic = new Player(CellState.TIC);
//        AsyncTicTacToe playerTac = new DumbPlayer(CellState.TAC);
            TicTacToe playerTac = getPlayer(CellState.TAC);
//        TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);

//        TicTacToe playerTic = new DumbPlayer(CellState.TIC);
            ClientPlayer playerTic = new ClientPlayer(result.get());
//        playerTic.init();
//        DumbPlayer playerTac = new DumbPlayer(CellState.TAC);
//        TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);
            Referee referee = new RefereeAsyncWrapper(playerTic, playerTac, this);

//       Referee referee = new RefereeImpl(playerTic, playerTac, fxmlController);
            playerTic.registerObserver(referee);
            playerTac.registerObserver(referee);
            lockBattleField();
            referee.startGame(CellState.TIC);
        }
//        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    @FXML
    private void handleCreateServerAction(ActionEvent event) {
        System.out.println(" ! handleCreateServer Action = You clicked me! event source" + event.getSource());
        // Game settings
//        TicTacToe playerTic = new Player(CellState.TIC);
//        AsyncTicTacToe playerTac = new DumbPlayer(CellState.TAC);
        TicTacToe playerTac = getPlayer(CellState.TAC);
//        TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);

//        TicTacToe playerTic = new DumbPlayer(CellState.TIC);
        ClientPlayer playerTic = new ClientPlayer();
//        playerTic.init();
//        DumbPlayer playerTac = new DumbPlayer(CellState.TAC);
//        TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);
        Referee referee = new RefereeAsyncWrapper(playerTic, playerTac, this);

//       Referee referee = new RefereeImpl(playerTic, playerTac, fxmlController);
        playerTic.registerObserver(referee);
        playerTac.registerObserver(referee);
        lockBattleField();
        referee.startGame(CellState.TIC);
//        Referee referee = new Referee(playerTic, playerTac, fxmlController);
    }

    @FXML
    private void handleRestartAction(ActionEvent event) {
        System.out.println("handleRestartAction = You clicked me! event source" + event.getSource());
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
        if (player == null) {
            player = new HumanPlayer(mySymbol);
        }
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
}

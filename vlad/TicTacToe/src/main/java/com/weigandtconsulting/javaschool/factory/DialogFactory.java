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
package com.weigandtconsulting.javaschool.factory;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

/**
 *
 * @author vlad
 */
public class DialogFactory {

    public static final String FIRST_PLAYER_TIC = "First player - X";
    public static final String SECOND_PLAYER_TAC = "Second player - 0";

    private static final String DEFAULT_HOSTNAME = "localhost";
    private static final String CHOOSE_DIALOG_TITLE = "Choice Dialog";
    private static final String CHOOSE_DIALOG_HEADER = "Look, a Choice Dialog";
    private static final String CHOOSE_DIALOG_PLAYER = "Choose your player:";

    public enum DialogType {

        WARN, ERROR, WAIT, CHOOSE_PLAYER, CONNECT_TO, SERVER
    }

    public static Dialog getDialog(DialogType dialogType, String message) {
        Dialog result = null;
        List<String> choices = new ArrayList<>();
        switch (dialogType) {
            case ERROR:
                result = new Alert(Alert.AlertType.ERROR);
                result.setTitle("Error Dialog");
                result.setHeaderText("Something wrong!");
                result.setContentText(message);
                break;
            case CHOOSE_PLAYER:
                choices.add(FIRST_PLAYER_TIC);
                choices.add(SECOND_PLAYER_TAC);
                result = new ChoiceDialog<>(FIRST_PLAYER_TIC, choices);
                result.setTitle(CHOOSE_DIALOG_TITLE);
                result.setHeaderText(CHOOSE_DIALOG_HEADER);
                result.setContentText(CHOOSE_DIALOG_PLAYER);
                break;
            case CONNECT_TO:
                result = new TextInputDialog("localhost");
                result.setTitle("Connect to");
                result.setHeaderText("Network information");
                // Create the username and password labels and fields.
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                TextField serverName = new TextField();
                serverName.setPromptText("Hostname");
                serverName.setText(DEFAULT_HOSTNAME);
                TextField playerName = new TextField();
                playerName.setText(SECOND_PLAYER_TAC);
                playerName.setDisable(true);

                grid.add(new Label("Server name:"), 0, 0);
                grid.add(serverName, 1, 0);
                grid.add(new Label("Player:"), 0, 1);
                grid.add(playerName, 1, 1);
                result.getDialogPane().setContent(grid);
//                result.setContentText("Please enter server ");
                break;
            case WAIT:
                result = new Alert(Alert.AlertType.INFORMATION);
                result.setTitle("Waiting Dialog");
                result.setHeaderText(null);
                result.setContentText("Please wait. Your opponent is still thinking");
                break;
            case SERVER:
                choices.add(FIRST_PLAYER_TIC);
                result = new ChoiceDialog<>(FIRST_PLAYER_TIC, choices);
                result.setTitle("Choice Dialog");
                result.setHeaderText("Look, a Choice Dialog");
                result.setContentText("Choose your player:");
                break;
        }
        return result;
    }

    public static Dialog getDialog(DialogType dialogType) {
        return getDialog(dialogType, null);
    }
}

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
package com.weigandtconsulting.javaschool;

import com.weigandtconsulting.javaschool.api.TicTacToe;
import com.weigandtconsulting.javaschool.beans.CellState;
import com.weigandtconsulting.javaschool.controllers.FXMLController;
import com.weigandtconsulting.javaschool.service.Player;
import com.weigandtconsulting.javaschool.service.Referee;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GuiApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        final FXMLController fxmlController = new FXMLController();
        fxmlLoader.setController(fxmlController);
        
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("TicTacToe GUI(JavaFX)");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
        
        // Use multithreading for avoid UI locks
        Task task = new Task<Void>() {
            @Override
            public Void call() {
                TicTacToe playerTic = new Player(CellState.TIC);
                TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);
                Referee referee = new Referee(playerTic, playerTac);
                referee.startGame(fxmlController, CellState.TIC);
                return null;
            }
        };
        new Thread(task).start();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
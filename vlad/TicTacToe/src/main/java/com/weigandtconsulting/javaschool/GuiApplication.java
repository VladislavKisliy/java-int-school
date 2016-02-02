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
import com.weigandtconsulting.javaschool.service.DumbPlayer;
import com.weigandtconsulting.javaschool.service.Player;
import com.weigandtconsulting.javaschool.service.Referee;
import com.weigandtconsulting.javaschool.service.RefereeAsyncWrapper;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
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

//        TicTacToe playerTic = new Player(CellState.TIC);
//        AsyncTicTacToe playerTac = new DumbPlayer(CellState.TAC);
        TicTacToe playerTac = fxmlController.getPlayer(CellState.TAC);
//        TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);
        
        
        DumbPlayer playerTic = new DumbPlayer(CellState.TIC);
//        DumbPlayer playerTac = new DumbPlayer(CellState.TAC);
//        TicTacToe playerTac = fxmlController.new HumanPlayer(CellState.TAC);
//        RefereeAsyncWrapper referee = new RefereeAsyncWrapper(playerTic, playerTac, fxmlController);

        Referee referee = new Referee(playerTac, playerTic, fxmlController);
        
        playerTic.registerObserver(referee);
        playerTac.registerObserver(referee);
        fxmlController.lockBattleField();
        referee.startGame(CellState.TIC);
//        Referee referee = new Referee(playerTic, playerTac, fxmlController);
//        
    }
//
    @Override
    public void stop() throws Exception {
        super.stop();
//        System.out.println("Try to exit");
//        if (referee != null) {
//            System.out.println("Try to stop the game");
//            referee.stopGame();
//        }
        Platform.exit();
        System.exit(0);
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

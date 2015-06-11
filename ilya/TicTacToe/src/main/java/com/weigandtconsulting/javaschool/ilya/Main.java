package com.weigandtconsulting.javaschool.ilya;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {

//        List<Integer> newGameField = new ArrayList<Integer>(9);
//        newGameField = Arrays.asList(0, 0, 0, 0, 0,0,0,0,0);

        int turnNumber = 1;

        GameField newGameField = new GameField(GameField.LINES_NUM);

        PlayTicTacToe playerA = new AIPlayer(GameSigns.CROSS);
        PlayTicTacToe playerB = new AIPlayer(GameSigns.ZERO);

        Judge gameJudge = new GameJudge();

        Showable printIt = new ShowCli();
//        GUIForm guiForm = new GUIForm();
//
//        Showable printIt = guiForm;

//        JFrame frame = new JFrame("GUIForm");
//        frame.setContentPane(guiForm.getPanel1());
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

        while (!gameJudge.isGameOver(newGameField)) {

            System.out.println("-=* " + turnNumber + " *=-");

            newGameField = playerA.nextStep(newGameField);

            printIt.refreshBattleField(newGameField);

            if (gameJudge.isCheatAfterMove(playerA.getCurrentPlayerMark(), newGameField, turnNumber)) {

                System.out.println("Player A is a CHEATER!!!!!");

            }

            if (gameJudge.isWon(playerA.getCurrentPlayerMark(),newGameField)) {

                System.out.println("Player A is Winner !!!!");

            }
            else if (gameJudge.hasNextStep(newGameField)) {

                newGameField = playerB.nextStep(newGameField);

                printIt.refreshBattleField(newGameField);

                if (gameJudge.isCheatAfterMove(playerA.getCurrentPlayerMark(), newGameField, turnNumber)) {

                    System.out.println("Player B is a CHEATER!!!!!");

                }

                if (gameJudge.isWon(playerB.getCurrentPlayerMark(),newGameField)){

                    System.out.println("Player B is Winner !!!!");

                }
            }
            else {

                System.out.println("Draw game!");

            }

            turnNumber++;

        }

    }
}

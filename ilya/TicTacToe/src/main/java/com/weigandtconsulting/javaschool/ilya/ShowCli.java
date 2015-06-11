package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 13/03/15.
 * ╔═══╤═══╤═══╗
 * ║   │   │   ║
 * ╟───┼───┼───╢
 * ║   │   │   ║
 * ╟───┼───┼───╢
 * ║   │   │   ║
 * ╚═══╧═══╧═══╝
 */
public class ShowCli implements Showable{

//    private static final String[] LINE_CHARS = {"╔","\u2564","\u2557"};


    public void refreshBattleField(GameField gameField){

        printBattleFieldTopLine(GameField.LINES_NUM);

        for(int i=0; i < gameField.getBoard().size(); i = i + GameField.LINES_NUM){

            System.out.print("║ ");

            for(int j =0; j < GameField.LINES_NUM; j++){

                System.out.print(getGameChar(gameField.getBoard().get(i + j)));

                if (j != GameField.LINES_NUM - 1) {
                    System.out.print(" │ ");
                } else {
                    System.out.println(" ║");
                }
            }
            if (i != (GameField.LINES_NUM*(GameField.LINES_NUM-1))){
            printBattleFieldMidleLine(GameField.LINES_NUM);
            }
        }
        printBattleFieldBottomLine(GameField.LINES_NUM);
    }

    public String getGameChar(GameSigns fieldSat){

        return fieldSat.playerSymbol;
    }

    private void printBattleFieldTopLine (int charsCount){
        System.out.print("╔");
        for (int i = 0; i < charsCount; i++){
            System.out.print("═══");
            if (i == (charsCount - 1)){
                System.out.println("╗");
            } else {
                System.out.print("╤");
            }
        }

    }

    private void printBattleFieldMidleLine (int charsCount){
        System.out.print("╟");
        for (int i = 0; i < charsCount; i++){
            System.out.print("───");
            if (i == (charsCount - 1)){
                System.out.println("╢");
            } else {
                System.out.print("┼");
            }
        }

    }

    private void printBattleFieldBottomLine (int charsCount){
        System.out.print("╚");
        for (int i = 0; i < charsCount; i++){
            System.out.print("═══");
            if (i == (charsCount - 1)){
                System.out.println("╝");
            } else {
                System.out.print("╧");
            }
        }

    }


}

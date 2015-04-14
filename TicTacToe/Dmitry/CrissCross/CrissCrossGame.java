package CrissCross;
import java.util.*;
public class CrissCrossGame {

	public static void main(String[] args) {
		//init gamefield
		final int length = 9;
		List<CellState> gameField = new ArrayList<CellState>();
		for (int i=0; i<length; i++)
			gameField.add(i, CellState.TOE);
		
		//init players and referee
		TicTacToe player1 = new CrissCrossPlayer(CellState.TIC,"Player1");
		TicTacToe player2 = new CrissCrossPlayer(CellState.TAC,"Player2");  
		Referee referee = new Referee(gameField);
		referee.addPlayer(player1);
		referee.addPlayer(player2);
		Showable show = new CrissCrossShow();
		
		//the game itself
		int step;
		do {
 			referee.nextPlayerStep(gameField);
 			show.refreshBattleField(gameField);	
			step = referee.checkStep(gameField);
			switch (step){
			case 0: System.out.println("Has next step!"); break;
			case -1: System.out.println("It's a draw!"); break;
			case -99: System.out.println("Replace this sucker and try again!"); break; 
			default: System.out.println("Player"+step+" is the winner!");  break;
			}
		} while (step == 0);
	}

}

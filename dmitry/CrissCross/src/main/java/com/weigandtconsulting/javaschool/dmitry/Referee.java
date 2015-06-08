package com.weigandtconsulting.javaschool.dmitry;
import java.util.*;

public class Referee {
	private List<CellState> gameFieldSnapshot;
	private final Winner winner = new Winner();
	private List<TicTacToe> players = new ArrayList<TicTacToe>();
	public int playerTurn = Math.random() < 0.5 ? 1 : 2;
	
	Referee(List<CellState> gameField){
		gameFieldSnapshot = new ArrayList<CellState>(gameField);
	}	
	
	public void addPlayer(TicTacToe player){
		players.add(player);
	}
	
	private boolean checkSuckers(List<CellState> gameField){
		int count = 0;
		for (int i=0; i<gameField.size(); i++){
			if (gameField.get(i)!=gameFieldSnapshot.get(i)){
				if (gameFieldSnapshot.get(i)!=CellState.TOE)
					return true;
				else{
					count++;
					gameFieldSnapshot.set(i, gameField.get(i));
				}
			}				 
		}
		return count>1;
	}
	
	public List<CellState> nextPlayerStep(List<CellState> gameField){
		playerTurn = playerTurn%players.size()+1;
		if (playerTurn==2)
			return players.get(playerTurn-1).nextStep(gameField);
		else
			return gameField;
	}
	
	public int checkStep(List<CellState> gameField){
		//if suckers
		if (checkSuckers(gameField))
			return -99;
		//if winners
		CellState win;
		win = winner.getWinner(gameField);
		if (win!=CellState.TOE)
			return playerTurn;	
		//if draw 
		if (players.get(playerTurn-1).hasNextStep(gameField)) 
			return 0;
		else 
			return -1;
	}
	
}

package CrissCross;
import java.util.List;

public class CrissCrossPlayer implements TicTacToe{
	CellState player;
	String playerName;
	private final Winner winner = new Winner();

	CrissCrossPlayer(CellState player, String playerName){
		this.player = player;
		this.playerName = playerName;
	}	
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	//player can win next turn
	private int canWin (List<CellState> gameField, CellState player){
		for (int i=0; i<gameField.size(); i++){
			if (gameField.get(i)==CellState.TOE){
				gameField.set(i, player);
				if (winner.getWinner(gameField) == player){
					gameField.set(i, CellState.TOE);
					return i;
				} 
				gameField.set(i, CellState.TOE);
			}			
		}
		return -1;
	}

	//next move
	public List<CellState> nextStep(List<CellState> gameField){
		int length = gameField.size();
		int winCell;
		CellState opPlayer = CellState.TOE;
		
		//get opponent player value
		for (int i=0; i<length; i++){
			if (gameField.get(i)!=CellState.TOE && gameField.get(i)!=player){
				opPlayer = gameField.get(i); break;
			}				
		}
		
		//player can win next turn
		if ((winCell=canWin(gameField, player)) != -1){
			gameField.set(winCell, player);
			return gameField;
		}
		
		//opponent can win next turn
		if (opPlayer!=CellState.TOE){
			if ((winCell=canWin(gameField, opPlayer)) != -1){
				gameField.set(winCell, player);
				return gameField;
			}
		}
		
		//opponent can win next turn after next turn
		if (opPlayer!=CellState.TOE){
			for (int i=0; i<length; i++){
				if (gameField.get(i)==CellState.TOE){
					gameField.set(i, opPlayer);				
					if ((winCell=canWin(gameField, opPlayer)) != -1){
						gameField.set(i, CellState.TOE);
						gameField.set((Math.random() < 0.5) ? i : winCell, player);
						return gameField;
					}
					gameField.set(i, CellState.TOE);
				}			
			}
		}			
		
		//player can win next turn after next turn
		for (int i=0; i<length; i++){
			if (gameField.get(i)==CellState.TOE){
				gameField.set(i, player);
				if ((winCell=canWin(gameField, player)) != -1){
					gameField.set(i, CellState.TOE);
					gameField.set((Math.random() < 0.5) ? i : winCell, player);
					return gameField;
				}
				gameField.set(i, CellState.TOE);
			}
		}
		
		//center
		if (gameField.get(length/2)==CellState.TOE){
			gameField.set(length/2, player);
			return gameField;
		}		
		
		//can be commented at 3x3 begin - moves nearby if nothing interesting above
		/*for (int i=0; i<length; i++){
			if (gameField[i].equals(player)){
				if ((i+1)<length && gameField[i+1].equals(0)){
					gameField[i+1] = player;
					return gameField;
				}
				if ((i-1)>=0 && gameField[i-1].equals(0)){
					gameField[i-1] = player;
					return gameField;
				}
				if ((i+divisor)<length && gameField[i+divisor].equals(0)){
					gameField[i+divisor] = player;
					return gameField;
				}
				if ((i-divisor)>=0 && gameField[i-divisor].equals(0)){
					gameField[i-divisor] = player;
					return gameField;
				}
				if ((i+divisor+1)<length && gameField[i+divisor+1].equals(0)){
					gameField[i+divisor+1] = player;
					return gameField;
				}
				if ((i+divisor-1)<length && gameField[i+divisor-1].equals(0)){
					gameField[i+divisor-1] = player;
					return gameField;
				}					
				if ((i-divisor-1)>=0 && gameField[i-divisor-1].equals(0)){
					gameField[i-divisor-1] = player;
					return gameField;
				}
				if ((i-divisor+1)>=0 && gameField[i-divisor+1].equals(0)){
					gameField[i-divisor+1] = player;
					return gameField;
				}					
			}	
		} */
		// can be commented end		
		
		//first available move
		for (int i=0; i<length; i++)
			if (gameField.get(i)==CellState.TOE){ 
				gameField.set(i, player);
				return gameField;
			}
		
		return gameField;
	}
	
	public boolean hasNextStep(List<CellState> gameField){
		for (int i=0; i<gameField.size(); i++)
				if (gameField.get(i)==CellState.TOE)
					return true; //has next step
		return false; //draw
	}
	
}

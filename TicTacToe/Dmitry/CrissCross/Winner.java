package CrissCross;
import java.util.List;

public class Winner implements Winnerable{	
	
	//win at horizontal from position i
	private CellState winHorizontal(List<CellState> gameField, int i){
		CellState current = gameField.get(i);
		int divisor = (int) Math.round(Math.sqrt(gameField.size()));
		if ((divisor - i%divisor)>2 && gameField.get(i+1)==current && gameField.get(i+2)==current)
			return current;
		return CellState.TOE;
	}
	
	//win at vertical from position i
	private CellState winVertical(List<CellState> gameField, int i){
		CellState current = gameField.get(i);
		int length = gameField.size();
		int divisor = (int) Math.round(Math.sqrt(length));
		if ((i+2*divisor)<length && gameField.get(i+divisor)==current && gameField.get(i+2*divisor)==current)
			return current;
		return CellState.TOE;
	}

	//win at diagonal1 from position i
	private CellState winDiag1(List<CellState> gameField, int i){
		CellState current = gameField.get(i);
		int length = gameField.size();
		int divisor = (int) Math.round(Math.sqrt(length));
		if ((i+2*divisor+2)<length && (i+2*divisor+2)<=((i - i%divisor) + 3*divisor - 1) && 
				gameField.get(i+divisor+1)==current && gameField.get(i+2*divisor+2)==current)
			return current;
		return CellState.TOE;
	}
	
	//win at diagonal2 from position i
	private CellState winDiag2(List<CellState> gameField, int i){
		CellState current = gameField.get(i);
		int length = gameField.size();
		int divisor = (int) Math.round(Math.sqrt(length));
		if ((i+2*divisor-2)<length && (i+2*divisor-2)>= ((i - i%divisor) + 2*divisor) &&
				gameField.get(i+divisor-1)==current && gameField.get(i+2*divisor-2)==current)
			return current;
		return CellState.TOE;
	}
	
	public CellState getWinner(List<CellState> gameField){
		CellState win;
		for (int i=0; i<gameField.size(); i++){
			if (gameField.get(i)!=CellState.TOE){
				if ((win=winHorizontal(gameField, i)) !=CellState.TOE) return win;
				if ((win=winVertical(gameField, i)) !=CellState.TOE) return win; 
				if ((win=winDiag1(gameField, i)) !=CellState.TOE) return win; 
				if ((win=winDiag2(gameField, i)) !=CellState.TOE) return win; 
			}	
		}	
		return CellState.TOE;
	}
}

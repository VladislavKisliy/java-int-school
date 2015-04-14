package CrissCross;
import java.util.*;

public interface TicTacToe {
		List<CellState> nextStep(List<CellState> gameField);
		boolean hasNextStep(List<CellState> gameField);
		String getPlayerName();
}

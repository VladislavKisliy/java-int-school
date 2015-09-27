package com.weigandtconsulting.javaschool.dmitry;
import java.util.List;

public interface Winnerable {
	public CellState getWinner(List<CellState> gameField);
}

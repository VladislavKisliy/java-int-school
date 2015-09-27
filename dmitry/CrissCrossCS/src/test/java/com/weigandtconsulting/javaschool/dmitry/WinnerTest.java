package com.weigandtconsulting.javaschool.dmitry;

import static com.weigandtconsulting.javaschool.dmitry.CellState.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class WinnerTest extends TestCase {
	final List<CellState> gameFieldWinH =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
			  														  			  TOE,TOE,TAC,
			  																	  TIC,TIC,TIC));			
	final List<CellState> gameFieldWinV =  new ArrayList<CellState>(Arrays.asList(TIC,TAC,TOE,
			  																	  TOE,TAC,TOE,
			  																	  TIC,TAC,TAC));
	final List<CellState> gameFieldWinD =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
			  																	  TOE,TAC,TOE,
			  																	  TIC,TIC,TAC));
	
	public void testGetWinner() {
		Winner w = new Winner();
		assertEquals("Winner.getWin win - horizontal:", TIC, w.getWinner(gameFieldWinH));
		assertEquals("Winner.getWin win - vertical:", TAC, w.getWinner(gameFieldWinV));
		assertEquals("Winner.getWin win - diagonal:", TAC, w.getWinner(gameFieldWinD));
	}

}

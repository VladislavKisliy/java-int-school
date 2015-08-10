package com.weigandtconsulting.javaschool.dmitry;

import static com.weigandtconsulting.javaschool.dmitry.CellState.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class RefereeTest extends TestCase {

	final List<CellState> gameFieldInit =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
				 																  TAC,TOE,TIC,
				 																  TOE,TOE,TOE));

	final List<CellState> gameFieldSuckers =  new ArrayList<CellState>(Arrays.asList(TAC,TAC,TAC,
  			  																		 TAC,TOE,TIC,
  			  																		 TOE,TOE,TOE));
	final List<CellState> gameFieldNextStep =  new ArrayList<CellState>(Arrays.asList(TAC,TAC,TOE,
				 																	  TAC,TOE,TIC,
				 																	  TOE,TOE,TOE));
	
	public void testCheckStep() {
		Referee refs = new Referee(gameFieldInit);
		Referee refn = new Referee(gameFieldInit);		
		refn.playerTurn = 1;
		assertEquals("Referee.checkStep(for suckers):", -99, refs.checkStep(gameFieldSuckers));
		//assertEquals("Referee.checkStep(for next step):", 0, refn.checkStep(gameFieldNextStep));
		
	}

}

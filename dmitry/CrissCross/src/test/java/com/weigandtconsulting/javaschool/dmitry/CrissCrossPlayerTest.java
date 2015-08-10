package com.weigandtconsulting.javaschool.dmitry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import static com.weigandtconsulting.javaschool.dmitry.CellState.*;

public class CrissCrossPlayerTest extends TestCase {
	final List<CellState> gameFieldFull = new ArrayList<CellState>(Arrays.asList(TIC,TIC,TIC,
																		   		 TIC,TIC,TIC,
																		   		 TIC,TIC,TIC));
	final List<CellState> gameFieldToe =  new ArrayList<CellState>(Arrays.asList(TIC,TIC,TIC,
			   													 		   		 TIC,TOE,TIC,
			   															   		 TIC,TIC,TIC));
	final List<CellState> gameFieldWinH =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
				   																  TOE,TOE,TOE,
		   																		  TIC,TIC,TOE));
	final List<CellState> gameFieldWinHR = new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
				 																  TOE,TOE,TOE,
				 																  TIC,TIC,TIC));
	final List<CellState> gameFieldWinV =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
				 																  TOE,TIC,TAC,
				 																  TOE,TIC,TOE));
	final List<CellState> gameFieldWinVR = new ArrayList<CellState>(Arrays.asList(TAC,TIC,TOE,
			  																	  TOE,TIC,TAC,
			  																	  TOE,TIC,TOE));
	final List<CellState> gameFieldWinD =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TIC,
			 																 	  TOE,TIC,TAC,
			 																	  TOE,TOE,TOE));
	final List<CellState> gameFieldWinDR = new ArrayList<CellState>(Arrays.asList(TAC,TOE,TIC,
				  																  TOE,TIC,TAC,
				  																  TIC,TOE,TOE));
	final List<CellState> gameFieldNotWinH =  new ArrayList<CellState>(Arrays.asList(TAC,TAC,TOE,
			 																	     TOE,TIC,TAC,
			 																	     TOE,TOE,TOE));
	final List<CellState> gameFieldNotWinHR = new ArrayList<CellState>(Arrays.asList(TAC,TAC,TIC,
				  																     TOE,TIC,TAC,
				  																     TOE,TOE,TOE));
	final List<CellState> gameFieldNotWinV =  new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
		     																		 TAC,TIC,TOE,
		     																		 TOE,TOE,TOE));
	final List<CellState> gameFieldNotWinVR = new ArrayList<CellState>(Arrays.asList(TAC,TOE,TOE,
			     																	 TAC,TIC,TOE,
			     																	 TIC,TOE,TOE));
	final List<CellState> gameFieldNotWinD =  new ArrayList<CellState>(Arrays.asList(TAC,TIC,TOE,
																				     TOE,TAC,TOE,
																				     TOE,TOE,TOE));
	final List<CellState> gameFieldNotWinDR = new ArrayList<CellState>(Arrays.asList(TAC,TIC,TOE,
		     																		 TOE,TAC,TOE,
		     																		 TOE,TOE,TIC));


	public void testHasNextStep() {
		CrissCrossPlayer testPlayer = new CrissCrossPlayer(CellState.TIC, "MegaTester");
		assertFalse("CrissCrossPlayer.hasNextStep test for false:", testPlayer.hasNextStep(gameFieldFull));
		assertTrue("CrissCrossPlayer.hasNextStep test for true:" , testPlayer.hasNextStep(gameFieldToe));		
	}

	public void testNextStep() {
		CrissCrossPlayer testPlayer = new CrissCrossPlayer(CellState.TIC, "MegaTester");
		assertEquals("CrissCrossPlayer.nextStep should win - horizontal:", gameFieldWinHR, testPlayer.nextStep(gameFieldWinH));
		assertEquals("CrissCrossPlayer.nextStep should win - vertical:", gameFieldWinVR, testPlayer.nextStep(gameFieldWinV));
		assertEquals("CrissCrossPlayer.nextStep should win - diagonal:", gameFieldWinDR, testPlayer.nextStep(gameFieldWinD));
		assertEquals("CrissCrossPlayer.nextStep opponent shouldn't win - horizontal:", gameFieldNotWinHR, testPlayer.nextStep(gameFieldNotWinH));
		assertEquals("CrissCrossPlayer.nextStep opponent shouldn't win - vertical:", gameFieldNotWinVR, testPlayer.nextStep(gameFieldNotWinV));
		assertEquals("CrissCrossPlayer.nextStep opponent shouldn't win - diagonal:", gameFieldNotWinDR, testPlayer.nextStep(gameFieldNotWinD));
	}
	
}

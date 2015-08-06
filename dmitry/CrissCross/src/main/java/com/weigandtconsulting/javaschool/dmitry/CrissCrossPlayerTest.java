package com.weigandtconsulting.javaschool.dmitry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class CrissCrossPlayerTest extends TestCase {


    public void testHasNextStep() {
        CrissCrossPlayer testPlayer = new CrissCrossPlayer(CellState.TIC, "MegaTester");
        List<CellState> gameFieldFalse = new ArrayList<CellState>(Arrays.asList(CellState.TIC,CellState.TIC,CellState.TIC,
                                                                   CellState.TIC,CellState.TIC,CellState.TIC,
                                                                  CellState.TIC,CellState.TIC,CellState.TIC));
        List<CellState> gameFieldTrue = new ArrayList<CellState>(Arrays.asList(CellState.TIC,CellState.TIC,CellState.TIC,
                                                                 CellState.TIC,CellState.TOE,CellState.TIC,
                                                                 CellState.TIC,CellState.TIC,CellState.TIC));
        assertEquals("CrissCrossPlayer.hasNextStep test for false:", false, testPlayer.hasNextStep(gameFieldFalse));
        assertEquals("CrissCrossPlayer.hasNextStep test for true:", true, testPlayer.hasNextStep(gameFieldTrue));
    }

}
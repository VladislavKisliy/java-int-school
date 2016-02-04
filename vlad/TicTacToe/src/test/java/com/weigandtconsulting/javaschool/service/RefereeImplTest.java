/*
 * Copyright (C) 2015 Weigandt Consulting
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.weigandtconsulting.javaschool.service;

import com.weigandtconsulting.javaschool.beans.CellState;
import static com.weigandtconsulting.javaschool.beans.CellState.TAC;
import static com.weigandtconsulting.javaschool.beans.CellState.TIC;
import static com.weigandtconsulting.javaschool.beans.CellState.TOE;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author vlad
 */
public class RefereeImplTest {
    
    private final CellState[] oneEmptySpace = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TAC};
    
    private final CellState[] twoEmptySpace = {
            TIC, TAC, TAC,
            TAC, TIC, TIC,
            TAC, TOE, TOE};
    
    public RefereeImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
}

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
package com.weigandtconsulting.javaschool.beans;

/**
 *
 * @author vlad
 */
public class Game {

    public enum State {

        START, CONTINUE, OVER
    }

    public enum Result {

        UKNOWN, WIN, DRAW
    }
    
    
    private State state = State.START;
    private Result result = Result.UKNOWN;
    private CellState winnerSign = CellState.TOE;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public CellState getWinnerSign() {
        return winnerSign;
    }

    public void setWinnerSign(CellState winnerSign) {
        this.winnerSign = winnerSign;
    }

    @Override
    public String toString() {
        return "Game{" + "state=" + state + ", result=" + result + ", winnerSign=" + winnerSign + '}';
    }
}

package com.weigandtconsulting.javaschool.ilya;

/**
 * Created by ibeketov on 26/03/15.
 */
public enum GameSigns {
    EMPTY(" "),
    CROSS("X"),
    ZERO("O");

    public String playerSymbol;

    GameSigns(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }
}

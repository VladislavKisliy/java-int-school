package com.weigandtconsulting.javaschool.dmitry;
import java.util.*;
public class CrissCrossShow implements Showable{

	private void printDivLine(int divisor){
		for (int i=0; i<=divisor*2; i++)
			System.out.print("-");
		System.out.println();
	}
	
	public void refreshBattleField(List<CellState> battleField){
		int divisor = (int) Math.round(Math.sqrt(battleField.size()));
		printDivLine(divisor);
		for (int j=0; j<battleField.size()/divisor; j++){
			System.out.print("|");
			for (int i=j*divisor; i<divisor*j+divisor; i++){
				System.out.print(battleField.get(i).toString() + "|");
			}
			System.out.println();
			printDivLine(divisor);
		}
	}

}

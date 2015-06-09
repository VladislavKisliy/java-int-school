/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weigandtconsulting.javaschool.escender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author W
 */
public class ShowableImpl implements Showable {

    private Integer widthField;
    private Integer heightFiled;
    private String rowSpace;
    private Map<Integer, String> mapValue;

    public ShowableImpl(Integer widthField, Integer heightFiled) {
        this.widthField = widthField;
        this.heightFiled = heightFiled;
        mapValue = new HashMap<Integer, String>();
        rowSpace = "-";
        for (int i = 0; i < widthField * 2; i++) {
            rowSpace = rowSpace + "-";
        }
    }

    public ShowableImpl(Integer rowLength) {
        this.widthField = rowLength;
        this.heightFiled = rowLength;
        mapValue = new HashMap<Integer, String>();
        rowSpace = "-";
        for (int i = 0; i < rowLength * 2; i++) {
            rowSpace = rowSpace + "-";
        }
    }

    public void SetMapValue(Integer inValue, String outValue) {
        mapValue.put(inValue, outValue);
    }

    public String GetMapValue(Integer inValue) {
        String res = mapValue.get(inValue);
        if (res == null) {
            return String.valueOf(inValue);
        } else {
            return res;
        }
    }

    @Override
    public void refreshBattleField(List<GameState> battleField) {
        System.out.println(rowSpace);
        for (int i = 0; i < heightFiled; i++) {
            String rowText = "|";
            for (int j = 0; j < widthField; j++) {
                rowText = rowText + battleField.get(i * widthField + j) + "|";
            }

            System.out.println(rowText);
            System.out.println(rowSpace);
        }
    }
}

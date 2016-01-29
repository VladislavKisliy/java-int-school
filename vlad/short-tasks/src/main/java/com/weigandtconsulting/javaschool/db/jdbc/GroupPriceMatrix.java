/*
 * Copyright (C) 2016 Weigandt Consulting
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
package com.weigandtconsulting.javaschool.db.jdbc;

/**
 *
 * @author vlad
 */
public class GroupPriceMatrix {

    private static final int MAX_INDEX = 9;
    private long modelId;
    private int modelDetailId;
    private String c01;
    private String c02;
    private String c03;
    private String c04;
    private String c05;
    private String c06;
    private String c07;
    private String c08;
    private String c09;
    private String c10;

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public int getModelDetailId() {
        return modelDetailId;
    }

    public void setModelDetailId(int modelDetailId) {
        this.modelDetailId = modelDetailId;
    }

    public String getC01() {
        return c01;
    }

    public void setC01(String c01) {
        this.c01 = c01;
    }

    public String getC02() {
        return c02;
    }

    public void setC02(String c02) {
        this.c02 = c02;
    }

    public String getC03() {
        return c03;
    }

    public void setC03(String c03) {
        this.c03 = c03;
    }

    public String getC04() {
        return c04;
    }

    public void setC04(String c04) {
        this.c04 = c04;
    }

    public String getC05() {
        return c05;
    }

    public void setC05(String c05) {
        this.c05 = c05;
    }

    public String getC06() {
        return c06;
    }

    public void setC06(String c06) {
        this.c06 = c06;
    }

    public String getC07() {
        return c07;
    }

    public void setC07(String c07) {
        this.c07 = c07;
    }

    public String getC08() {
        return c08;
    }

    public void setC08(String c08) {
        this.c08 = c08;
    }

    public String getC09() {
        return c09;
    }

    public void setC09(String c09) {
        this.c09 = c09;
    }

    public String getC10() {
        return c10;
    }

    public void setC10(String c10) {
        this.c10 = c10;
    }

    public String getCForArray(int arrayIndex) {
        String result = null;
        if (arrayIndex > MAX_INDEX) {
            throw new IllegalArgumentException();
        } else {
            switch (arrayIndex) {
                case 0:
                    result = c01;
                    break;
                case 1:
                    result = c02;
                    break;
                case 2:
                    result = c03;
                    break;
                case 3:
                    result = c04;
                    break;
                case 4:
                    result = c05;
                    break;
                case 5:
                    result = c06;
                    break;
                case 6:
                    result = c07;
                    break;
                case 7:
                    result = c08;
                    break;
                case 8:
                    result = c09;
                    break;
                case 9:
                    result = c10;
                    break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "GroupPriceMatrix{" + "modelId=" + modelId + ", modelDetailId=" + modelDetailId + ", c01=" + c01 + ", c02=" + c02 + ", c03=" + c03 + ", c04=" + c04 + ", c05=" + c05 + ", c06=" + c06 + ", c07=" + c07 + ", c08=" + c08 + ", c09=" + c09 + ", c10=" + c10 + '}';
    }
}

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

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vlad
 */
public class StartApp {

    private final static int MODEL_ID = 5;

    public static void main(String[] args) throws SQLException {
        DbUtils dbUtils = new DbUtils();

        List<GpmModelDefinition> definitions = dbUtils.getDefinitions(MODEL_ID);
        final String[] columnNames = new String[definitions.size()];
        if (definitions.size() > 0) {
            List<GroupPriceMatrix> gpxs = dbUtils.getGroupPriceMatrix(MODEL_ID);
            System.out.println("definitions size =" + definitions.size());
            System.out.println("gpxs size =" + gpxs.size());
            final Object[][] data = new Object[gpxs.size()][definitions.size()];
            int column = 0;

//            rowData[row][col] = value;
            for (GpmModelDefinition definition : definitions) {
                int row = 0;
                columnNames[column] = definition.getLabel();
                for (GroupPriceMatrix gpx : gpxs) {
                    System.out.println("column =" + column + ", row=" + row + " , def =" + gpx);
                    data[row][column] = gpx.getCForArray(column);
                    row++;
                }
                column++;
            }
            //Schedule a job for the event-dispatching thread:
            //creating and showing this application's GUI.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    TableView.createAndShowGUI(columnNames, data);
                }
            });
        }
    }
}

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
package com.weigandtconsulting.javaschool.table.nested;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HeaderTableExample extends JFrame {

    public HeaderTableExample() {
        super("this is a title");
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setDataVector(
                new Object[][]{},
                new String[]{
                    "Klient", "Numer dokumentu", "E2", "Jedn.", "EUR", "H1", "E2", "Jedn.", "EUR", "H1"
                });

        JTable jTable1 = new javax.swing.JTable(tableModel) {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        TableColumnModel cm = jTable1.getColumnModel();
        ColumnGroup documentGroup = new ColumnGroup("Dokumenty");
        ColumnGroup sendedGroup = new ColumnGroup("Wysłane");
        ColumnGroup returnedGroup = new ColumnGroup("Zwracane");

        documentGroup.add(cm.getColumn(0));
        documentGroup.add(cm.getColumn(1));

        sendedGroup.add(cm.getColumn(2));
        sendedGroup.add(cm.getColumn(3));
        sendedGroup.add(cm.getColumn(4));
        sendedGroup.add(cm.getColumn(5));

        returnedGroup.add(cm.getColumn(6));
        returnedGroup.add(cm.getColumn(7));
        returnedGroup.add(cm.getColumn(8));
        returnedGroup.add(cm.getColumn(9));

        GroupableTableHeader header = (GroupableTableHeader) jTable1.getTableHeader();
        header.addColumnGroup(documentGroup);
        header.addColumnGroup(sendedGroup);
        header.addColumnGroup(returnedGroup);

        JScrollPane scroll = new JScrollPane(jTable1);
        getContentPane().add(scroll);
        setSize(400, 120);
        scroll.setViewportView(jTable1);
    }

    public static void main(String[] args) {
        HeaderTableExample frame = new HeaderTableExample();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}

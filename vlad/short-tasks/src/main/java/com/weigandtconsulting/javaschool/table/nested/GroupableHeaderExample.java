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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @version 1.0 11/09/98
 */
public class GroupableHeaderExample extends JFrame {

    GroupableHeaderExample() {
        super("Groupable Header Example");

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{
            {"119", "foo", "bar", "ja", "ko", "zh"},
            {"911", "bar", "foo", "en", "fr", "pt"}},
                new Object[]{"SNo.", "1", "2", "Native", "2", "3"});

        JTable table = new JTable(dm) {
            @Override
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        TableColumnModel cm = table.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("Name");
        g_name.add(cm.getColumn(1));
        g_name.add(cm.getColumn(2));
        ColumnGroup g_lang = new ColumnGroup("Language");
        g_lang.add(cm.getColumn(3));
        ColumnGroup g_other = new ColumnGroup("Others");
        g_other.add(cm.getColumn(4));
        g_other.add(cm.getColumn(5));
        g_lang.add(g_other);
        GroupableTableHeader header = (GroupableTableHeader) table.getTableHeader();
        header.addColumnGroup(g_name);
        header.addColumnGroup(g_lang);
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll);
        setSize(400, 120);
    }

    public static void main(String[] args) {
        GroupableHeaderExample frame = new GroupableHeaderExample();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}

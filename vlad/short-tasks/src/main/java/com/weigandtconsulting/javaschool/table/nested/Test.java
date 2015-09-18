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

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class Test {

    public static void main(String[] args) {
        JTable table = new JTable(new CustomTableModel());
        table.setDefaultRenderer(Person.class, new CustomRenderer());
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(table));
        JFrame frame = new JFrame();
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

@SuppressWarnings("serial")
class CustomTableModel extends AbstractTableModel {

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 0) {
            return new Person("Bob");
        } else {
            return "is awesome!";
        }
    }
}

@SuppressWarnings("serial")
class CustomRenderer extends JPanel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int col) {
        JTable t = new JTable(new CustomTableModel());
        add(new JScrollPane(t));
        return this;
    }
}

class Person {

    public String name = "";

    public Person(String name) {
        this.name = name;
    }
}

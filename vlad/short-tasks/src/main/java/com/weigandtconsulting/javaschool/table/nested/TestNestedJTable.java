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
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
 
import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
 
 
public class TestNestedJTable extends JFrame{
    private static final long serialVersionUID = -722808327091366767L;
     
    private JTable mainTable;
    private JTable leftTable;
    private JTable rightTable;
    private Object[][] tableList;
    private Container container;
     
    private String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
    private Object[][] data = {
            {"Mary", "Campione",
            "Snowboarding", new Integer(5), new Boolean(false)},
            {"Alison", "Huml",
            "Rowing", new Integer(3), new Boolean(true)},
            {"Kathy", "Walrath",
            "Knitting", new Integer(2), new Boolean(false)},
            {"Sharon", "Zakhour",
            "Speed reading", new Integer(20), new Boolean(true)},
            {"Philip", "Milne",
            "Pool", new Integer(10), new Boolean(false)},
    };
    private String[] columnNames2 = {"",
            "Last Name",
            "Group",
            "Year"};
    private Object[][] data2 = {
            {false, "Li",
            "Tiger", new Integer(1997), },
            {false, "Roy",
            "Lion", new Integer(1996)},
            {false, "Lee",
            "Dragon", new Integer(1989)},
            {false, "Jones",
            "Lion", new Integer(1981)},
            {false, "Lam",
            "Dragon", new Integer(1980)},
    };
     
    private String[] names = {"sdf", "sdf", "dfs"};
     
    public TestNestedJTable(){
        container = this.getContentPane();
         
        leftTable = new JTable(data, columnNames);
        rightTable = new JTable(data2, columnNames2);
        leftTable.getTableHeader().setVisible(true);
        rightTable.getTableHeader().setVisible(true);
         
        tableList = new Object[1][];
        tableList[0] = new Object[3];
        tableList[0][0] = leftTable;
        tableList[0][1] = rightTable;
        tableList[0][2] = "dfgfdg";
         
        mainTable = new JTable(new DefaultTableModel(tableList, names));
         
        TableColumn tc = mainTable.getColumnModel().getColumn(0);
        tc.setCellRenderer(new CustomTableCellRenderer(leftTable));
        tc.setCellEditor(new CustomTableCellEditor(leftTable));
        tc = mainTable.getColumnModel().getColumn(1);
        tc.setCellRenderer(new CustomTableCellRenderer(rightTable));
        tc.setCellEditor(new CustomTableCellEditor(rightTable));
        mainTable.setRowHeight(leftTable.getPreferredSize().height+leftTable.getTableHeader().getPreferredSize().height+4);
 
        mainTable.addMouseMotionListener(new MyMouseMotionListener());
         
        // Enable the ability to select a single cell 
        mainTable.setColumnSelectionAllowed(true); 
        mainTable.setRowSelectionAllowed(true); 
         
        container.add(new JScrollPane(mainTable));
         
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.pack();
        setSize(500,500);
        setVisible(true);
    }
     
     
    class CustomTableCellRenderer extends DefaultTableCellRenderer {
        private static final long serialVersionUID = 4415155875184525824L;
        JTable table;
         
        CustomTableCellRenderer(JTable table){
            this.table=table;
            this.table.setOpaque(true);
            this.table.setAlignmentY(JTable.LEFT_ALIGNMENT);
        }
         
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            this.table=(JTable)value;
            return new JScrollPane(this.table); 
        }
    }
     
    class CustomTableCellEditor extends AbstractCellEditor implements TableCellEditor{
 
        JTable table;
         
        CustomTableCellEditor(JTable table){
            this.table=table;
            this.table.setOpaque(true);
            this.table.setAlignmentY(JTable.LEFT_ALIGNMENT);
        }
         
        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            this.table=(JTable)value;
            return new JScrollPane(this.table);
        }
 
        @Override
        public Object getCellEditorValue() {
            return this.table;
        }
         
    }
     
    class MyMouseMotionListener implements MouseMotionListener{
         
        @Override
        public void mouseDragged(MouseEvent e) {
        }
 
        @Override
        public void mouseMoved(MouseEvent e) {
            if(e.getComponent() instanceof JTable){
                JTable tempTable = (JTable)e.getComponent();
                 
                TableColumnModel columnModel = tempTable.getColumnModel();
                int viewColumn = columnModel.getColumnIndexAtX(e.getX());
                int column = columnModel.getColumn(viewColumn).getModelIndex();
                int row = tempTable.rowAtPoint(e.getPoint());
                 
                // change the if-statement to the columns you want to programmatically 
                // enter edit mode of, when the mouse is over it.
                if(column==0 || column==1){
                    // Set the cell on the row and column in edit mode 
                    boolean success = tempTable.editCellAt(row, column);
                    if (success) {
                      boolean toggle = false;
                      boolean extend = false;
                      // Select cell 
                      tempTable.changeSelection(row, column, toggle, extend);
                    }
                }
            }
        }
    }
     
    /**
     * @param args
     */
    public static void main(String[] args) {
        new TestNestedJTable();
    }
}

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
          
import javax.swing.AbstractCellEditor;  
import javax.swing.JCheckBox;
import javax.swing.JFrame;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.table.DefaultTableCellRenderer;      
import javax.swing.table.DefaultTableModel;  
import javax.swing.table.TableCellEditor;  
import javax.swing.table.TableColumn;  
import javax.swing.table.TableColumnModel;
      
public class NestedJTableHeader extends JFrame {  
          
        private JTable mainTable;  
        private JTable innerTable;  
        private Object[][] tableList;         
          
        private String[] columnNames = {"A", "B", "C", "D", "E"};  
        private Object[][] data = {  
                {"1", "2", "3", "4", new Boolean(false)},  
                {"5", "6", "7", "8", new Boolean(true)},  
                {"9", "10", "11", "12", new Boolean(false)},  
                {"13", "14", "15", "16", new Boolean(true)},  
                {"17", "18", "19", "20", new Boolean(false)}  
        };          
          
        private String[] names = {"Type", "Source", "Target", "Default"};
          
        public NestedJTableHeader(){             
              
            innerTable = new JTable(new DefaultTableModel (data, columnNames){
                    public Class<?> getColumnClass(int columnIndex) {
                         if(columnIndex==4)
                              return Boolean.class;
                         else 
                              return super.getColumnClass(columnIndex);
                    }
               });                
              
            tableList = new Object[1][];  
            tableList[0] = new Object[1];  
            tableList[0][0] = innerTable;  
              
            mainTable = new JTable(new DefaultTableModel(tableList, names));  
              
            TableColumn tc = mainTable.getColumnModel().getColumn(0);  
            tc.setCellRenderer(new CustomTableCellRenderer(innerTable));  
            tc.setCellEditor(new CustomTableCellEditor(innerTable));  
            tc = mainTable.getColumnModel().getColumn(1);  
            mainTable.setRowHeight(innerTable.getPreferredSize().height+innerTable.getTableHeader().getPreferredSize().height+4);  
      
            // Enable the ability to select a single cell   
            mainTable.setColumnSelectionAllowed(true);   
            mainTable.setRowSelectionAllowed(true);   
              
            this.getContentPane().add(new JScrollPane(mainTable));  
              
            this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );  
            this.pack();
            setSize(600,450);
            setVisible(true);  
        }  
          
          
        class CustomTableCellRenderer extends DefaultTableCellRenderer {  
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
          
        /** 
         * @param args 
         */  
        public static void main(String[] args) {  
            new NestedJTableHeader();  
        }  
} 

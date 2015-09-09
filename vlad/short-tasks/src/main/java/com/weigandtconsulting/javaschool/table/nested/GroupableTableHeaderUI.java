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

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.plaf.basic.*;
  
public class GroupableTableHeaderUI extends BasicTableHeaderUI {
   
  public void paint(Graphics g, JComponent c) {
    Rectangle clipBounds = g.getClipBounds();
    if (header.getColumnModel() == null) return;
    ((GroupableTableHeader)header).setColumnMargin();
    int column = 0;
    Dimension size = header.getSize();
    Rectangle cellRect  = new Rectangle(0, 0, size.width, size.height);
    Hashtable h = new Hashtable();
    int columnMargin = header.getColumnModel().getColumnMargin();
     
    Enumeration enumeration = header.getColumnModel().getColumns();
    while (enumeration.hasMoreElements()) {
      cellRect.height = size.height;
      cellRect.y      = 0;
      TableColumn aColumn = (TableColumn)enumeration.nextElement();
      Enumeration cGroups = ((GroupableTableHeader)header).getColumnGroups(aColumn);
      if (cGroups != null) {
        int groupHeight = 0;
        while (cGroups.hasMoreElements()) {
          ColumnGroup cGroup = (ColumnGroup)cGroups.nextElement();
          Rectangle groupRect = (Rectangle)h.get(cGroup);
          if (groupRect == null) {
            groupRect = new Rectangle(cellRect);
            Dimension d = cGroup.getSize(header.getTable());
            groupRect.width  = d.width;
            groupRect.height = d.height;   
            h.put(cGroup, groupRect);
          }
          paintCell(g, groupRect, cGroup);
          groupHeight += groupRect.height;
          cellRect.height = size.height - groupHeight;
          cellRect.y      = groupHeight;
        }
      }     
      cellRect.width = aColumn.getWidth() + columnMargin;
      if (cellRect.intersects(clipBounds)) {
        paintCell(g, cellRect, column);
      }
      cellRect.x += cellRect.width;
      column++;
    }
  }
  
  private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
    TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
    TableCellRenderer renderer = aColumn.getHeaderRenderer();
    Component component = renderer.getTableCellRendererComponent(
      header.getTable(), aColumn.getHeaderValue(),false, false, -1, columnIndex);
    rendererPane.add(component);
    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
                                cellRect.width, cellRect.height, true);
  }
  
  private void paintCell(Graphics g, Rectangle cellRect,ColumnGroup cGroup) {
    TableCellRenderer renderer = cGroup.getHeaderRenderer();
    Component component = renderer.getTableCellRendererComponent(
      header.getTable(), cGroup.getHeaderValue(),false, false, -1, -1);
    rendererPane.add(component);
    rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y,
                                cellRect.width, cellRect.height, true);
  }
  
  private int getHeaderHeight() {
    int height = 0;
    TableColumnModel columnModel = header.getColumnModel();
    for(int column = 0; column < columnModel.getColumnCount(); column++) {
      TableColumn aColumn = columnModel.getColumn(column);
      TableCellRenderer renderer = aColumn.getHeaderRenderer();
      Component comp = renderer.getTableCellRendererComponent(
        header.getTable(), aColumn.getHeaderValue(), false, false,-1, column);
      int cHeight = comp.getPreferredSize().height;
      Enumeration enumeration = ((GroupableTableHeader)header).getColumnGroups(aColumn);     
      if (enumeration != null) {
        while (enumeration.hasMoreElements()) {
          ColumnGroup cGroup = (ColumnGroup)enumeration.nextElement();
          cHeight += cGroup.getSize(header.getTable()).height;
        }
      }
      height = Math.max(height, cHeight);
    }
    return height;
  }
  
  private Dimension createHeaderSize(long width) {
    TableColumnModel columnModel = header.getColumnModel();
    width += columnModel.getColumnMargin() * columnModel.getColumnCount();
    if (width > Integer.MAX_VALUE) {
      width = Integer.MAX_VALUE;
    }
    return new Dimension((int)width, getHeaderHeight());
  }
  
  public Dimension getPreferredSize(JComponent c) {
    long width = 0;
    Enumeration enumeration = header.getColumnModel().getColumns();
    while (enumeration.hasMoreElements()) {
      TableColumn aColumn = (TableColumn)enumeration.nextElement();
      width = width + aColumn.getPreferredWidth();
    }
    return createHeaderSize(width);
  }
}
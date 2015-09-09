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

/**
 * ColumnGroup
 *
 * @version 1.0 10/20/98
 * @author Nobuo Tamemasa
 */
public class ColumnGroup {

    protected TableCellRenderer renderer;
    protected Vector v;
    protected String text;
    protected int margin = 0;

    public ColumnGroup(String text) {
        this(null, text);
    }

    public ColumnGroup(TableCellRenderer renderer, String text) {
        if (renderer == null) {
            this.renderer = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    JTableHeader header = table.getTableHeader();
                    if (header != null) {
                        setForeground(header.getForeground());
                        setBackground(header.getBackground());
                        setFont(header.getFont());
                    }
                    setHorizontalAlignment(JLabel.CENTER);
                    setText((value == null) ? "" : value.toString());
                    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                    return this;
                }
            };
        } else {
            this.renderer = renderer;
        }
        this.text = text;
        v = new Vector();
    }

    /**
     * @param obj TableColumn or ColumnGroup
     */
    public void add(Object obj) {
        if (obj == null) {
            return;
        }
        v.addElement(obj);
    }

    /**
     * @param c TableColumn
     * @param v ColumnGroups
     */
    public Vector getColumnGroups(TableColumn c, Vector g) {
        g.addElement(this);
        if (v.contains(c)) {
            return g;
        }
        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof ColumnGroup) {
                Vector groups
                        = (Vector) ((ColumnGroup) obj).getColumnGroups(c, (Vector) g.clone());
                if (groups != null) {
                    return groups;
                }
            }
        }
        return null;
    }

    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    public Object getHeaderValue() {
        return text;
    }

    public Dimension getSize(JTable table) {
        Component comp = renderer.getTableCellRendererComponent(
                table, getHeaderValue(), false, false, -1, -1);
        int height = comp.getPreferredSize().height;
        int width = 0;
        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof TableColumn) {
                TableColumn aColumn = (TableColumn) obj;
                width += aColumn.getWidth();
                width += margin;
            } else {
                width += ((ColumnGroup) obj).getSize(table).width;
            }
        }
        return new Dimension(width, height);
    }

    public void setColumnMargin(int margin) {
        this.margin = margin;
        Enumeration enumeration = v.elements();
        while (enumeration.hasMoreElements()) {
            Object obj = enumeration.nextElement();
            if (obj instanceof ColumnGroup) {
                ((ColumnGroup) obj).setColumnMargin(margin);
            }
        }
    }
}

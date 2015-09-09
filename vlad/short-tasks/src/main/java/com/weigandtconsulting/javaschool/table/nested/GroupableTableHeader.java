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
 * GroupableTableHeader
 *
 * @version 1.0 10/20/98
 * @author Nobuo Tamemasa
 */
public class GroupableTableHeader extends JTableHeader {

    private static final String uiClassID = "GroupableTableHeaderUI";
    protected Vector columnGroups = null;

    public GroupableTableHeader(TableColumnModel model) {
        super(model);
        setUI(new GroupableTableHeaderUI());
        setReorderingAllowed(false);
    }

    public void setReorderingAllowed(boolean b) {
        reorderingAllowed = false;
    }

    public void addColumnGroup(ColumnGroup g) {
        if (columnGroups == null) {
            columnGroups = new Vector();
        }
        columnGroups.addElement(g);
    }

    public Enumeration getColumnGroups(TableColumn col) {
        if (columnGroups == null) {
            return null;
        }
        Enumeration enumeration = columnGroups.elements();
        while (enumeration.hasMoreElements()) {
            ColumnGroup cGroup = (ColumnGroup) enumeration.nextElement();
            Vector v_ret = (Vector) cGroup.getColumnGroups(col, new Vector());
            if (v_ret != null) {
                return v_ret.elements();
            }
        }
        return null;
    }

    public void setColumnMargin() {
        if (columnGroups == null) {
            return;
        }
        int columnMargin = getColumnModel().getColumnMargin();
        Enumeration enumeration = columnGroups.elements();
        while (enumeration.hasMoreElements()) {
            ColumnGroup cGroup = (ColumnGroup) enumeration.nextElement();
            cGroup.setColumnMargin(columnMargin);
        }
    }
}

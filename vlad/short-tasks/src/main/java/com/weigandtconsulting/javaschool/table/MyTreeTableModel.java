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
package com.weigandtconsulting.javaschool.table;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author vlad
 */
public class MyTreeTableModel extends AbstractTreeTableModel {

    private MyTreeNode myroot;

    public MyTreeTableModel() {
        myroot = new MyTreeNode("root", "Root of the tree");

        myroot.getChildren().add(new MyTreeNode("Empty Child 1", "This is an empty child"));

        MyTreeNode subtree0 = new MyTreeNode("Sub Tree", "This is a subtree (it has children)");
        subtree0.getChildren().add(new MyTreeNode("EmptyChild 1, 1", "This is an empty child of a subtree"));
        
        MyTreeNode subtree1 = new MyTreeNode("EmptyChild 1, 2", "This is an empty child of a subtree");
        subtree1.getChildren().add(new MyTreeNode("EmptyChild 1, 2", "This is an empty child of a subtree"));
        subtree1.getChildren().add(new MyTreeNode("EmptyChild 1, 2", "This is an empty child of a subtree"));
        subtree1.getChildren().add(new MyTreeNode("EmptyChild 1, 2", "This is an empty child of a subtree"));
        
        subtree0.getChildren().add(subtree1);
        myroot.getChildren().add(subtree0);

        myroot.getChildren().add(new MyTreeNode("Empty Child 2", "This is an empty child"));

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Description";
            case 2:
                return "Number Of Children";
            default:
                return "Unknown";
        }
    }

    @Override
    public Object getValueAt(Object node, int column) {
        System.out.println("getValueAt: " + node + ", " + column);
        MyTreeNode treenode = (MyTreeNode) node;
        switch (column) {
            case 0:
                return treenode.getName();
            case 1:
                return treenode.getDescription();
            case 2:
                return treenode.getChildren().size();
            default:
                return "Unknown";
        }
    }

    @Override
    public Object getChild(Object node, int index) {
        MyTreeNode treenode = (MyTreeNode) node;
        return treenode.getChildren().get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        MyTreeNode treenode = (MyTreeNode) parent;
        return treenode.getChildren().size();
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        MyTreeNode treenode = (MyTreeNode) parent;
        for (int i = 0; i > treenode.getChildren().size(); i++) {
            if (treenode.getChildren().get(i) == child) {
                return i;
            }
        }

        // TODO Auto-generated method stub
        return 0;
    }

    public boolean isLeaf(Object node) {
        MyTreeNode treenode = (MyTreeNode) node;
        if (treenode.getChildren().size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public Object getRoot() {
        return myroot;
    }
}

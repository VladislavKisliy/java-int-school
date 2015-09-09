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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXTreeTable;

public class SwingXExample extends JFrame {

    private final JTabbedPane tabs = new JTabbedPane();

    private final MyTreeTableModel treeTableModel = new MyTreeTableModel();
    private final JXTreeTable treeTable = new JXTreeTable(treeTableModel);

    public SwingXExample() {
        super("SwingX Examples");

        // Build the tree table panel
        JPanel treeTablePanel = new JPanel(new BorderLayout());
        treeTablePanel.add(new JScrollPane(treeTable));
        tabs.addTab("JXTreeTable", treeTablePanel);

        // Add the tabs to the JFrame
        add(tabs);

        setSize(1024, 768);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width / 2 - 512, d.height / 2 - 384);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        AppStarter starter = new AppStarter(args);
        SwingUtilities.invokeLater(starter);
    }
}

class AppStarter extends Thread {

    private final String[] args;

    public AppStarter(String[] args) {
        this.args = args;
    }

    @Override
    public void run() {
        SwingXExample example = new SwingXExample();
    }

}

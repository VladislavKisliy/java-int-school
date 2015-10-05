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
package com.weigandtconsulting.javaschool.db;

import java.sql.*;

/**
 *
 * @author vlad
 */
public class SimpleJDBC {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t";
        String userName = "kws";
        String password = "drwkws5t";
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            System.out.println("Database connection: Successful");
        } catch (Exception e) {
            System.out.println("Database connection: Failed");
            e.printStackTrace();
        }
    }
}

/*
 * Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.weigandtconsulting.javaschool.db.jdbc;

import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.sql.DataSource;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import org.apache.commons.dbcp2.BasicDataSource;

public class DbUtils {

    private static final Logger LOG = Logger.getLogger(DbUtils.class.getName());

    private final String url = "jdbc:oracle:thin:@konzum2.weigandt-consulting.com:1521:mom5t";
    private final String userName = "kws";
    private final String password = "drwkws5t";
    private DataSource dataSource;

    public DbUtils() {
        setupDataSource(url);
    }

    public List<GpmModelDefinition> getDefinitions(int modelId) throws SQLException {
        List<GpmModelDefinition> result = new ArrayList<>();
        JdbcRowSet jdbcRs = new JdbcRowSetImpl(dataSource.getConnection());
        jdbcRs.setCommand("select ID,MODEL_ID,COLUMN_NAME,LABEL,DESCRIPTION "
                + "from GPM_MODEL_DEFINITIONS "
                + "where MODEL_ID = ? order by 1");
        jdbcRs.setInt(1, modelId);
        jdbcRs.execute();
        jdbcRs.beforeFirst();
        while (jdbcRs.next()) {
            result.add(processGpmModel(jdbcRs));
        }
        return result;
    }

    public List<GroupPriceMatrix> getGroupPriceMatrix(int modelId) throws SQLException {
        List<GroupPriceMatrix> result = new ArrayList<>();
        JdbcRowSet jdbcRs = new JdbcRowSetImpl(dataSource.getConnection());
        jdbcRs.setCommand("select MODEL_ID, MODEL_DETAIL_ID, "
                + "C01, C02, C03, C04, C05, C06, C07, C08, C09, C10 "
                + "from VIEW_GROUP_PRICE_MATRIX "
                + "where MODEL_ID = ?");
        jdbcRs.setInt(1, modelId);
        jdbcRs.execute();
        jdbcRs.beforeFirst();
        while (jdbcRs.next()) {
            result.add(processGPX(jdbcRs));
        }
        return result;
    }

    private GpmModelDefinition processGpmModel(RowSet rs) throws SQLException {
        return new GpmModelDefinition(rs.getLong(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
    }

    private GroupPriceMatrix processGPX(RowSet rs) throws SQLException {
        GroupPriceMatrix result = new GroupPriceMatrix();
        result.setModelId(rs.getLong(1));
        result.setModelDetailId(rs.getInt(2));
        result.setC01(rs.getString(3));
        result.setC02(rs.getString(4));
        result.setC03(rs.getString(5));
        result.setC04(rs.getString(6));
        result.setC05(rs.getString(7));
        result.setC06(rs.getString(8));
        result.setC07(rs.getString(9));
        result.setC08(rs.getString(10));
        result.setC09(rs.getString(11));
        result.setC10(rs.getString(12));
        return result;
    }

    private void setupDataSource(String connectURI) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl(connectURI);
        ds.setUsername(userName);
        ds.setPassword(password);
        dataSource = ds;
    }
}

package com.xwintop.tool.h2db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDao {
    public static void crateTable() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            DatabaseMetaData meta = conn.getMetaData();

            ResultSet rsTables = meta.getTables(null, null, "WEATHERINFO",
                new String[] { "TABLE" });
            if (!rsTables.next()) {
                stmt = conn.createStatement();
                stmt.execute("CREATE TABLE WEATHERINFO(WEATHERSTR VARCHAR(1024),LASTMODIFYTIME VARCHAR(1024),STATUS VARCHAR(1024),PRIMARY KEY(WEATHERSTR,LASTMODIFYTIME))");
            }
            rsTables.close();
        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    public static void addInfo(String str, long lastModifyTime,
                               String status) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();

            stmt = conn
                .prepareStatement("INSERT INTO WEATHERINFO VALUES(?,?,?)");
            stmt.setString(1, str);
            stmt.setString(2, String.valueOf(lastModifyTime));
            stmt.setString(3, status);
            stmt.execute();

        } finally {
            releaseConnection(conn, stmt, null);
        }
    }

    public static boolean isInfoExits(String filePath, long lastModifyTime)
        throws SQLException
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            stmt = conn
                .prepareStatement("SELECT WEATHERSTR FROM WEATHERINFO WHERE STATUS=? AND LASTMODIFYTIME=?");
            stmt.setString(1, filePath);
            stmt.setString(2, String.valueOf(lastModifyTime));
            rs = stmt.executeQuery();
            return rs.next();
        } finally {
            releaseConnection(conn, stmt, rs);
        }
    }

    private static void releaseConnection(Connection conn, Statement stmt,
                                          ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}

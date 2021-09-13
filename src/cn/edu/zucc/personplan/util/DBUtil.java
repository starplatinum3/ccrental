package cn.edu.zucc.personplan.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {


    private static final String jdbcUrl = "jdbc:mysql://localhost:3307/cc?useSSL=false&serverTimezone=GMT";
    private static final String dbUser = "root";
    private static final String dbPwd = "123";

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection conn) {
        if (conn == null) return;
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws java.sql.SQLException {
        return DBPool.getInstance().getConnection();
//		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
    }
}

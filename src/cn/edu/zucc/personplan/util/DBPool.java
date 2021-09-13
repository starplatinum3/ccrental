package cn.edu.zucc.personplan.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
	private static DBPool dbPool;
	private ComboPooledDataSource dataSource;

	static {
		dbPool = new DBPool();
	}

	public DBPool() {
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("123");
//			dataSource
//					.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/tops?autoReconnect=true&useUnicode=true&characterEncoding=utf-8");
			
//			dataSource
//			.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/plan?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT");

			dataSource
					.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/cc?autoReconnect=true&useUnicode=true" +
							"&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT");
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setInitialPoolSize(2);
			dataSource.setMinPoolSize(1);
			dataSource.setMaxPoolSize(10);
			dataSource.setMaxStatements(50);
			dataSource.setMaxIdleTime(60);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
	}

	public final static DBPool getInstance() {
		return dbPool;
	}

	public final Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("无法从数据源获取连接 ", e);
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			System.out.println("开始连接");
			con = DBPool.getInstance().getConnection();
			System.out.println("获得链接");
//			java.sql.ResultSet rs=con.createStatement().executeQuery("select id from tbl_logger_visit");
			java.sql.ResultSet rs=con.createStatement().executeQuery("select plan_id from tbl_plan");
			System.out.println("抓东西完毕");
			while(rs.next())
				System.out.println(rs.getString(1));
			System.out.println("输出完毕");
		} catch (Exception e) {
		} finally {
			if (con != null)
				con.close();
		}
	}
}
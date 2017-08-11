//package com.xwintop.xcore;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.apache.tomcat.jdbc.pool.PoolProperties;
//import org.junit.Test;
//
//public class TomcatJdbcTest {
//	@Test
//	public void testJdbc() throws SQLException {
//		PoolProperties p = new PoolProperties();
////		p.setUrl("jdbc:mysql://localhost:3306/mysql");
//		p.setUrl("jdbc:mysql://192.168.66.205:3306/hzecc2017?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
//		p.setDriverClassName("com.mysql.jdbc.Driver");
//		p.setUsername("admin");
//		p.setPassword("Kingdee@0021");
//		p.setJmxEnabled(true);
//		p.setTestWhileIdle(false);
//		p.setTestOnBorrow(true);
//		p.setValidationQuery("SELECT 1");
//		p.setTestOnReturn(false);
//		p.setValidationInterval(30000);
//		p.setTimeBetweenEvictionRunsMillis(30000);
//		p.setMaxActive(100);
//		p.setInitialSize(10);
//		p.setMaxWait(10000);
//		p.setRemoveAbandonedTimeout(60);
//		p.setMinEvictableIdleTimeMillis(30000);
//		p.setMinIdle(10);
//		p.setLogAbandoned(true);
//		p.setRemoveAbandoned(true);
//		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
//				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
//		DataSource datasource = new DataSource();
//		datasource.setPoolProperties(p);
//		Connection con = null;
//		try {
//			con = datasource.getConnection();
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery("select * from t_sa_user");
//			int cnt = 1;
//			while (rs.next()) {
//				System.out.println((cnt++) + ". Host:" + rs.getString("fid") + " User:" + rs.getString("fuserId")
//						+ " Password:" + rs.getString("fuserName"));
//			}
//			rs.close();
//			st.close();
//		} finally {
//			if (con != null)
//				try {
//					con.close();
//				} catch (Exception ignore) {
//				}
//		}
//	}
//}

package com.fbb.jjz.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author fengbb
 *
 */
public class DBHelper {
    
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	static final String USER = "root";
	static final String PASS = "a123456";
	static final String DBNAME = "bjjj_jjz";
	static final String url ="jdbc:mysql://localhost:3306/"+DBNAME+"?useSSL=false";
	
	static Connection conn = null;
	Statement stmt = null;

	public static Connection getConnection(String dbName) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName+"?useSSL=false", USER , PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnection() {
		return getConnection(DBNAME);
	}
	

	public static Statement createStmt(Connection conn){
		Statement statement = null;
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}
	
	public static PreparedStatement prepareStmt(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public static ResultSet executeQuery(Statement stmt, String sql){
		ResultSet resultSet = null;
		try {
			resultSet = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static int executeUpdate(Connection conn, String sql) {
		int ret = 0;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ret = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ret;
	}
	
	public static boolean executeSql(Connection conn, String sql) {
		boolean ret = false;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ret = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return ret;
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
	
	public static void main(String[] args) throws IOException {
//        System.out.println("缓冲池模拟开始"); 
//        new PoolThreadTest().start();
//        PoolThreadTest[] threads = new PoolThreadTest[1];  
//        for(int i=0;i<threads.length;i++){  
//            threads[i] = new PoolThreadTest();  
//        }  
//        for(int i=0;i<threads.length;i++){  
//            threads[i].start();  
//        }  

    }
}

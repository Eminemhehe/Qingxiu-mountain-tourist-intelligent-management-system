/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author wz
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static Connection con ;
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try{
			readConfig();
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
		}catch(Exception ex){
			throw new RuntimeException("数据库连接失败");
		}
	}
	
	private static void readConfig(){
		try {
			InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("database.properties");
			 Properties pro = new Properties();
			 pro.load(in);
			 driverClass=pro.getProperty("driverClass");
			 url = pro.getProperty("url");
			 username = pro.getProperty("username");
			 password = pro.getProperty("password");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection(){
		return con;
	}
	
	
	
	public static void release(Connection conn , Statement st , ResultSet rs){
		closeRs(rs);
		closePt(st);
		closeConn(conn);
	}

	public static void release(Connection conn , Statement st){
		closePt(st);
		closeConn(conn);
	}

	
	private static void closeRs(ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			rs = null;
		}
	}
	

	private static void closePt(Statement st ){
		try {
			if(st != null){
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			st = null;
		}
	}
	private static void closeConn(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}
	
}



package com.mobile.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mobile.db.DButil;


public class IDButil implements DButil {
	Connection con=null;
	Statement st=null;
	ResultSet rt=null;
	
	public void getConn() {
		
		try {
			Class.forName(DB_DRIVER);
			con=DriverManager.getConnection(DB_URL,USER_NO,USER_PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ResultSet exeQuery(String sql) {
		if(con!=null){
			try {
				st=con.createStatement();
				return st.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
		}else{
			return null;
		}
		
		
	}

	public int exeUpdate(String sql) {
		
		if(con!=null){
			try {
				st=con.createStatement();
				return st.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
			
		}else{
			return 0;
		}
	}

	public void close() {
		if(rt!=null){
			try {
				rt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

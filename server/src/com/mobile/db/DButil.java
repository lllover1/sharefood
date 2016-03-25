package com.mobile.db;

import java.sql.ResultSet;

public interface DButil {

 public	static final String DB_DRIVER = "com.mysql.jdbc.Driver";
 
 public static final String DB_URL="jdbc:mysql://localhost:3306/moblie_oa?useUnicode=true&characterEncoding=utf-8";
 
 public static final String USER_NO="root";
 
 public static final String USER_PASS="llw854597236";
 
 public void getConn() ;
 public ResultSet exeQuery(String sql);
 public int exeUpdate(String sql);
 public void close();
}

	
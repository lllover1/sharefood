package com.mobile.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;

import com.mobile.dao.UserDao;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.User;

public class ImplUserDao extends IDButil implements UserDao {

	@Override
	public User getUserByName(String nickname) {
		this.getConn();
		String sql= "SELECT * FROM user_identity WHERE nickname = '" +nickname+"'";
		ResultSet rs = this.exeQuery(sql);
		User user = null;
		try {
			while(rs.next()){
				int uId =rs.getInt("u_id");
				String uName=rs.getString("u_name");
				String uDiscription=rs.getString("u_discrption");
				String uNickname= rs.getString("nickname");
				int uSex =rs.getInt("u_sex");
				Date uDate = rs.getDate("u_birthday");
				String uPassword=rs.getString("u_password");
				int uHeadImage=rs.getInt("head_image");
				user = new User(uId, uName, uDiscription, uNickname, uSex, uDate, uHeadImage, uPassword);
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return user;
		}

	@Override
	public int insertUser(User user) {
		this.getConn();
		String sql= " INSERT INTO user_identity (u_name,nickname,u_sex,u_birthday,head_image,u_password) VALUES ('"+user.getuName()+"','"+user.getuNickname()+"',"+user.getuSex()+",'"+user.getuDate()+"','"+user.getuHeadImage()+"','"+user.getuPassword()+"')";
		int result =this.exeUpdate(sql);
		return result;
	}
	
	public User getUserById(int id) {
		this.getConn();
		String sql= "SELECT * FROM user_identity WHERE u_id = "+id;
		ResultSet rs = this.exeQuery(sql);
		User user = null;
		try {
			while(rs.next()){
				int uId =rs.getInt("u_id");
				String uName=rs.getString("u_name");
				String uDiscription=rs.getString("u_discrption");
				String uNickname= rs.getString("nickname");
				int uSex =rs.getInt("u_sex");
				Date uDate = rs.getDate("u_birthday");
				String uPassword=rs.getString("u_password");
				int uHeadImage=rs.getInt("head_image");
				user = new User(uId, uName, uDiscription, uNickname, uSex, uDate, uHeadImage, uPassword);
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return user;
		}

	}


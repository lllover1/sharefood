package com.mobile.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mobile.dao.CritismDao;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.Critism;
import com.moblie.bean.Health;

public class ImplCritismDao  extends IDButil implements CritismDao{

	@Override
	public List<Critism> getCritism(int id) {
		

			this.getConn();
			String sql= "SELECT a.`u_name` username,b.`c_discription` cc FROM user_identity a,critism b WHERE a.`u_id` = b.`c_user_id` AND b.`m_id` ="+id;
			ResultSet rs = this.exeQuery(sql);
			List<Critism> lists = new ArrayList<Critism>();
			Critism c;
			try {
				while(rs.next()){
					c = new Critism();
					c.setuName(rs.getString("username"));
					c.setuDiscrption(rs.getString("cc"));
					lists.add(c);
					
				}
			} catch (Exception e) {
				System.out.println("设置数据库 获取数据异常"+e);
			}
			return lists;
		}
	}

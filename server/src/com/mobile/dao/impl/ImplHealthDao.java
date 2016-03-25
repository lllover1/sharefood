package com.mobile.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mobile.dao.HealthDao;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.Health;
import com.moblie.bean.User;

public class ImplHealthDao extends IDButil implements HealthDao {

	@Override
	public List<Health> getFirstPage() {
		List<Health> lists = new ArrayList<Health>();
		this.getConn();
		String sql= "SELECT h_id,h_name,h_image FROM health ";
		ResultSet rs = this.exeQuery(sql);
		Health h;
		try {
			while(rs.next()){
				h = new Health();
				h.sethId(rs.getInt("h_id"));
				h.sethName(rs.getString("h_name"));
				h.sethImage(rs.getString("h_image"));
				lists.add(h);
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return lists;
	}

	@Override
	public Health getHeath(int id) {

		this.getConn();
		String sql= "SELECT h_name,h_doing,h_help FROM health WHERE h_id = "+id;
		ResultSet rs = this.exeQuery(sql);
		Health h = null;
		try {
			while(rs.next()){
				h = new Health();
				h.sethName(rs.getString("h_name"));
				h.sethDoing(rs.getString("h_doing"));
				h.setHelp(rs.getString("h_help"));
			}
		} catch (Exception e) {
			System.out.println("设置数据库 获取数据异常"+e);
		}
		return h;
	}

}

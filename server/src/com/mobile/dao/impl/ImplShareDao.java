package com.mobile.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mobile.dao.ShareDao;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.MenuCard;
import com.moblie.bean.ShareCollection;

public class ImplShareDao extends IDButil implements ShareDao {

	@Override
	public List<ShareCollection> getall() {
		// TODO Auto-generated method stub
		this.getConn();
		String sql= "SELECT * FROM  collection ORDER BY c_id DESC LIMIT 5" ;
		ResultSet rs = this.exeQuery(sql);
		List<ShareCollection> lists = new ArrayList<ShareCollection>();
		try {
			ShareCollection sc;
			while(rs.next()){
				sc = new ShareCollection();
				sc.setcId(rs.getInt("c_id"));
				sc.setmId(rs.getInt("c_menu_id"));
				sc.setuId(rs.getInt("u_id"));
				lists.add(sc);
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return lists;
		}
	}


package com.mobile.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mobile.dao.DoingDao;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.Doing;
import com.moblie.bean.MenuCard;

public class ImplDoingDao extends IDButil implements DoingDao {
	
	private List<Doing> lists;
	@Override
	public List<Doing> getDoingList(int mid) {
		this.getConn();
		String sql= "SELECT * FROM doing WHERE m_id = " +mid+" ORDER BY d_name";
		ResultSet rs = this.exeQuery(sql);
	    lists = new ArrayList<Doing>();
		try {
			Doing doing;
			while(rs.next()){
				doing = new Doing();
				doing.setdId(rs.getInt("d_id"));
				doing.setmId(rs.getInt("m_id"));
				doing.setdName(rs.getInt("d_name"));
			    doing.setdDiscription(rs.getString("d_discription"));
			    doing.setdImageUrl(rs.getString("d_imageurl"));
			    lists.add(doing);
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return lists;
	}

}

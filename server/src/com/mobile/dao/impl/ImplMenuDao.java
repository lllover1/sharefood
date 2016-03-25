package com.mobile.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mobile.dao.MenuDao;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.MenuCard;

public class ImplMenuDao extends IDButil implements MenuDao {

	@Override
	public List<MenuCard> getListMenu() {
		this.getConn();
		String sql= "select * from  menu_card order by m_id desc limit 5" ;
		ResultSet rs = this.exeQuery(sql);
		List<MenuCard> lists = new ArrayList<MenuCard>();
		try {
			MenuCard menu;
			while(rs.next()){
				menu = new MenuCard();
				menu.setmId(rs.getInt("m_id"));
				menu.setmName(rs.getString("m_name"));
				menu.setmDiscription(rs.getString("m_discription"));
				menu.setmImageUrl(rs.getString("m_image_url"));
				lists.add(menu);
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return lists;
		}
	public MenuCard getOne(int id) {
		this.getConn();
		String sql= "SELECT * FROM  menu_card WHERE m_id = "+id ;
		ResultSet rs = this.exeQuery(sql);
		MenuCard menu =null;
		try {
			while(rs.next()){
				menu = new MenuCard();
				menu.setmId(rs.getInt("m_id"));
				menu.setmName(rs.getString("m_name"));
				menu.setmDiscription(rs.getString("m_discription"));
				menu.setmImageUrl(rs.getString("m_image_url"));
				
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return menu;
		}
	}


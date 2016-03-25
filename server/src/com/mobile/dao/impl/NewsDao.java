package com.mobile.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mobile.dao.INews;
import com.mobile.db.impl.IDButil;
import com.moblie.bean.News;
import com.moblie.bean.User;


public class NewsDao extends IDButil  implements INews{

	
//	insert into news(title,imgurl,content,remark) values('t1','http://www.baiidu.com','asdasdas','remark');
//	insert into news(title,imgurl,content,remark) values('t1','http://www.baiidu.com','asdasdas','remark');

	@Override
	public List<News> getLastNews() {
		this.getConn();
		String sql= "select * from  news order by nid desc limit 5" ;
		ResultSet rs = this.exeQuery(sql);
		List<News> lists = new ArrayList<News>();
		
		try {
			int nid=0;
			String  title = "";
			String  imgurl = "";
			String  content = "";
			String  remark = "";
			while(rs.next()){
				nid=rs.getInt("nid");
				title=rs.getString("title");
				imgurl=rs.getString("imgurl");
				content=rs.getString("content");
				remark=rs.getString("remark");
				lists.add(new News(nid, title, imgurl, content, remark));
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return lists;
	}

	@Override
	public String getOne() {
		this.getConn();
		Random r = new Random();
		int i = r.nextInt(10);
		String sql= "select content from light where l_id ="+i;
		ResultSet rs = this.exeQuery(sql);
		String s =null;
		try {
			while(rs.next()){
				s = rs.getString("content");
			}
		} catch (Exception e) {
			System.out.println("数据库 获取数据异常"+e);
		}
		
		return s;
		}
	
	}


package com.mobile.dao;

import java.util.List;

import com.moblie.bean.News;


public interface INews {

	// 返回数据库 后边 十条数据
	public List<News> getLastNews();
	
	public String getOne();
	
}

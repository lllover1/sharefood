package com.mobile.dao;

import java.util.List;

import com.moblie.bean.News;


public interface INews {

	// �������ݿ� ��� ʮ������
	public List<News> getLastNews();
	
	public String getOne();
	
}

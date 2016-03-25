package com.mobile.dao;

import java.util.List;

import com.moblie.bean.Health;

public interface HealthDao {
	public List<Health> getFirstPage();
	public Health getHeath(int id);
	
}

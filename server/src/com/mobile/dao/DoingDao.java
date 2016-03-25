package com.mobile.dao;

import java.util.List;

import com.moblie.bean.Doing;

public interface DoingDao {
	public List<Doing> getDoingList(int mid);
}

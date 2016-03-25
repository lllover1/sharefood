package com.mobile.dao;

import com.moblie.bean.User;

public interface UserDao {
	public User getUserByName(String nickname);
	public int insertUser(User user);
}

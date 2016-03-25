package com.mobile.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.mobile.dao.impl.ImplUserDao;
import com.mobile.jsonTools.JSONTools;
import com.moblie.bean.User;

public class UserAction {
	private String newsJson;
	//获得向android传递数据流的对象 ，记得需要  get set 方法
	private HttpServletResponse response;
	private HttpServletRequest request;
	private User user;
	
	public void getUser(){
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		
		ImplUserDao nd = new ImplUserDao();
		
		String nickname = request.getParameter("username");
		String password = request.getParameter("password");
		
		user = nd.getUserByName(nickname);
		
		if(password.equals(user.getuPassword())){
			newsJson = JSONTools.getJSONString(user);
		}else{
			newsJson = null;
		}
		//
		System.out.println(newsJson);
		try {
			if(response==null){
				System.out.println("应答对象为空");
				return;
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(newsJson);
			
		} catch (IOException e) {
			System.out.println("向android端写入数据异常");
			e.printStackTrace();
		}
		
	}
	
	public void insertOne(){
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		
		ImplUserDao nd = new ImplUserDao();
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
		String temp = request.getParameter("user");
		String date = request.getParameter("date");
		
		user = JSONTools.getObjectFromJson(temp, User.class);
		user.setuDate(Date.valueOf(date));
		System.out.println(user);
		int result = nd.insertUser(user);
		newsJson = JSONTools.getJSONString(result);
		
		//
		System.out.println(newsJson);
		try {
			if(response==null){
				System.out.println("应答对象为空");
				return;
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(newsJson);
			
		} catch (IOException e) {
			System.out.println("向android端写入数据异常");
			e.printStackTrace();
		}
	}
}

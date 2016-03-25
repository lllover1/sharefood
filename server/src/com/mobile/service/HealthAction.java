package com.mobile.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mobile.dao.impl.ImplHealthDao;
import com.mobile.dao.impl.ImplMenuDao;
import com.mobile.jsonTools.JSONTools;

public class HealthAction {
	private String newsJson;
	//获得向android传递数据流的对象 ，记得需要  get set 方法
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void getLast(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
	
		ImplHealthDao hd = new ImplHealthDao();
		newsJson = JSONTools.getJSONString(hd.getFirstPage());
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
		//return "success";
	}
	
	public void getContent(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		ImplHealthDao hd = new ImplHealthDao();
		newsJson = JSONTools.getJSONString(hd.getHeath(id));
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

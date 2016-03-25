package com.mobile.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mobile.dao.impl.NewsDao;
import com.mobile.jsonTools.JSONTools;


public class NewsAction {
	
	private String newsJson;
	//获得向android传递数据流的对象 ，记得需要  get set 方法
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void getLast(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		NewsDao nd = new NewsDao();
		newsJson = JSONTools.getJSONString(nd.getLastNews());
		//
	
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
	
	public void getone(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		
		NewsDao nd = new NewsDao();
		newsJson = nd.getOne();
		//
	
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

	

	public String getNewsJson() {
		return newsJson;
	}




	public void setNewsJson(String newsJson) {
		this.newsJson = newsJson;
	}




	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
}

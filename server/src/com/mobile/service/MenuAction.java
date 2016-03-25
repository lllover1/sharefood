package com.mobile.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mobile.dao.impl.ImplMenuDao;
import com.mobile.jsonTools.JSONTools;

public class MenuAction {
	
	private String newsJson;
	//�����android�����������Ķ��� ���ǵ���Ҫ  get set ����
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void getLast(){
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
	
		ImplMenuDao nd = new ImplMenuDao();
		newsJson = JSONTools.getJSONString(nd.getListMenu());
		//
	
		try {
			if(response==null){
				System.out.println("Ӧ�����Ϊ��");
				return;
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(newsJson);
			
		} catch (IOException e) {
			System.out.println("��android��д�������쳣");
			e.printStackTrace();
		}
		//return "success";
	}
}

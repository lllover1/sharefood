package com.mobile.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mobile.dao.impl.ImplDoingDao;
import com.mobile.dao.impl.ImplUserDao;
import com.mobile.jsonTools.JSONTools;
import com.moblie.bean.Doing;
import com.moblie.bean.User;

public class DoingAction {
	
	private String newsJson;
	//�����android�����������Ķ��� ���ǵ���Ҫ  get set ����
	private HttpServletResponse response;
	private HttpServletRequest request;
	private List<Doing> lists;
	
	public void getList(){
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		
		ImplDoingDao idd = new ImplDoingDao();
		lists = new ArrayList<Doing>();
		
		String mid = request.getParameter("mid");
		int id = Integer.parseInt(mid);
		
		lists = idd.getDoingList(id);
		newsJson = JSONTools.getJSONString(lists);
		
		//
		System.out.println(newsJson);
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
		
	}
}

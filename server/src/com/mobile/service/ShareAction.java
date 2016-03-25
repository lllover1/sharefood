package com.mobile.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mobile.dao.impl.ImplCritismDao;
import com.mobile.dao.impl.ImplMenuDao;
import com.mobile.dao.impl.ImplShareDao;
import com.mobile.dao.impl.ImplUserDao;
import com.mobile.jsonTools.JSONTools;
import com.moblie.bean.Critism;
import com.moblie.bean.MenuCard;
import com.moblie.bean.ShareCollection;
import com.moblie.bean.User;
import com.moblie.bean.shareBean;

public class ShareAction {
	
	private String newsJson;
	//�����android�����������Ķ��� ���ǵ���Ҫ  get set ����
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void getAll(){
		
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
	
		ImplShareDao isd = new ImplShareDao();
		List<ShareCollection> shareList = isd.getall();
		System.out.println("��������"+shareList);
		
		List<shareBean> lists = new ArrayList<shareBean>();
		shareBean sb ;
		for(int i=0;i<shareList.size();i++){
			ShareCollection s  = shareList.get(i);
			sb = new shareBean();
			
			MenuCard m = new ImplMenuDao().getOne(s.getmId());
			if(m!=null){
				sb.setContent(m.getmDiscription());
				sb.setImage(m.getmImageUrl());
				}
			
			User user = new ImplUserDao().getUserById(s.getcId());
			if(user!=null){
				sb.setNickname(user.getuNickname());
				sb.setDiscrption(user.getuDiscription());
				sb.setHead(user.getuHeadImage());
				}
			
			List<Critism> clist = new ImplCritismDao().getCritism(s.getmId());
			if(clist!=null){
				sb.setClist(clist);
			}
			lists.add(sb);
		}
		
		
		newsJson = JSONTools.getJSONString(lists);
		//
	    System.out.println("������"+newsJson);
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
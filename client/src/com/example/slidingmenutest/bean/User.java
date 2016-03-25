package com.example.slidingmenutest.bean;

import java.sql.Date;



public class User {
	
	private int uId;
	private String uName;
	private String uDiscription;
	private String uNickname;
	private int uSex;
	private Date uDate;
	private int uHeadImage;
	private String uPassword;
	
	
	public User(int uId, String uName, String uDiscription, String uNickname, int uSex, Date uDate, int uHeadImage,
			String uPassword) {
		super();
		this.uId = uId;
		this.uName = uName;
		this.uDiscription = uDiscription;
		this.uNickname = uNickname;
		this.uSex = uSex;
		this.uDate = uDate;
		this.uHeadImage = uHeadImage;
		this.uPassword = uPassword;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuDiscription() {
		return uDiscription;
	}
	public void setuDiscription(String uDiscription) {
		this.uDiscription = uDiscription;
	}
	public String getuNickname() {
		return uNickname;
	}
	public void setuNickname(String uNickname) {
		this.uNickname = uNickname;
	}
	public int getuSex() {
		return uSex;
	}
	public void setuSex(int uSex) {
		this.uSex = uSex;
	}
	public Date getuDate() {
		return uDate;
	}
	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}
	public int getuHeadImage() {
		return uHeadImage;
	}
	public void setuHeadImage(int uHeadImage) {
		this.uHeadImage = uHeadImage;
	}
	
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uName=" + uName + ", uDiscription=" + uDiscription + ", uNickname=" + uNickname
				+ ", uSex=" + uSex + ", uDate=" + uDate + ", uHeadImage=" + uHeadImage + "]";
	}
	
	
	
	
}

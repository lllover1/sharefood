package com.moblie.bean;

public class News {
	 private int  nid;
	 private String title;
	 private  String imgurl;
	 private  String content;
	 private  String remark;
	 
	 
	public News(int nid, String title, String imgurl, String content,
			String remark) {
		super();
		this.nid = nid;
		this.title = title;
		this.imgurl = imgurl;
		this.content = content;
		this.remark = remark;
	}


	public int getNid() {
		return nid;
	}


	public void setNid(int nid) {
		this.nid = nid;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getImgurl() {
		return imgurl;
	}


	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
	public String toString() {
		return "News [nid=" + nid + ", title=" + title + ", imgurl=" + imgurl
				+ ", content=" + content + ", remark=" + remark + "]";
	}
	 
	
	
	 
	
	
}

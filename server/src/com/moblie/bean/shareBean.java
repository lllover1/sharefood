package com.moblie.bean;


import java.util.List;

public class shareBean {
	private int head;
	private String nickname;
	private String discrption;
	private String image;
	private String content ;
	private List<Critism> clist;
	
	
	public List<Critism> getClist() {
		return clist;
	}
	public void setClist(List<Critism> clist) {
		this.clist = clist;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDiscrption() {
		return discrption;
	}
	public void setDiscrption(String discrption) {
		this.discrption = discrption;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public shareBean(int head, String nickname, String discrption, String image,
			String content) {
		super();
		this.head = head;
		this.nickname = nickname;
		this.discrption = discrption;
		this.image = image;
		this.content = content;
	}
	
	public shareBean(){
		
	}
	@Override
	public String toString() {
		return "shareBean [head=" + head + ", nickname=" + nickname
				+ ", discrption=" + discrption + ", image=" + image
				+ ", content=" + content + "]";
	}
	
	
	
}

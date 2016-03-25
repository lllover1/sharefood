package com.example.slidingmenutest.bean;


public class Health {
	private int hId;
	private String hName;
	private String hMaterial;
	private String hDoing;
	private String hImage;
	private String Help;
	public int gethId() {
		return hId;
	}
	public void sethId(int hId) {
		this.hId = hId;
	}
	public String gethName() {
		return hName;
	}
	public void sethName(String hName) {
		this.hName = hName;
	}
	public String gethMaterial() {
		return hMaterial;
	}
	public void sethMaterial(String hMaterial) {
		this.hMaterial = hMaterial;
	}
	public String gethDoing() {
		return hDoing;
	}
	public void sethDoing(String hDoing) {
		this.hDoing = hDoing;
	}
	public String gethImage() {
		return hImage;
	}
	public void sethImage(String hImage) {
		this.hImage = hImage;
	}
	public String getHelp() {
		return Help;
	}
	public void setHelp(String help) {
		Help = help;
	}
	public Health(int hId, String hName, String hMaterial, String hDoing, String hImage, String help) {
		super();
		this.hId = hId;
		this.hName = hName;
		this.hMaterial = hMaterial;
		this.hDoing = hDoing;
		this.hImage = hImage;
		Help = help;
	}
	
	public Health(){
		
	}
	@Override
	public String toString() {
		return "Health [hId=" + hId + ", hName=" + hName + ", hMaterial=" + hMaterial + ", hDoing=" + hDoing
				+ ", hImage=" + hImage + ", Help=" + Help + "]";
	}
	
}

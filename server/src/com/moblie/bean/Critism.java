package com.moblie.bean;


public class Critism {
	
	private String uName;
	private String uDiscrption;
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuDiscrption() {
		return uDiscrption;
	}
	public void setuDiscrption(String uDiscrption) {
		this.uDiscrption = uDiscrption;
	}
	@Override
	public String toString() {
		return "Critism [uName=" + uName + ", uDiscrption=" + uDiscrption + "]";
	}
	public Critism(String uName, String uDiscrption) {
		super();
		this.uName = uName;
		this.uDiscrption = uDiscrption;
	}
	
	public Critism(){
		
	}

}

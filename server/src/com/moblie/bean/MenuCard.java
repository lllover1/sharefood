package com.moblie.bean;

public class MenuCard {
	
	private int mId;
	private String mName;
	private String mDiscription;
	private String mImageUrl;
	
	
	public MenuCard(){
		
	}
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmDiscription() {
		return mDiscription;
	}
	public void setmDiscription(String mDiscription) {
		this.mDiscription = mDiscription;
	}
	public String getmImageUrl() {
		return mImageUrl;
	}
	public void setmImageUrl(String mImageUrl) {
		this.mImageUrl = mImageUrl;
	}

	@Override
	public String toString() {
		return "MenuCard [mId=" + mId + ", mName=" + mName + ", mDiscription=" + mDiscription + ", mImageUrl="
				+ mImageUrl + "]";
	}

	
	
	

}

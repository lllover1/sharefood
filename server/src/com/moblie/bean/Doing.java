package com.moblie.bean;

public class Doing {
	private int dId;
	private int mId;
	private int dName;
	private String  dDiscription;
	private String dImageUrl;
	
	public Doing(){
		
	}

	public Doing(int dId, int mId, int dName, String dDiscription, String dImageUrl) {
		super();
		this.dId = dId;
		this.mId = mId;
		this.dName = dName;
		this.dDiscription = dDiscription;
		this.dImageUrl = dImageUrl;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public int getdName() {
		return dName;
	}

	public void setdName(int dName) {
		this.dName = dName;
	}

	public String getdDiscription() {
		return dDiscription;
	}

	public void setdDiscription(String dDiscription) {
		this.dDiscription = dDiscription;
	}

	public String getdImageUrl() {
		return dImageUrl;
	}

	public void setdImageUrl(String dImageUrl) {
		this.dImageUrl = dImageUrl;
	}

	@Override
	public String toString() {
		return "Doing [dId=" + dId + ", mId=" + mId + ", dName=" + dName + ", dDiscription=" + dDiscription
				+ ", dImageUrl=" + dImageUrl + "]";
	}
	
	
}

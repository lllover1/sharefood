package com.example.slidingmenutest.bean;

public class ShareCollection {
	private int cId;
	private int uId;
	private int mId;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public ShareCollection(int cId, int uId, int mId) {
		super();
		this.cId = cId;
		this.uId = uId;
		this.mId = mId;
	}
	
	public ShareCollection(){
		
	}
	@Override
	public String toString() {
		return "ShareCollection [cId=" + cId + ", uId=" + uId + ", mId=" + mId + "]";
	}
	
	
}

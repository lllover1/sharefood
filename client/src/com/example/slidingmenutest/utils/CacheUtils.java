package com.example.slidingmenutest.utils;

import android.content.Context;

/**
 *  ���湤����
 * @author TAN
 *
 */
public class CacheUtils {
	public static final String U_NAME = "uName";
	public static final String U_NICKNAME = "uNickname";
	public static final String U_DISCRPTION = "uDiscrption";
	public static final String U_SEX = "uSex";
	public static final String U_DATE = "uDate";
	public static final String U_PASSWORD = "uPassword"; 
	public static final String U_HEADIMAGE = "uHeadimage";
	/**
	 * ���û���key��url,value��json
	 * @param key
	 * @param value
	 * @param ctx
	 */
	public static void setCache(String key,String value,Context ctx){
		PrefUtils.setString(ctx, key, value);
	}
	
	public static String getCache(String key,Context ctx){
		return PrefUtils.getString(ctx, key,null);
	}
}

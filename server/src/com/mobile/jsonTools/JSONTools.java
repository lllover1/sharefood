package com.mobile.jsonTools;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class JSONTools {
		//把参数变为JSON串
		public static String getJSONString(Object obj){
			Gson gson = new Gson();
			String json= gson.toJson(obj);
			return json;
		}
		
		
		//还原JSON串 到 对象
		public static <T> T getObjectFromJson(String json,Class<T> cls){
			T t=null;
			try {
				Gson gson= new Gson();
				JsonObject object = new JsonParser().parse(json).getAsJsonObject();  //反射 并实例化  进行赋值一个 T对象
				t=gson.fromJson(object, cls);  
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return t;
		}
		
		public static <T> List<T> getListFromJson(String json){
			List<T> list =null;
			try {
				Gson gson = new Gson();
				list =gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
			} catch (Exception e) {
				
			}
			return list;
		}
		
		public static  List<String> getListStringFromJson(String json){
			List<String> list =null;
			try {
				Gson gson = new Gson();
				list =gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
			} catch (Exception e) {
				
			}
			return list;
		}
		public static  List<Map<String,Object>> getListMapFromJson(String json){
			List<Map<String,Object>> list =null;
			try {
				Gson gson = new Gson();
				list =gson.fromJson(json, new TypeToken<List<Map<String,Object>>>(){}.getType());
			} catch (Exception e) {
				
			}
			return list;
		}
}

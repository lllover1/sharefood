package com.example.slidingmenutest;

import java.text.SimpleDateFormat;

import com.example.slidingmenutest.bean.User;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.CacheUtils;
import com.example.slidingmenutest.utils.JSONTools;
import com.example.slidingmenutest.utils.PrefUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoadActivity extends Activity {
	private EditText username;
	private EditText password;
	private Button load;
	private Button register;
	private String userid;
	private String userpw;
	private User users ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String name = PrefUtils.getString(LoadActivity.this, CacheUtils.U_NICKNAME, null);
		if(name!=null){
			Intent intent = new Intent(LoadActivity.this,MainActivity.class);
			startActivity(intent);
			this.finish();
		}else{
			initActivity();
		}
		
	}
	
	public void  initActivity(){
		setContentView(R.layout.activity_load);
		username = (EditText) findViewById(R.id.load_username);
		password = (EditText) findViewById(R.id.load_password);
		load = (Button) findViewById(R.id.load_login);
		register = (Button) findViewById(R.id.load_register);
		
		load.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userid = username.getText().toString();
				userpw = password.getText().toString();
				
				getDataFromServer(userid,userpw);
			}
		});
		
		register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoadActivity.this,RegisterActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public  void getDataFromServer(String username,String password) {
		
		HttpUtils utils=new HttpUtils();
		//使用xutils发送请求
		RequestParams params = new RequestParams();
		
		params.addBodyParameter("username", username);
		params.addBodyParameter("password", password);
		//http://192.168.1.151:8080/MobileOA/getUserName.action?username=pic&password=8545
		utils.send(HttpMethod.POST, GlobalContants.USER_URL, params,new RequestCallBack<String>() {
			//访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				
//				System.out.println("返回结果："+result);
				
				if(result!=null){
					users = JSONTools.getObjectFromJson(result, User.class);
					saveUser(users);
					
					
					Intent intent = new Intent(LoadActivity.this,MainActivity.class);
					startActivity(intent);
					LoadActivity.this.finish();
					
				}else{
					Toast.makeText(LoadActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
				}
				
			}
			//访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(LoadActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			
			}
		});
	}
	
	public void saveUser(User user){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PrefUtils.setString(LoadActivity.this, CacheUtils.U_NICKNAME, user.getuNickname());
		PrefUtils.setString(LoadActivity.this, CacheUtils.U_PASSWORD, user.getuPassword());
		PrefUtils.setInt(LoadActivity.this, CacheUtils.U_SEX, user.getuSex());
		PrefUtils.setString(LoadActivity.this, CacheUtils.U_DATE, sdf.format(user.getuDate()));
		PrefUtils.setString(LoadActivity.this, CacheUtils.U_NAME, user.getuName());
		PrefUtils.setString(LoadActivity.this, CacheUtils.U_DISCRPTION, user.getuDiscription());
		PrefUtils.setInt(LoadActivity.this, CacheUtils.U_HEADIMAGE, user.getuHeadImage());
	}
}

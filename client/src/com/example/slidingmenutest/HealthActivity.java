package com.example.slidingmenutest;

import com.example.slidingmenutest.bean.Health;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.JSONTools;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class HealthActivity extends Activity{
	private int id;
	private TextView name,content,help;
	private Health health;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Health health = (Health) msg.obj;
			name.setText(health.gethName());
			content.setText(health.gethDoing());
			help.setText(health.getHelp());
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_item);
		
		id = this.getIntent().getIntExtra("id", 0);
		
		name = (TextView) findViewById(R.id.tv_title);
		content = (TextView) findViewById(R.id.tv_doing);
		help = (TextView) findViewById(R.id.tv_help);
		getDataFromServer(id);
		
	}
	
private void getDataFromServer(int id) {
		
		HttpUtils utils=new HttpUtils();
		//使用xutils发送请求
		RequestParams params = new RequestParams();
		params.addBodyParameter("id", Integer.toString(id));
		utils.send(HttpMethod.POST, GlobalContants.CONTENT_URL,params, new RequestCallBack<String>() {
			//访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				System.out.println("返回结果he："+result);
				health = JSONTools.getObjectFromJson(result, Health.class);
				Message message = new Message();
				message.obj = health;
				handler.sendMessage(message);
				
			}
			//访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(HealthActivity.this, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
}

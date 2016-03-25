package com.example.slidingmenutest;

import java.util.ArrayList;
import java.util.List;

import com.example.slidingmenutest.adapter.DoingAdapter;
import com.example.slidingmenutest.bean.Doing;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.JSONTools;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DoingAcitity extends Activity{
	
	private ListView lv_doing;
	public BitmapUtils mBitmapUtils;
    public BitmapDisplayConfig mConfig;
	private List<Doing> doingList;
	private int id;
	private String name;
	private  DoingAdapter adapter;
	private TextView tv_name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doing);
		
		mBitmapUtils = new BitmapUtils(this);
		mConfig = new BitmapDisplayConfig();
		doingList = new ArrayList<Doing>();
		
		id = this.getIntent().getIntExtra("id",0);
		name = this.getIntent().getStringExtra("name");
		
		lv_doing = (ListView) findViewById(R.id.list_view);
		tv_name = (TextView) findViewById(R.id.doing_title);
		adapter = new DoingAdapter(this, mBitmapUtils, mConfig);
		
		getDataFromServer(id);
		
		lv_doing.setAdapter(adapter);
		tv_name.setText(name);
	}
	
	
	private void getDataFromServer(int id) {
		
		HttpUtils utils=new HttpUtils();
		//使用xutils发送请求
		RequestParams params = new RequestParams();
		params.addBodyParameter("mid", Integer.toString(id));
		utils.send(HttpMethod.POST, GlobalContants.DOING_URL,params, new RequestCallBack<String>() {
			//访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				System.out.println("返回结果："+result);
				parseData(result);
			}
			//访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(DoingAcitity.this, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
	protected void parseData(String result) {
		 doingList = JSONTools.getListFromJson(result, Doing.class);
		adapter.addAll(doingList);
		adapter.notifyDataSetChanged();
		//((Object) lv_doing).getRefreshableView().setSelection(adapter.getCount()-1);
//		lv.setStackFromBottom(true);  // 显示到最底部    pulltorefresh  不能这样使用
	}
	
}

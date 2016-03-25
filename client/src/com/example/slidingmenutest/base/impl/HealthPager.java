package com.example.slidingmenutest.base.impl;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slidingmenutest.HealthActivity;
import com.example.slidingmenutest.R;
import com.example.slidingmenutest.adapter.HealthAdapter;
import com.example.slidingmenutest.base.BasePager;
import com.example.slidingmenutest.bean.Health;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.JSONTools;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HealthPager extends BasePager {
	
	private GridView gv;
	private View myView;
	private List<Health> healthList;
	private HealthAdapter adapter;
	public BitmapUtils mBitmapUtils;
    public BitmapDisplayConfig mConfig;
    
	public HealthPager(Activity activity) {
		super(activity);
		
		mBitmapUtils = new BitmapUtils(mActivity);
		mConfig = new BitmapDisplayConfig();
		healthList = new ArrayList<Health>();
		
	}

	@Override
	public void initData() {
		tvTitle.setText("健康助手");
		
		
		if(myView==null){
		adapter = new HealthAdapter(mActivity, mBitmapUtils, mConfig,healthList);
		myView = View.inflate(mActivity, R.layout.h_health, null);
		// 向FrameLayout中动态添加控件
		flContent.addView(myView);
		
		}
		getDataFromServer();
		gv = (GridView) myView.findViewById(R.id.gridView1);
		gv.setAdapter(adapter);
		
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Health health= (Health) arg0.getAdapter().getItem(arg2);
				int id = health.gethId();
				Intent intent = new Intent(mActivity,HealthActivity.class);
				intent.putExtra("id", id);
				mActivity.startActivity(intent);
				
			}
		});

			
	}
	
	
 private void getDataFromServer() {
		
		HttpUtils utils=new HttpUtils();
		//使用xutils发送请求
		utils.send(HttpMethod.POST, GlobalContants.HEALTH_URL, new RequestCallBack<String>() {
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
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
		
		
	}
	protected void parseData(String result) {
		healthList .addAll(JSONTools.getListFromJson(result, Health.class));
		adapter.addAll(healthList);
		adapter.notifyDataSetChanged();
		//((Object) lv_doing).getRefreshableView().setSelection(adapter.getCount()-1);
//		lv.setStackFromBottom(true);  // 显示到最底部    pulltorefresh  不能这样使用
	}
}

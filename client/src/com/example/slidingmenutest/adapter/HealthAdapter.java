package com.example.slidingmenutest.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.slidingmenutest.R;
import com.example.slidingmenutest.bean.Health;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthAdapter extends BaseAdapter {
	List<Health> healthList;
	
	private LayoutInflater healinflater;
	private BitmapUtils hBitmapUtils;
	private BitmapDisplayConfig hConfig;
	
	public HealthAdapter(Context newscontext,BitmapUtils mBitmapUtils ,BitmapDisplayConfig mConfig,List<Health> healthList){
		this.healthList = healthList;
		healinflater = LayoutInflater.from(newscontext);
		healthList =  new ArrayList<Health>();
		this.hBitmapUtils = mBitmapUtils;
		this.hConfig =mConfig;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return healthList.size();
	}

	@Override
	public Health getItem(int position) {
		// TODO Auto-generated method stub
		return healthList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public String getItemUrl(int position){
		return healthList.get(position).gethImage();
	}
	
	public void addAll(List<Health> newsLists){
		healthList.addAll(newsLists);
		System.out.println(healthList.size());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view =convertView;
		if(view == null){
			view = healinflater.inflate(R.layout.h_healthpage, null);
		}
		  ImageView ib = (ImageView) view.findViewById(R.id.imageButton1);
		  TextView tv = (TextView) view.findViewById(R.id.textView2);
          
          // mBitmapUtils.display(ico, getItem(position), mConfig);
          hBitmapUtils.display(ib, getItemUrl(position), hConfig, new BitmapLoadCallBack<ImageView>() {

              @Override
              public void onLoadCompleted(ImageView arg0, String arg1, Bitmap arg2, BitmapDisplayConfig arg3, BitmapLoadFrom arg4) {
                  // 若设置了BitmapLoadCallBack的
                  // 回调，那么在onLoadCompleted方法中必须要手动的给ImageView设置图片背景
                  arg0.setImageBitmap(arg2);
              }
              @Override
              public void onLoadFailed(ImageView arg0, String arg1, Drawable arg2) {
                  arg0.setImageDrawable(arg2);
              }
          });
          
          
          tv.setText(getItem(position).gethName());
		return view;
	}

}

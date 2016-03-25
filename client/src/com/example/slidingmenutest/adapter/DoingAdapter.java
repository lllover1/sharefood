package com.example.slidingmenutest.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.slidingmenutest.R;
import com.example.slidingmenutest.bean.Doing;
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

public class DoingAdapter extends BaseAdapter {
	
	private List<Doing> doingList;
	private LayoutInflater doinginflater;
	private BitmapUtils dBitmapUtils;
	private BitmapDisplayConfig dConfig;
	
	public DoingAdapter(Context newscontext,BitmapUtils mBitmapUtils ,BitmapDisplayConfig mConfig){
		doinginflater = LayoutInflater.from(newscontext);
		doingList =  new ArrayList<Doing>();
		this.dBitmapUtils = mBitmapUtils;
		this.dConfig =mConfig;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return doingList.size();
	}

	@Override
	public Doing getItem(int position) {
		// TODO Auto-generated method stub
		return doingList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public void addAll(List<Doing> newsLists){
		doingList.addAll(newsLists);
		System.out.println(doingList.size());
	}
	
	public String getItemUrl(int position){
		return doingList.get(position).getdImageUrl();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =convertView;
		if(view == null){
			view = doinginflater.inflate(R.layout.doing_items, null);
		}
		  ImageView img = (ImageView) view.findViewById(R.id.item_image);
          TextView title = (TextView) view.findViewById(R.id.tv_title);
          TextView content = (TextView) view.findViewById(R.id.tv_content);
          
          // mBitmapUtils.display(ico, getItem(position), mConfig);
          dBitmapUtils.display(img, getItemUrl(position), dConfig, new BitmapLoadCallBack<ImageView>() {

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
          
          title.setText(Integer.toString(getItem(position).getdName()));
          content.setText(getItem(position).getdDiscription());
		return view;
	}

}

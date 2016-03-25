package com.example.slidingmenutest.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slidingmenutest.R;
import com.example.slidingmenutest.bean.MenuCard;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

public class HomeNewsAdpater extends BaseAdapter {

	private List<MenuCard> newlists;
	private LayoutInflater newsinflater;
	private BitmapUtils mBitmapUtils;
	private BitmapDisplayConfig mConfig;
	
	public HomeNewsAdpater(Context newscontext,BitmapUtils mBitmapUtils ,BitmapDisplayConfig mConfig) {
		super();
		newsinflater = LayoutInflater.from(newscontext);
		newlists =  new ArrayList<MenuCard>();
		this.mBitmapUtils = mBitmapUtils;
		this.mConfig =mConfig;
	}

	@Override
	public int getCount() {
		return newlists.size();
	}

	public void addAll(List<MenuCard> newsLists){
		newlists.addAll(newsLists);
		System.out.println(newlists.size());
	}
	
	@Override
	public MenuCard getItem(int position) {
		return newlists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public String getItemUrl(int position){
		return newlists.get(position).getmImageUrl();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view =convertView;
		if(view == null){
			view = newsinflater.inflate(R.layout.fragment_content_lv_item, null);
		}
		  ImageView img = (ImageView) view.findViewById(R.id.lv_item_conent_img);
          TextView text = (TextView) view.findViewById(R.id.lv_item_conent_text2);
          TextView title = (TextView) view.findViewById(R.id.lv_item_conent_text1);
          // mBitmapUtils.display(ico, getItem(position), mConfig);
          mBitmapUtils.display(img, getItemUrl(position), mConfig, new BitmapLoadCallBack<ImageView>() {

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
          
          text.setText(getItem(position).getmDiscription());
          title.setText(getItem(position).getmName());
		return view;
	}

}

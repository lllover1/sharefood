package com.example.slidingmenutest.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.slidingmenutest.R;
import com.example.slidingmenutest.bean.Critism;
import com.example.slidingmenutest.bean.shareBean;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShareAdapter extends BaseAdapter {
	List<shareBean> share;
	private LayoutInflater healinflater;
	private BitmapUtils hBitmapUtils;
	private BitmapDisplayConfig hConfig;
	Context context;
	
	List<Critism> critism;
public ShareAdapter(Context newscontext,BitmapUtils mBitmapUtils ,BitmapDisplayConfig mConfig,List<shareBean> share){
		this.context = newscontext;
		healinflater = LayoutInflater.from(newscontext);
		this.share = share;
		this.hBitmapUtils = mBitmapUtils;
		this.hConfig =mConfig;
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return share.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return share.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public String getItemUrl(int position){
		return share.get(position).getImage();
	}
	
	public void addAll(List<shareBean> newsLists){
		share.addAll(newsLists);
		System.out.println("这是适配器的大小"+share.size());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view =convertView;
		if(view == null){
			view = healinflater.inflate(R.layout.h_circle_title, null);
		}
		  ImageView head = (ImageView) view.findViewById(R.id.h_iv_head); 
          TextView nickname = (TextView) view.findViewById(R.id.tv_nickname);
          TextView discrption = (TextView) view.findViewById(R.id.tv_discrption);
          TextView content = (TextView) view.findViewById(R.id.more);
          ImageView image = (ImageView) view.findViewById(R.id.iv_image);
          // mBitmapUtils.display(ico, getItem(position), mConfig);
          hBitmapUtils.display(image, getItemUrl(position), hConfig, new BitmapLoadCallBack<ImageView>() {

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
          
          switch(share.get(position).getHead()){
          case 1:
  			head.setBackgroundResource(R.drawable.headimage1);
  			break;
  		case 2:
  			head.setBackgroundResource(R.drawable.headimage2);
  			break;
  		case 3:
  			head.setBackgroundResource(R.drawable.headimage3);
  			break;
  		case 4:
  			head.setBackgroundResource(R.drawable.headimage4);
  			break;
  		case 5:
  			head.setBackgroundResource(R.drawable.headimage5);
  			break;
  		case 6:
  			head.setBackgroundResource(R.drawable.headimage6);
  			break;
  		case 7:
  			head.setBackgroundResource(R.drawable.headimage7);
  			break;
  	    default:
  	    	head.setBackgroundResource(R.drawable.ic_launcher);
  			break;
          }
          nickname.setText(share.get(position).getNickname());
          discrption.setText(share.get(position).getDiscrption());
          content.setText(share.get(position).getContent());
          critism = share.get(position).getClist();
          System.out.println("CR DAXIAO"+critism.size());
//          SimpleAdapter s = new SimpleAdapter(context, getData(), R.layout.critism_items, new String[]{"name","discrption"}, new int[]{R.id.textView1,R.id.textView2});
//		  lv.setAdapter(s);
          return view;
	}
	
	public List<Map<String,Object>>getData(){
		List<Map<String,Object>> mlist =new ArrayList<Map<String,Object>>();
		
		for(int i = 0;i<critism.size();i++){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("name",critism.get(i).getuName());
			map.put("discrption", critism.get(i).getuDiscrption());
			mlist.add(map);
		}
		return mlist;
	}

}

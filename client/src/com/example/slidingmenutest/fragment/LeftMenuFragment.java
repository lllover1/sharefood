package com.example.slidingmenutest.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.slidingmenutest.MainActivity;
import com.example.slidingmenutest.R;
import com.example.slidingmenutest.SettingActivity;
import com.example.slidingmenutest.utils.CacheUtils;
import com.example.slidingmenutest.utils.PrefUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


public class LeftMenuFragment extends BaseFragment{

	@ViewInject(R.id.lv_list)
	private ListView lv_list ;
	
	@ViewInject(R.id.imageView)
	private ImageView iv_headimage; 
	
	@ViewInject(R.id.textView1)
	private TextView tv_nickname;
	
	@ViewInject(R.id.textView2)
	private TextView tv_discrption;
	
	private int lHeadimage;
	private String lNickname;
	private String lDiscrption;
	
	@Override
	public View initViews() {  // 锟斤拷始锟斤拷锟斤拷图锟斤拷示锟斤拷锟角碉拷 锟斤拷锟斤拷
		View view = View.inflate(mActivity, R.layout.fragment_left_menu,null);
		ViewUtils.inject(this, view);
		return view;
	}
	
	@Override
	public void initData() {
//		ArrayAdapter<String>  adapter = new ArrayAdapter<String>(mActivity, 
//				android.R.layout.simple_list_item_1, getData());
//		System.out.println(lv_list);
		
		SimpleAdapter adapter2 = new SimpleAdapter(mActivity, getData(), R.layout.fragment_left_lv_item, 
					new String[]{"img","title"}, new int[]{R.id.lv_item_left_img,R.id.lv_item_left_tv});
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				toggleSlidingMenu();
			}
			
		});
		lv_list.setAdapter(adapter2);
		lv_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2){
				case 3:
					Intent intent = new Intent(mActivity,SettingActivity.class);
				    mActivity.startActivity(intent);
				}
			}
		});
		
		initUser();
	}
	
	public void initUser(){
		lHeadimage = PrefUtils.getInt(mActivity, CacheUtils.U_HEADIMAGE,0);
		lNickname = PrefUtils.getString(mActivity, CacheUtils.U_NICKNAME, "请登录");
		lDiscrption = PrefUtils.getString(mActivity, CacheUtils.U_DISCRPTION, "输入你的签名");
		switch(lHeadimage){
		case 1:
			iv_headimage.setBackgroundResource(R.drawable.headimage1);
			break;
		case 2:
			iv_headimage.setBackgroundResource(R.drawable.headimage2);
			break;
		case 3:
			iv_headimage.setBackgroundResource(R.drawable.headimage3);
			break;
		case 4:
			iv_headimage.setBackgroundResource(R.drawable.headimage4);
			break;
		case 5:
			iv_headimage.setBackgroundResource(R.drawable.headimage5);
			break;
		case 6:
			iv_headimage.setBackgroundResource(R.drawable.headimage6);
			break;
		case 7:
			iv_headimage.setBackgroundResource(R.drawable.headimage7);
			break;
	    default:
	    	iv_headimage.setBackgroundResource(R.drawable.ic_launcher);
			break;
		}
		tv_nickname.setText(lNickname);
		tv_discrption.setText(lDiscrption);
	}
	
	
	public List<Map<String,Object>> getData(){
		String[] content = new String[]{"我的菜谱","我的收藏","我的评论","设置"
				
		};
		int [] image = new int []{R.drawable.left_image_meau,R.drawable.left_image_find,R.drawable.left_image_receive,R.drawable.left_image_setting};
		 List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		 for (int i = 0; i < 4; i++) {
			 Map<String,Object> map = new HashMap<String, Object>();
			 map.put("img", image[i]);
			 map.put("title", content[i]);
			 lists.add(map);
		}
		 return lists;
	}
	
//	public List<String> getData(){
//		List<String> lists = new ArrayList<String>();
//		for (int i = 0; i <10; i++) {
//			lists.add("导航栏"+i);
//		}
//		return lists;
//	}
	/**
	 * 切换菜单栏状态
	 */
	protected void toggleSlidingMenu() {
		MainActivity mainUI=(MainActivity) mActivity;
		SlidingMenu slidingMenu=mainUI.getSlidingMenu();
		slidingMenu.toggle();
	}

	
}

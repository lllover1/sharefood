package com.example.slidingmenutest.base;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.slidingmenutest.MainActivity;
import com.example.slidingmenutest.R;
import com.example.slidingmenutest.base.popwindow.CustomPopupWindow;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.core.BitmapSize;
/**
 * BasePager基类
 * @author TAN
 *
 */
public class BasePager {
	
	public Activity mActivity;
	
	public View mRootView;//布局对象
	public TextView tvTitle;//标题对象
	
	public FrameLayout flContent;//内容
	
	public ImageButton btnMenu;//菜单按钮
	public ImageButton btnPhoto;//组图切换按钮
	
	
    public BitmapUtils mBitmapUtils;
    public BitmapDisplayConfig mConfig;
	
    public CustomPopupWindow mpopWindow;
    
	public BasePager(Activity activity){
		mActivity=activity;
		initViews();
		initUtils();
	}
	/**
	 * 初始化工具类
	 */
	private void initUtils() {
			mBitmapUtils = new BitmapUtils(mActivity);
	        // diskCachePath : 设置图片缓存sdcard的路径
	        // memoryCachePercent ：设置图片内存缓冲占整个应用程序可用内存的百分比
	        // diskCacheSize ： 设置sdcard缓存的总大小
	        // mBitmapUtils = new BitmapUtils(context, diskCachePath,
	        // memoryCachePercent, diskCacheSize)
	        mConfig = new BitmapDisplayConfig();

	        AnimationSet set = new AnimationSet(true);
	        AlphaAnimation alpha = new AlphaAnimation(0.3f, 1);
	        ScaleAnimation scale = new ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	        set.addAnimation(alpha);
	        set.addAnimation(scale);
	        set.setDuration(350);
	        // 设置 图片显示在界面上的时候播放一个动画
	        mConfig.setAnimation(set);
	        // 设置加载失败的时候显示的图片
	        mConfig.setLoadFailedDrawable(mActivity.getResources().getDrawable(R.drawable.health));
	        // 设置加载中的时候显示的图片
	        mConfig.setLoadingDrawable(mActivity.getResources().getDrawable(R.drawable.home));
	        // 设置图片的分辨率
	        BitmapSize size = new BitmapSize(110, 110);
	        mConfig.setBitmapMaxSize(size);
		
	}
	/**
	 * 初始化布局
	 */
	public void initViews(){
		mRootView=View.inflate(mActivity, R.layout.base_pager, null);
		tvTitle=(TextView) mRootView.findViewById(R.id.tv_title);
		flContent=(FrameLayout) mRootView.findViewById(R.id.fl_content);
		btnMenu=(ImageButton) mRootView.findViewById(R.id.btn_menu);
		mpopWindow = new CustomPopupWindow(mActivity);
		btnPhoto=(ImageButton) mRootView.findViewById(R.id.btn_photo);
		
		btnPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(mpopWindow.isShowing()){
					mpopWindow.dismiss();
				}else{
					mpopWindow.showAtDropDownRight(btnPhoto);
				}
				
			}
		});
		btnMenu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleSlidingMenu();
			}
		});
	}
	protected void toggleSlidingMenu() {
		MainActivity mainUi=(MainActivity) mActivity;
		SlidingMenu slidingMenu=mainUi.getSlidingMenu();
		slidingMenu.toggle();// 切换状态, 显示时隐藏, 隐藏时显示
	}
		
	/**
	 * 初始化数据
	 */
	public void initData(){}
	
	public void setSlidingMenuEnable(boolean enable){
		
	}
}

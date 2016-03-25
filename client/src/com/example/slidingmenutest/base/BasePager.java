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
 * BasePager����
 * @author TAN
 *
 */
public class BasePager {
	
	public Activity mActivity;
	
	public View mRootView;//���ֶ���
	public TextView tvTitle;//�������
	
	public FrameLayout flContent;//����
	
	public ImageButton btnMenu;//�˵���ť
	public ImageButton btnPhoto;//��ͼ�л���ť
	
	
    public BitmapUtils mBitmapUtils;
    public BitmapDisplayConfig mConfig;
	
    public CustomPopupWindow mpopWindow;
    
	public BasePager(Activity activity){
		mActivity=activity;
		initViews();
		initUtils();
	}
	/**
	 * ��ʼ��������
	 */
	private void initUtils() {
			mBitmapUtils = new BitmapUtils(mActivity);
	        // diskCachePath : ����ͼƬ����sdcard��·��
	        // memoryCachePercent ������ͼƬ�ڴ滺��ռ����Ӧ�ó�������ڴ�İٷֱ�
	        // diskCacheSize �� ����sdcard������ܴ�С
	        // mBitmapUtils = new BitmapUtils(context, diskCachePath,
	        // memoryCachePercent, diskCacheSize)
	        mConfig = new BitmapDisplayConfig();

	        AnimationSet set = new AnimationSet(true);
	        AlphaAnimation alpha = new AlphaAnimation(0.3f, 1);
	        ScaleAnimation scale = new ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
	        set.addAnimation(alpha);
	        set.addAnimation(scale);
	        set.setDuration(350);
	        // ���� ͼƬ��ʾ�ڽ����ϵ�ʱ�򲥷�һ������
	        mConfig.setAnimation(set);
	        // ���ü���ʧ�ܵ�ʱ����ʾ��ͼƬ
	        mConfig.setLoadFailedDrawable(mActivity.getResources().getDrawable(R.drawable.health));
	        // ���ü����е�ʱ����ʾ��ͼƬ
	        mConfig.setLoadingDrawable(mActivity.getResources().getDrawable(R.drawable.home));
	        // ����ͼƬ�ķֱ���
	        BitmapSize size = new BitmapSize(110, 110);
	        mConfig.setBitmapMaxSize(size);
		
	}
	/**
	 * ��ʼ������
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
		slidingMenu.toggle();// �л�״̬, ��ʾʱ����, ����ʱ��ʾ
	}
		
	/**
	 * ��ʼ������
	 */
	public void initData(){}
	
	public void setSlidingMenuEnable(boolean enable){
		
	}
}

package com.example.slidingmenutest;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.animation.Interpolator;

import com.example.slidingmenutest.fragment.ContentFragment;
import com.example.slidingmenutest.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class MainActivity extends SlidingFragmentActivity {

	private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
	private static final String FRAGMENT_CONTENT = "fragment_content";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.left_menu);// 设置侧边栏
		SlidingMenu slidingMenu = getSlidingMenu();// 获取侧边栏对象
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置全屏触摸
		
		slidingMenu.setMode(SlidingMenu.LEFT);
		
		slidingMenu.showMenu(true);
		
		int width=getWindowManager().getDefaultDisplay().getWidth();
		
		slidingMenu.setBehindOffset(width*100/300);// 设置预留屏幕的宽度

		initAnimation();
		initSlidingMenu();
		initFragment();
	}

	
	
	
	/**
	 * 初始化fragment, 将fragment数据填充给布局文件
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开启事务
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);// 用fragment替换framelayout
		transaction.replace(R.id.fl_content, new ContentFragment(),
				FRAGMENT_CONTENT);

		transaction.commit();// 提交事务
		// Fragment leftMenuFragment = fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
	}
	// 获取侧边栏fragment
	public LeftMenuFragment getLeftMenuFragment(){
		FragmentManager fm=getSupportFragmentManager();
		LeftMenuFragment fragment=(LeftMenuFragment) fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;
	}
	// 获取主页面fragment
	public ContentFragment getContentFragment(){
		FragmentManager fm=getSupportFragmentManager();
		ContentFragment fragment= (ContentFragment) fm.findFragmentByTag(FRAGMENT_CONTENT);
		return fragment;
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
	  /** 

     * 初始化滑动菜单 

     */  
	private CanvasTransformer mTransformer;
    private void initSlidingMenu(){  
        // 设置滑动菜单的属性值  
        SlidingMenu sm = getSlidingMenu();        
        sm.setShadowWidthRes(R.dimen.shadow_width);  
        sm.setShadowDrawable(R.drawable.shadow);  
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);  
//        sm.setFadeDegree(0.0f);  // 
        sm.setBehindScrollScale(0.0f);   // 主菜单是否 缩放 再滚动的时候
        sm.setBehindCanvasTransformer(mTransformer); //Behind : 在什么的后面，支持 ,设置 菜单展示的时 要执行的动画 

    }  

    /** 

     * 初始化动画效果 

     */  

    private void initAnimation(){  

        mTransformer = new CanvasTransformer(){  
            @Override  
            public void transformCanvas(Canvas canvas, float percentOpen) {  

            	Log.i("TAG",".."+percentOpen);
                float scale = (float) (percentOpen*0.25 + 0.75); 
                //canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);                
               // canvas.rotate(percentOpen*360,canvas.getWidth()/2, canvas.getHeight()/2);
               // canvas.scale(percentOpen, 1, 0, 0);    
                // 从上往下移动  需要的  一个  插值器
                canvas.translate(0, canvas.getHeight() * (1 - interp.getInterpolation(percentOpen)));  
            }  
            
        };  

    }  
    
    // 从上往下移动 需要的 插值器
    private static Interpolator interp = new Interpolator() {  
        @Override  
        public float getInterpolation(float t) {  
            t -= 1.0f;  
            return t * t * t + 1.0f;  
        }         

    };  
}

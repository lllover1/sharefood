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
		setBehindContentView(R.layout.left_menu);// ���ò����
		SlidingMenu slidingMenu = getSlidingMenu();// ��ȡ���������
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// ����ȫ������
		
		slidingMenu.setMode(SlidingMenu.LEFT);
		
		slidingMenu.showMenu(true);
		
		int width=getWindowManager().getDefaultDisplay().getWidth();
		
		slidingMenu.setBehindOffset(width*100/300);// ����Ԥ����Ļ�Ŀ��

		initAnimation();
		initSlidingMenu();
		initFragment();
	}

	
	
	
	/**
	 * ��ʼ��fragment, ��fragment�������������ļ�
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// ��������
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				FRAGMENT_LEFT_MENU);// ��fragment�滻framelayout
		transaction.replace(R.id.fl_content, new ContentFragment(),
				FRAGMENT_CONTENT);

		transaction.commit();// �ύ����
		// Fragment leftMenuFragment = fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
	}
	// ��ȡ�����fragment
	public LeftMenuFragment getLeftMenuFragment(){
		FragmentManager fm=getSupportFragmentManager();
		LeftMenuFragment fragment=(LeftMenuFragment) fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
		return fragment;
	}
	// ��ȡ��ҳ��fragment
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

     * ��ʼ�������˵� 

     */  
	private CanvasTransformer mTransformer;
    private void initSlidingMenu(){  
        // ���û����˵�������ֵ  
        SlidingMenu sm = getSlidingMenu();        
        sm.setShadowWidthRes(R.dimen.shadow_width);  
        sm.setShadowDrawable(R.drawable.shadow);  
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);  
//        sm.setFadeDegree(0.0f);  // 
        sm.setBehindScrollScale(0.0f);   // ���˵��Ƿ� ���� �ٹ�����ʱ��
        sm.setBehindCanvasTransformer(mTransformer); //Behind : ��ʲô�ĺ��棬֧�� ,���� �˵�չʾ��ʱ Ҫִ�еĶ��� 

    }  

    /** 

     * ��ʼ������Ч�� 

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
                // ���������ƶ�  ��Ҫ��  һ��  ��ֵ��
                canvas.translate(0, canvas.getHeight() * (1 - interp.getInterpolation(percentOpen)));  
            }  
            
        };  

    }  
    
    // ���������ƶ� ��Ҫ�� ��ֵ��
    private static Interpolator interp = new Interpolator() {  
        @Override  
        public float getInterpolation(float t) {  
            t -= 1.0f;  
            return t * t * t + 1.0f;  
        }         

    };  
}

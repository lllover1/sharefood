package com.example.slidingmenutest.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不能左右滑的ViewPager
 * 
 * @author TAN
 * 
 */
public class NoScrollViewPager extends ViewPager {

	private boolean isCanScroll = false;

	public NoScrollViewPager(Context context) {
		super(context);
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}

	/**
	 * 切换视图 以及  滑动的 必要方法  ，绝对 不滑动 或者不切换   可以  不调用  super
	 */
	@Override
	public void scrollTo(int x, int y) {
		// if (isCanScroll){
		super.scrollTo(x, y);
		// }
	}

	@Override
	public void setCurrentItem(int item) {
		// TODO Auto-generated method stub
		super.setCurrentItem(item);
	}

	
	/**
	 * 两个 触摸式件   主要目的是   只允许 点击 滑动   不允许   手势滑动
	 */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (isCanScroll) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}
	}
	/**
	 * 两个 触摸式件   主要目的是   只允许 点击 滑动   不允许   手势滑动
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (isCanScroll) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}
	}

}

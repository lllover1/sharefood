package com.example.slidingmenutest.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * �������һ���ViewPager
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
	 * �л���ͼ �Լ�  ������ ��Ҫ����  ������ ������ ���߲��л�   ����  ������  super
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
	 * ���� ����ʽ��   ��ҪĿ����   ֻ���� ��� ����   ������   ���ƻ���
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
	 * ���� ����ʽ��   ��ҪĿ����   ֻ���� ��� ����   ������   ���ƻ���
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

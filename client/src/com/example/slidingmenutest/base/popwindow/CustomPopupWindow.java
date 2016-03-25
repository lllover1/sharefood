package com.example.slidingmenutest.base.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.slidingmenutest.R;

public class CustomPopupWindow extends PopupWindow {
	private Activity activity;
	private View contentView;

	// ���ڱ���PopupWindows�Ŀ��
	private int width;
	// ���ڱ���PopupWindows�ĸ߶�
	private int height;

	public CustomPopupWindow(Activity activity) {
		super();
		this.activity = activity;
		this.initPopupWindow();
	}

	private void initPopupWindow() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.contentView = inflater.inflate(R.layout.popup_window, null);
		this.setContentView(contentView);
		this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
		this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		this.setTouchable(true);
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		this.setAnimationStyle(R.style.PopupAnimation);
		ColorDrawable background = new ColorDrawable(0x4f000000);
		this.setBackgroundDrawable(background);
		this.mandatorDraw();
		// pop �ĵ�һ����ѡ��ĵ���¼�
		View view1 = contentView.findViewById(R.id.popwindow_item1);
		view1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(activity, "����������ѡ��1", 0).show();
				CustomPopupWindow.this.dismiss();
			}
		});
		
	}

	private void mandatorDraw() {
		this.contentView.measure(View.MeasureSpec.UNSPECIFIED,
				View.MeasureSpec.UNSPECIFIED);
		this.width = this.contentView.getMeasuredWidth();
		this.height = this.contentView.getMeasuredHeight();
	}

	/* ��ʾ�ڿؼ����ҷ� */
	public void showAtDropDownRight(View parent) {
		if (parent.getVisibility() == View.GONE) {
			this.showAtLocation(parent, 0, 0, 0);
		} else {
			int[] location = new int[2];
			parent.getLocationOnScreen(location);
			this.showAtLocation(parent, 0, location[0] + parent.getWidth()
					- this.getWidth(), location[1] + parent.getHeight());
		}
	}
	
	/**
     * ��ʾ�ڿؼ�������
     *
     * @param parent parent
     */
    public void showAtDropDownLeft(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //��ȡ��������Ļ�ڵľ�������
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0], location[1] + parent.getHeight());
        }
    }

    /**
     * ��ʾ�ڿؼ������з�
     *
     * @param parent parent
     */
    public void showAtDropDownCenter(View parent) {
        if (parent.getVisibility() == View.GONE) {
            this.showAtLocation(parent, 0, 0, 0);
        } else {
            // x y
            int[] location = new int[2];
            //��ȡ��������Ļ�ڵľ�������
            parent.getLocationOnScreen(location);
            this.showAtLocation(parent, 0, location[0] / 2 + parent.getWidth() / 2 - this.width / 6, location[1] + parent.getHeight());
        }
    }
    
    public static class PopupWindowBuilder{
    	private static String activityHashCode;
    	private static CustomPopupWindow popupWindow;
    	public static PopupWindowBuilder ourInstance;
    	
    	 public static PopupWindowBuilder getInstance(Activity activity) {
             if (ourInstance == null) ourInstance = new PopupWindowBuilder();
             String hashCode = String.valueOf(activity.hashCode());
             /**
              * ��ͬһ��Activity
              */
             if (!hashCode.equals(String.valueOf(activityHashCode))) {
                 activityHashCode = hashCode;
                 popupWindow = new CustomPopupWindow(activity);
             }
             return ourInstance;
         }

         public PopupWindowBuilder setTouchable(boolean touchable) {
             popupWindow.setTouchable(touchable);
             return this;
         }

         public PopupWindowBuilder setAnimationStyle(int animationStyle) {
             popupWindow.setAnimationStyle(animationStyle);
             return this;
         }

         public PopupWindowBuilder setBackgroundDrawable(Drawable background) {
             popupWindow.setBackgroundDrawable(background);
             return this;
         }

         public CustomPopupWindow getPopupWindow() {
             popupWindow.update();
             return popupWindow;
         }

     }
}

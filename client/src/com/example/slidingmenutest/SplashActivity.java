package com.example.slidingmenutest;

import com.example.slidingmenutest.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private RelativeLayout rlRoot;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
		startAnim();
	}

	/**
	 * ��������
	 */
	private void startAnim() {
		// ��������
		AnimationSet set = new AnimationSet(false);

		// ��ת����
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(2000);// ����ʱ��
		rotate.setFillAfter(true);// ���ֶ���״̬

		// ���Ŷ���
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scale.setDuration(2000);// ����ʱ��
		scale.setFillAfter(true);// ���ֶ���״̬

		// ���䶯��
		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(2000);// ����ʱ��
		alpha.setFillAfter(true);// ���ֶ���״̬

		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alpha);
		
		// ���ö�������
				set.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {

					}

					@Override
					public void onAnimationRepeat(Animation animation) {

					}

					// ����ִ�н���
					@Override
					public void onAnimationEnd(Animation animation) {
						jumpNextPage();
					}
				});

				rlRoot.startAnimation(set);

	}

	protected void jumpNextPage() {
		// �ж�֮ǰ��û����ʾ����������
		boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed",
				false);

		if (!userGuide) {
			// ��ת����������ҳ
			startActivity(new Intent(SplashActivity.this, GuideActivity.class));
		} else {
			startActivity(new Intent(SplashActivity.this, LoadActivity.class));
		}

		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
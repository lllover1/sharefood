package com.example.slidingmenutest.fragment;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.example.slidingmenutest.R;
import com.example.slidingmenutest.base.BasePager;
import com.example.slidingmenutest.base.impl.HomePager;
import com.example.slidingmenutest.base.impl.HealthPager;
import com.example.slidingmenutest.base.impl.SharePager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class ContentFragment extends BaseFragment {

	@ViewInject(R.id.rg_group)
	private RadioGroup rgGroup;
	
	@ViewInject(R.id.vp_content)
	private ViewPager mViewPager;
	
	private ArrayList<BasePager> mPagerList;
	
	@Override
	public View initViews() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
//		rgGroup=(RadioGroup) view.findViewById(R.id.rg_group);
		ViewUtils.inject(this, view);
		return view;
	}
	
	public void initData() {
		rgGroup.check(R.id.rb_home);//Ĭ�Ϲ�ѡ��ҳ
		
		mPagerList=new ArrayList<BasePager>();
		mPagerList.add(new HomePager(mActivity));
		mPagerList.add(new SharePager(mActivity));
		mPagerList.add(new HealthPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
		
		//����RadioGroup��ѡ���¼�
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					mViewPager.setCurrentItem(0, true);// ȥ���л�ҳ��Ķ���
					break;
				case R.id.rb_technology:
					mViewPager.setCurrentItem(1, true);// ���õ�ǰҳ��
					break;
				case R.id.rb_sport:
					mViewPager.setCurrentItem(2, true);// ���õ�ǰҳ��
					break;
				default:
					break;
				}
			}
		});
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mPagerList.get(arg0).initData();// ��ȡ��ǰ��ѡ�е�ҳ��, ��ʼ����ҳ������
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		mPagerList.get(0).initData();//��ʼ����ҳ����
	}
	
	
	class ContentAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return mPagerList.size();
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mPagerList.get(position).mRootView);
			return mPagerList.get(position).mRootView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			//���������Ƴ�
			container.removeView((View) object);
		}

		
	}
}
	



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
		rgGroup.check(R.id.rb_home);//默认勾选首页
		
		mPagerList=new ArrayList<BasePager>();
		mPagerList.add(new HomePager(mActivity));
		mPagerList.add(new SharePager(mActivity));
		mPagerList.add(new HealthPager(mActivity));
		
		mViewPager.setAdapter(new ContentAdapter());
		
		//监听RadioGroup的选择事件
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					mViewPager.setCurrentItem(0, true);// 去掉切换页面的动画
					break;
				case R.id.rb_technology:
					mViewPager.setCurrentItem(1, true);// 设置当前页面
					break;
				case R.id.rb_sport:
					mViewPager.setCurrentItem(2, true);// 设置当前页面
					break;
				default:
					break;
				}
			}
		});
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				mPagerList.get(arg0).initData();// 获取当前被选中的页面, 初始化该页面数据
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		mPagerList.get(0).initData();//初始化首页数据
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
			//从容器中移除
			container.removeView((View) object);
		}

		
	}
}
	



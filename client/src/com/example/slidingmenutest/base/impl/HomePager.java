package com.example.slidingmenutest.base.impl;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.slidingmenutest.DoingAcitity;
import com.example.slidingmenutest.R;
import com.example.slidingmenutest.adapter.HomeNewsAdpater;
import com.example.slidingmenutest.base.BasePager;
import com.example.slidingmenutest.bean.MenuCard;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.JSONTools;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HomePager extends BasePager {

	HomeNewsAdpater adpater = null;
	PullToRefreshListView lv = null;
	public HomePager(Activity activity) {
		super(activity);
	}

	@Override
	public void initData() {
		adpater = new HomeNewsAdpater(mActivity, mBitmapUtils, mConfig);
		tvTitle.setText("首页");//设置标题
		btnMenu.setVisibility(View.GONE);//隐藏菜单按钮
	
		lv=(PullToRefreshListView) mRootView.findViewById(R.id.lv_list_content);
//		lv.setVisibility(View.VISIBLE);
		lv.setMode(Mode.PULL_FROM_END);
		lv.setBackgroundResource(R.drawable.basspage_back);
		initListView(lv);
	
		lv.setAdapter(adpater);
//		// 向FrameLayout中动态添加控件
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				MenuCard mc = (MenuCard) arg0.getAdapter().getItem(arg2);
				int id = mc.getmId();
				String name = mc.getmName();
				//Toast.makeText(mActivity, "点击咯"+id, Toast.LENGTH_SHORT).show();
			    
				Intent intent = new Intent(mActivity,DoingAcitity.class);
				intent.putExtra("id", id);
				intent.putExtra("name", name);
				mActivity.startActivity(intent);
			}
		});
//		flContent.addView(lv);
//
		getDataFromServer();
		
	}

	/**
	 * 初始化  下拉刷新的 参数
	 */
	private void initListView(PullToRefreshListView pullv) {
		ILoadingLayout loadingLayout = pullv.getLoadingLayoutProxy();
		//修改图片（就是转圈的那个）
		//Drawable drawable= getResources().getDrawable(R.drawable.progressbar_circle);
		//loadingLayout.setLoadingDrawable(drawable);
		loadingLayout.setPullLabel("上拉刷新");//刚拉下给出的提示
		loadingLayout.setRefreshingLabel("正在刷新");//正在刷新的时候给出的提示
		loadingLayout.setReleaseLabel("松开进行刷新");//下拉到一定距离的时候的提示文字
		//设置文字演示样式
		Typeface typeface  =Typeface.createFromAsset(mActivity.getResources().getAssets(), "fonts/test.ttf");
		loadingLayout.setTextTypeface(typeface); //设置字体样式
		
		/**
		 * 添加  声音的 监听 
		 */
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(mActivity);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		pullv.setOnPullEventListener(soundListener);
		// 设置刷新监听
		pullv.setOnRefreshListener(new OnRefreshListener<ListView>() {
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(mActivity, System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				//  更新 刷新的 参数信息（展示文字）
				//  设置  更新是的参数  例如  ：  图片，显示文字等
				ILoadingLayout loadingLayout = refreshView.getLoadingLayoutProxy();
				loadingLayout.setLastUpdatedLabel(label); // 设置最后更新的时间戳
				// 触发刷新 异步框架  去 家在数据
				new GetDataTask().execute();
			}
		});
	}

	private void getDataFromServer() {

		HttpUtils utils=new HttpUtils();
		//使用xutils发送请求
		utils.send(HttpMethod.GET, GlobalContants.SERVER_URL, new RequestCallBack<String>() {
			//访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				System.out.println("返回结果："+result);
				parseData(result);
			}
			//访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
	
//	List<News> listsnews = new ArrayList<News>();
	/*
	 *解析数据 
	 */
	protected void parseData(String result) {
		List<MenuCard> lists = JSONTools.getListFromJson(result, MenuCard.class);
		adpater.addAll(lists);
		adpater.notifyDataSetChanged();
		lv.getRefreshableView().setSelection(adpater.getCount()-1);
//		lv.setStackFromBottom(true);  // 显示到最底部    pulltorefresh  不能这样使用
	}
	
	
	
	private class GetDataTask extends AsyncTask<Void, Void, List<MenuCard>> {

		@Override
		protected List<MenuCard> doInBackground(Void... params) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(List<MenuCard> result) {
			adpater.notifyDataSetChanged();  // 通知  适配器  有新数据  马上更新显示条目
			//告诉mPullRefreshListView，刷新完成可以 收回  刷新界面
			lv.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}

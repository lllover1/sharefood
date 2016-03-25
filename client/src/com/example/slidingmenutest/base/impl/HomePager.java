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
		tvTitle.setText("��ҳ");//���ñ���
		btnMenu.setVisibility(View.GONE);//���ز˵���ť
	
		lv=(PullToRefreshListView) mRootView.findViewById(R.id.lv_list_content);
//		lv.setVisibility(View.VISIBLE);
		lv.setMode(Mode.PULL_FROM_END);
		lv.setBackgroundResource(R.drawable.basspage_back);
		initListView(lv);
	
		lv.setAdapter(adpater);
//		// ��FrameLayout�ж�̬��ӿؼ�
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				MenuCard mc = (MenuCard) arg0.getAdapter().getItem(arg2);
				int id = mc.getmId();
				String name = mc.getmName();
				//Toast.makeText(mActivity, "�����"+id, Toast.LENGTH_SHORT).show();
			    
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
	 * ��ʼ��  ����ˢ�µ� ����
	 */
	private void initListView(PullToRefreshListView pullv) {
		ILoadingLayout loadingLayout = pullv.getLoadingLayoutProxy();
		//�޸�ͼƬ������תȦ���Ǹ���
		//Drawable drawable= getResources().getDrawable(R.drawable.progressbar_circle);
		//loadingLayout.setLoadingDrawable(drawable);
		loadingLayout.setPullLabel("����ˢ��");//�����¸�������ʾ
		loadingLayout.setRefreshingLabel("����ˢ��");//����ˢ�µ�ʱ���������ʾ
		loadingLayout.setReleaseLabel("�ɿ�����ˢ��");//������һ�������ʱ�����ʾ����
		//����������ʾ��ʽ
		Typeface typeface  =Typeface.createFromAsset(mActivity.getResources().getAssets(), "fonts/test.ttf");
		loadingLayout.setTextTypeface(typeface); //����������ʽ
		
		/**
		 * ���  ������ ���� 
		 */
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(mActivity);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		pullv.setOnPullEventListener(soundListener);
		// ����ˢ�¼���
		pullv.setOnRefreshListener(new OnRefreshListener<ListView>() {
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(mActivity, System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				//  ���� ˢ�µ� ������Ϣ��չʾ���֣�
				//  ����  �����ǵĲ���  ����  ��  ͼƬ����ʾ���ֵ�
				ILoadingLayout loadingLayout = refreshView.getLoadingLayoutProxy();
				loadingLayout.setLastUpdatedLabel(label); // ���������µ�ʱ���
				// ����ˢ�� �첽���  ȥ ��������
				new GetDataTask().execute();
			}
		});
	}

	private void getDataFromServer() {

		HttpUtils utils=new HttpUtils();
		//ʹ��xutils��������
		utils.send(HttpMethod.GET, GlobalContants.SERVER_URL, new RequestCallBack<String>() {
			//���ʳɹ�
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				System.out.println("���ؽ����"+result);
				parseData(result);
			}
			//����ʧ��
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
	
//	List<News> listsnews = new ArrayList<News>();
	/*
	 *�������� 
	 */
	protected void parseData(String result) {
		List<MenuCard> lists = JSONTools.getListFromJson(result, MenuCard.class);
		adpater.addAll(lists);
		adpater.notifyDataSetChanged();
		lv.getRefreshableView().setSelection(adpater.getCount()-1);
//		lv.setStackFromBottom(true);  // ��ʾ����ײ�    pulltorefresh  ��������ʹ��
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
			adpater.notifyDataSetChanged();  // ֪ͨ  ������  ��������  ���ϸ�����ʾ��Ŀ
			//����mPullRefreshListView��ˢ����ɿ��� �ջ�  ˢ�½���
			lv.onRefreshComplete();
			super.onPostExecute(result);
		}
	}
}

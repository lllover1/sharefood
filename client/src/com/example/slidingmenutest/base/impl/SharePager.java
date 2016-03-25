package com.example.slidingmenutest.base.impl;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.slidingmenutest.R;
import com.example.slidingmenutest.adapter.ShareAdapter;
import com.example.slidingmenutest.base.BasePager;
import com.example.slidingmenutest.bean.shareBean;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.JSONTools;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SharePager extends BasePager {

	

    private View view ;
    private ListView lv_share;
    private List<shareBean> shareList;
    private ShareAdapter adapter;
    
	public SharePager(Activity activity) {
		super(activity);
		shareList = new ArrayList<shareBean>();
	}
	
	
	@Override
	public void initData() {
		tvTitle.setText("����Ȧ");
		if(view == null){
			adapter = new ShareAdapter(mActivity, mBitmapUtils, mConfig,shareList);
			view = View.inflate(mActivity, R.layout.share, null);
			lv_share = (ListView) view.findViewById(R.id.lv_share);
			
			lv_share.setAdapter(adapter);
			// ��FrameLayout�ж�̬��ӿؼ�
			flContent.addView(view);
			
		}
		getDataFromServer();
		

	}
	
	private void getDataFromServer() {
		
		HttpUtils utils=new HttpUtils();
		//ʹ��xutils��������
		utils.send(HttpMethod.POST, GlobalContants.SHARE_URL, new RequestCallBack<String>() {
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
	protected void parseData(String result) {
//		 shareList = JSONTools.getListFromJson(result, shareBean.class);
		 //shareList.clear();
		shareList.addAll(JSONTools.getListFromJson(result, shareBean.class));
		adapter.addAll(shareList);
		adapter.notifyDataSetChanged();
		//((Object) lv_doing).getRefreshableView().setSelection(adapter.getCount()-1);
//		lv.setStackFromBottom(true);  // ��ʾ����ײ�    pulltorefresh  ��������ʹ��
	}
}

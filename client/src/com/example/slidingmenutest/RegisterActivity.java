package com.example.slidingmenutest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.slidingmenutest.R;
import com.example.slidingmenutest.bean.User;
import com.example.slidingmenutest.global.GlobalContants;
import com.example.slidingmenutest.utils.CacheUtils;
import com.example.slidingmenutest.utils.JSONTools;
import com.example.slidingmenutest.utils.PrefUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class RegisterActivity extends Activity{

	private List<String> year = new ArrayList<String>();       
    private Spinner yearSpinner;    
    private ArrayAdapter<String> yearadapter;
    
    private List<String> month = new ArrayList<String>();       
    private Spinner monthSpinner;    
    private ArrayAdapter<String> monthadapter;
    
    private List<String> day = new ArrayList<String>();       
    private Spinner daySpinner;    
    private ArrayAdapter<String> dayadapter;
    
    private Spinner imageSpinner;  
    
    private RadioGroup rSex;
    private User rUser;
    private int rYear,rMonth,rDay;
    
    private Button bRegister;
    private EditText name,nickname,password;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		year.add("1970");year.add("1971");year.add("1972");year.add("1973");year.add("1974");
		year.add("1975");year.add("1976");year.add("1977");year.add("1978");year.add("1979");
		year.add("1980");year.add("1981");year.add("1982");year.add("1983");year.add("1984");
		year.add("1985");year.add("1986");year.add("1987");year.add("1988");year.add("1989");
		year.add("1990");year.add("1991");year.add("1992");year.add("1993");year.add("1994");
		year.add("1995");year.add("1996");year.add("1997");year.add("1998");year.add("1999");
		year.add("2000");year.add("2001");year.add("2002");year.add("2003");year.add("2004");
		year.add("2005");year.add("2006");year.add("2007");year.add("2008");year.add("2009");
		year.add("2010");year.add("2011");year.add("2012");year.add("2013");year.add("2014");
   
        yearSpinner = (Spinner)findViewById(R.id.spinner2);
        yearadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, year);
        yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearadapter);
        
        
        month.add("1");month.add("2");month.add("3");month.add("4");
        month.add("5");month.add("6");month.add("7");month.add("8");
        month.add("9");month.add("10");month.add("11");month.add("12");
        
        monthSpinner = (Spinner)findViewById(R.id.spinner3);
        monthadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, month);
        monthadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthadapter);
        
        
        day.add("1");day.add("2");day.add("3");day.add("4");day.add("5");
        day.add("6");day.add("7");day.add("8");day.add("9");day.add("10");
        day.add("11");day.add("12");day.add("13");day.add("14");day.add("15");
        day.add("16");day.add("17");day.add("18");day.add("19");day.add("20");
        day.add("21");day.add("22");day.add("23");day.add("24");day.add("25");
        day.add("26");day.add("27");day.add("28");day.add("29");day.add("30");
        day.add("31");
        
        daySpinner = (Spinner)findViewById(R.id.spinner4);
        dayadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, day);
        dayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayadapter);
        
        imageSpinner = (Spinner) findViewById(R.id.spinner1);
        SimpleAdapter sap = new SimpleAdapter(this, getData(), R.layout.image_head_items, new String[]{"head"},new int[]{R.id.ib_headimage} );
	    imageSpinner.setAdapter(sap);
	    
	    rSex = (RadioGroup) findViewById(R.id.radioGroup1);
	    rUser = new User();
	    
	    rSex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stubs
				switch(checkedId){
				case R.id.radio0:
					rUser.setuSex(0);
					break;
				case R.id.radio1:
					rUser.setuSex(1);
					break;
				}
			}
		});
	    
	    yearSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				rYear = 1970+arg2;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    monthSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				rMonth = 1+arg2;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	    daySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						rDay = 1+arg2;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
		});
	    
	    imageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				rUser.setuHeadImage(arg2+1);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				rUser.setuHeadImage(1);
			}
		});
	    
	    bRegister = (Button) findViewById(R.id.register_btn);
	    name = (EditText) findViewById(R.id.email);
	    nickname = (EditText) findViewById(R.id.password);
	    password = (EditText) findViewById(R.id.name);
	    bRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = name.getText().toString();
				String userpw = password.getText().toString();
				String usernick = nickname.getText().toString();
				
				readyRegister(username,userpw,usernick);
			}
		});
	}
	
	protected void readyRegister(String username, String userpw, String usernick) {
		if(username==null){
			Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
		}else if(usernick == null){
			Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
		}else if(userpw == null){
			Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
		}else if(rYear==0||rMonth==0||rDay==0){
			Toast.makeText(RegisterActivity.this, "请输入正确的生日", Toast.LENGTH_SHORT).show();
		}else{
			rUser.setuName(username);
			rUser.setuNickname(usernick);
			rUser.setuPassword(userpw);
			String birthday =rYear+"-"+rMonth+"-"+rDay;
			
			sendDatatoServer(rUser,birthday);
			
		}
	}

	public List<Map<String,Object>>getData(){
		List<Map<String,Object>> lists = new ArrayList<Map<String,Object>>();
		Map<String,Object> map;
		int[] data  = new int[]{R.drawable.headimage1,R.drawable.headimage2,
				R.drawable.headimage3,R.drawable.headimage4,R.drawable.headimage5,
				R.drawable.headimage6,R.drawable.headimage7};
		for(int i=0;i<data.length;i++){
			map = new HashMap<String, Object>();
			map.put("head", data[i]);
			lists.add(map);
			
		}
		return lists;
	}
	
    private void sendDatatoServer(final User user,final String birthday) {
		
		HttpUtils utils=new HttpUtils();
		//使用xutils发送请求
		RequestParams params = new RequestParams();
		String temp = JSONTools.getJSONString(user);
		System.out.println("temp"+temp);
		params.addBodyParameter("user", temp);
		params.addBodyParameter("date",birthday);
		
		utils.send(HttpMethod.POST, GlobalContants.REGISTER_URL,params, new RequestCallBack<String>() {
			//访问成功
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result=responseInfo.result;
				System.out.println("返回结果："+result);
				int i = Integer.parseInt(result);
				if(i==1){
					saveUser(user,birthday);
					Intent intent = new Intent(RegisterActivity.this,LoadActivity.class);
				    startActivity(intent);
				    RegisterActivity.this.finish();
				    
				}else{
					Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
				}
//				parseData(result);
			}
			//访问失败
			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
				error.printStackTrace();
			}
		});
	}
    public void saveUser(User user,String date){
		
		PrefUtils.setString(RegisterActivity.this, CacheUtils.U_NICKNAME, user.getuNickname());
		PrefUtils.setString(RegisterActivity.this, CacheUtils.U_PASSWORD, user.getuPassword());
		PrefUtils.setInt(RegisterActivity.this, CacheUtils.U_SEX, user.getuSex());
		PrefUtils.setString(RegisterActivity.this, CacheUtils.U_DATE, date);
		PrefUtils.setString(RegisterActivity.this, CacheUtils.U_NAME, user.getuName());
		PrefUtils.setString(RegisterActivity.this, CacheUtils.U_DISCRPTION, user.getuDiscription());
		PrefUtils.setInt(RegisterActivity.this, CacheUtils.U_HEADIMAGE, user.getuHeadImage());
	}
}

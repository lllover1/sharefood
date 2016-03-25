package com.example.slidingmenutest;

import com.example.slidingmenutest.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingActivity extends Activity {
	
	Button exit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		exit = (Button) findViewById(R.id.bt_exit);
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PrefUtils.exit(SettingActivity.this);
				Intent intent = new Intent(SettingActivity.this,LoadActivity.class);
				startActivity(intent);
				SettingActivity.this.finish();
			}
		});
		
	}
}

package com.example.mylib_test.activity.system;

import com.example.mylib_test.R;

import Android.Zone.Log.ToastUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SystemMainActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_system_test);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;//true�����Ĳ˵�������ʾ
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			
			break;
		case R.id.sb:
			
			break;

		default:
			break;
		}
		ToastUtils.showLong(this, "��ɶ��������");
		return super.onOptionsItemSelected(item);
	}

}

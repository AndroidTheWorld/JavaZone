package com.example.mylib_test.activity.system;

import android.view.View;
import android.widget.Button;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.BaseActvity;

public class ResultBase2 extends BaseActvity {
	Button bt_returnOne;
	@Override
	public void setContentView() {
		setContentView(R.layout.activity_result3);
	}

	@Override
	public void findIDs() {
		bt_returnOne=(Button) findViewById(R.id.bt_returnOne);
		bt_returnOne.setText("������һ������ˢ��");
	}

	@Override
	public void initData() {

	}

	@Override
	public void setListener() {

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_returnOne:
			finishWithBackRefresh();
			break;

		default:
			break;
		}
		super.onClick(v);
	}

	@Override
	public void backRefresh() {
		
	}

}

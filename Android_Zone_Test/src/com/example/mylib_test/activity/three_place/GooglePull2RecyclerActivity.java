package com.example.mylib_test.activity.three_place;

import java.util.LinkedList;

import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.BaseActvity;
import Android.Zone.Abstract_Class.recyclerAdapter.AdapterRecycler_Zone;
import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import Android.Zone.Network.XutilsHttpUtils;

public class GooglePull2RecyclerActivity extends BaseActvity implements OnRefreshListener{

	private SwipeRefreshLayout swipe_container;
	private RecyclerView rv;
	private AdapterRecycler_Zone<String> adapter;
	private static final int tag=1111;
	private  LinkedList<String> data=new LinkedList<String>();
	@Override
	public void setContentView() {
		setContentView(R.layout.a_pull2recycler);
		for (int i = 0; i < 15; i++) {
			data.add("һֱ��ɣ��");
		}
	}

	@Override
	public void findIDs() {
		swipe_container=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
//		swipe_container.setColorScheme(android.R.color.holo_blue_bright,  android.R.color.holo_green_light, 
//        android.R.color.holo_orange_light, android.R.color.holo_red_light);
		swipe_container.setColorScheme(android.R.color.holo_red_light);
		swipe_container.setOnRefreshListener(this);
		
		rv=(RecyclerView)findViewById(R.id.rv);
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setAdapter(adapter=new AdapterRecycler_Zone<String>(this, data) {

			@Override
			public int setLayoutID() {
				return  R.layout.item_recycler;
			}

			@Override
			public void setData(RecyclerHolder_Zone holder, String data,
					int position) {
				TextView id_num=(TextView) holder.findViewById(R.id.id_num);
				id_num.setText(data);
			}
		});
	}

	@Override
	public void initData() {
	}

	@Override
	public void setListener() {
		
	}
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case tag:
		  	swipe_container.setRefreshing(false);
		  	adapter.add(1,"baidu���ؽ��");
			System.out.println("�������ǣ�"+msg.obj);
			break;

		default:
			break;
		}
		return super.handleMessage(msg);
	}

	@Override
	public void onRefresh() {
		adapter.add(1,"baiduˢ��");
		XutilsHttpUtils.getHandler_Json(this, "https://www.baidu.com/", handler, tag);
	}

}

package com.example.mylib_test.activity.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.http.entity.Data;
import com.nostra13.universalimageloader.core.ImageLoader;

import Android.Zone.Abstract_Class.BaseActvity;
import Android.Zone.Abstract_Class.Adapter.Adapter_Zone;
import Android.Zone.Abstract_Class.Adapter.core.ViewHolder_Zone;
import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import Android.Zone.Network.core.BasePullView.OnRefresh2LoadMoreListener;
import Android.Zone.Network.engine.XutilsEngine;
import Android.Zone.Network.pullView.GooglePullView;

public class NetworkPull_TestActivity extends BaseActvity implements OnRefresh2LoadMoreListener{
	final	String UrlPath = "http://182.254.243.254:8080/Test/log";
	private SwipeRefreshLayout swipe_container;
	private ListView rv;
	private XutilsEngine engineGet;
	private static final int GET_TAG=1;
	Map<String,String> params=new HashMap<String,String>();
	private List<String> dataImg=new ArrayList<String>();
	private Adapter_Zone<String> adapter;
	private GooglePullView<String, Data> googlePullView;

	@Override
	public void setContentView() {
		params.put("name", "����");
		setContentView(R.layout.a_network_pull);
		engineGet=new XutilsEngine(this, handler);
		engineGet.send(UrlPath, params, GET_TAG);
		
	}

	@Override
	public void findIDs() {
		swipe_container=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
		rv=(ListView)findViewById(R.id.rv);
	}

	@Override
	public void initData() {

		adapter=new Adapter_Zone<String>(this, dataImg) {

			@Override
			public int setLayoutID() {
				return  R.layout.item_network_pull;
			}

			@Override
			public void setData(ViewHolder_Zone holder, String data,
					int position) {
				ImageView id_num=(ImageView) holder.findViewById(R.id.id_num);
				ImageLoader.getInstance().displayImage(data, id_num);
//				id_num.setText(data);
				
			}

		};
		rv.setAdapter(adapter);
		
		googlePullView=new GooglePullView<String, Data>(swipe_container, rv, adapter, dataImg) {
			@Override
			public List<String> getData(Data entity) {
				return entity.getImgEntity().getImg();
			}
		};
		googlePullView.init2Listener(this);
		engineGet.relateList(googlePullView);
	}

	@Override
	public void setListener() {
		
	}

	@Override
	public void loadMore(int firstVisibleItem, int visibleItemCount,
			int totalItemCount) {
		
	}

	@Override
	public void onRefresh() {
		
	}
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case GET_TAG:
//			System.out.println("GET_TAG:"+msg.obj);
			System.err.println("size:"+dataImg.size());
//			adapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
		return super.handleMessage(msg);
	}

}

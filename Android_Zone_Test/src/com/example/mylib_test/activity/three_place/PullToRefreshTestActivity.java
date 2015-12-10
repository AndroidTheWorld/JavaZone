package com.example.mylib_test.activity.three_place;

import java.util.LinkedList;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.three_place.adapter.PullToAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import Android.Zone.Log.ToastUtils;
import Android.Zone.Utils.thirdPlace.RefreshUtils;
import Android.Zone.Utils.thirdPlace.RefreshUtils.PullToRefreshListener;
import Android.Zone.Wifi.NetManager;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.View;

public class PullToRefreshTestActivity extends Activity implements Callback,PullToRefreshListener{
	protected static final int DownToRefresh = 0, Refresh = 1;
	private PullToRefreshListView list;
	private Handler handler=new Handler(this);
	private PullToAdapter adapter;
	private static LinkedList<String> data=new LinkedList<String>();
	static{
		for (int i = 0; i < 20; i++) {
			data.add("һֱ��ɣ��");
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_threethrid_pull);
		list=(PullToRefreshListView) findViewById(R.id.list);
		adapter=new PullToAdapter(this, data);
		RefreshUtils.initPullToRefreshListView(list, adapter, this);
	}
//	@Override
//	public boolean handleMessage(Message msg) {
//		switch (msg.what) {
//		case DownToRefresh:
//			data.add("�������ع���");
//			break;
//		case Refresh:
//			data.add("����ˢ���أ�");
//			break;
//
//		default:
//			break;
//		}
//		adapter.notifyDataSetChanged();
//		list.onRefreshComplete();
//		return false;
//	}
	@Override
	public boolean handleMessage(Message msg) {
		list.onRefreshComplete();
		return false;
	}
	private class GetDataTask extends AsyncTask<Void, Void, String> {
		// ��̨������
		@Override
		protected String doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			String str="Added after refresh...I add";
			return str;
		}

		//�����Ƕ�ˢ�µ���Ӧ����������addFirst������addLast()�������¼ӵ����ݼӵ�LISTView��
		//����AsyncTask��ԭ��onPostExecute���result��ֵ����doInBackground()�ķ���ֵ
		@Override
		protected void onPostExecute(String result) {
			//��ͷ��������������
			 data.addFirst(result);
			//֪ͨ�������ݼ��Ѿ��ı䣬�������֪ͨ����ô������ˢ��mListItems�ļ���
			adapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			list.onRefreshComplete();
			super.onPostExecute(result);//����Ǳ��еģ�AsyncTask�涨�ĸ�ʽ
		}
	}
	@Override
	public void loadMore(View v) {
		//������Ҫ��װ����һ��handler �����������handler
		ToastUtils.showLong(this, "loadMore");
		HttpUtils http = new HttpUtils();
		//���ó���ʱ��
		http.configCurrentHttpCacheExpiry(1000 * 10);
		http.send(HttpRequest.HttpMethod.GET, "http://www.baidu.com",
		// params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// resultText.setText("response:" +
						// responseInfo.result);
						data.add("onSuccess");
						adapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						data.add("onFailure");
						adapter.notifyDataSetChanged();
					}
				});
	}
	@Override
	public void onRefresh(View v) {
		if (!NetManager.haveNetWork(this)) {
			handler.sendEmptyMessage(1);
			return ;
		}
		String label = DateUtils.formatDateTime(getApplicationContext(),
				System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
						| DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_ABBREV_ALL);
		// Update the LastUpdatedLabel
		list.getLoadingLayoutProxy().setLastUpdatedLabel(label);
		// Do work to refresh the list here.
		new GetDataTask().execute();
	}

}

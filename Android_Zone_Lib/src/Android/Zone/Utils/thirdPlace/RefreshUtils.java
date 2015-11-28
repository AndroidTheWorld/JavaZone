package Android.Zone.Utils.thirdPlace;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
/**
 * �����Ҫ���ص�ʱ����ʾfootview��������listһ�����footview Ҳ���ԡ���ӡ�headerView �빫˾�õ�һ����ͷ����listһ��ˢ��
 * @author 123
 */
public class RefreshUtils {
	/**
	 * ������ͷ������ ���û��ͷ �򲻿�
	 * @param listView
	 * @param adapter
	 * @param refreshListener
	 * @param loadMoreListener
	 */
	public static  void initPullToRefreshListView(final PullToRefreshListView listView, BaseAdapter adapter,final PullToRefreshListener listener) {
		listView.setAdapter(adapter);
		listView.setMode(Mode.PULL_FROM_START);
		if(listener==null)
			throw new IllegalArgumentException("arg:PullToRefreshListener maybe��null!");
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
					listener.onRefresh(listView);
			}
		});
		listView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				listener.loadMore(listView);
			}
		});
	}
	public interface PullToRefreshListener{
		public abstract void loadMore(View v);
		public abstract void onRefresh(View v);
		
	}
}

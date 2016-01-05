package Android.Zone.Network.core;

import java.util.List;

import android.view.View;
//һ��listView����װһ��T
public abstract class BaseListView<T> {
	public  T listView;
	public static final String LIMIT="limit";
	public static final String OFFSET="offset";
	private  int limit=10;
	private int pageNumber=0;
	public <E> BaseListView(T listView,List<E> data) {
		this.listView=listView;
	}
	public abstract void initListener();
	//��v���ж����Ǹ�list ˢ�µ� ��  Ҳ�ɲ��Ӱ�
	public  void loadMore(View v){
		pageNumber++;
	};
	public  void onRefresh(View v){
		 pageNumber=0;
	};
	public abstract void onRefreshComplete();
	public abstract void loadMoreComplete();
	
	public int getPageNumber() {
		return pageNumber;
	}
	public  int getLimit() {
		return limit;
	}
}

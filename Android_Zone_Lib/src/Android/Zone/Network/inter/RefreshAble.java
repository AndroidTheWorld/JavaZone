package Android.Zone.Network.inter;

import android.view.View;

public interface RefreshAble {
	//���ˢ�¼��� ����ظ�������� ע��
	public abstract void init();
	public abstract void loadMore(View v);
	public abstract void onRefresh(View v);
	public abstract void onRefreshComplete();
	public abstract void loadMoreComplete();
}

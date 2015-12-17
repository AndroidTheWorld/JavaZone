package Android.Zone.Abstract_Class.recyclerAdapter;

import java.util.List;

import Android.Zone.Abstract_Class.recyclerAdapter.core.BaseRecyclerAdapter;
import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
/**
 * /**
 * �಼�ֶ��� ��������bug��
 * Created by Zone on 2015/12/16.
 */
public abstract class AdapterRecycler_MuliLayout_Zone<T> extends BaseRecyclerAdapter<T>{
	private int[] layout_ids;
	public AdapterRecycler_MuliLayout_Zone(Context context,List<T> data) {
		super(context, data);
		this.layout_ids=setLayoutIDs();
	}
	/**
	 * ���ز���id
	 */
	@Override
	public int getItemViewType(int position) {
		int temp=getItemViewType_Zone(position, layout_ids);
		return temp;
	}
	
	@Override
	public RecyclerHolder_Zone onCreateViewHolder(ViewGroup parent,  int viewType) {
		return new RecyclerHolder_Zone(LayoutInflater.from(context).inflate(viewType, parent, false));
	}
	public abstract  int[] setLayoutIDs();
	/**
	 * 
	 * @param position
	 * @param layoutArr 
	 * @return  ���ز���id
	 */
	public abstract int getItemViewType_Zone(int position, int[] layoutArr);
	
	
}

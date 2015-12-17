package Android.Zone.Abstract_Class.recycler;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class Adapter_Zone_Recycler<T> extends Adapter<MyRecyclerHolder>{
	public List<T> data;
	private int layout_id;
	private Context context;
	public Adapter_Zone_Recycler(Context context,List<T> data,int layout_id) {
		this.context = context;
		this.data = data;
		this.layout_id=layout_id;
	}
	@Override
	public int getItemCount() {
		return data.size();
	}

	@Override
	public void onBindViewHolder( MyRecyclerHolder holder, int arg1) {
		int pos = arg1;
		setData(holder.map, data.get(pos), pos, holder);
	}

	@Override
	public MyRecyclerHolder onCreateViewHolder(ViewGroup parent,  int viewType) {
		return new MyRecyclerHolder(LayoutInflater.from(context).inflate(layout_id, parent, false));
	}
	/**
	 * holder.getPosition() �ڼ����к�ʹ ��ʹ�Ƕ����������
	 * @param viewMap   װ��convertView����ͼ �ʴ���ȡ��Ȼ��ֵ����
	 * @param data     ��itemIndex�����ݼ��� �е�item...
	 * @param position ��λ��  �ڼ��� �ص����������в�����   ��Ϊλ�ñ��� �����л�û�䡣����
	 * @param holder holder.getPosition() �ڼ����к�ʹ ��ʹ�Ƕ����������
	 */
	public abstract  void setData(Map<Integer, View> viewMap, T data, int position, MyRecyclerHolder holder); //ע�����ֻ�����������������û�о���ʵ�֡�

}

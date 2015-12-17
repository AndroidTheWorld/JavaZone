package Android.Zone.Abstract_Class.recycler;

import java.util.List;
import java.util.Map;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
/**
 * Created by Zone on 2015/12/16.
 */
public abstract class Adapter_Zone_Recycler<T> extends Adapter<RecyclerHolder_Zone>{
	public List<T> data;
	private int layout_id;
	private Context context;
	private OnItemClickListener listener;
	public Adapter_Zone_Recycler(Context context,List<T> data) {
		this.context = context;
		this.data = data;
		this.layout_id=setLayoutID();
	}
	@Override
	public int getItemCount() {
		return data.size();
	}

	@Override
	public void onBindViewHolder( final RecyclerHolder_Zone holder, int position) {
		holder.itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) 
					listener.onItemClick(holder.itemView, holder.getPosition(),holder);
			}
		});
		
		int pos = position;
		setData(holder.map, data.get(pos), pos, holder);
	}
	
	@Override
	public RecyclerHolder_Zone onCreateViewHolder(ViewGroup parent,  int viewType) {
		return new RecyclerHolder_Zone(LayoutInflater.from(context).inflate(layout_id, parent, false));
	}
	
	public void setOnItemClickListener(OnItemClickListener listener){
		this.listener=listener;
	}
	public void removeOnItemClickListener(){
		this.listener=null;
	}
	public interface OnItemClickListener{
		   void onItemClick(View convertView, int position, RecyclerHolder_Zone holder);
	}
	
	
	public abstract  int setLayoutID();
	/**
	 * holder.getPosition() �ڼ����к�ʹ ��ʹ�Ƕ����������
	 * @param viewMap   װ��convertView����ͼ �ʴ���ȡ��Ȼ��ֵ����
	 * @param data     ��itemIndex�����ݼ��� �е�item...
	 * @param position ��λ��  �ڼ��� �ص����������в�����   ��Ϊλ�ñ��� �����л�û�䡣����
	 * @param holder holder.getPosition() �ڼ����к�ʹ ��ʹ�Ƕ����������
	 */
	public abstract  void setData(Map<Integer, View> viewMap, T data, int position, RecyclerHolder_Zone holder); //ע�����ֻ�����������������û�о���ʵ�֡�
	

}

package Android.Zone.Abstract_Class.recyclerAdapter.core;

import java.util.List;

import Android.Zone.Abstract_Class.recyclerAdapter.I.OnItemClickListener;
import Android.Zone.Abstract_Class.recyclerAdapter.I.OnItemLongClickListener;
import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
/**
 * Created by Zone on 2015/12/16.
 */
public abstract class BaseRecyclerAdapter<T> extends Adapter<RecyclerHolder_Zone> {
	public List<T> data;
	public Context context;
	private OnItemClickListener clickListener;
	private OnItemLongClickListener longClicklistener;
	private boolean openAni=false;
	
	public BaseRecyclerAdapter(Context context,List<T> data) {
		this.context = context;
		this.data = data;
	}
	@Override
	public int getItemCount() {
		return data.size();
	}

	@Override
	public void onBindViewHolder(final RecyclerHolder_Zone holder, int position) {
		holder.itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (clickListener != null) 
					clickListener.onItemClick(holder.itemView, holder.getPosition(),holder);
				System.out.println("OnItemClick ok");
			}
		});
		holder.itemView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				if (longClicklistener != null){
					longClicklistener.onItemLongClick(holder.itemView, holder.getPosition(),holder);	
					System.out.println("OnItemLongClick ok");
					return true;
				} 
				return false;
			
			}
		});
		
		int pos = position;
		setData(holder, data.get(pos), pos);
	}
	public void setOnItemClickListener(OnItemClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public void removeOnItemClickListener() {
		this.clickListener = null;
	}

	public void setOnItemLongClickListener(
			OnItemLongClickListener longClicklistener) {
		this.longClicklistener = longClicklistener;
	}

	public void removeOnItemLongClickListener() {
		this.longClicklistener = null;
	}
	/**
	 * holder.getPosition() �ڻص������к�ʹ �ж���ȷ��λ��
	 * holder.findviewByid�����ҵ��ؼ�
	 * @param holder holder.getPosition() �ڼ����к�ʹ ��ʹ�Ƕ����������  findviewByid�����ҵ��ؼ�
	 * @param data     ��itemIndex�����ݼ��� �е�item...
	 * @param position ��λ��  �ڼ��� �ص����������в�����   ��Ϊλ�ñ��� �����л�û�䡣����
	 */
	public abstract  void setData(RecyclerHolder_Zone holder, T data, int position); //ע�����ֻ�����������������û�о���ʵ�֡�

	

	public void openAni(){
		openAni=true;
	}
	public void closeAni(){
		openAni=false;
	}
    public void add(T elem) {
    	data.add(elem);
    	if (openAni) 
    		notifyItemInserted(data.size());
		else
			notifyDataSetChanged();
    }
    public void add(int index,T elem) {
    	data.add(index,elem);
    	if (openAni) 
    		notifyItemInserted(index);
    	else
    		notifyDataSetChanged();
    }
    public void addAll(List<T> elem) {
    	int index=data.size();
    	data.addAll(elem);
    	if (openAni) 
			notifyItemRangeInserted(index, elem.size());
		else
			notifyDataSetChanged();
    }
    public void changePos(int fromPosition,int toPosition){
    	T temp=data.get(fromPosition);
    	data.remove(fromPosition);
    	data.set(toPosition, temp);
    	if (openAni) 
    		notifyItemMoved(fromPosition, toPosition);
		else
			notifyDataSetChanged();
    }
    public void set(int index, T elem) {
    	data.set(index, elem);
    	if (openAni) 
    		notifyItemChanged(index);
		else
			notifyDataSetChanged();
    }
    public void remove(T elem) {
    	int index=data.indexOf(elem);
    	data.remove(elem);
        if (openAni) 
    		notifyItemRemoved(index);
		else
			notifyDataSetChanged();
    }
    public void remove(int index) {
    	data.remove(index);
        if (openAni) 
    		notifyItemRemoved(index);
		else
			notifyDataSetChanged();
    }

    /**
     * Clear data list
     */
    public void clear() {
    	int count=data.size();
    	data.clear();
    	 if (openAni) 
     		notifyItemRangeRemoved(0, count);
 		else
 			notifyDataSetChanged();
    }
}

package com.example.mylib_test.activity.three_place.recyclerAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.recyclerAdapter.AdapterRecycler_Zone;
import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import android.content.Context;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * �Ա�list  
 * 1 ��remove remove��  Ȼ�� 
 * 2.Ȼ�� �ж���ӵ�
 * @author Zone
 *
 */
public class RecyclerBaseAdapterTest extends AdapterRecycler_Zone<String> {
	List<String> datas;
	List<String> backUpDatas=new ArrayList<String>();

	public RecyclerBaseAdapterTest(Context context, List<String> data) {
		super(context, data);
		datas = data;
	}
	
	public void addData(String str) {
		openAni();
		add(1, str);
	}

	public void saveDataForAni(List<String> backUpDatas){
		this.backUpDatas.clear();
		for (String item : backUpDatas) {
			this.backUpDatas.add(item);
		}
	}
	public void notifyDataSetChangedWithAni(){
		List<Integer> removeList=new ArrayList<Integer>();
		for (int i = 0; i < backUpDatas.size(); i++) {
			if(!data.contains(backUpDatas.get(i)))
				removeList.add(i);
		}
		//���𼴿�
//		for (int i = removeList.size()-1;i>=0; i--) {
//			//���� remove��ȥ���� �ڶԱȾ���add����
//			notifyItemRemoved(removeList.get(i));
//			backUpDatas.remove(removeList.get(i));
//		}
		for (int i = 0; i < removeList.size(); i++) {
			//���� remove��ȥ���� �ڶԱȾ���add����
			notifyItemRemoved(removeList.get(i));
			backUpDatas.remove(removeList.get(i));
		}
		removeList.clear();
		for (int i = 0; i < data.size(); i++) {
			if(!backUpDatas.contains(data.get(i)))
				removeList.add(i);	
		}
		System.out.println();
		//�������Ҫ����
		for (int i = 0; i < removeList.size(); i++) {
			notifyItemInserted(removeList.get(i));	
		}
//		for (int i = removeList.size()-1;i>=0; i--) {
//			notifyItemInserted(removeList.get(i));	
//		}
	}
//	public boolean dstContainSrcIndex(int index,List<String> src,List<String> dst){
//		//ԭ����  ��index�Ƿ��� dst��index
//		boolean result=false;
//		int max=sr
//		switch (index) {
//		case 0:
//			
//			break;
//		case size:
//			
//			break;
//
//		default:
//			break;
//		}
//		return result;
//	}
	public void addAllData(String str) {
		openAni();
		changePos(1, 3);
	}

	public void deleteData() {
		openAni();
		remove(1);
		
	}
	@Override
	public int setLayoutID() {
		return  R.layout.item_recycler;
	}

	@Override
	public void setData(RecyclerHolder_Zone holder, String data, int position) {
		TextView tem = (TextView) holder.findViewById(R.id.id_num);
		LinearLayout ll = (LinearLayout) holder.findViewById(R.id.ll_item);
//		ll.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				System.out.println("haha position:" + holder.getPosition()+"\t ���� postion"+position);
//			}
//		});
		tem.setText(data);
	}

}

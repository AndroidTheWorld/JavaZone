package com.example.mylib_test.activity.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.mylib_test.R;
import Android.Zone.Abstract_Class.Adapter_Zone;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class SoftTestActivity extends Activity{
	private List<Integer> list=new ArrayList<Integer>();
	Integer[] a=new Integer[]{ R.drawable.t0, R.drawable.t0,R.drawable.t0,R.drawable.t0, R.drawable.t0};
//	,R.drawable.t5, R.drawable.t6,R.drawable.t7, R.drawable.t8};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView iv = new ListView(this);
		setContentView(iv);
		for (int i = 0; i < 15; i++) {
			list.add( R.drawable.t0);
		}
		iv.setAdapter(new AdapterMecha(this, list, R.layout.imageitem));
//		for (int i = 0; i <=8; i++) {
//			list.add(Bitmap.createBitmap(temp,0,0,temp.getWidth(),temp.getHeight(),null,true));
//		}
		System.out.println("...");
	}
	public class Adapter extends Adapter_Zone<Integer> {

		public Adapter(Context context, List<Integer> data, int layout_id) {
			super(context, data, layout_id);
		}

		@Override
		public void setData(Map<Integer, View> viewMap, Integer data,
				int position) {
			ImageView ivD=(ImageView) viewMap.get(R.id.iv);
			ivD.setImageBitmap(BitmapFactory.decodeResource(getResources(),data));
		}
		
	}
	public class AdapterMecha extends Adapter_Zone<Integer> {
		
		public AdapterMecha(Context context, List<Integer> data, int layout_id) {
			super(context, data, layout_id);
		}
		
		@Override
		public void setData(Map<Integer, View> viewMap, Integer data,
				int position) {
			ImageView ivD=(ImageView) viewMap.get(R.id.iv);
			ivD.setImageBitmap(BitmapFactory.decodeResource(getResources(),data));
		}
		
	}
}

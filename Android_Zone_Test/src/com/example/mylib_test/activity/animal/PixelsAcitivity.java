package com.example.mylib_test.activity.animal;


import com.example.mylib_test.R;
import com.example.mylib_test.app.Apps;
import com.example.mylib_test.app.Constant;
import Android.Zone.Log.Logger_Zone;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class PixelsAcitivity extends Activity {
	private ImageView iv;
	private Bitmap bt;
	private int[] pixels;
	private int[] newPixels;
	private Bitmap newBt;
	private Logger_Zone logger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		logger=new Logger_Zone(PixelsAcitivity.class, Constant.Logger_Config);
		logger.logLocation("��λ����");
		logger.log("��ĳɲ���");
		iv=new ImageView(this);
		setContentView(iv);
		
		bt=BitmapFactory.decodeResource(getResources(), R.drawable.abcd);
		//Androidϵͳ�������޸�ԭͼ
		newBt=Bitmap.createBitmap(bt.getWidth(), bt.getHeight(), Config.ARGB_8888);
		
		pixels=new int[bt.getWidth()*bt.getHeight()];
		//�õ����е����ص�
		bt.getPixels(pixels, 0, bt.getWidth(), 0, 0, bt.getWidth(), bt.getHeight());
		
		System.out.println(" pixels.length:"+ pixels.length);
		newPixels=new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			//�õ�ÿ�����ص������ֵ
			int color = pixels[i];
			//�õ���Ӧ��ARGBֵ
			int r = Color.red(color);
			int g = Color.green(color);
			int b = Color.blue(color)*0;//ȥ����ɫ
			int a = Color.alpha(color);
			//ͨ��argb�ϳ� ����ֵ
			newPixels[i]=Color.argb(a, r, g, b);
		}
		newBt.setPixels(newPixels, 0, bt.getWidth(), 0, 0, bt.getWidth(), bt.getHeight());
		iv.setImageBitmap(newBt);
	}
}

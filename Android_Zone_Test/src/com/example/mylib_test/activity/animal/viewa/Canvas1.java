package com.example.mylib_test.activity.animal.viewa;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Canvas1 extends View {
	Paint paint=new Paint();
	public Canvas1(Context context) {
		super(context);
		Canvas ca = new Canvas();
		 
		System.out.println("new��"+ca.getSaveCount());
	}
	@Override
	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
		System.out.println("��ʼ��"+canvas.getSaveCount());
		paint.setColor(Color.RED);
		paint.setDither(true);
		paint.setAntiAlias(true);
		canvas.drawCircle(100, 100, 100, paint);
		canvas.save();
		System.out.println("save��"+canvas.getSaveCount());
		canvas.saveLayerAlpha(0, 0, getWidth(), getHeight(), 100, Canvas.ALL_SAVE_FLAG);
		System.out.println("saveLayerAlpha��"+canvas.getSaveCount());
		canvas.translate(100, 100);
		paint.setColor(Color.BLUE);
		canvas.drawCircle(100, 100, 100, paint);
		canvas.restoreToCount(1);
		System.out.println("restore��"+canvas.getSaveCount());
	}
}

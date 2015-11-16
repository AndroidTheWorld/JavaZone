package com.example.mylib_test.activity.touch.view;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ScrollerView extends LinearLayout {

	private static final String TAG = "CustomView";

	private Scroller mScroller;
	private GestureDetector mGestureDetector;
	
	public ScrollerView(Context context) {
		this(context, null);
	}
	
	public ScrollerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(true);
		setLongClickable(true);
		mScroller = new Scroller(context);
		mGestureDetector = new GestureDetector(context, new CustomGestureListener());
	}
	//���ô˷���������Ŀ��λ��
	public void smoothScrollTo(int fx, int fy) {
		int dx = fx - mScroller.getFinalX();
		int dy = fy - mScroller.getFinalY();
		smoothScrollBy(dx, dy);
	}

	//���ô˷������ù��������ƫ��
	public void smoothScrollBy(int dx, int dy) {

		//����mScroller�Ĺ���ƫ����
		mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
		invalidate();//����������invalidate()���ܱ�֤computeScroll()�ᱻ���ã�����һ����ˢ�½��棬����������Ч��
	}
	
	@Override
	public void computeScroll() {
		//���ж�mScroller�����Ƿ����
		if (mScroller.computeScrollOffset()) {
			//�������View��scrollTo()���ʵ�ʵĹ���
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			//������ø÷���������һ���ܿ�������Ч��
			postInvalidate();
		}
		super.computeScroll();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP :
			Log.i(TAG, "get Sy" + getScrollY());
			smoothScrollTo(0, 0);
			break;
		default:
			return mGestureDetector.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}
	
	class CustomGestureListener implements GestureDetector.OnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return true;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			int dis = (int)((distanceY-0.5)/2);
			Log.i(TAG, dis + ".");
			smoothScrollBy(0, dis);
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return false;
		}
		
	}
}
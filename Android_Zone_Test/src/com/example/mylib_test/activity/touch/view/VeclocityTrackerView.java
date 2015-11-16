package com.example.mylib_test.activity.touch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class VeclocityTrackerView extends View {
	private VelocityTracker mVelocityTracker;// ��������

	public VeclocityTrackerView(Context context) {
		super(context);
	}

	public VeclocityTrackerView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public VeclocityTrackerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();// ���VelocityTracker��ʵ��
		}
		mVelocityTracker.addMovement(event);// ���¼����뵽VelocityTracker��ʵ����
		final int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
//			 (float) 4.01: velocityTraker-0.30938187 		��1���롡Ϊ��λ�����ٶ�Ϊ��-0.30938187px/1ms
//				1000: 	velocityTraker:-309.38187  		 	��1�롡Ϊ��λ�����ٶ�Ϊ��-309px/1ms
			// 1000 provides pixels per second
			mVelocityTracker.computeCurrentVelocity(1, (float) 4.01); // ����maxVelocityֵΪ0.1ʱ�����ʴ���0.01ʱ����ʾ�����ʶ���0.01,����С��0.01ʱ����ʾ����
			Log.i("1, (float) 4.01", "velocityTraker" + mVelocityTracker.getXVelocity());
			mVelocityTracker.computeCurrentVelocity(1000); // ����units��ֵΪ1000����˼Ϊһ��ʱ�����˶��˶��ٸ�����
			Log.i("1000", "velocityTraker:" + mVelocityTracker.getXVelocity());
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			if (null != mVelocityTracker) {
				mVelocityTracker.clear();
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			break;

		default:
			break;
		}
		return true;
	}
}

package com.example.mylib_test.activity.animal.viewa;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

public class CustomAnim extends Animation {

    private int mCenterWidth;
    private int mCenterHeight;
    private Camera mCamera = new Camera();
    private float mRotateY = 0.0f;

    @Override
    public void initialize(int width,int height, int parentWidth, int parentHeight) {

        super.initialize(width, height, parentWidth, parentHeight);
        // ����Ĭ��ʱ��
        setDuration(2000);
        // ������������״̬
        setFillAfter(true);
        // ����Ĭ�ϲ�ֵ��
        setInterpolator(new BounceInterpolator());
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }

    // ��¶�ӿ�-������ת�Ƕ�
    public void setRotateX(float rotateY) {
        mRotateY = rotateY;
    }

    @Override
    protected void applyTransformation(
            float interpolatedTime,
            Transformation t) {
        final Matrix matrix = t.getMatrix();
        mCamera.save();
        // ʹ��Camera������ת�ĽǶ�
        mCamera.rotateX(mRotateY * interpolatedTime);
        // ����ת�任���õ�matrix��
        mCamera.getMatrix(matrix);
        mCamera.restore();
        // ͨ��pre�������þ�������ǰ��ƫ�������ı���ת����
//        matrix.preTranslate(mCenterWidth, mCenterHeight);
//        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }
}
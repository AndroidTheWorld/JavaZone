package view;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

//��view��ViewGroup������ onDraw�����Զ���
public abstract class ViewGroup_Standard extends LinearLayout {
	private int mMeasureWith, mMeasureHeight, offsetX, offsetY;
	private List<ViewAttr> childList = new ArrayList<ViewAttr>();
	private void onMeasureReset() {
		childList.clear();
		mMeasureWith = 0;
		mMeasureHeight = 0;
		offsetX=0; 
		offsetY=0;
	};
	public  class ViewAttr {
		public View view;// �ռ�view
		public Point location=new Point();// view�ڲ��������Ͻǵĵ�

		//�����Ƕ�View������  ��support
		public void layoutSupporVisibilty() {
			if (view.getVisibility()==View.VISIBLE) {
				int realX = location.x + offsetX;
				int realY = location.y + offsetY;
				view.layout(realX, realY, realX + view.getMeasuredWidth(),
						realY + view.getMeasuredHeight());
			}
		}
	}

	public ViewGroup_Standard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ViewGroup_Standard(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ViewGroup_Standard(Context context) {
		this(context, null);
	}

	/**
	 * ͨ���������ӵĴ�С �����������Լ���Ҫ��� ��������� wrap_content ����Բ�д�˷���,ֱ���ڲ�����д�߼�������
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		onMeasureReset();

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		//����widthSize heightSize �����ֵ
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getMode(heightMeasureSpec);

		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			ViewAttr viewAttr=new ViewAttr();
			View view = getChildAt(i);
			viewAttr.view=view;
			if (view.getVisibility()!=View.GONE) {
				viewAttr.location=getViewLocation(view);
			}
			childList.add(viewAttr);
		}
		
		Point sizePoint = makeSureSize();
		mMeasureWith=sizePoint.x;
		mMeasureHeight=sizePoint.y;
		
		//����֮ǰȷ��  mMeasureWith  mMeasureHeight��ֵ
		supportPadding(widthMode, heightMode,widthSize,heightSize);
		/**
		 * �����wrap_content����Ϊ���Ǽ����ֵ ����ֱ������Ϊ�����������ֵ
		 */
		setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize
				: mMeasureWith, heightMode == MeasureSpec.EXACTLY ? heightSize
				: mMeasureHeight);
	}

	public abstract Point makeSureSize();

	/**
	 * ��ε������λ�� Ȼ����viewAttr.location ��ֵ  ��ҵ���߼�   �ǵ�֧��margin 
	 * @param view 
	 * @return  ���ص�������ǲ�����padding��ҵ���߼�  ��Ϊpadding�Ѿ��ڲ�֧����
	 */
	public abstract Point getViewLocation(View view);

	private void supportPadding(int widthMode, int heightMode, int widthSize, int heightSize) {
		if (widthMode == MeasureSpec.EXACTLY) {
			offsetX = getPaddingLeft();
		} else {
			mMeasureWith += getPaddingLeft() + getPaddingRight();
			//������widthSize<0 ������޽��� view�Ŀ����ֵΪwidthSize 
			if(widthSize>0&&mMeasureWith>widthSize)
				mMeasureWith=widthSize;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			offsetY = getPaddingTop();
		} else {
			mMeasureHeight += getPaddingTop() + getPaddingBottom();
			if(heightSize>0&&mMeasureHeight>heightSize)
				mMeasureHeight=heightSize;
		}
	}

	/**
	 * ֪���Լ����� Ȼ������Ӳ���
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for (ViewAttr item : childList) {
			item.layoutSupporVisibilty();
		}
	}

}

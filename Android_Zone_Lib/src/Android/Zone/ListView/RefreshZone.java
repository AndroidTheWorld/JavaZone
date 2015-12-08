package Android.Zone.ListView;

import com.example.notperfectlib.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.TextView;

public class RefreshZone extends ListView implements OnScrollListener {
	static final int NONE=0,PULL=1,RELESE=2,REFLASHING=3;
	private View header=null;
	private int headerHeight=0;
	private boolean haveHeight=false;
	private int length=0;
	private int firstVisibleItem;
	private int scrollState;
	private float startY=0F;
	private int Pull_State=NONE;
	private boolean isRefresh=false;
	private TextView tip;
	public RefreshZone(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RefreshZone(Context context) {
		this(context, null);
	}

	public RefreshZone(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		header=LayoutInflater.from(context).inflate(R.layout.header_layout, null);
		tip=(TextView) header.findViewById(R.id.tip);
		addHeaderView(header);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		this.scrollState=scrollState;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.firstVisibleItem=firstVisibleItem;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		System.err.println("��ߣ�"+header.getHeight());
		if(header.getHeight()!=0&&!haveHeight){
			System.err.println("����");
			headerHeight=header.getHeight();
			haveHeight=true;
			topPadding(-header.getHeight());
		}
	
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(firstVisibleItem==0&&Pull_State==NONE){
				startY=ev.getY();
				isRefresh=true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			pull(ev);
			break;
		case MotionEvent.ACTION_UP:
			refreshAdjustPosition(ev);
			isRefresh=false;
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	private void pull(MotionEvent ev) {
		if (isRefresh) {
			length=(int)(ev.getY() - startY);
			switch (Pull_State) {
			case NONE:
				if (ev.getY() - startY> 0) {
					Pull_State = PULL;
					//TODO �л�PULL״̬
				}
				break;
			case PULL:
				if (ev.getY() - startY >= (headerHeight + 30)) {
					Pull_State = RELESE;
					//TODO �л�RELESE����
				}
				if(ev.getY() - startY <=0){
					Pull_State = NONE;
					//TODO �л�NONE״̬
				}
				break;
			case RELESE:
				if (ev.getY() - startY < (headerHeight + 30)) {
					Pull_State = PULL;
					//TODO �л�PULL����	
				}
				break;

			default:
				break;
			}
			stateAni();
		}
	}

	private void stateAni() {
		switch (Pull_State) {
		case NONE:
			topPadding(-headerHeight);
			break;
		case PULL:
			topPadding(length-headerHeight);
			tip.setText("��������ˢ�£�");
			break;
		case RELESE:
			topPadding(length-headerHeight);
			//TODO ��������
			tip.setText("�ͷ�ˢ�£�");
			break;
		case REFLASHING:
			topPadding(0);
			tip.setText("����ˢ�£�");
			postDelayed(new Runnable() {
				@Override
				public void run() {
					refreshCompele();
				}
			},1000);
			break;

		default:
			break;
		}
	}

	private void refreshAdjustPosition(MotionEvent ev) {
		length=(int)(ev.getY() - startY);
		if(Pull_State==RELESE){
		
			//TODO �����ָ���view��λ��
			
			//TODO Ȼ��������  ��״̬Ū�����
			Pull_State=REFLASHING;
			stateAni();
			
		}
		if(Pull_State==PULL){
			Pull_State = NONE;
			//TODO �л�NONE״̬
			stateAni();
		}
	}
	public void refreshCompele(){
		Pull_State = NONE;
		stateAni();
	}

	/**
	 * ����header ���� �ϱ߾ࣻ
	 * 
	 * @param topPadding
	 */
	private void topPadding(int topPadding) {
		header.setPadding(header.getPaddingLeft(), topPadding,header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();
	}

}

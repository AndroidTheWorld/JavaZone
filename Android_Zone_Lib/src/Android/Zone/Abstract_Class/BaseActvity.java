package Android.Zone.Abstract_Class;
import java.util.ArrayList;
import java.util.List;
import com.nostra13.universalimageloader.core.ImageLoader;
import Android.Zone.Constant;
import Android.Zone.Log.Logger_Zone;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseActvity extends FragmentActivity implements Callback,OnClickListener{
	
	protected ImageLoader imageLoader;
	private Logger_Zone logger;
	public static List<Activity> activitys=new ArrayList<Activity>();
	@Override
	protected void onCreate(Bundle arg0) {
		activitys.add(this);
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
		logger.log("BaseActvity  onCreate");
		super.onCreate(arg0);
		imageLoader = ImageLoader.getInstance();
		logger.log("BaseActvity  setContentView");
		setContentView();
		findIDs();
		initData();
		setListener();
	}


	/**
	 * �������಼�ֶ���
	 */
	public abstract void setContentView();

	/**
	 * ������ҵ�ǰ��������id
	 */
	public abstract void findIDs();

	/**
	 * �����ʼ������
	 */
	public abstract void initData();

	/**
	 * ���������¼�����
	 */
	public abstract void setListener();
	@Override
	public boolean handleMessage(Message msg) {
		return false;
	}
	@Override
	public void onClick(View v) {
		
	}
	@Override
	protected void onDestroy() {
		/*
		 * ��ֹ�ڴ�й©
		 */
		activitys.remove(this);
		super.onDestroy();
	}
	/**
	 * �������� �����ڵ�activitys  һ�����쳣���ֵ�ʱ��
	 */
	public void finishAll() {
		for (Activity item : activitys) {
			item.finish();
		}
	}
}

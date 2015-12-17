package Android.Zone.Abstract_Class.Adapter.core;

import java.util.List;

import Android.Zone.Constant;
import Android.Zone.Abstract_Class.Adapter.Adapter_MultiLayout_Zone;
import Android.Zone.Log.Logger_Zone;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseAdapter_Zone<T> extends BaseAdapter{
	public List<T> data;
	public LayoutInflater mInflater;// �õ�һ��LayoutInfalter�����������벼��
	public Logger_Zone logger;
	/**
	 * @param context
	 * @param data
	 * @param layout_id
	 */
	public BaseAdapter_Zone(Context context, List<T> data) {
		this.data = data;
		mInflater = LayoutInflater.from(context);
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder_Zone holder;
		if (convertView == null) {
			//convertView ���ظ�����  arg0�򲻻� �������� ÿ�ζ����ؾͺ��� ����  �й�convertView�Ķ��ǳ�ʼ����
			convertView = mInflater.inflate(getLayoutID(position), null);
			holder = new ViewHolder_Zone(convertView);
			//��View��Ӽ��� 
			convertView.setTag(holder);
			logger.log("��ʼ����position:"+position);
		} else {
			holder = ((ViewHolder_Zone) convertView.getTag());
			logger.log("���� position:"+position);
		}
		//���ֶ��Ѿ�Ū����  ������view�� ������   holder����view ��index �и��������
		T dataIndex = data.get(position);
		setData(holder,dataIndex,position);
		return convertView;
	}
	/**
	 * holder.findviewByid�����ҵ��ؼ�
	 * @param holder   findviewByid�����ҵ��ؼ�
	 * @param data     ��itemIndex�����ݼ��� �е�item...
	 * @param position 
	 */
	public abstract  void setData(ViewHolder_Zone holder, T data, int position); //ע�����ֻ�����������������û�о���ʵ�֡�
	public abstract int getLayoutID(int position);
}

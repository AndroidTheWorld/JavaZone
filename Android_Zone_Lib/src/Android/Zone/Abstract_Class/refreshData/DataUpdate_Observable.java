package Android.Zone.Abstract_Class.refreshData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * ��Ӻ� �ǵ���ondestory��ݻ�
 * @author Zone
 *
 */
public class DataUpdate_Observable {
	private List<DataUpdate_IObserver> observersList = new ArrayList<DataUpdate_IObserver>();
	
	public void addIObserver (DataUpdate_IObserver observer){
		observersList.add(observer);
	}
	
	public void clear(){
		observersList.clear();
	}
	/**
	 * �����ɾ�� ���еĹ������������м���
	 * @param observer
	 */
	public void removeIObserver(DataUpdate_IObserver observer){
		observersList.remove(observer);
	}
	
	public void notify(Object o){
		for (DataUpdate_IObserver item : observersList) {
			item.updateData(o);
		}
	}
}

package Android.Zone.Network.inter;

import java.util.Map;

public interface NetworkRequestAble {
	//map type  post Ĭ�� get  �����file Ĭ�� ��ɶ�Լ��뱻
	//2 ִ�е�ʱ�� �Ƿ���dialog   
	public abstract void request(String urlString,Map<String,String> map);
	
	
}

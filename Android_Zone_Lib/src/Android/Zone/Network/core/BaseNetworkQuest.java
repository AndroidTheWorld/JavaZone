package Android.Zone.Network.core;

import java.util.Map;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
//����Ǵ���    �������� dialog  ��handler������Ϣ
public abstract class BaseNetworkQuest {
	public Handler handler;
	private boolean showDialog=false;
	private Dialog dialog;
	private BaseListView listView;
	
	public BaseNetworkQuest(Context context,Handler handler) {
		this(context,handler,false);
	}
	public BaseNetworkQuest(Context context,Handler handler,boolean showDialog) {
		dialog=setDialog(context);
		this.handler= handler;
		this.showDialog= showDialog;
	}
	//map type  post Ĭ�� get 
	public  void send(String urlString,Map<String,String> map,int tag,NetworkListener listener){
		sendFile(urlString, map, null, tag, listener);
	};
	public  void sendFile(String urlString,Map<String,String> map,Map<String,String> fileMap,int tag,NetworkListener listener){
		showDialog();
		associationAddTurnPage(map);
		if(fileMap!=null)
			ab_Send(urlString, fileMap, tag, listener);
		else
			ab_SendFile(urlString,map,fileMap,tag,listener);
	}
	// TODO  error  ��  success����Ҫ ������Ϣ
	public void sendhandlerMsg(String msg,int tag){
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				hideDialog();
			}
		});
	
		handler.obtainMessage(tag,msg).sendToTarget();
	}
	//����list������ ����ӷ�ҳ����
	public void associationList(BaseListView  listView ){
		this.listView=listView;
	}
	public void associationAddTurnPage(Map<String, String> map){
		if(listView!=null){
			map.put(listView.LIMIT, listView.getPageNumber() + "");
	        int offest = listView.getLimit()* listView.getPageNumber();
	        map.put(listView.OFFSET, offest + "");
		}
	}
	//TODO   ��������ʵ��onSuccess onFailure�� sendhandlerMsg �ڵ���listener .ononSuccess onFailure
	protected abstract void ab_Send(String urlString, Map<String, String> map, int tag, NetworkListener listener);
	//TODO   ��������ʵ��onSuccess onFailure�� sendhandlerMsg �ڵ���listener .ononSuccess onFailure
	protected  abstract void ab_SendFile(String urlString, Map<String, String> map,Map<String, String> fileMap, int tag,
			NetworkListener listener);
	//����dialog
	protected  abstract Dialog setDialog(Context context);
	//����dialog
	protected   void showDialog(){
		dialog.show();
	};
	protected   void hideDialog(){
		dialog.dismiss();
	}
	
	
	public boolean isShowDialog() {
		return showDialog;
	}
	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
	
}

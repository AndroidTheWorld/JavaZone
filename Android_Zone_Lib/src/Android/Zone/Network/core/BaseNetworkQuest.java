package Android.Zone.Network.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
//����Ǵ���    �������� dialog  ��handler������Ϣ
public abstract class BaseNetworkQuest {
	public Handler handler;
	private NetworkListener listener;
	private Map<String,String> fileMap,map;
	private int tag;
	private  String urlString;
	
	private boolean showDialog=false;
	private Dialog dialog;

	private BasePullView listView;
	public static final String LIMIT="limit";
	public static final String OFFSET="offset";
	private  int limit=10, pageNumber=0;
	private List<Integer> pageNumberhistory=new ArrayList<Integer>();
	private boolean isGet=true;
	
	public BaseNetworkQuest(Context context,Handler handler) {
		this(context,handler,false);
	}
	public BaseNetworkQuest(Context context,Handler handler,boolean showDialog) {
		dialog=createDefaultDialog(context);
		this.handler= handler;
		this.showDialog= showDialog;
	}
	
	//������Ѿ������  ����firstPage����
	public  void firstPage(){
		exceptionChecked();
		pageNumber=0;
		sendFile(urlString, map, fileMap, tag, listener);
	};
	//firstPage 
	public  void firstPage(String urlString,Map<String,String> map,int tag,NetworkListener listener){
		exceptionChecked();
		pageNumber=0;
		startTask();
	};
	public  void nextPage(){
		 exceptionChecked();
		 pageNumber++;
		 startTask();
	};
	private void exceptionChecked(){
		if(listView==null)
			throw new IllegalStateException("please must be use  associationList!");
	}
	
	//��ʼ����
	public void startTask(){
		 sendFile(urlString, map, fileMap, tag, listener,true);
	}
	//map type  post Ĭ�� get 
	public  void send(String urlString,Map<String,String> map,int tag,NetworkListener listener){
		isGet=true;
		sendFile(urlString, map, null, tag, listener);
	};
	public  void sendPost(String urlString,Map<String,String> map,int tag,NetworkListener listener){
		isGet=false;
		sendFile(urlString, map, null, tag, listener);
	};
	public  void sendFile(String urlString,Map<String,String> map,Map<String,String> fileMap,int tag,NetworkListener listener){
		this.urlString=urlString;
		this.map=map;
		this.fileMap=fileMap;
		this.tag=tag;
		this.listener=listener;
		sendFile(urlString, map, fileMap, tag, listener,false);
	}
	private  void sendFile(String urlString,Map<String,String> map,Map<String,String> fileMap,int tag,NetworkListener listener,boolean run){
		if (run) {
			showDialog();
			relateAddTurnPage(map);
			if (fileMap == null) {
				if (isGet) {
					ab_Send(urlString, fileMap, tag, listener);
				} else
					ab_SendPost(urlString, fileMap, tag, listener);
			} else
				ab_SendFile(urlString, map, fileMap, tag, listener);
		}
	}
	// TODO  error  ��  success����Ҫ ������Ϣ  ���Ǽ�ס����ֻ��һ��������
	public void sendhandlerMsg(final String msg,int tag){
		//��dialogŪ��
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				hideDialog();
			}
		});
		//����list ����Ū��   ���ݴ���
		if(listView!=null&&pageNumberhistory.size()>0){
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					int number=pageNumberhistory.get(0);
					//����Ū��
					if(number==0)
						listView.onRefreshComplete();
					else
						listView.onloadMoreComplete();
					//���ݴ���
					listView.gsonParse(msg);
					if(number==0)
						listView.clearData();
					listView.addAllData2Notify();
					//��nubmerHistory������� �Ƴ�
					pageNumberhistory.remove(0);
				}
			});
		}else{
			//δ���� ������Ϣ ��handler
			handler.obtainMessage(tag,msg).sendToTarget();
		}
	}
	//����list������ ����ӷ�ҳ����
	public void relateList(BasePullView  listView ){
		this.listView=listView;
		listView.relateBaseNetworkQuest(this);
	}
	public void relateAddTurnPage(Map<String, String> map){
		if(listView!=null){
			map.put(LIMIT, pageNumber+ "");
			pageNumberhistory.add(pageNumber);
	        int offest = limit* pageNumber;
	        map.put(OFFSET, offest + "");
		}
	} 
	//TODO ���listenr�� �����õ�  �������Լ��� ����Ҫ�Լ��ڴ���һ�� ��������ȷ��������Ϣ��
	//TODO   ��������ʵ��onSuccess onFailure�� sendhandlerMsg �ڵ���listener .ononSuccess onFailure
	protected abstract void ab_Send(String urlString, Map<String, String> map, int tag, NetworkListener listener);
	//TODO   ��������ʵ��onSuccess onFailure�� sendhandlerMsg �ڵ���listener .ononSuccess onFailure
	protected abstract void ab_SendPost(String urlString, Map<String, String> map, int tag, NetworkListener listener);
	//TODO   ��������ʵ��onSuccess onFailure�� sendhandlerMsg �ڵ���listener .ononSuccess onFailure
	protected  abstract void ab_SendFile(String urlString, Map<String, String> map,Map<String, String> fileMap, int tag,
			NetworkListener listener);
	//���� Ĭ�ϵ�dialog 
	protected  abstract Dialog createDefaultDialog(Context context);
	//����dialog   
	public  void setDialog(Dialog dialog){
		this.dialog=dialog;
	};
	protected   void showDialog(){
		//����û��dialogҲ���ᱬ����
		if(dialog!=null)
			dialog.show();
	};
	protected   void hideDialog(){
		if(dialog!=null)
			dialog.dismiss();
	}
	
	public boolean isShowDialog() {
		return showDialog;
	}
	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
	
	public  <A> A gsonParseNoRelateList(String msg, Class<A> clazz){
		boolean resultIsRight=MsgCheck.errorChecked(msg);
		if(!resultIsRight)
			return null;
		Gson g=new Gson();
		return 	g.fromJson(msg, clazz);
	};
	

	
}

package Android.Zone.Network.core;

import java.io.File;
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
	private Map<String,String> map;
	private Map<String,File> fileMap;
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
	private Context context;
	public BaseNetworkQuest(Context context,Handler handler) {
		this(context,handler,false);
	}
	public BaseNetworkQuest(Context context,Handler handler,boolean showDialog) {
		this.context=context;
		this.handler= handler;
		this.showDialog= showDialog;
	}
	
	//������Ѿ������  ����firstPage����
	public  void firstPage(){
		exceptionChecked();
		pageNumber=0;
		startTask();
	};
	public  void nextPage(){
		 exceptionChecked();
		 pageNumber++;
		 startTask();
	};
	public  void trunPage(int number){
		exceptionChecked();
		pageNumber=number;
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
	public  void send(String urlString,Map<String,String> map,int tag){
		send(urlString, map, tag, null);
	};
	public  void sendPost(String urlString,Map<String,String> map,int tag,NetworkListener listener){
		isGet=false;
		sendFile(urlString, map, null, tag, listener);
	};
	public  void sendPost(String urlString,Map<String,String> map,int tag){
		sendPost(urlString, map, tag, listener);
	};
	public  void sendFile(String urlString,Map<String,String> map,Map<String,File> fileMap,int tag,NetworkListener listener){
		this.urlString=urlString;
		this.map=map;
		this.fileMap=fileMap;
		this.tag=tag;
		this.listener=listener;
		sendFile(urlString, map, fileMap, tag, listener,false);
	}
	public  void sendFile(String urlString,Map<String,String> map,Map<String,File> fileMap,int tag){
		sendFile(urlString, map, fileMap, tag, null);
	}
	private  void sendFile(String urlString,Map<String,String> map,Map<String,File> fileMap,int tag,NetworkListener listener,boolean run){
		if (run) {
			showDialog();
			relateAddTurnPage(map);
			if (fileMap == null) {
				if (isGet) {
					//TODO ����get��ʱ���л������  �ڴ滺��   ���ػ��� Ȼ��http
					ab_Send(urlString, map, tag, listener);
				} else
					ab_SendPost(urlString, map, tag, listener);
			} else
				ab_SendFile(urlString, map, fileMap, tag, listener);
		}
	}
	// TODO  error  ��  success����Ҫ ������Ϣ  ���Ǽ�ס����ֻ��һ��������
	public void sendhandlerMsg(final String msg,final int tag){
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
					//���ݴ���
					listView.gsonParse(msg);
					if(number==0)
						listView.clearData();
					removeListAnimal(number,listView);
					//����Ū��
					listView.addAllData2Notify();
					//��nubmerHistory������� �Ƴ�
					pageNumberhistory.remove(0);
					handler.obtainMessage(tag,msg).sendToTarget();
				}
				//����Ū��
				private void removeListAnimal(int number, BasePullView listView) {
					if(number==0)
						listView.onRefreshComplete();
					else
						listView.onloadMoreComplete();
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
			map.put(LIMIT, limit+ "");
			pageNumberhistory.add(pageNumber);
	        int offest = limit* pageNumber;
	        map.put(OFFSET, offest + "");
		}
	} 
	protected abstract void ab_Send(String urlString, Map<String, String> map, int tag, NetworkListener listener);
	protected abstract void ab_SendPost(String urlString, Map<String, String> map, int tag, NetworkListener listener);
	protected  abstract void ab_SendFile(String urlString, Map<String, String> map,Map<String, File> fileMap, int tag,
			NetworkListener listener);
	//���� Ĭ�ϵ�dialog 
	protected  abstract Dialog createDefaultDialog(Context context);
	//����dialog   
	public  void setDialog(Dialog dialog){
		this.dialog=dialog;
	};
	protected   void showDialog(){
		if(dialog==null)
			dialog=createDefaultDialog(context);
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
	void relateReturnEmptyData(){
		if(pageNumber>0)
			pageNumber--;
	}

	
}

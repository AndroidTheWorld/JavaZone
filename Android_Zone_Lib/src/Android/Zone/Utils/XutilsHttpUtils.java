package Android.Zone.Utils;

import java.io.File;

import android.content.Context;
import android.os.Handler;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
//TODO session cookieδ�� ����δ��
//TODO dialogδ��
//RequestParams params = new RequestParams(); // �������������������
//��ѯ��������Ϊ�˸���
//params.addQueryStringParameter("qmsg", "���");
//params.addBodyParameter("msg", "����");
// ����ļ�
//params.addBodyParameter("file", new File("/sdcard/test.zip"));
//params.addBodyParameter("testfile", new File("/sdcard/test2.zip")); // ��������ļ�

// ���ڷ�multipart���ĵ��ļ��ϴ�
//params.setBodyEntity(new FileUploadEntity(new File("/sdcard/test.zip"), "binary/octet-stream"));

// ���ڷ�multipart�������ϴ�
//params.setBodyEntity(new InputStreamUploadEntity(stream ,length));
public class XutilsHttpUtils {
	private static long timeout=1000*10;
	//�������
	private static final String Request_Charset="utf-8";
	// ���÷����ı��ı��룬 Ĭ�ϱ���UTF-8
	private static final  String Response_Charset="utf-8";
	
	private static final String Params_Charset="utf-8";
	
	public static RequestParams getXutilsParams(){
		return new RequestParams(Params_Charset);
	}
	public static void getHandler_Json(Context context,String url,RequestParams params,final Handler handler,final int tag){
		HttpUtils http = InnerDispose.getXutils_HttpUtils();
        if(params==null)
        	http.send(HttpRequest.HttpMethod.GET,url,InnerDispose.getXutilsCallback(context,handler, tag));
        else
        	http.send(HttpRequest.HttpMethod.GET,url,params,InnerDispose.getXutilsCallback(context,handler, tag));
	}
	public static void getHandler_Json(Context context,String url,final Handler handler,final int tag){
		getHandler_Json(context, url, null, handler, tag);
	}
	public static void postHandler_Json(Context context,String url,RequestParams params,final Handler handler,final int tag){
		HttpUtils http = InnerDispose.getXutils_HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,url,params,InnerDispose.getXutilsCallback(context,handler, tag));
	}
	public static void uploadFile(String url,RequestParams params,RequestCallBack<String> listener){
		HttpUtils http = InnerDispose.getXutils_HttpUtils();
		http.send(HttpRequest.HttpMethod.POST,url,params,listener);
	}
	
	static  class InnerDispose{
		private static HttpUtils getXutils_HttpUtils(){
			HttpUtils http = new HttpUtils(Request_Charset);
			http.configResponseTextCharset(Response_Charset);
			http.configCurrentHttpCacheExpiry(timeout);
			return http;
		}
		private static RequestCallBack<String> getXutilsCallback(final Context context, final Handler handler,final int tag){
			return new RequestCallBack<String>() {

	            @Override
	            public void onStart() {
	            }

	            @Override
	            public void onLoading(long total, long current, boolean isUploading) {
	            }

	            @Override
	            public void onSuccess(ResponseInfo<String> responseInfo) {
	            	handler.obtainMessage(tag,responseInfo.result).sendToTarget();
	            	
	            }
	            @Override
	            public void onFailure(HttpException error, String msg) {
	            	httpOnFailure(context, error, msg);
	            }
	        };
		}
		private static void httpOnFailure(Context context,HttpException error, String msg){
			
		}
	}
	
}

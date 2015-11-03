package Android.Zone.Wifi;

import Android.Zone.Log.ToastUtils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class NetManager {
	public static final String Net_MOBILE = "MOBILE";
	public static final String Net_WIFI = "WIFI";
	public enum NetStatue{
		WIFI,MOBILE,NO_WORK;
	}

	public static NetStatue getNetStatue(Context context){
		 ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo info=connectivityManager.getActiveNetworkInfo();
         NetStatue status=NetStatue.NO_WORK;
         if(info != null && info.isAvailable()) {
             if(Net_MOBILE.equals(info.getTypeName())){
            	 status=NetStatue.MOBILE;
             } else if(Net_WIFI.equals(info.getTypeName())){
            	 status=NetStatue.WIFI;
             }
         }
         return status;
	}
	/**
	 * �ж�wifi�Ƿ�������״̬
	 * 
	 * @return boolean :����wifi�Ƿ�����
	 */
	public static boolean isWIFI(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean result = false;
		if (networkInfo != null) {
			result = networkInfo.isConnected();
		}
		return result;
	}

	/**
	 * �ж��Ƿ�APN�б���ĳ��������������״̬
	 * 
	 * @return �����Ƿ�����
	 */
	public static boolean isMoble(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean result = false;
		if (networkInfo != null) {
			result = networkInfo.isConnected();
		}
		return result;
	}
	public boolean haveNetWork(Context context) {
		 ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo info=connectivityManager.getActiveNetworkInfo();
       if(info==null){
       	ToastUtils.showLong(context, "��ǰ������");
       	 return false;
       }
       if(info.getState()==NetworkInfo.State.CONNECTED){
       	return true;
       }
    	ToastUtils.showLong(context, "��ǰ������");
       return false;
	}
}

package Android.Zone.Image;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import Android.Zone.SD.SdSituation;
import Android.Zone.Utils.AppUtils;
import Android.Zone.Utils.MD5Utils;

public class DiskLruUtils {
	private static DiskLruUtils diskLru = new DiskLruUtils();
	private static DiskLruCache mDiskLruCache = null;

	/**
	 * ��Ϊһ��Ӧ��Ӧ�þ���һ�������Ƕ�� �����Ҿ�final�� ����Լ��ľ�ok��
	 */
	private static final String DirName = "bitmap";
	private static final long CacheMax=10 * 1024 * 1024;
	
	private static final String TAG="DiskLruUtils";
	private static boolean writeLog=true;
	public static void log(String str){
		if (writeLog) {
			Log.d(TAG, str);
		}
	}

	private DiskLruUtils() {
	}
	public static DiskLruUtils getInstance(){
		return diskLru;
	}

	/**
	 * �汾�Ÿı� ���Զ����
	 * @param context
	 * @return
	 */
	public   void  openLru(Context context) {
		try {
			/**
			 * open()���������ĸ���������һ������ָ���������ݵĻ����ַ��
			 * �ڶ�������ָ����ǰӦ�ó���İ汾�ţ�
			 * ����������ָ��ͬһ��key���Զ�Ӧ���ٸ������ļ����������Ǵ�1�����ĸ�����ָ�������Ի�������ֽڵ����ݡ�
			 */
			File cacheDir = SdSituation.getDiskCacheDir(context, DirName);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			mDiskLruCache = DiskLruCache.open(cacheDir,AppUtils.getAppVersion(context), 1, CacheMax);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ֻ�гɹ��ŵ����������
	 * 
	 * @param url
	 */
	public void addUrl(String url) {
		// if (downloadUrlToStream(imageUrl, outputStream)) {
		// editor.commit();
		// } else {
		// editor.abort(); //������ǲ��ύ��~
		// }
		String key = MD5Utils.hashKeyForDisk(url);
		try {
			DiskLruCache.Editor editor = mDiskLruCache.edit(key);
			editor.commit();
			log("addUrl:"+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean removeUrl(String url) {
		String key = MD5Utils.hashKeyForDisk(url);
		try {
			boolean temp= mDiskLruCache.remove(key);
			log("addUrl:"+url+(temp==true?"�ɹ�":"ʧ��"));
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Bitmap getBitmapByUrl(String url) {
		String key = MD5Utils.hashKeyForDisk(url);
		Bitmap bitmap = null;
		try {
			DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
			bitmap = BitmapFactory.decodeStream(snapShot.getInputStream(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * �Ƚϱ�׼������������Activity��onPause()������ȥ����һ��flush()�����Ϳ����ˡ�
	 */
	public void flush() {
		try {
			mDiskLruCache.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����������ڽ�DiskLruCache�رյ����Ǻ�open()������Ӧ��һ���������رյ���֮��Ͳ����ٵ���DiskLruCache���κβ����������ݵķ�����
	 * ͨ��ֻӦ����Activity��onDestroy()������ȥ����close()������
	 */
	public void close() {
		try {
			mDiskLruCache.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����������ڽ����еĻ�������ȫ��ɾ��������˵���������е��Ǹ��ֶ������湦�ܣ���ʵֻ��Ҫ����һ��DiskLruCache��delete()�����Ϳ���ʵ���ˡ�
	 */
	public void delete() {
		try {
			mDiskLruCache.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��������᷵�ص�ǰ����·�������л������ݵ����ֽ�������byteΪ��λ��
		���Ӧ�ó�������Ҫ�ڽ�������ʾ��ǰ�������ݵ��ܴ�С���Ϳ���ͨ�����������������������������������о�������һ�����ܣ�����ͼ��ʾ��
	 * @return 
	 */
	public long size(){
		return 	mDiskLruCache.size();
	}
	
}

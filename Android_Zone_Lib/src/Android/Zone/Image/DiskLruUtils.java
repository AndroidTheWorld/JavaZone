package Android.Zone.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import Android.Zone.Constant;
import Android.Zone.Abstract_Class.Adapter_MultiLayout_Zone;
import Android.Zone.Image.official.DiskLruCache;
import Android.Zone.Log.Logger_Zone;
import Android.Zone.SD.FileUtils_SD;
import Android.Zone.SD.SdSituation;
import Android.Zone.Utils.AppUtils;
import Android.Zone.Utils.MD5Utils;
import Java.Zone.CustomException.OperationFailException;
import Java.Zone.IO.IOUtils;
import Java.Zone.Utils.FileUtils;

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
	private static Logger_Zone logger;
	static{
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
	}

	private DiskLruUtils() {
	}

	/**
	 * �汾�Ÿı� ���Զ����
	 * @param context
	 * @return
	 */
	public  static   DiskLruUtils  openLru(Context context) {
		try {
			/**
			 * open()���������ĸ���������һ������ָ���������ݵĻ����ַ��
			 * �ڶ�������ָ����ǰӦ�ó���İ汾�ţ�
			 * ����������ָ��ͬһ��key���Զ�Ӧ���ٸ������ļ����������Ǵ�1�����ĸ�����ָ�������Ի�������ֽڵ����ݡ�
			 */
//			File cacheDir = SdSituation.getDiskCacheDir(context, DirName);
			File cacheDir = FileUtils_SD.FolderCreateOrGet("Love",DirName);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			mDiskLruCache = DiskLruCache.open(cacheDir,AppUtils.getAppVersion(context), 1, CacheMax);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return diskLru;
	}

	/**
	 * ����ֻ�гɹ��ŵ����������
	 * 
	 * @param url
	 */
	public void addUrl(String url,String path) {
		addUrl(url, Compress_Sample_Utils.getRawBitmap(path));
	}
	private static ByteArrayInputStream bitmapToOs(Bitmap bt){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bt.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ��ѹ���������baos��ŵ�ByteArrayInputStream��
		return isBm;
	}
	// -----------------------------------------------------------readToOutStream---------------------------------------------------------------
	private static boolean readToOutStream(Bitmap bt,OutputStream os){
		InputStream in=bitmapToOs(bt);
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = in.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw new OperationFailException("����ȡ����IOException������");
		} finally {
			try {
				in.close();
				os.close();
			} catch (IOException e) {
				throw new OperationFailException("���رշ����쳣��");
			}
		}
		return true;
	}
	/**
	 * ����ֻ�гɹ��ŵ����������
	 * 
	 * @param url
	 */
	public void addUrl(String url, Bitmap bm) {
		// if (downloadUrlToStream(imageUrl, outputStream)) {
		// editor.commit();
		// } else {
		// editor.abort(); //������ǲ��ύ��~
		// }
		String key = MD5Utils.hashKeyForDisk(url);
		try {
			DiskLruCache.Editor editor = mDiskLruCache.edit(key);
			OutputStream outputStream = editor.newOutputStream(0);  
			if(readToOutStream(bm, outputStream)){
				editor.commit();
				logger.log("addUrl:"+url);
			}else {  
				editor.abort();  
			}  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean removeUrl(String url) {
		String key = MD5Utils.hashKeyForDisk(url);
		try {
			boolean temp= mDiskLruCache.remove(key);
			logger.log("addUrl:"+url+(temp==true?"�ɹ�":"ʧ��"));
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
			if(snapShot != null){
				bitmap = BitmapFactory.decodeStream(snapShot.getInputStream(0));
				logger.log("getBitmapByUrl:"+url);
			}
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
			if (!mDiskLruCache.isClosed()) {
				mDiskLruCache.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����������ڽ����еĻ�������ȫ��ɾ��������˵���������е��Ǹ��ֶ������湦�ܣ���ʵֻ��Ҫ����һ��DiskLruCache��delete()�����Ϳ���ʵ���ˡ�
	 */
	public void delete() {
		try {
			if (!mDiskLruCache.isClosed()) {
				mDiskLruCache.delete();
			}
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

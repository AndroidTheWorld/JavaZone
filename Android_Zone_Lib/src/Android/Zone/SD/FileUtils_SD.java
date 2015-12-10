package Android.Zone.SD;

import java.io.File;

import Java.Zone.CustomException.OperationFailException;
import android.content.Context;
import android.os.Environment;
/**
 * �����Ǵ����ļ��е�����
 * @version 2015.7.15
 * @author Zone
 *
 */
public class FileUtils_SD {
	public static File getCacheDir(Context context){
		return context.getCacheDir();
	}
	/**
	 * ����SD����  ����ļ��Ľ���
	 * ���Եõ��ļ��� ,�ļ� ,��Ŀ¼
	 * @param arg  �����ļ���·�� 
	 * <br><strong> ������getFile("test001","test002","test003"); �ļ���Ŀ¼
	 * <br> ��������Ϊ��getFile("") ��ʾSD����Ŀ¼  
	 * <br> ��������Ϊ��getFile("a.txt")   �ļ�
	 * </strong>
	 * @return	
	 */
	public static File getFile(String... arg) {
		return getFile(true,arg);
	}
	/**
	 * ����SD����  ����ļ��Ľ���
	 * @param isNotCreate  ���ļ������ڵ�ʱ���Ƿ񴴽� 
	 * <br>������������ļ� �������ļ���  ��ʹ isNotCreate false��Ҳ���Զ��޸ĳ�true
	 * @param arg  �����ļ���·�� 
	 * @return	
	 */
	private static File getFile(boolean isNotCreate,String... arg) {
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
			throw new NullPointerException("sd�����ڷ�false!");
		}
		String pathJoin = "";
		String fileEnd=null;
		for (String str : arg) {
			if(str.contains(".")){
				fileEnd= str;
			}else{
				pathJoin += "/" + str;
			}
			
		}
		String f = Environment.getExternalStorageDirectory().getPath();
		File file = new File(f + pathJoin);
		if (fileEnd!=null) 
			isNotCreate=true;
		if (isNotCreate&&!file.exists()) {
			boolean isOk = file.mkdirs();
			if (!isOk) {
				throw new OperationFailException("�ļ�����ʧ�ܣ�");
			}
		}
		if (fileEnd!=null) {
			file = new File(file, fileEnd);
		}
		return file;
	}
	
}

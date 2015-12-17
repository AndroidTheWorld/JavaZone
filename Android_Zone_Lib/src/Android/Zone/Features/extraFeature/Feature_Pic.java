package Android.Zone.Features.extraFeature;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import Android.Zone.Constant;
import Android.Zone.Abstract_Class.Adapter.Adapter_MultiLayout_Zone;
import Android.Zone.Features.ExtraFeature;
import Android.Zone.Features.RequestCodeConfig;
import Android.Zone.Log.Logger_Zone;
import Android.Zone.SD.FileUtils_SD;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;

/**
 * ���Ե��� ϵͳ����� ϵͳ���� activity
 * <br>openCamera pop��Ҳ���Ե��� ��̬����  �봦�� onActivityResult���Ǹ� �̳д�activity����
 * @author Zone
 *
 */
public abstract  class Feature_Pic extends ExtraFeature{
	private Logger_Zone logger;
	private static String path;
	private static File outFile = FileUtils_SD.getFile("Zone", "picSave");

	public Feature_Pic(Activity activity) {
		super(activity);
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
	}
	
	public  void openCamera() {
		String picName = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA))+ ".jpg";
		logger.log("��Ƭ����ʽ��yyyyMMdd_hhmmss.jpg");
		outFile = new File(outFile, picName);
		path = outFile.getPath();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
		activity.startActivityForResult(intent, RequestCodeConfig.Feature_Pic__REQUESTCODE_CAMERA);
	}
	public  void openPhotos() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
		activity.startActivityForResult(intent,RequestCodeConfig.Feature_Pic__REQUESTCODE_PHOTOS);
	}

	/**
	 * 
	 * @param path ��Ƭ��·��  �Ѿ������� ����þ���
	 */
	protected abstract void getReturnedPicPath(String path);

	/**
	 * �ж��ļ��Ƿ����
	 * @return
	 */
	private boolean isFileExist() {
		File file = new File(path);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void init() {
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (resultCode == -1) {
			switch (requestCode) {
			case RequestCodeConfig.Feature_Pic__REQUESTCODE_CAMERA:
				if (isFileExist()) {
					getReturnedPicPath(path);
				}
				break;
			case RequestCodeConfig.Feature_Pic__REQUESTCODE_PHOTOS:
				if (intent!=null) {
					Uri uri = intent.getData();
					ContentResolver cr = activity.getContentResolver();
					Cursor cursor = cr.query(uri, null, null, null, null);
					if(cursor==null){
//						file:///storage/sdcard0/MIUI/Gallery/cloud/.thumbnailFile/3fbad3015691d03f59ccb97561103abcc9b0ba12.jpg
//						content://media/external/images/media/8
						getReturnedPicPath(uri.getPath());
						break;
					}
					cursor.moveToFirst();
					for (int i = 0; i < cursor.getColumnCount(); i++) {
						logger.log(i + "-----------------"+cursor.getString(i)+"----"+cursor.getColumnName(i));
						if (cursor.getColumnName(i).contains("data")) {
							path = cursor.getString(i);
						}
					}
					getReturnedPicPath(path);
				}
				break;

			default:
				break;
			}
		}
	}
	@Override
	public void destory() {
		
	}
	
}

package Android.Zone.Features;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import Android.Zone.Constant;
import Android.Zone.Abstract_Class.Adapter_MultiLayout_Zone;
import Android.Zone.Log.Logger_Zone;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;

public abstract class Feature_SystemClip extends ExtraFeature{
	private  String saveFolder="";
	private Uri savePath=null;
	private Logger_Zone logger;
	public Feature_SystemClip(Activity activity,String saveFolder) {
		super(activity);
		this.saveFolder=saveFolder;
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
	}
	
	public void cropImageUri(String path){
		Uri uri = Uri.parse("file://" + "/"+path);
		//С�������intent action
//		����֪�������Ǵ����ѡȡͼƬ��ActionΪIntent.ACTION_GET_CONTENT��
//		 Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		 Intent intent = new Intent("com.android.camera.action.CROP");
		//����ѡ��ͼƬ���ͣ������*�����������͵�ͼƬ
		 intent.setDataAndType(uri, "image/*");
	     // �������crop = true�������ڿ�����Intent��������ʾ��VIEW�ɲü�
		 intent.putExtra("crop", "true");
		//�ü�ʱ�Ƿ���ͼƬ�ı���������ı�����1:1
		 intent.putExtra("scale", true);
		 
		// ������Ϊ�ü���ı���.   �̶����ʡ�����ͬʱ�����ˡ�����
//		 intent.putExtra("aspectX", 2);
//		 intent.putExtra("aspectY", 1);
		  // outputX outputY �ǲü�ͼƬ��ߡ������ǹ̶��ü�ͼ�Ĵ�С�ˡ���Ҫ�̶�
//		 intent.putExtra("outputX", outputX);
//		 intent.putExtra("outputY", outputY);
		 
		 //�Ƿ����ݱ�����Bitmap�з���
		 intent.putExtra("return-data", true);
		 //��������ĸ�ʽ
		 intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		 intent.putExtra("noFaceDetection", true); // no face detection
		 
		 String picName = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA))+ ".jpg";
		 File saveFile = new File(saveFolder, picName);
		 savePath=Uri.fromFile(saveFile);
		 logger.log("savePath Uri:"+savePath);
		 intent.putExtra(MediaStore.EXTRA_OUTPUT,savePath );//�����ַ  
		 activity.startActivityForResult(intent, RequestCodeConfig.Feature_SystemClip__REQUESTCODE_Clip);
		}
	
	@Override
	public void init() {
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			switch (requestCode) {
			case RequestCodeConfig.Feature_SystemClip__REQUESTCODE_Clip:
				if (intent != null) {
					logger.log("onActivityResult  savePath:"+savePath);
					getReturnedClipPath(savePath);
				}
				break;

			default:
				break;
			}
	}

	public abstract void getReturnedClipPath(Uri savePath);

	@Override
	public void destory() {
		
	}

}

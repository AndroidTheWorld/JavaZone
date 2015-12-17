package Android.Zone.Image;

import Android.Zone.Constant;
import Android.Zone.Abstract_Class.Adapter.Adapter_MultiLayout_Zone;
import Android.Zone.Log.Logger_Zone;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class BitmapUtils {
	private static Logger_Zone logger;

	static{
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
	}
	/**
	 *  �������Ĭ�����ĵ���ͼƬ������
	 * @param bitmap λͼ
	 * @param degress Ҫ��ת�ĽǶ�
	 * @param recycledSrc  �Ƿ����ԭͼ
	 * @return ��ת��Ƭ
	 */
	public static Bitmap rotateBitmap(Bitmap bitmap, int degress,boolean recycledSrc) {
		Bitmap temp =null;
		if (bitmap != null) {
			Matrix matrix = new Matrix();
			// ��תͼƬ ����
			matrix.postRotate(degress);
			// �����µ�ͼƬ �������Ĭ�����ĵ���ͼƬ������
			temp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, true);
			if(recycledSrc)
				recycledBitmap(bitmap);
		}
		return temp;
	}

	/**
	 *  �������Ĭ�����ĵ���ͼƬ������
	 * @param bitmap��λͼ
	 * @param sx�����������
	 * @param sy�� ���������
	 *��@param recycledSrc  �Ƿ����ԭͼ
	 * @return ��ת��Ƭ
	 */
	public static Bitmap scaleBitmap(Bitmap bitmap, float sx, float sy,boolean recycledSrc) {
		Bitmap temp =null;
		if (bitmap != null) {
			Matrix matrix = new Matrix();
			// ��תͼƬ ����
			matrix.postScale(sx, sy);
			// �����µ�ͼƬ�� �������Ĭ�����ĵ���ͼƬ������
			temp= Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),bitmap.getHeight(), matrix, true);
			if(recycledSrc)
				recycledBitmap(bitmap);
		}
		return temp;
	}

	public static void recycledBitmap(Bitmap bitmap) {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
		// �����˵�� ϵͳ���˻��Զ����ðѡ�
		// System.gc();
	}
	
	/**
	 * ת��ͼƬ��Բ��
	 * 
	 * @param bitmap  ����Bitmap����
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap,boolean recycledSrc) {
		if (bitmap == null)
			return null;
		Bitmap circleBt = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		
		Canvas canvas=new Canvas(circleBt);
		
		Paint paint=new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		canvas.saveLayer(0, 0, circleBt.getWidth(), circleBt.getHeight(), paint,Canvas.ALL_SAVE_FLAG);
		
		float radius=0F;
		if(circleBt.getWidth()<circleBt.getHeight())
			radius=circleBt.getWidth()/2;
		else
			radius=circleBt.getHeight()/2;
		
		paint.setColor(Color.RED);
		canvas.drawCircle(circleBt.getWidth()/2, circleBt.getHeight()/2,radius, paint);
		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0,0, paint);
		canvas.restoreToCount(1);
		if(recycledSrc)
			recycledBitmap(bitmap);
		return circleBt;
	}
	
	// ����ͳ��ڵ����ص�ͼƬ��Ⱥ�dstWidth�Ҹ߶Ⱥ�dstHeight��ͬʱ��
	// Bitmap.createBitmap�����᷵��ԭ����bitmap��������bitmap== temp��
	/**
	 * ���µ�����bitmap�Ŀ�ߡ�����������
	 * @param src
	 * @param dstWidth����Ŀ���
	 * @param dstHeight 
	 * @param recycledSrc �Ƿ����ԭλͼ
	 * @return
	 */
	public static Bitmap getScaledBitmapDeep(Bitmap src, int dstWidth, int dstHeight,boolean recycledSrc) {
		Bitmap temp=null;
		if (src != null) {
			if (dstWidth > 0 && dstHeight > 0) {
				if(dstWidth==src.getWidth()&&dstHeight==src.getHeight()){
					temp=copyDeep(src, false);
				}else{
					 temp = Bitmap.createScaledBitmap(src, dstWidth, dstHeight, true);
				}
				 logger.log("getNewBitmap:���Ƶ�λͼ����ԭͼ���Ƿ�Ϊͬһ����"+(temp==src?"��":"����"));
				if (recycledSrc) {
					recycledBitmap(src);
				}
			}
		}
		return temp;
	}
	/**
	 * 
	 * @param src
	 * @param recycledSrc �Ƿ����ԭλͼ
	 * @return
	 */
	public static Bitmap copyDeep(Bitmap src,boolean recycledSrc) {
		if (src != null) {
			Bitmap temp = src.copy(src.getConfig(), true);
			logger.log("copy:���Ƶ�λͼ����ԭͼ���Ƿ�Ϊͬһ����"+(temp==src?"��":"����"));
				if (recycledSrc) {
					recycledBitmap(src);
				}
				return temp;
		}
		return null;
	}

}

package Android.Zone.Image;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
public class ImageLoaderUtils {
	/**
	 * 	 ���ӣ� http�Ļ� �κ��˶����þͲ���װ������
		<br>String imageUri = "http://site.com/image.png"; // ����ͼƬ  
		<br>String imageUri = "file:///mnt/sdcard/image.png"; //SD��ͼƬ  
		<br>String imageUri = "content://media/external/audio/albumart/13"; // ý���ļ���  
		<br>String imageUri = "assets://image.png"; // assets  
		<br>String imageUri = "drawable://" + R.drawable.image; //  drawable�ļ�  
	 *
	 */
	public enum Type{
		File("file://"),Assets("assets://"),Drawable("drawable://"),Content("content://");
		private String str;
		private Type(String str) {
			this.str=str;
		}
		public String getStr() {
			return str;
		}
	}
	public void displayImage(ImageView imageView,String uri,Type type){
		uri=type.getStr()+uri;
		ImageLoader.getInstance().displayImage(uri, imageView,new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}

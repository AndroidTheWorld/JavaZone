package Android.Zone.Image;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
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

    /**
     * ��ʾͼƬ����������
     * @return
     */
//    private DisplayImageOptions getWholeOptions() {
//        DisplayImageOptions options = new DisplayImageOptions.Builder()  
//        .showImageOnLoading(R.drawable.loading) //����ͼƬ�������ڼ���ʾ��ͼƬ  
//        .showImageForEmptyUri(R.drawable.ic_launcher)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ  
//        .showImageOnFail(R.drawable.error)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
//        .cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���  
//        .cacheOnDisk(true)//�������ص�ͼƬ�Ƿ񻺴���SD����  
//        .considerExifParams(true)  //�Ƿ���JPEGͼ��EXIF��������ת����ת��
//        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//����ͼƬ����εı��뷽ʽ��ʾ  
//        .bitmapConfig(Bitmap.Config.RGB_565)//����ͼƬ�Ľ�������
//        //.decodingOptions(BitmapFactory.Options decodingOptions)//����ͼƬ�Ľ�������  
//        .delayBeforeLoading(0)//int delayInMillisΪ�����õ�����ǰ���ӳ�ʱ��
//        //����ͼƬ���뻺��ǰ����bitmap��������  
//        //.preProcessor(BitmapProcessor preProcessor)  
//        .resetViewBeforeLoading(true)//����ͼƬ������ǰ�Ƿ����ã���λ  
//        .displayer(new RoundedBitmapDisplayer(20))//���Ƽ��ã��������Ƿ�����ΪԲ�ǣ�����Ϊ����  
//        .displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ�䣬���ܻ��������
//        .build();//�������
//        
//        return options;
//    }
}

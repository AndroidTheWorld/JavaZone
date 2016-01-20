package Android.Zone.Image.ImageLoader;
import android.graphics.Bitmap;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//�ǵ����ü�����ͼƬ ��Ȼ��������http��ʱ������ ����ʾ����֮ǰ�ı����ͻ���� �Ӹ��ù�����ͼ��� ���غ��ͼ������
public class ImageLoaderOptionsUtils {
	private static int imageOnLoading=-1;
	private static int imageForEmptyUri=-1;
	private static int imageOnFail=-1;
	private static boolean initShowImage_Used=false;
	public static void initShowImage(int imageOnLoading,int imageForEmptyUri,int imageOnFail){
		initShowImage_Used=true;
		ImageLoaderOptionsUtils.imageOnLoading=imageOnLoading;
		ImageLoaderOptionsUtils.imageForEmptyUri=imageForEmptyUri;
		ImageLoaderOptionsUtils.imageOnFail=imageOnFail;
	}
	
	public static Builder getNormalOption��NotBuild(){
		if(!initShowImage_Used){
			throw new IllegalStateException("please first use initShowImage method !");
		}
		Builder options = new DisplayImageOptions.Builder();
		if (imageOnLoading!=-1) {
			options.showImageOnLoading(imageOnLoading);
		}
		if (imageForEmptyUri!=-1) {
			options.showImageForEmptyUri(imageForEmptyUri);
		}
		if (imageOnFail!=-1) {
			options.showImageOnFail(imageOnFail);
		}
		options.imageScaleType(ImageScaleType.IN_SAMPLE_INT);
		options.cacheInMemory(true);
		options.cacheOnDisk(true);
		options.bitmapConfig(Bitmap.Config.RGB_565); // ����ͼƬ�Ľ�������
		
//		------------------------����չ�Ķ���-------------------------------
//		options.considerExifParams(true);  //�Ƿ���JPEGͼ��EXIF��������ת����ת��
//	    options.decodingOptions(BitmapFactory.Options decodingOptions);//����ͼƬ�Ľ�������  
//		options.delayBeforeLoading(0);//int delayInMillisΪ�����õ�����ǰ���ӳ�ʱ��
//		options.resetViewBeforeLoading(true);//����ͼƬ������ǰ�Ƿ����ã���λ  
//		options.displayer(new RoundedBitmapDisplayer(20))//���Ƽ��� ��Ϊ���������ɡ�ARGB8888����ͼƬ���������Ƿ�����ΪԲ�ǣ�����Ϊ����  
//	    options.displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ�䣬���ܻ��������
		return options;
	}

}

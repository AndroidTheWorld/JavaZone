package Android.Zone.Image.ImageLoader;

import java.io.File;

import Android.Zone.Utils.ScreenUtils;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class ImageLoaderConfigUtils {
	public static void initImageLoader(Context context,boolean writeDebugLogs) {
		initImageLoader(context, null,writeDebugLogs);
	}
	/**
	 * �ڴ�����Ļ� �����ұʼ�
	 * @param context
	 */
	public static void initImageLoader(Context context,DisplayImageOptions defaultDisplayImageOptions,boolean writeDebugLogs) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		
		//·���ǣ�/data/data/com.example.mylib_test/cache Ҫ��image
		File cacheDir =new File(context.getCacheDir(),"images");
		
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		/** ==========================�̷߳��� =========================*/		
		// ������ʾͼƬ�̳߳ش�С��Ĭ��Ϊ3
		config.threadPoolSize(3);
		// �趨�̵߳ȼ�����ͨ��һ��
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		//�������ڼ��غ���ʾͼ�������Ķ��д������͡�
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		/** ==========================�ڴ滺��  =========================*/
		//ͼƬ�����ڼ�  ��bitmap�Ŀ�� Ĭ����  �ֻ����  ���Ǿ�Ĭ���ˡ�
		config.memoryCacheExtraOptions(480, 800);
		/**
		 * memoryCache(...)��memoryCacheSize(...)�����������ụ�า�ǣ�������ImageLoaderConfiguration��ʹ��һ���ͺ���
		 */
		// �ڴ滺������ֵ
		config.memoryCache(new LruMemoryCache(2 * 1024 * 1024));
		//���浽�ڴ��������� 
//		config.memoryCacheSize(2 * 1024 * 1024);
		// �趨ֻ����ͬһ�ߴ��ͼƬ���ڴ�
		config.denyCacheImageMultipleSizesInMemory();
		/** ==========================�ļ�����  =========================*/
		int[] screenParams=ScreenUtils.getScreenPixByContext(context);
		//����ͼƬ�� compress���浽�ļ��е� ���
		config.diskCacheExtraOptions(screenParams[0], screenParams[1], null);
		/**
		 * diskCacheSize(...)��diskCache(...)��diskCacheFileCount(...)�����������ụ�า�ǣ�ֻʹ��һ��
		 */
		// �趨�����SDcardĿ¼��UnlimitDiscCache�ٶ����
		config.diskCache(new UnlimitedDiskCache(cacheDir));
		//���浽�ļ����������   50 MiB
		config.diskCacheSize(50 * 1024 * 1024); 
		//�ļ�����
		//config.diskCacheFileCount(1000); 
		
		// //�������ʱ���URI������MD5 ����
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator()); 
	
		//BaseImageDecoder  true:��ӡlog  false:�㶮��
		config.imageDecoder(new BaseImageDecoder(true));
		
		/** ==========================��ʱ ��log��ӡ  =========================*/
		// �趨�������ӳ�ʱ timeout: 10s ��ȡ�������ӳ�ʱread timeout: 45s
		config.imageDownloader(new BaseImageDownloader(context, 10000, 45000));
	
		if (defaultDisplayImageOptions!=null) {
			//�����ĳ�����ʹ��displayImage��������ʱ����Ĳ���������һ���ģ���ôһ������Ľ��������һ��
			config.defaultDisplayImageOptions(defaultDisplayImageOptions);
		}
		if (writeDebugLogs) {
			//��ӡlog
			config.writeDebugLogs(); // Remove for release app
		}
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
}

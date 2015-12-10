package Android.Zone.Image.ImageLoader;

public class ImageLoaderURIUtils {
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
	public static String transformURI(String uri,Type type){
		return type.getStr()+uri;
	}
}

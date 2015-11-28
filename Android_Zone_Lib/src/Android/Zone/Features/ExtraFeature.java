package Android.Zone.Features;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
/**
 * ���ס���ҪΪ�ˡ�
 * ����������ڵ�onActivityResult ��Ȼ�������������onResume��ʼ����onPause�ݻ�
 *����Ƶ�(���԰����ܶ෽����������)
 *
 *	���飺��Ϊ�ⲿsetonclick�ᶥ������ġ���onclick�Ͳ���װ�ˡ�������װ����������
 * @author 123
 *
 */
public abstract class ExtraFeature {
	public  Activity activity=null;
	public ExtraFeature(Activity activity) {
		if(activity==null)
			throw new IllegalArgumentException("arg:activity may be null!");
		this.activity=activity;
	}
	public abstract void init();
	public abstract void onActivityResult(int requestCode, int resultCode, Intent intent);
	public abstract void destory();
}

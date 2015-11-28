package Android.Zone.Features;

import java.util.ArrayList;
import java.util.List;
import Android.Zone.Abstract_Class.BaseActvity;
import android.content.Intent;
import android.os.Bundle;
/**
 * <br>�ʣ�onResume�Ժ������ExtraFeature��onPause�Ͳ�������
 * <br>ע�⣺onResume()�������³�ʼ����Դ ����Camera��sensor��
 * <br>onPause()�������Դ �����˷�  ����Camera��sensor��
 * @author 123
 *
 */
public abstract class FeaturesActivity extends BaseActvity {
	public List<ExtraFeature> featureList= new ArrayList<ExtraFeature>();
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		init2AddFeature();
		for (ExtraFeature item : featureList) {
			item.init();
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		for (ExtraFeature item : featureList) {
			item.onActivityResult(requestCode, resultCode, intent);
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		for (ExtraFeature item : featureList) {
			item.destory();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	protected FeaturesActivity addFeature(ExtraFeature feature) {
		featureList.add(feature);
		return this;
	}
	protected abstract void init2AddFeature() ;
}

package Android.Zone.Abstract_Class;

import android.support.v4.app.Fragment;  

/** 
* Author: msdx (645079761@qq.com) 
* Time: 14-7-17 ����5:46 
*/  
public abstract class Fragment_Lazy extends Fragment {  
  protected boolean isVisible;  
  /** 
   * ������ʵ��Fragment���ݵĻ�����. 
   * @param isVisibleToUser 
   */  
  @Override  
  public void setUserVisibleHint(boolean isVisibleToUser) {  
      super.setUserVisibleHint(isVisibleToUser);
      //�ж��Ƿ���û��ɼ�
      if(getUserVisibleHint()) {  
          isVisible = true;  
          onVisible();  
      } else {  
          isVisible = false;  
          onInvisible();  
      }  
  }  

  protected abstract void onVisible();

  protected abstract  void onInvisible();
}  
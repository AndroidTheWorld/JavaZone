package Android.Zone.Abstract_Class.recycler;
import Android.Zone.Utils.ViewIDsUtils;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zone on 2015/12/16.
 */
public class RecyclerHolder_Zone extends android.support.v7.widget.RecyclerView.ViewHolder{
    private int[] idArray;// �õ�һ��LayoutInfalter�����������벼��
    public Map<Integer,View> map=new HashMap<Integer, View>();
    public RecyclerHolder_Zone(View convertView) {
        super(convertView);
        //��id ���ҳ���
        List<Integer> idList = ViewIDsUtils.getIDsByView(convertView);
        idArray=new int[idList.size()];
        for (int i = 0; i < idList.size(); i++) {
            idArray[i]=idList.get(i);
        }
        for (int i = 0; i < idArray.length; i++) {
            //�� convertView��ÿ���ؼ� ����map ������ȡ��
            map.put(idArray[i], convertView.findViewById(idArray[i]));
        }
    }
}
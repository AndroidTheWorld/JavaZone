package Android.Zone.Utils.rexUtils.RexWorkHelp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Android.Zone.Utils.rexUtils.RexHelperUtils;

public class Rex_Phone {
	public static class PhoneEntity{
		public String str;
		public int start;
		public int end;
		public PhoneEntity(String str,int start,int end) {
			this.str=str;
			this.start=start;
			this.end=end;
		}
	}
	// 11λ 18510640011 185-1064-0011
	// 10λ 4002342222 400-234-2222
	// 8λ 22223334 2222-3334
	//���ô�绰  ��-Ҳ�ǿ���ʶ���
	//	Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneno));
	//	startActivity(intent);
	public static List<PhoneEntity>  byContextGetPhone(String str){
		RexHelperUtils ru=new RexHelperUtils();
		ru.addRule("\\D(\\d{11})\\D");
		ru.addRule("\\D(\\d{3}-\\d{4}-\\d{4})\\D");
		ru.addRule("\\D(\\d{10})\\D");
		ru.addRule("\\D(\\d{3}-\\d{3}-\\d{4})\\D");
		ru.addRule("\\D(\\d{8})\\D");
		ru.addRule("\\D(\\d{4}-\\d{4})\\D");
		return getValuePhone(str,ru.build());
	}
	/**
	 * ��һ�������Ӧ������
	 * 
	 * @param con
	 * @param reg
	 * @return
	 */
	private static List<PhoneEntity> getValuePhone(String con, String reg) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(" "+con+" ");//��Ϊ����Ϊ�����ڿ�ͷ�ġ���β��Ҳƥ���� ���Եõ���˳��-1
		String res = "";
		List<PhoneEntity>  list=new ArrayList<PhoneEntity>();
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				res = m.group(i);
				if(res == null||i==0){
					continue;
				}
				//��������˳��-1
				list.add(new PhoneEntity(res, m.start(i)-1, m.end(i)-1));
//				System.out.println(res+"-----------\t ks:"+m.start(i)+"   \tend:"+m.end(i)+"\t i��"+i);
			}
		}
		return list;
	}
}

package Android.Zone.Utils.rexUtils;

public class RexHelperUtils{
	private  StringBuffer sb=new StringBuffer();
	private boolean isFirst=true;
	/**
	 * ֻ���м������|��  Ϊ�˷�ֹ̫���������Ժ�
	 * @param rex
	 * @return
	 */
	public RexHelperUtils addRule(String rex){
		if (isFirst) {
			sb.append(rex);
			isFirst=false;
		}else{
			sb.append("|" +rex);
		}
		return this;
	}
	public String build() {
		return sb.toString();
	}
}

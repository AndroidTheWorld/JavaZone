package 公司需求测试.设计模式.解释器;

//终结符表达式角色

class Constant extends Expression {
	private int i;

	public Constant(int i) {
		this.i = i;
	}

	public int interpret(Context con) {
		return i;
	}
}
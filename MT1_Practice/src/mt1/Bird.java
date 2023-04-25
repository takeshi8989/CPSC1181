package mt1;

public class Bird extends Animal{
	private boolean flies;
	public Bird(boolean canFly) {
		super();
		this.flies = canFly;
	}
	
	public boolean canFly() {
		return this.flies;
	}
	
	public String action() {
		String result = super.action();
		if(this.flies) {
			result = result + " and flies";
		}
		return result;
	}
}

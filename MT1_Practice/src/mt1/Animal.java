package mt1;

public abstract class Animal {
	public String action() {
		return "lives";
	}
	
	public String toString() {
		return "This animal " + this.action();
	}
}

package mt1;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Animal s = new Bird(true);
//		Error because Animal doesn't have canFly()
//		System.out.println(s.canFly())
		
//		Error because Bird cannot be Parrot
//		Parrot p = new Bird(true);
//		System.out.println(p.action());
		
		Animal c = new Coral();
		System.out.println(c);
		
		Parrot p = new Parrot();
		System.out.println(p.canFly());
		
		Bird m = new Parrot();
		System.out.println(m);
		
		Animal d = new Bird(false);
		System.out.println(d);

	}

}

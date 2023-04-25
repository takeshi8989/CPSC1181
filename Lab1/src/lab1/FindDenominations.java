/**
 * 
 */
package lab1;

import java.util.Scanner;

/**
 * @author Takeshi Hashimoto
 * Prompt user to input the amount of money.
 * And then, print the number of different coins.
 *
 */
import java.util.Scanner;
public class FindDenominations {

	/**
	 * @param args
	 * Main Method
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int QUARTER = 25;
		final int DIME = 10;
		final int NICKEL = 5;
		// ask a user to input the money
		Scanner input = new Scanner(System.in);
		System.out.print("input the amount of money from 0 to 99: ");
		int money = input.nextInt();
		int quarters = money / QUARTER;
		money -= quarters * QUARTER;
		int dimes = money / DIME;
		money -= dimes * DIME;
		int nickels = money / NICKEL;
		money -= nickels * NICKEL;
		// print the number of coins
		System.out.println("Quarters: " + quarters);
		System.out.println("Dimes: " + dimes);
		System.out.println("Nickels: " + nickels);
		System.out.println("Cents: " + money);
		input.close();
	}

}

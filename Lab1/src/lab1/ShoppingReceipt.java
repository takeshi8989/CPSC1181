/**
 * 
 */
package lab1;

import java.util.Scanner;

/**
 * @author Takeshi Hashimoto
 * Prompt user to input the item's name, price, and quantity
 * Print the receipt with its subtotal and taxes
 */
public class ShoppingReceipt {

	/**
	 * @param args
	 * Main Method
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the item name: ");
		String itemName = input.nextLine();
		System.out.print("Enter the item price: ");
		double itemPrice = input.nextDouble();
		System.out.print("Enter the quantity: ");
		int quantity = input.nextInt();
		// print receipt
		printReceipt(itemName, quantity, itemPrice);
		input.close();
	}
	
	/**
	 * This method prints the receipt with the shopping information and its total price
	 * 
	 * @param name item's name, quantity, price
	 */
	public static void printReceipt(String name, int quantity, double price) {
		final double GST = 0.05;
		final double PST = 0.07;
		
		double subtotal = getSubTotal(price, quantity);
		System.out.println();
		System.out.println("Your Receipt");
		System.out.println();
		System.out.printf("Item name:\t\t%8s\n", name);
		System.out.printf("Quantity:\t\t%8s\n", quantity);
		System.out.printf("Item price:\t$\t%8s\n", price);
		System.out.printf("Subtotal:\t$\t%8s\n", subtotal);
		double gst = Math.floor(subtotal * GST * 100) / 100.0;
		System.out.printf("GST (5%%):\t$\t%8s\n", gst);
		double pst = Math.floor(subtotal * PST * 100) / 100.0;
		System.out.printf("PST (7%%):\t$\t%8s\n", pst);
		double total = Math.round((subtotal + pst + gst) * 100) / 100.0;
		System.out.printf("Total:\t\t$\t%8s", total);
	}
	
	/**
	 * This method returns the subtotal of the items with discount.
	 * 
	 * @param price,quantity
	 */
	public static double getSubTotal(double price, int quantity) {
		double subtotal = price * quantity;
		// give discount per 3 items
		int discounts = quantity / 3;
		return subtotal - discounts * price / 2;
	}

}

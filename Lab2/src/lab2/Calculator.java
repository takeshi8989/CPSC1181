package lab2;

import java.util.Arrays;

/**
 * @author Takeshi Hashimoto
 * A simple class that contains many common methods used for performing
 * various computations. These methods may or may not be correct. We
 * should add a test class to check the validity of these methods.
 */
public class Calculator {
	/** Main method */
	public static void main(String[] args) {
		
	}
	
	/**
	 * This method returns the remainder of a / b
	 * @param a
	 * @param b
	 * @return the remainder of a / b
	 */
	public static int remainder(int a, int b) {
		// if(b == 0) return 0;
		if(b == 0) {
			throw new ArithmeticException("remainder by zero");
		}
		return a % b;
	}

	/**
	 * This method returns sum of a and b
	 * @param a
	 * @param b
	 * @return sum of a and b
	 */
	public static double add(double a, double b) {
		return a + b;
	}

	/**
	 * This method returns the product of a and b
	 * @param a
	 * @param b
	 * @return the product of a and b
	 */
	public static double multiply(double a, double b) {
		return a * b;
	}

	/**
	 * This method returns the difference of a and b by a - b
	 * @param a
	 * @param b
	 * @return difference of a and b by a - b
	 */
	public static double subtract(double a, double b) {
		return a - b;
	}

	/**
	 * This method returns the quotient of a / b
	 * @param a
	 * @param b
	 * @return the quotient of a / b
	 */
	public static double divide(double a, double b) {
		if(b == 0) return 0;
		return a / b;
	}

	/**
	 * This method returns the average value of parameters
	 * @param values
	 * @return the average value of parameters
	 */
	public static int average(double... values) {
		if(values == null || values.length == 0) return 0;
		int sum = 0;
		for (double n : values) {
			sum += n;
		}
		return sum / values.length;
	}

	// calculate arithmetic median
	// he median of a finite list of numbers can be found
	// by arranging all the numbers from smallest to greatest.
	// If there is an odd number of numbers, the middle one is
	// picked. If there is an even number of observations, then
	// there is no single middle value; the median is then usually
	// defined to be the mean of the two middle values
	public static double median(double... values) {
		if(values == null || values.length == 0) return 0;
		Arrays.sort(values);
		if(values.length % 2 == 0) {
			int mid = values.length / 2;
			return (values[mid-1] + values[mid]) / 2.0;
		}
		return values[values.length / 2];

	}

	/**
	 * This method returns the maximum value of parameters
	 * @param values
	 * @return max value of paramters
	 */
	public static double max(double... values) {
		double max = 0;
		if (values != null && values.length > 0) {
			max = values[0];
			for (int i = 1; i < values.length; i++) {
				if (values[i] > max)
					max = values[i];
			}
		}
		return max;
	}

	/**
	 * This method returns the minimum value of parameters
	 * @param values
	 * @return the minimum value of parameters
	 */
	public static double min(double... values) {
		double min = 0;
		if (values != null && values.length > 0) {
			min = values[0];
			for (int i = 1; i < values.length; i++) {
				if (values[i] < min)
					min = values[i];
			}
		}
		return min;
	}

}

package lab2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Takeshi Hashimoto
 * A class for testing the methods of the Calculator class behave
 * as expected.
 * See: http://junit.sourceforge.net/javadoc/org/junit/Assert.html
 * For more JUnit Assertion Statements
 */
class TestCalculator {

	@Test
	void testRemainder() {
		assertEquals(3, Calculator.remainder(7, 4));
		assertEquals(1, Calculator.remainder(13, 3));
		assertEquals(0, Calculator.remainder(0, 8));
		assertEquals(3, Calculator.remainder(13, -5));
		assertEquals(-3, Calculator.remainder(-13, -5));
		try {
			assertEquals(0, Calculator.remainder(13, 0));
			fail("Devided by zero is invalid");
		} catch(ArithmeticException e) {}
	}

	@Test
	void testAdd() {
		assertEquals(15, Calculator.add(10, 5));
		assertEquals(16, Calculator.add(37,-21));
		assertEquals(13.07, Calculator.add(9.87, 3.2), 1E-10);
		assertEquals(-11.14, Calculator.add(-9.8,-1.34), 1E-10);
	}
	@Test
	void testMultiply() {
		assertEquals(6, Calculator.multiply(2, 3));
		assertEquals(-12, Calculator.multiply(-4, 3));
		assertEquals(-31.04, Calculator.multiply(9.7, -3.2), 1E-10);
		assertEquals(5.32, Calculator.multiply(-3.8,-1.4), 1E-10);
	}
	
	@Test
	void testSubtract() {
		assertEquals(5, Calculator.subtract(12, 7));
		assertEquals(-10, Calculator.subtract(-4, 6));
		assertEquals(1.7, Calculator.subtract(4.9, 3.2), 1E-10);
		assertEquals(-1.5, Calculator.subtract(-2.9,-1.4), 1E-10);
	}

	@Test
	void testDivide() {
		assertEquals(3, Calculator.divide(21, 7));
		assertEquals(-2, Calculator.divide(-12, 6));
		assertEquals(0, Calculator.divide(21, 0));
		assertEquals(-6.3, Calculator.divide(-12.6, 2), 1E-10);
	}

	@Test
	void testAverage() {
		assertEquals(4, Calculator.average(3, 5, 6, 1, 5));
		assertEquals(-1, Calculator.average(-9, -4, 7, 3, -2));
		assertEquals(1, Calculator.average(3.0, 5.2, 6.5, 1.4, 5.9, -8.2), 1E-10);
		assertEquals(0, Calculator.average());
		assertEquals(0, Calculator.average(null));
	}


	@Test
	void testMedian() {
		assertEquals(8.5, Calculator.median(12, 4, 5, 8, 9, 10));
		assertEquals(2, Calculator.median(-9, -4, 7, 3, -2, 10, 2));
		assertEquals(0, Calculator.median());
		assertEquals(0, Calculator.median(null));
		assertEquals(5.3, Calculator.median(9.3, -3.4, 5.3, -4.87, 12.82));
		assertEquals(-1.475, Calculator.median(-7.23, 4.28, 12.47, 8, -23.3, -8.34));
	}

	@Test
	void testMax() {
		assertEquals(12, Calculator.max(12, 4, 5, 8, 9, 10));
		assertEquals(10, Calculator.max(-9, -4, 7, 3, -2, 10, 2));
		assertEquals(12.462, Calculator.max(-9.33, 4.37, 8.23, 12.462, 9.22, -9.23));
		assertEquals(0, Calculator.max());
		assertEquals(0, Calculator.max(null));
	}
	
	@Test
	void testMin() {
		assertEquals(4, Calculator.min(12, 4, 5, 8, 9, 10));
		assertEquals(-9, Calculator.min(-9, -4, 7, 3, -2, 10, 2));
		assertEquals(-9.33, Calculator.min(-9.33, 4.37, 8.23, 12.462, 9.22, -9.23));
		assertEquals(0, Calculator.min());
		assertEquals(0, Calculator.min(null));
	}
}

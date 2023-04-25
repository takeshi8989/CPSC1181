package lab3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAccount {

	@Test
	void testAccount() {
		Account a = new Account();
		assertEquals(a.getAccountNumber(), 1000001);
		Account b = new Account(100, 0.03);
		assertEquals(b.getAccountNumber(), 1000002);
		try {
			Account c = new Account(-10, 0.1);
			fail("negative balance");
		} catch(IllegalArgumentException e) {}
		try {
			Account d = new Account(10, -0.1);
			fail("negative interest rate");
		} catch(IllegalArgumentException e) {}
		Account e = new Account();
		assertEquals(e.getAccountNumber(), 1000003);
	}

	@Test
	void testGetBalance() {
		Account c = new Account();
		Account d = new Account(111, 0.04);
		assertEquals(c.getBalance(), 0);
		assertEquals(d.getBalance(), 111);
	}
	
	@Test
	void testSetBalance() {
		Account account = new Account();
		account.setBalance(100.1);
		assertEquals(account.getBalance(), 100.1);
		try {
			account.setBalance(-32.1);
			fail("setting negative balance");
		}catch(IllegalArgumentException e) {}
		assertEquals(account.getBalance(), 100.1);
	}

	@Test
	void testGetAnnualInterestRate() {
		Account account = new Account(31.9, 0.11);
		assertEquals(account.getAnnualInterestRate(), 0.11);
		
	}

	@Test
	void testSetAnnualInterestRate() {
		Account account = new Account();
		account.setAnnualInterestRate(0.09);
		assertEquals(account.getAnnualInterestRate(), 0.09);
		try {
			account.setAnnualInterestRate(-0.2);
			fail("setting negative interest rate");
		} catch(IllegalArgumentException e) {}
		assertEquals(account.getAnnualInterestRate(), 0.09);
	}

	@Test
	void testGetMonthlyInterest() {
		Account account = new Account(100, 0.01);
		assertEquals(account.getMonthlyInterest(), 1 / 12.0, 10E-8);
	}

	@Test
	void testWithdraw() {
		Account account = new Account(1200.35, 0.03);
		account.withdraw(100.1);
		assertEquals(account.getBalance(), 1100.25);
		try {
			account.withdraw(-300.8);
			fail("withdrawing negative amount");
		} catch(IllegalArgumentException e) {}
		assertEquals(account.getBalance(), 1100.25);
		account.withdraw(100000);
		assertEquals(account.getBalance(), 0);
	}

	@Test
	void testDeposit() {
		Account account = new Account(1.1, 0.03);
		account.deposit(100);
		assertEquals(account.getBalance(), 101.1);
		try {
			account.deposit(-30.5);
			fail("deposit negative amount");
		} catch(IllegalArgumentException e) {}
		assertEquals(account.getBalance(), 101.1);
	}
}

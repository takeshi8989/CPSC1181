/**
 * 
 */
package lab3;

/**
 * @author Takeshi Hashimoto
 * Account Class
 * An object has account number, balance, annualInterestRate
 * it can withdraw or deposit money
 */
public class Account {
	private int accountNumber;
	private double balance;
	private double annualInterestRate;
	private static int totalAccounts = 1000000;
	
	/**
	 * Constructor
	 * accout number is set by incremented number of total accounts
	 * balance and annual interest rate are set to zero
	 */
	public Account() {
		this.balance = 0;
		this.annualInterestRate = 0;
		Account.totalAccounts += 1;
		this.accountNumber = Account.totalAccounts;
	}
	
	/**
	 * Constructor
	 * @param balance
	 * @param rate
	 * accout number is set by incremented number of total accounts
	 */
	public Account(double balance, double rate) {
		if (balance < 0) {
	        throw new IllegalArgumentException("balance is greater than zero.");
	    }
		if (rate < 0) {
	        throw new IllegalArgumentException("interest rate is greater than zero.");
	    }
		this.balance = balance;
		this.annualInterestRate = rate;
		Account.totalAccounts += 1;
		this.accountNumber = Account.totalAccounts;
	}
	
	/**
	 * This method returns the balance
	 * @return current balance of the bank account
	 */
	public double getBalance() {
		return this.balance;
	}
	
	/**
	 * This method set new balance
	 * new balance should be positive value
	 * @param newBalance
	 */
	public void setBalance(double newBalance) {
		if (newBalance < 0) {
	        throw new IllegalArgumentException("balance cannot be negative value");
	    }
		this.balance = newBalance;
	}
	
	/**
	 * this method returns its annual interest rate as decimal fraction (1% -> 0.01)
	 * @return annual interest rate
	 */
	public double getAnnualInterestRate() {
		return this.annualInterestRate;
	}
	
	/**
	 * this method sets annual interest
	 * new rate should be positive
	 * @param new annual interest rate
	 */
	public void setAnnualInterestRate(double newRate) {
		if (newRate < 0) {
	        throw new IllegalArgumentException("interest rate cannot be negative");
	    }
		this.annualInterestRate = newRate;
	}
	
	/**
	 * this method returns its account number
	 * @return account number
	 */
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	/**
	 * this method returns its monthly interest (annual interest / 12)
	 * @return the amount of monthly interest
	 */
	public double getMonthlyInterest() {
		return this.balance * this.annualInterestRate / 12;
	}
	
	/**
	 * this method takes money from its account
	 * if amount is greater than its balance, it takes all money from account
	 * @param amount of money to withdraw
	 * @return withdrawn amount
	 */
	public double withdraw(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Cannot withdraw negative amount");
		}
		if(amount > this.balance) {
			double withdraw = this.balance;
			this.balance = 0;
			return withdraw;
		}
		this.balance -= amount;
		return amount;
	}
	
	/**
	 * this method puts money to an account
	 * the amount must be positive
	 * @param amount of money to deposit
	 */
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Cannot deposit negative amount");
		}
		this.balance += amount;
	}
	
	/**
	 * this method returns the information of the account
	 * account number, current balance, annual interest rate, and monthly interest
	 * @return string information
	 */
	public String toString() {
		String str = "Account Information\n\n";
		str += "Account Number: " + this.getAccountNumber() + "\n";
		str += "Your Current Balance: " + this.getBalance() + "\n";
		str += "Annual Interest rate: " + this.getAnnualInterestRate() + "\n";
		str += "Your monthly Interest: " + this.getMonthlyInterest() + "\n\n";
		return str;
	}
}

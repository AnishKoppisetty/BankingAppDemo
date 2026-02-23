package BankingApp;

abstract class accountDetails {
	
	protected String customerName;
	protected int accountNumber;
	protected double balance;
	
	public abstract void withdraw(double amount);
	
	public accountDetails(String name, int id, double initialBalance) {
        this.customerName = name;
        this.accountNumber = id;
        this.balance = initialBalance;
    }
	

}
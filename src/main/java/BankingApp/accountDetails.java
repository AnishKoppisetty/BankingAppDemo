package BankingApp;

abstract class accountDetails {
	
	public String customerName;
	public int accountNumber;
	public double balance;
	
	abstract void withdraw(double amount);
	

}

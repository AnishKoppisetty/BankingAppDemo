package BankingApp;
import java.io.*;

public class SavingAccount extends accountDetails {
	
	public void setUpAccount(String nameInput, int id) {
		customerName = nameInput;
		accountNumber = id;
	}
	
	public void setInitialBalance(double amount) {
		balance = amount;
	}
	
	public void deposit(double amount) {
		if(amount > 0) {
			balance += amount;
			System.out.println("Amount Deposited: " + amount );
			System.out.println("New Balance: " + balance);
		}
	}
	
	public double getBalance() {
		return balance;
	}

	@Override
	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
			System.out.println("Withdrawl Complete. Remaining: " + balance);
		} else {
			System.out.println("Insufficient funds in savings account");
		}
		
	}

}

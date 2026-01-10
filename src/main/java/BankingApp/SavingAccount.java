package BankingApp;

public class SavingAccount extends accountDetails {


    public SavingAccount(String name, int id, double initialBalance) {
		super(name, id, initialBalance);
		// TODO Auto-generated constructor stub
	}

	public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("New Balance: " + balance);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal Complete. Remaining: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}
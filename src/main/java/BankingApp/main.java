package BankingApp;

import java.util.*;
import java.io.*;

public class main {
	
	final static String filePath = "/Users/anishkoppisetty/Desktop/Framework/BankingApp/src/main/java/users.txt";
	public double userBalance = 0.0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		ArrayList<accountDetails> bankRecords = new ArrayList();

		System.out.println("Would you like to create a new account or login to an existing account");
		System.out.print("TYPE 1 to login and 2 to sign up: ");
		int decision = s.nextInt();
		s.nextLine();
		
		switch(decision) {
			case 1:
				System.out.print("Enter Name: ");
		        String inputName = s.nextLine();
		        


		        System.out.print("Enter ID: ");
		        int inputID = Integer.parseInt(s.nextLine());
		        
		        if (validateCredentials(inputName, inputID)) {
		            System.out.println("Login successful for " + inputName + " (ID: " + inputID + ")");
		            System.out.println("Balance: " + getUserBalance(inputName, inputID));
		        } else {
		            System.out.println("Invalid username or password.");
		        }
		        break;
			case 2: 
				System.out.println("Creating a new Saving Account...");
				
				try (FileWriter writer = new FileWriter(filePath, true)) {
			

				    System.out.print("Enter Name: ");
				    String name = s.nextLine();

				    System.out.print("Enter ID: ");
				    int id = s.nextInt();
		
					
					SavingAccount newCustomer = new SavingAccount();
					newCustomer.setUpAccount(name, id);
					System.out.print("Enter Initial Deposit: ");
					double deposit = s.nextDouble();
					while (deposit < 0) {
						System.out.print("Can't deposit a negative amount, Enter a valid amount: ");
						deposit = s.nextDouble();
					}
					newCustomer.setInitialBalance(deposit);

					bankRecords.add(newCustomer);

					System.out.print("Enter amount to withdraw: ");
					double withdrawAmt = s.nextDouble();
					while (withdrawAmt < 0 || withdrawAmt > newCustomer.getBalance()) {
						System.out.print("Can't withdraw negative amt or higher than your balance, Enter a valid amount: ");
						withdrawAmt = s.nextDouble();
					}
					newCustomer.withdraw(withdrawAmt);

					System.out.println("Final Balance for " + newCustomer.customerName + ": " + newCustomer.getBalance()); 
					String userDetails = name + ", " + id + ", " + newCustomer.getBalance() + "\n";
				    writer.write(userDetails);
					writer.close();
					break;
				}
				catch (IOException e) {
					System.out.println("Users not able to be signed up");
				}
				
			default:
				System.out.println("Invalid input, please try again.");
			

		}	
		s.close();

	}
	public static boolean validateCredentials(String name, int id) {
		
				
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); 
                if (parts.length >= 2) {
                	String storedName = parts[0].trim();
                    int storedId = Integer.parseInt(parts[1].trim());
               
                    if (storedName.equals(name.trim()) && storedId == id) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }
        return false;
		
	}
	public static double getUserBalance(String name, int id) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); 
                if (parts.length >= 2) {
                	String storedName = parts[0].trim();
                    int storedId = Integer.parseInt(parts[1].trim());
                    double storedBalance = Double.parseDouble(parts[2].trim());
               
                    if (storedName.equals(name.trim()) && storedId == id) {
                        return storedBalance;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials file: " + e.getMessage());
        }
        return -1;
	}

}

package BankingApp;

import java.io.*;

public class UserActions extends ObjectsPageFactory {

    public static void runLogin() {
        System.out.print("Enter Name: ");
        String name = s.nextLine();
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(s.nextLine());

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].trim().equalsIgnoreCase(name) && Integer.parseInt(parts[1].trim()) == id) {
                    System.out.println("Login Successful! Balance: " + parts[2].trim());
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Invalid Credentials.");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void runSignUp() {
        System.out.print("Enter Name: ");
        String name = s.nextLine();
        System.out.print("Enter ID: ");
        int id = s.nextInt();
        System.out.print("Initial Deposit: ");
        double deposit = s.nextDouble();

        SavingAccount newCustomer = new SavingAccount(name, id, deposit);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(newCustomer.customerName + ", " + newCustomer.accountNumber + ", " + newCustomer.balance + "\n");
            System.out.println("Account created for " + name);
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
}
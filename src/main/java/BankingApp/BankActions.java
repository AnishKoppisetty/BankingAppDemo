package BankingApp;

public class BankActions extends ObjectsPageFactory{

    public static void signup() {

        try {
            System.out.println("\n=== Sign Up ===");

            System.out.print("Enter your name: ");
            String name = s.nextLine();

            System.out.print("Enter your ID: ");
            String id = s.nextLine();

            System.out.print("Enter initial balance: ");
            double balance = Double.parseDouble(s.nextLine());

            UserActions.signUp(name, id, balance);
            System.out.println("Signup successful!");

        } catch (Exception e) {
            System.out.println("Signup failed: " + e.getMessage());
        }
    }

    public static void login() {

        try {
            System.out.println("\n=== Login ===");

            System.out.print("Enter your name: ");
            String name = s.nextLine();

            System.out.print("Enter your ID: ");
            String id = s.nextLine();

            double balance = UserActions.login(name, id);

            if (balance >= 0) {
                System.out.println("Login successful!");
                System.out.println("Your balance is: $" + balance);
            } else {
                System.out.println("Invalid login details.");
            }

        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}

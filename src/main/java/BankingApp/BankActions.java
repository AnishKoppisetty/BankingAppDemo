package BankingApp;

public class BankActions extends ObjectsPageFactory{

    public static void signup() {

        try {
        	System.out.println("\n=== Sign Up ===");

            System.out.print("Username: ");
            String username = s.nextLine();

            System.out.print("First Name: ");
            String firstName = s.nextLine();

            System.out.print("Last Name: ");
            String lastName = s.nextLine();

            System.out.print("DOB (example 01/15/2006 or 2006-01-15): ");
            String dob = s.nextLine();

            System.out.print("Email: ");
            String email = s.nextLine();

            //System.out.print("Address: ");
            //String address = s.nextLine();
            
            System.out.print("Street Name: ");
            String street = s.nextLine();
            
            System.out.print("City: ");
            String city = s.nextLine();
            
            System.out.print("State: ");
            String state = s.nextLine();
            
            System.out.print("Country: ");
            String country = s.nextLine();

            System.out.print("Zip Code: ");
            String zip = s.nextLine();
            
            String address = street + ", " + city + ", " + state + ", " + country + " - " + zip;


            System.out.print("Phone: ");
            String phone = s.nextLine();

            System.out.print("Initial Balance: ");
            double balance = Double.parseDouble(s.nextLine());

            // Password is common for now, so we do not ask the user
            UserActions.signUp(username, firstName, lastName, dob, email, address, phone, balance);

            System.out.println("\nSignup successful!");
            System.out.println("Your temporary password is: 12345");
            System.out.println("Your ID: " + UserActions.generateId(firstName, lastName, dob));

        } catch (Exception e) {
            System.out.println("\nSignup failed: " + e.getMessage());
        }
    }

    public static void login() {

    	try {
            System.out.println("\n=== Login ===");

            System.out.print("Username: ");
            String username = s.nextLine();

            System.out.print("Password: ");
            String password = s.nextLine();

            double balance = UserActions.login(username, password);

            if (balance >= 0) {
                System.out.println("\nLogin successful!");
                System.out.println("Your balance is: $" + balance);
            } else {
                System.out.println("\nInvalid username or password.");
            }

        } catch (Exception e) {
            System.out.println("\nLogin failed: " + e.getMessage());
        }
    }
}

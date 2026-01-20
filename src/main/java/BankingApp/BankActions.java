package BankingApp;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BankActions extends ObjectsPageFactory {

    private static final DateTimeFormatter DOB_FMT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public static void signup() {
        try {
            System.out.println("\n=== Sign Up ===");

            String username, firstName, lastName, dob, email;
            String street, city, state, country, zip, address;
            String phone;
            double balance;

            // Username
            do {
                System.out.print("Username (3-20, letters/digits/_): ");
                username = s.nextLine().trim();
                if (!username.matches("^[A-Za-z0-9_]{3,20}$")) {
                    System.out.println("Invalid username.");
                }
            } while (!username.matches("^[A-Za-z0-9_]{3,20}$"));

            // First Name
            do {
                System.out.print("First Name: ");
                firstName = s.nextLine().trim();
                if (!firstName.matches("^[A-Za-z][A-Za-z '\\-]{0,49}$")) {
                    System.out.println("Invalid first name.");
                }
            } while (!firstName.matches("^[A-Za-z][A-Za-z '\\-]{0,49}$"));

            // Last Name
            do {
                System.out.print("Last Name: ");
                lastName = s.nextLine().trim();
                if (!lastName.matches("^[A-Za-z][A-Za-z '\\-]{0,49}$")) {
                    System.out.println("Invalid last name.");
                }
            } while (!lastName.matches("^[A-Za-z][A-Za-z '\\-]{0,49}$"));

            // DOB
            do {
                System.out.print("DOB (MM/dd/yyyy): ");
                dob = s.nextLine().trim();
                if (!isValidDob(dob)) {
                    System.out.println("Invalid DOB. Use MM/dd/yyyy (e.g., 01/17/2000).");
                }
            } while (!isValidDob(dob));

            // Email
            do {
                System.out.print("Email: ");
                email = s.nextLine().trim();
                if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    System.out.println("Invalid email.");
                }
            } while (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));

            // Address parts (non-empty)
            do {
                System.out.print("Street Name: ");
                street = s.nextLine().trim();
                if (street.isEmpty()) System.out.println("Street name cannot be empty.");
            } while (street.isEmpty());

            do {
                System.out.print("City: ");
                city = s.nextLine().trim();
                if (city.isEmpty()) System.out.println("City cannot be empty.");
            } while (city.isEmpty());

            do {
                System.out.print("State: ");
                state = s.nextLine().trim();
                if (state.isEmpty()) System.out.println("State cannot be empty.");
            } while (state.isEmpty());

            do {
                System.out.print("Country: ");
                country = s.nextLine().trim();
                if (country.isEmpty()) System.out.println("Country cannot be empty.");
            } while (country.isEmpty());

            do {
                System.out.print("Zip Code (5 or 9 digits): ");
                zip = s.nextLine().trim();
                if (!zip.matches("^\\d{5}(\\d{4})?$")) {
                    System.out.println("Invalid zip code.");
                }
            } while (!zip.matches("^\\d{5}(\\d{4})?$"));

            address = street + ", " + city + ", " + state + ", " + country + " - " + zip;

            // Phone (must contain 10 digits)
            do {
                System.out.print("Phone (10 digits): ");
                phone = s.nextLine().trim();
                if (phone.replaceAll("\\D", "").length() != 10) {
                    System.out.println("Invalid phone number.");
                }
            } while (phone.replaceAll("\\D", "").length() != 10);

            // Balance (>= 0)
            do {
                System.out.print("Initial Balance (>= 0): ");
                String balStr = s.nextLine().trim();
                balance = parseDoubleOrNaN(balStr);
                if (Double.isNaN(balance) || balance < 0) {
                    System.out.println("Invalid balance.");
                }
            } while (Double.isNaN(balance) || balance < 0);

            // Password is common for now
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

            String username, password;

            do {
                System.out.print("Username: ");
                username = s.nextLine().trim();
                if (username.isEmpty()) System.out.println("Username cannot be empty.");
            } while (username.isEmpty());

            do {
                System.out.print("Password: ");
                password = s.nextLine();
                if (password.trim().isEmpty()) System.out.println("Password cannot be empty.");
            } while (password.trim().isEmpty());

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

    private static boolean isValidDob(String dob) {
        try {
            LocalDate parsed = LocalDate.parse(dob, DOB_FMT);
            return !parsed.isAfter(LocalDate.now()); // not in the future
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static double parseDoubleOrNaN(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}
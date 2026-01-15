package BankingApp;

public class main extends ObjectsPageFactory {

	public static void main(String[] args) {

        System.out.println("=== Simple Bank App ===");
        System.out.println("1) Sign Up");
        System.out.println("2) Login");
        System.out.println("3) Exit");
        System.out.print("Choose an option: ");

        String choiceStr = s.nextLine();

        switch (choiceStr) {
            case "1":
                BankActions.signup();
                break;

            case "2":
                BankActions.login();
                break;

            case "3":
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Invalid option.");
        }

        s.close();
    }
}
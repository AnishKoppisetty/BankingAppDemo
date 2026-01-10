package BankingApp;

public class main extends ObjectsPageFactory {

    public static void main(String[] args) {
        System.out.print("TYPE 1 to login and 2 to sign up: ");
        int decision = s.nextInt();
        s.nextLine(); 

        switch(decision) {
            case 1:
                UserActions.runLogin();
                break;
            case 2:
                UserActions.runSignUp();
                break;
            default:
                System.out.println("Invalid selection.");
        }
        
        s.close();
    }
}
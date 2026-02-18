package bank;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();
        while (true) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    System.out.println("Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
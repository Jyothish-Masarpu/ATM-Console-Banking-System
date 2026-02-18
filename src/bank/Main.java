package bank;

import java.util.Scanner;

/**
 * Main class for ATM Console Application.
 * Provides menu for creating accounts, login, and performing transactions.
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        // Load accounts from file at startup
        bank.loadAccountsFromFile();

        while (true) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    bank.createAccount();
                    break;

                case 2:
                    Account acc = bank.login();
                    if (acc != null) {
                        // Logged-in session menu
                        while (true) {
                            System.out.println("\n--- Account Menu ---");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Logout");
                            System.out.print("Choose option: ");
                            int opt = sc.nextInt();

                            switch (opt) {
                                case 1 -> bank.deposit(acc);
                                case 2 -> bank.withdraw(acc);
                                case 3 -> bank.checkBalance(acc);
                                case 4 -> {
                                    System.out.println("Logged out successfully.");
                                    break;
                                }
                                default -> System.out.println("Invalid choice! Try again.");
                            }

                            if (opt == 4) break; // exit account menu
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please enter 1, 2, or 3.");
            }
        }
    }
}
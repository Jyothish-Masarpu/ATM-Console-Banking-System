package bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

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
                        while (true) {
                            System.out.println("\n1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Logout");
                            System.out.print("Choose: ");
                            int opt = sc.nextInt();

                            if (opt == 1)
                                bank.deposit(acc);
                            else if (opt == 2)
                                bank.withdraw(acc);
                            else if (opt == 3)
                                bank.checkBalance(acc);
                            else if (opt == 4)
                                break;
                            else
                                System.out.println("Invalid");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
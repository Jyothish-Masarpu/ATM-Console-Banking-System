package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {

    ArrayList<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public void createAccount() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter name: ");
        String name = sc.next();

        System.out.print("Enter pin: ");
        int pin = sc.nextInt();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        Account acc = new Account(accNo, name, pin, balance);
        accounts.add(acc);

        System.out.println("Account created successfully!");
    }
    public Account login() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        for (Account a : accounts) {
            if (a.accountNumber == accNo && a.pin == pin) {
                System.out.println("Login successful!");
                return a;
            }
        }

        System.out.println("Invalid details");
        return null;
    }
    public void deposit(Account acc) {
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();
        acc.balance += amt;
        System.out.println("Deposit successful");
    }
    public void withdraw(Account acc) {
        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        if (amt <= acc.balance) {
            acc.balance -= amt;
            System.out.println("Withdraw successful");
        } else {
            System.out.println("Insufficient balance");
        }
    }
    public void checkBalance(Account acc) {
        System.out.println("Balance: " + acc.balance);
    }
    public void showAccounts() {
        for (Account a : accounts) {
            System.out.println(a.accountNumber + " " + a.name + " " + a.balance);
        }
    }
}
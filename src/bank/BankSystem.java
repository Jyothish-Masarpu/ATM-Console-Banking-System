package bank;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * BankSystem class handles all banking operations including:
 * - Creating accounts with full names and 4-digit PIN
 * - Login with PIN (hidden in terminal)
 * - Deposit, withdraw, and check balance
 * - Persistent storage of accounts in a file
 */
public class BankSystem {

    // List to store all bank accounts
    private ArrayList<Account> accountList = new ArrayList<>();
    private Scanner inputScanner = new Scanner(System.in);
    private final String DATA_FILE = "accounts.txt";  // File to save account data

    // Load accounts from file at program start
    public void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int accountNumber = Integer.parseInt(parts[0]);
                String name = parts[1];
                int pin = Integer.parseInt(parts[2]);
                double balance = Double.parseDouble(parts[3]);
                accountList.add(new Account(accountNumber, name, pin, balance));
            }
        } catch (Exception e) {
            System.out.println("No previous data found. Starting with empty bank.");
        }
    }

    // Save all accounts to file
    public void saveAccountsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Account acc : accountList) {
                writer.write(acc.accountNumber + "," + acc.name + "," + acc.pin + "," + acc.balance);
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create a new account with validation
    public void createAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = inputScanner.nextInt();
        inputScanner.nextLine(); // consume leftover newline

        // Check for duplicate account number
        for (Account existingAcc : accountList) {
            if (existingAcc.accountNumber == accountNumber) {
                System.out.println("Account number already exists! Try a different number.");
                return;
            }
        }

        // Read full name
        System.out.print("Enter account holder name: ");
        String holderName = inputScanner.nextLine();

        // Read 4-digit PIN only
        int pin;
        while (true) {
            System.out.print("Enter PIN (4 digits): ");
            pin = inputScanner.nextInt();
            if (pin >= 1000 && pin <= 9999) {
                break; // valid PIN
            } else {
                System.out.println("Invalid PIN! Please enter exactly 4 digits.");
            }
        }

        // Initial deposit
        System.out.print("Enter initial deposit amount: ");
        double initialBalance = inputScanner.nextDouble();

        // Add account and save
        accountList.add(new Account(accountNumber, holderName, pin, initialBalance));
        saveAccountsToFile();
        System.out.println("Account created successfully! Initial balance: " + initialBalance);
    }

    // Login to existing account (PIN hidden in terminal)
    public Account login() {
        System.out.print("Enter account number: ");
        int accountNumber = inputScanner.nextInt();

        int pin;

        // Try to hide PIN input in terminal
        java.io.Console console = System.console();
        if (console != null) {
            char[] pinChars = console.readPassword("Enter PIN: ");
            pin = Integer.parseInt(new String(pinChars));
        } else {
            // Fallback for IDE: PIN visible
            System.out.print("Enter PIN: ");
            pin = inputScanner.nextInt();
        }

        // Verify credentials
        for (Account acc : accountList) {
            if (acc.accountNumber == accountNumber && acc.pin == pin) {
                System.out.println("Login successful! Welcome " + acc.name);
                return acc;
            }
        }

        System.out.println("Invalid account number or PIN.");
        return null;
    }

    // Deposit money into account
    public void deposit(Account acc) {
        System.out.print("Enter deposit amount: ");
        double amount = inputScanner.nextDouble();
        acc.balance += amount;
        saveAccountsToFile();
        System.out.println("Deposit successful. Current balance: " + acc.balance);
    }

    // Withdraw money from account
    public void withdraw(Account acc) {
        System.out.print("Enter withdrawal amount: ");
        double amount = inputScanner.nextDouble();

        if (amount <= acc.balance) {
            acc.balance -= amount;
            saveAccountsToFile();
            System.out.println("Withdrawal successful. Current balance: " + acc.balance);
        } else {
            System.out.println("Insufficient balance! Withdrawal canceled.");
        }
    }

    // Check current balance
    public void checkBalance(Account acc) {
        System.out.println("Current balance: " + acc.balance);
    }
}
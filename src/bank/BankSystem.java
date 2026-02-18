package bank;
import java.util.ArrayList;
import java.util.Scanner;
public class BankSystem {
    ArrayList<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    public void createAccount() {
        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        Account acc = new Account(accNo, name, pin, balance);
        accounts.add(acc);
        System.out.println("Account Created Successfully");
    }
}
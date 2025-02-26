import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ATMTransaction {
    String type;
    double amount;
    String date;

    public ATMTransaction(String type, double amount, String date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return type + ": Rs." + amount + " on " + date;
    }
}

class ATM {
    private String pin = "1234";
    private double balance = 1000;
    private List<ATMTransaction> transactions = new ArrayList<>();

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            transactions.add(new ATMTransaction("Withdraw", amount, "2025-02-26"));
            System.out.println("Withdrawal completed. Remaining balance: Rs." + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new ATMTransaction("Deposit", amount, "2025-02-26"));
        System.out.println("Deposit completed. Updated balance: Rs." + balance);
    }

    public void printStatement() {
        System.out.println("Mini Statement:");
        for (ATMTransaction t : transactions) {
            System.out.println(t);
        }
    }
}

public class SimpleATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (!atm.checkPin(pin)) {
            System.out.println("Wrong PIN.");
            return;
        }

        System.out.println("\n Welcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Deposit Money");
        System.out.println("4. Print Mini-Statement");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Your balance is: Rs." + atm.getBalance());
                break;
            case 2:
                System.out.print("Enter amount to withdraw: Rs.");
                double withdrawAmount = scanner.nextDouble();
                atm.withdraw(withdrawAmount);
                break;
            case 3:
                System.out.print("Enter amount to deposit: Rs.");
                double depositAmount = scanner.nextDouble();
                atm.deposit(depositAmount);
                break;
            case 4:
                atm.printStatement();
                break;
            default:
                System.out.println("Invalid option!");
        }

        System.out.println("\nThank you for using the ATM.");
        scanner.close();
    }
}
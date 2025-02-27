import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {
    String type;
    double amount;
    String date;

    public Transaction(String type, double amount, String date) {
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
    private List<Transaction> transactions = new ArrayList<>();

    public boolean checkPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance -= amount;
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            transactions.add(new Transaction("Withdraw", amount, date));
            System.out.println("Withdrawal is successful. Remaining balance: Rs." + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        transactions.add(new Transaction("Deposit", amount, date));
        System.out.println("Deposit is successful. Updated balance: Rs." + balance);
    }

    public void printStatement() {
        System.out.println("Mini Statement:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

public class ATMsimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        int attempts = 3;
        boolean authenticated = false;

        while (attempts > 0) {
            System.out.print("Enter your PIN: ");
            String pin = scanner.nextLine();

            if (atm.checkPin(pin)) {
                authenticated = true;
                break;
            } else {
                attempts--;
                System.out.println("Wrong PIN. " + attempts + " attempts left.");
            }
        }

        if (!authenticated) {
            System.out.println("3 wrong attempts");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the ATM!");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Print Mini-Statement");
            System.out.println("5. Exit");
            System.out.print("Choose an option");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: Rs." + atm.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw:Rs.");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit:Rs.");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 4:
                    atm.printStatement();
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using the ATM");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
}
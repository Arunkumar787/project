import java.util.Scanner;

class Customer {
    String name;
    int cid;
    String accno;
    String branch;
    String bank;
    int balance = 0;
    int pin;

    public Customer(String name, int cid, String accno, String branch, String bank, int pin) {
        this.name = name;
        this.cid = cid;
        this.accno = accno;
        this.branch = branch;
        this.bank = bank;
        this.pin = pin;
    }

    public boolean verifyPin(int enteredPin) {
        return enteredPin == pin;
    }

    public void displayInfo() {
        System.out.println("\n--- Account Information ---");
        System.out.println("Name       : " + name);
        System.out.println("Customer ID: " + cid);
        System.out.println("Account No : " + accno);
        System.out.println("Branch     : " + branch);
        System.out.println("Bank       : " + bank);
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
            System.out.println("Updated Balance: ₹" + balance);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public void withdraw(int enteredPin, int amount) {
        if (!verifyPin(enteredPin)) {
            System.out.println("Incorrect PIN. Withdrawal denied.");
            return;
        }
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance.");
        } else {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            System.out.println("Remaining Balance: ₹" + balance);
        }
    }

    public void checkBalance() {
        System.out.println("Available Balance: ₹" + balance);
    }

    public void changePin(Scanner sc) {
        System.out.print("Enter new PIN: ");
        int newPin = sc.nextInt();
        System.out.print("Re-enter new PIN to confirm: ");
        int confirmPin = sc.nextInt();
        if (newPin == confirmPin) {
            pin = newPin;
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("PIN mismatch. Try again.");
        }
    }
}

public class AtmApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Updated customer details
        String name = "Arun";
        int cid = 31052004;
        String accno = "xxxxxxx2416";
        String branch = "NPL";
        String bank = "SBI";
        int pin = 2004;

        Customer customer = new Customer(name, cid, accno, branch, bank, pin);

        System.out.print("Enter your ATM PIN to access your account: ");
        int enteredPin = sc.nextInt();

        if (!customer.verifyPin(enteredPin)) {
            System.out.println("Incorrect PIN. Access Denied.");
            return;
        }

        int choice;
        do {
            showMenu();
            System.out.print("Select an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    customer.displayInfo();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    int depositAmount = sc.nextInt();
                    customer.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter PIN to withdraw: ");
                    int pinCheck = sc.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    int withdrawAmount = sc.nextInt();
                    customer.withdraw(pinCheck, withdrawAmount);
                    break;
                case 4:
                    customer.checkBalance();
                    break;
                case 5:
                    customer.changePin(sc);
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (choice != 6);

        sc.close();
    }

    public static void showMenu() {
        System.out.println("\n-------- ATM Menu --------");
        System.out.println("1. Display Account Info");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Change PIN");
        System.out.println("6. Exit");
    }
}

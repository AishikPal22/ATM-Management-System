import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'INR '###,##0.00");
    HashMap<Integer, Account> data = new HashMap<Integer, Account>();

    public void getLogin() throws IOException {
        boolean end = false;
        int customerNumber = 0;
        int pinNumber = 0;
        //System.out.println(data);
        //if(data.isEmpty()) {
            //System.out.println("No existing account found! Create a new one.");
            //createAccount();
        //}
        //else {
        while (!end) {
            try {
                System.out.print("\nEnter your customer number: ");
                customerNumber = menuInput.nextInt();
                System.out.print("\nEnter your PIN number: ");
                pinNumber = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Account acc = (Account) pair.getValue();
                    if (data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()) {
                        getAccountType(acc);
                        end = true;
                        break;
                    }
                }
                if (!end) {
                    System.out.println("\nWrong Customer Number or Pin Number! Access denied.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Character(s). Only Numbers.");
            }
        }
    }
    //}

    public void getAccountType(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSelect the account you want to access: ");
                System.out.println(" Enter 1 - Checkings Account");
                System.out.println(" Enter 2 - Savings Account");
                System.out.println(" Enter 3 - Log Out.");
                System.out.print("\nEnter your choice: ");

                int selection = menuInput.nextInt();

                switch (selection) {
                    case 1:
                        getChecking(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    public void getChecking(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCheckings Account: ");
                System.out.println(" Enter 1 - View Balance");
                System.out.println(" Enter 2 - Withdraw Funds");
                System.out.println(" Enter 3 - Deposit Funds");
                System.out.println(" Enter 4 - Transfer Funds");
                System.out.println(" Enter 5 - Go back to menu.");
                System.out.print("\nEnter your choice: ");

                int selection = menuInput.nextInt();

                switch (selection) {
                    case 1:
                        System.out.println("\nCheckings Account Balance: " + moneyFormat.format(acc.getCheckingBalance()));
                        break;
                    case 2:
                        acc.getCheckingWithdrawInput();
                        break;
                    case 3:
                        acc.getCheckingDepositInput();
                        break;

                    case 4:
                        acc.getTransferInput("Checkings");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    public void getSaving(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSavings Account: ");
                System.out.println(" Enter 1 - View Balance");
                System.out.println(" Enter 2 - Withdraw Funds");
                System.out.println(" Enter 3 - Deposit Funds");
                System.out.println(" Enter 4 - Transfer Funds");
                System.out.println(" Enter 5 - Go back to menu.");
                System.out.print("\nEnter your choice: ");
                int selection = menuInput.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("\nSavings Account Balance: " + moneyFormat.format(acc.getSavingBalance()));
                        break;
                    case 2:
                        acc.getsavingWithdrawInput();
                        break;
                    case 3:
                        acc.getSavingDepositInput();
                        break;
                    case 4:
                        acc.getTransferInput("Savings");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    public void createAccount() throws IOException {
        int cst_no = 0;
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nCreate your customer number: ");
                cst_no = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    if (!data.containsKey(cst_no)) {
                        end = true;
                    }
                }
                if (!end) {
                    System.out.println("\nThis customer number is already registered.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
        System.out.println("\nEnter PIN to be registered: ");
        int pin = menuInput.nextInt();
        data.put(cst_no, new Account(cst_no, pin));
        System.out.println("\nYour new account has been successfully registered!");
        System.out.println("\nRe-directing to login.............");
        getLogin();
    }

    public void mainMenu() throws IOException {
        data.put(952141, new Account(952141, 191904, 1000, 5000));
        data.put(123, new Account(123, 123, 20000, 50000));
        boolean end = false;
        int count = 0;
        //System.out.println(data);
        while (!end) {
            try {
                System.out.println(data);
                System.out.println();
                System.out.println("\n Enter 1 - Log into an existing account:");
                System.out.println(" Enter 2 - Create a new account:");
                System.out.println(" Enter 3 - Exit.");
                System.out.print("\nEnter your choice: ");
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1:
                        if(count != 0)
                        {
                            getLogin();
                            break;
                        }
                        else if(count == 0)
                        {
                            System.out.println("No existing account found. Create a new one.");
                            count+=1;
                            createAccount();
                            break;
                        }

                    case 2:
                        count+=1;
                        createAccount();
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
        System.out.println("\nThank You for using this ATM. Have a nice day!\n");
        menuInput.close();
        System.exit(0);
    }
}

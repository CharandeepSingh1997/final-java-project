package pkgfinal.java.project;
import java.util.Locale;
import java.text.NumberFormat;
import java.util.Scanner;

public class FinalJavaProject {
  
    String accno;  
    String name;  
    int acc_type;  
    long balance;  
    String str;
   
   
    Scanner sc = new Scanner(System.in);  // to receive user input and parse them 
                                          //into primitive data types such as int, double or default String
    //method to open new account  
    public void openAccount() {  
        System.out.print("Enter Account No: ");  
        accno = sc.next();  
        accoutType();
        System.out.print("Enter Name: ");  
        name = sc.next();  
        System.out.print("Enter Balance: ");  
        balance = sc.nextLong();  
    }  
    public void accoutType() //method to Select Account type for Above new Account 
    {
        while(true) // to check Account type
        {
            System.out.print("Enter Account type:\n ");
            System.out.print("1. Savings 2. Chequing ");
            acc_type= sc.nextInt();
            if(acc_type == 1)
            {
                str = "Saving";
                break;
            }
            else if (acc_type == 2)
            {
                str = "Chequing";
                break;
            }
            else{
            
                System.out.println("Wrong Input! please Try Again");
           
            }
       
        }
      
    }
    //method to display account details  
    public void showAccount() {  
        System.out.println("Name of account holder: " + name);  
        System.out.println("Account no.: " + accno);
        if (acc_type == 1) {
            str = "Saving";
        } else if (acc_type == 2) {
            str = "Chequing";
        }
        System.out.println("Account type: " + str);  
        System.out.println("Balance: " + currenyFormatter(balance)); 
        System.out.println("--------------------------------------------------");
    }  
    //method to deposit money  
    public void deposit() {  
        long amt;  
        System.out.println("Enter the amount you want to deposit: ");  
        amt = sc.nextLong();  
        balance = balance + amt;  
        System.out.printf("Balance after Deposit: " + currenyFormatter(balance)); 
    }  
    //method to withdraw money  
    public void withdrawal() {  
        long amt;  
        System.out.println("Enter the amount you want to withdraw: ");  
        amt = sc.nextLong();  
        if (balance >= amt) {  
            balance = balance - amt;  
            System.out.printf("Balance after withdrawal: " + currenyFormatter(balance));  
        } else {  
            System.out.printf("Your balance is less than "+ currenyFormatter(amt) + "\tTransaction failed...!!" );  
        }  
    }  
    // method to pay utility bills
    public void billPay() {  
        long amt;  
        System.out.println("Enter the amount you want to pay for bills: ");  
        amt = sc.nextLong();  
        if (balance >= amt) {  
            balance = balance - amt;  
            System.out.println("Balance after paying bills: " + currenyFormatter(balance));  
        } else {  
            System.out.println("Your balance is less than " + currenyFormatter(amt) + "\tTransaction failed...!!" );  
        }  
    }  
    
    String currenyFormatter(long amount) {
        //set currency formatter country to canada
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA);  
        //set values to keep after decimal
        formatter.setMaximumFractionDigits(0);
        return formatter.format(amount);  
    }
    //method to search an account number  
    public boolean search(String ac_no) {  
        if (accno.equals(ac_no)) {  
            showAccount();  
            return (true);  
        }  
        return (false);  
    }
    
    
}  
  

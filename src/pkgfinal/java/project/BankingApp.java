package pkgfinal.java.project;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class BankingApp {  
        
    public static void main(String arg[]) { 
        
        BankingApp app = new BankingApp(); // Create an object of class BankingApp
        
        ArrayList<FinalJavaProject> users = new ArrayList<FinalJavaProject>(); // creating a arraylist
        
        try {
            Scanner fileScanner = new Scanner(new File("users.csv")); // create scanner instance
            fileScanner.useDelimiter(","); //  set comma as delimiter

            int index = 0, traverseFileIndex = 0;
            
             // read all fields
            while(fileScanner.hasNext()){
                final String value = fileScanner.next().trim(); // trim() is used to remove all whitespace from front and back of character.
                if (traverseFileIndex == 0) {
                    users.add(new FinalJavaProject()); // add user from class FinalJavaProject
                    users.get(index).accno = value;
                } else if (traverseFileIndex == 1) {
                    
                    users.get(index).name = value;
                } else if (traverseFileIndex == 2) {
                    
                    users.get(index).acc_type = Integer.parseInt(value); //typed conversion for converting a string value to an integer by using the method parseInt()
                } else if (traverseFileIndex == 3) {
                    users.get(index).balance = Long.parseLong(value);
                    index++;
                }
                if (traverseFileIndex <= 2) {
                    traverseFileIndex++;
                } else {
                    traverseFileIndex = 0;
                }
            }
            fileScanner.close();
        } catch(FileNotFoundException e) {
            System.out.println("Error in finding file = " + e);
        }
       
        Scanner sc = new Scanner(System.in);
        
         
        boolean wantToContinue = true;  
        do {  
            System.out.println("\n ***Banking System Application***");  
            System.out.println(" 0 .Add User \n 1. Display all account details \n 2. Search by Account number\n 3. Deposit the amount \n"
                    + " 4. Withdraw the amount \n 5. Transfer money to other accounts within the bank \n 6. Pay utility bills \n"
                    + " 7. Exit ");  
            System.out.println("****************************************************");
            System.out.println("Enter your choice: ");  
            final int ch = sc.nextInt();  
                switch (ch) {  
                   // Add new User
                    case 0:
                        FinalJavaProject newUser = new FinalJavaProject(); // Create an object of class FinalJavaProject
                        newUser.openAccount(); // calling method from class FinalJavaProject
                        users.add(newUser);     // Add new user into an arraylist
                        app.createFile(users); // Add data into a CSV file
                   
                        // Display all account details
                    case 1:  
                        // For each loop is used to iterate through elements of arrays and collections (like ArrayList)
                        for(FinalJavaProject user: users) {
                            user.showAccount();  
                        }  
                        break;  
                        //Search by Account number
                    case 2:  
                        System.out.print("Enter account no. you want to search: ");  
                        String ac_no = sc.next();  
                        boolean found = false;  
                        for(FinalJavaProject user: users) {
                            found = user.search(ac_no);  
                            if (found) {  
                                break;  
                            }  
                        }  
                        if (!found) {  
                            System.out.println("Search failed! Account doesn't exist..!!");  
                        }  
                        break;  
                    
                    //Deposit the amount
                    case 3:  
                        System.out.print("Enter Account no. : ");  
                        ac_no = sc.next();  
                        found = false;  
                        for(FinalJavaProject user: users) {
                            found = user.search(ac_no);  
                            if (found) {  
                                user.deposit();  
                                app.createFile(users);
                                break;  
                            }  
                        }  
                        if (!found) {  
                            System.out.println("Search failed! Account doesn't exist..!!");  
                        }  
                        break;  
                    
                    //Withdraw the amount
                    case 4:  
                        System.out.print("Enter Account No : ");  
                        ac_no = sc.next();  
                        found = false;  
                        for(FinalJavaProject user: users) {
                            found = user.search(ac_no);  
                            if (found) 
                            {    
                                user.withdrawal();  
                                app.createFile(users);
                                break;  
                            }  
                        }  
                        if (!found) {  
                            System.out.println("Search failed! Account doesn't exist..!!");  
                        }  
                        break;  
                    
                    //Transfer money to other accounts within the bank
                    case 5:  
                        System.out.print("Enter Account No: ");  
                        ac_no = sc.next();  
                        found = false;  
                        for(FinalJavaProject user: users) {
                            found = user.search(ac_no);  
                            if (found) {
                                System.out.println("Enter the amount you want to transfer:");
                                final Long amountToTransfer = sc.nextLong();
                                if (amountToTransfer > user.balance) {
                                    System.out.println("Amount not in your balance");
                                    break;
                                }
                                System.out.println("Enter the account number you would like to pay amount to ");
                                final String toPayAccNo = sc.next();
                                if (ac_no.equals(toPayAccNo)) {
                                    System.out.println("Account no cannot be same");
                                    break;
                                }
                                
                                boolean foundTransferUser = false;
                                for(FinalJavaProject transferToUser: users) {
                                    foundTransferUser = transferToUser.search(toPayAccNo); 
                                    
                                    if(foundTransferUser) {
                                        user.balance = user.balance - amountToTransfer;
                                        transferToUser.balance = transferToUser.balance + amountToTransfer;
                                        app.createFile(users);
                                        break;
                                    }
                                    
                                }
                                
                                if (!foundTransferUser) {
                                    System.out.println("Search failed! Account doesn't exist..!!");  
                                }
                                
                                break;
                            }
                        }      
                        
                        if (!found) {  
                            System.out.println("Search failed! Account doesn't exist..!!");  
                        }   
                        break; 
                        
                        //Pay utility bills
                    case 6:
                        System.out.print("Enter Account No : ");  
                        ac_no = sc.next();  
                        found = false;  
                        for(FinalJavaProject user: users) {
                            found = user.search(ac_no);  
                            if (found) 
                            {  
                                
                                 System.out.println(" 1. Pay Internet Bills \n 2. Pay Insurance \n 3. Pay Loan Amount \n 4. Pay Electricity Bill \n 5. None ");
                                 System.out.println("Enter the choice");
                                 int choice = sc.nextInt();
                                 if (choice==1)
                                 {
                                    user.billPay();
                                    app.createFile(users);
                                   
                                 }
                                 else if(choice==2)
                                 {
                                    user.billPay();  
                                    System.out.println("Amount Paid for Insurance");
                                    app.createFile(users);   
                                 }
                                 else if(choice==3)
                                 {
                                    user.billPay();  
                                    System.out.println("Amount Paid for Loan"); 
                                    app.createFile(users);
                                     
                                 }
                                 else if(choice==4)
                                 {
                                    user.billPay();  
                                  System.out.println("Amount Paid for Electricity Bills");  
                                  app.createFile(users);
                                 }
                                    else
                                 {
                                 System.out.println("No Option Selected..."); 
                                 }}
                              
                            }  
                        
                        if (!found) {  
                            System.out.println("Search failed! Account doesn't exist..!!");  
                        }  
                           
                                                   
                        break;
                        case 7:  
                            System.out.println("See you soon...");
                            wantToContinue = false;
                        break; 
                }  
            }  
            while(wantToContinue);  
        }  
    
    
    // Write output in CSV file
    void createFile(ArrayList<FinalJavaProject> users) {
        try {
            FileWriter myWriter = new FileWriter("users.csv");
            String fileText = "";
            for (FinalJavaProject user: users) {
                if (!fileText.isEmpty()) {
                      fileText = fileText + ",";
                  }
                 fileText = fileText +  user.accno + "," + user.name + "," + user.acc_type + "," + user.balance;
            }
            myWriter.write(fileText);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in createFile = " + e);
        }
    }

}
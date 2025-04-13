package chatapp.pkg1;
import java.util.*;

public class ChatApp1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Infinite loop to keep the program running until user chooses to exit
        while (true){
        // Displaying menu options to the user
        System.out.println("1. Register");
        System.out.println("2. Login ");
        System.out.println("3. Exite ");
        System.out.println("Please Login or create an account...");
        String choice = scanner.nextLine();
          
        if (choice.equals("1")){
              System.out.print("Enter Username: ");
              String UserName = scanner.nextLine();
    
              System.out.print("Enter Cell Number: ");
              String CellNumber = scanner.nextLine();
    
              System.out.print("Create Password: ");
              String PassWord = scanner.nextLine();
    
               // Creating Register object and setting user details
            Register register = new Register();   
              register.userName(UserName);
              register.Number(CellNumber);
              register.passWord(PassWord);
      
              System.out.println("===Register===");
              if(register.Username(UserName)){
              System.out.println("Username successfully captured.");
              }else{
              System.out.println("Username is not correctly formatted. It should contain an underscore( _ ) and be no more than 5 characters.");
              }
              if(register.Password(PassWord)){ 
              System.out.println("Password successfully captured.");
              }else{
              System.out.println("Password is not correctly formatted; it must contain at least 8 characters, a capital letter, a number, and a special character.");
              }
              if (register.CellNumber(CellNumber)){
              System.out.println("Cell phone number successfully added.");
              }else{
              System.out.println("Cell phone number incorrect formatted or doses not contain international code (+27XXXXXXXXX).");
              }
              break;// Exit loop after registration

      
        } else if (choice.equals("2")){
              System.out.print("Enter Username: ");
              String UserName = scanner.nextLine();

              System.out.print("Enter Password: ");
              String PassWord = scanner.nextLine();
              
              // Creating Login object and setting login credentials
            Login log = new Login(); 
              log.userName(UserName);
              log.passWord(PassWord);
     
              System.out.println("===Login===");
              if(log.Username(UserName)){
              System.out.println("Welcome " + UserName + " it is great to see you");
              }else{
              System.out.println("Username or Password is incorrect, please try again");
              }
              break; 
              
                
        } else if (choice.equals("3")){
            System.out.println("Goodbye...");
            break;
        } else {
            System.out.println("Invalid choice");
            scanner.close();
            break;
        }
        }  
    }
    
}

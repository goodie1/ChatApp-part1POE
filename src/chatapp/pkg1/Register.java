
package chatapp.pkg1;
import java.io.FileWriter;
import java.io.IOException;
public class Register {
    
    private String Firstname;
    private String Lastname;
    private String UserName;
    private String PassWord;
    private String CellNumber;
    
    
    // Setter method 
    public Register(String firstname, String LastName, String Username, String Password, String Cellnumber){
        this.Firstname = firstname;
        this.Lastname = LastName;
        this.UserName = Username;
        this.PassWord = Password;
        this.CellNumber = Cellnumber;
    }

    // Getters
    public String getfirstname() {
        return Firstname;
    }

    public String getlastname() {
        return Lastname;
    }

    public String getusername() {
        return UserName;
    }

    public String getpassWord() {
        return PassWord;
    }

    public String getcellNumber() {
        return CellNumber;
    }

    
}

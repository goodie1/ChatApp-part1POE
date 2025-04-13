
package chatapp.pkg1;
public class Register {
    
    private String UserName;
    private String PassWord;
    private String CellNumber;
    
    // Setter method 
    public void userName(String UserName){
        this.UserName = UserName;
    }
    public void passWord(String PassWord){
        this.PassWord = PassWord;
    }
    public void Number (String CellNumber){
        this.CellNumber = CellNumber;
    } 
    
    public static boolean Username(String UserName){
         // Returns true only if username contains "_" and is 5 characters or less
        return UserName.contains("_")&& UserName.length()<=5;
    }
    public static boolean Password(String PassWord){
        // Regex explanation:At least one uppercase letter, one lowercase letter, one digit,one special character from [@#$%^&+=]
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return PassWord.matches(regex);
    }
    public static boolean CellNumber(String CellNumber){
         // Validates South African phone number format (starts with +27 followed by 9 digits)
        String regex = "^\\+27\\d{9}$";
        return CellNumber.matches(regex);
    }
}


package chatapp.pkg1;
public class Login {
    private String UserName;
    private String PassWord;
    
    // Setter method 
    public void userName(String UserName){
        this.UserName = UserName;
    }
    public void passWord(String PassWord){
        this.PassWord = PassWord;
    }
   
    
     public static boolean Username(String UserName){
          // Returns true only if username contains "_" and is 5 characters or less
        return UserName.contains("_") && UserName.length()<=5;
    }
    public static boolean Password(String PassWord){
         // Regex explanation:At least one uppercase letter, one lowercase letter, one digit,one special character from [@#$%^&+=]
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
        return PassWord.matches(regex);
    }
}

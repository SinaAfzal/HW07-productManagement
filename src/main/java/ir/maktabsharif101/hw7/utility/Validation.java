package ir.maktabsharif101.hw7.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isPasswordValid(String password){
        Pattern pattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");//at least one upper-case, one lower-case and one digit and at least 8 characters
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email){
        Pattern pattern=Pattern.compile("^(.+)@(\\S+)$"); //check the presence of @ .
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }



}

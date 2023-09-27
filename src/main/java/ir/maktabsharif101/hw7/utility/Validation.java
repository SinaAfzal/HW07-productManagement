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

    public static boolean isWebsiteValid(String website){
        Pattern pattern=Pattern.compile("[Ww][Ww][Ww]\\.[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher=pattern.matcher(website);
        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber){
        Pattern pattern=Pattern.compile("^[1-9]\\d{6,9}$");//7 to 10 digit number not starting with zero
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();
    }





}

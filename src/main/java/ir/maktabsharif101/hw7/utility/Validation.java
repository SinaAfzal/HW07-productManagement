package ir.maktabsharif101.hw7.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isPasswordValid(String password){
        Pattern pattern =
                Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        Matcher matcher=pattern.matcher(password);
        return matcher.matches();
    }

}

package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {
    private LoginValidator(){}

    public static boolean validate(String login){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.UNIVERSAL_PATTERN);
        Matcher matcher = needed.matcher(login);
        if(login.length()<=20 && matcher.find()==true){
            if(login.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

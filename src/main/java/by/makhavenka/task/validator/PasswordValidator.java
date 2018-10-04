package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private PasswordValidator(){}
    public static boolean validate(String password){
        boolean result = false;

        Pattern needed = Pattern.compile(ConstantValidator.UNIVERSAL_PATTERN);
        Matcher matcher = needed.matcher(password);
        if(password.length()<=20 && matcher.find()==true){
            if(password.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

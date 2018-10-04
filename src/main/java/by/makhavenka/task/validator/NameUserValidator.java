package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameUserValidator {
    private NameUserValidator(){}
    public static boolean validate(String userName){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.TEXT_PATTERN);
        Matcher matcher = needed.matcher(userName);
        if(userName.length()<=20 && matcher.find()==true){
            if(userName.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

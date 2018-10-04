package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SurnameValidator {
    private SurnameValidator(){}
    public static boolean validate(String surname){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.TEXT_PATTERN);
        Matcher matcher = needed.matcher(surname);
        if(surname.length()<=20 && matcher.find()==true){
            if(surname.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

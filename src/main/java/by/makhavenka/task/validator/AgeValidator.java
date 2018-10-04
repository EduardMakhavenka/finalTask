package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AgeValidator {
    private AgeValidator(){}
    public static boolean validate(String age){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.AGE_PATTERN);
        Matcher matcher = needed.matcher(age);
        if(age.length()<=20 && matcher.find()==true){
            if(age.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

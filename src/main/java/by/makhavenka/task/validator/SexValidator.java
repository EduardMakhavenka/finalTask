package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SexValidator {
    private SexValidator(){}
    public static boolean validate(String sex){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.TEXT_PATTERN);
        Matcher matcher = needed.matcher(sex);
        if(sex.length()<=20 && matcher.find()==true){
            if(sex.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

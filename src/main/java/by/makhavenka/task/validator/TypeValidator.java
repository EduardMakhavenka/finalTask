package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeValidator {
    private TypeValidator(){}
    public static boolean validate(String type){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.TEXT_PATTERN);
        Matcher matcher = needed.matcher(type);
        if(type.length()<=20 && matcher.find()==true){
            if(type.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

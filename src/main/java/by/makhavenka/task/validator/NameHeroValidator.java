package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameHeroValidator {
    private NameHeroValidator(){}
    public static boolean validate(String name){
        boolean result = false;
        if(name==null){return result;}
        Pattern needed = Pattern.compile(ConstantValidator.TEXT_DIGIT_PATTERN);
        Matcher matcher = needed.matcher(name);
        if(name.length()<=20 && matcher.find()==true){
            if(name.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

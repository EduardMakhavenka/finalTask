package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    private PhoneValidator(){}
    public static boolean validate(String phone){
        boolean result = false;
        if(phone==null){return result;}
        Pattern needed = Pattern.compile(ConstantValidator.PHONE_PATTERN);
        Matcher matcher = needed.matcher(phone);
        if(phone.length()<=20 && matcher.find()==true){
            if(phone.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

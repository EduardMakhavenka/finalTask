package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NicknameValidator {
    private NicknameValidator(){}
    public static boolean validate(String nickName){
        boolean result = false;
        if(nickName==null){return result;}
        Pattern needed = Pattern.compile(ConstantValidator.EN_TEXT_PATTERN);
        Matcher matcher = needed.matcher(nickName);
        if(nickName.length()<=20 && matcher.find()==true){
            if(nickName.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

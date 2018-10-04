package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RatingValidator {
    private RatingValidator(){}
    public static boolean validate(String rating){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.RATING_PATTERN);
        Matcher matcher = needed.matcher(rating);
        if(rating.length()<=4 && matcher.find()==true){
            if(rating.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

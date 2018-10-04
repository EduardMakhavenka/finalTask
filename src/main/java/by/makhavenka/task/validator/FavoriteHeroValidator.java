package by.makhavenka.task.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FavoriteHeroValidator {
    private FavoriteHeroValidator(){}
    public static boolean validate(String hero){
        boolean result = false;
        Pattern needed = Pattern.compile(ConstantValidator.TEXT_PATTERN);
        Matcher matcher = needed.matcher(hero);
        if(hero.length()<=20 && matcher.find()==true){
            if(hero.equals(matcher.group())){
                result= true;
            }
        }
        return result;
    }
}

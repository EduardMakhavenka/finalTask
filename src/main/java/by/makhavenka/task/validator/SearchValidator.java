package by.makhavenka.task.validator;


public class SearchValidator {
    private SearchValidator(){}
    public static boolean validate(String search){
        if(search.length()<=20){
           return true;
        }
        return false;
    }
}

package by.makhavenka.task.validator;

public class DescriptionValidator {
    private DescriptionValidator(){}
    public static boolean validate(String description){
        if(description.length()<=2000){
            return true;
        }
        return false;
    }
}

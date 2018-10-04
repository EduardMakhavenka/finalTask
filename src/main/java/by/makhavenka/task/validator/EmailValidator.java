package by.makhavenka.task.validator;


public class EmailValidator {
    private EmailValidator(){}
    public static boolean validate(String email){
        boolean result = false;
        if(email.length()<=30){
                result= true;
        }
        return result;
    }
}

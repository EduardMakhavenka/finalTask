package by.makhavenka.task.validator;

public class CommentValidator {
    private CommentValidator(){}
    public static boolean validate(String comment){
        if(comment.length()<=355){
            return true;
        }
        return false;
    }
}

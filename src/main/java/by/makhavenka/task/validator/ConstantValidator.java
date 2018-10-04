package by.makhavenka.task.validator;

class ConstantValidator {
    private ConstantValidator(){}

    static final String UNIVERSAL_PATTERN = "([A-Za-z-0-9]){1,20}";
    static final String AGE_PATTERN = "[0-9]{0,2}";
    static final String TEXT_PATTERN = "[A-Za-zА-Яа-я]{0,20}";
    static final String TEXT_DIGIT_PATTERN = "([A-Za-zА-Яа-я-0-9]){0,20}";
    static final String EN_TEXT_PATTERN = "[A-Za-z-0-9]{0,20}";
    static final String PHONE_PATTERN = "[0-9-+]{0,20}";
    static final String RATING_PATTERN = "[0-9]{0,4}";
}

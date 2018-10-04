package by.makhavenka.task.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestEmailValidator {

    @Test
    public void testValidator(){
        String test = "11111222223333344444555556666677777";
        Assert.assertEquals(EmailValidator.validate(test),false);
    }

    @Test
    public void testTwoValidator(){
        String test = "111112222233333444445555566666";
        Assert.assertEquals(EmailValidator.validate(test),true);
    }
}

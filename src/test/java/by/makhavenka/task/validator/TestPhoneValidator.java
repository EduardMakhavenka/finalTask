package by.makhavenka.task.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPhoneValidator {

    @Test
    public void testValidator(){
        String test = "+375-29-760-45-11";
        Assert.assertEquals(PhoneValidator.validate(test),true);
    }

    @Test
    public void testTwoValidator(){
        String test = "+375fdf-2sdds9-760-dfd45-11";
        Assert.assertEquals(PhoneValidator.validate(test),false);
    }
}

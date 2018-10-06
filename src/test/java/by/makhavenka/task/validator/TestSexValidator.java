package by.makhavenka.task.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSexValidator {

    @Test
    public void testValidator(){
        String test ="dddввв";
        Assert.assertEquals(SexValidator.validate(test),true);
    }

    @Test
    public void testTwoValidator(){
        String test ="dddввв12";
        Assert.assertEquals(SexValidator.validate(test),false);
    }

    @Test
    public void tesThreetValidator(){
        String test ="dddвввdsfdsfdsfdsfdsfdsfdsfds";
        Assert.assertEquals(SexValidator.validate(test),false);
    }

    @Test
    public void testFourValidator(){
        String test ="";
        Assert.assertEquals(SexValidator.validate(test),true);
    }

    @Test
    public void testFiveValidator(){
        String test =null;
        Assert.assertEquals(SexValidator.validate(test),false);
    }
}

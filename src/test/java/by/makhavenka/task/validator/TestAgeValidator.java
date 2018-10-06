package by.makhavenka.task.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAgeValidator {

    @Test
    public void testValidate(){
        String testAge = "11";
        Assert.assertEquals(AgeValidator.validate(testAge),true);
    }

    @Test
    public void testTwoValidate(){
        String testAge = "111";
        Assert.assertEquals(AgeValidator.validate(testAge),false);
    }

    @Test
    public void testThreeValidate(){
        String testAge = "dsfds";
        Assert.assertEquals(AgeValidator.validate(testAge),false);
    }

    @Test
    public void testFourValidate(){
        String testAge = "";
        Assert.assertEquals(AgeValidator.validate(testAge),true);
    }

    @Test
    public void testFiveValidate(){
        String testAge = null;
        Assert.assertEquals(AgeValidator.validate(testAge),false);
    }
}

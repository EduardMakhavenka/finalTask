package by.makhavenka.task.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLoginValidator {

    @Test
    public  void testValidate(){
        String testLogin= "login";
        Assert.assertEquals(LoginValidator.validate(testLogin),true);
    }

    @Test
    public  void tesTwoValidate(){
        String testLogin= "login12321";
        Assert.assertEquals(LoginValidator.validate(testLogin),true);
    }

    @Test
    public  void testThreeValidate(){
        String testLogin= "loginloginloginloginlogin";
        Assert.assertEquals(LoginValidator.validate(testLogin),false);
    }

    @Test
    public  void testFourValidate(){
        String testLogin= "loginloginloginloginlogin21321321";
        Assert.assertEquals(LoginValidator.validate(testLogin),false);
    }

    @Test
    public  void testFiveValidate(){
        String testLogin= "";
        Assert.assertEquals(LoginValidator.validate(testLogin),false);
    }
}

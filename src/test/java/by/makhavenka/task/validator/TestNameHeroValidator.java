package by.makhavenka.task.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNameHeroValidator {

    @Test
    public void testValidator(){
        String test ="dddввв";
        Assert.assertEquals(NameHeroValidator.validate(test),true);
    }

    @Test
    public void testTwoValidator(){
        String test ="dddввв12";
        Assert.assertEquals(NameHeroValidator.validate(test),true);
    }

    @Test
    public void tesThreetValidator(){
        String test ="dddвввdsfdsfdsfdsfdsfdsfdsfds";
        Assert.assertEquals(NameHeroValidator.validate(test),false);
    }

    @Test
    public void testFourValidator(){
        String test ="";
        Assert.assertEquals(NameHeroValidator.validate(test),true);
    }
}

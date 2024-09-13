package config;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;

public class TestData {
    @Step("")
@DataProvider(name = "loginData")
    public Object [][]loginData(){
    return new Object[][]{
            {EmailGenerator.generateEmail(5,5,3), PasswordStringGenerator.generateRandomPassword(),401},
            {EmailGenerator.generateEmail(5,5,3), PasswordStringGenerator.generateRandomPassword(),401}
    };

    }

    @DataProvider(name = "registrationData")
    public Object [][]registrationData(){
        return new Object[][]{
                {EmailGenerator.generateEmail(5,5,3), PasswordStringGenerator.generateRandomPassword(),401},
                {EmailGenerator.generateEmail(5,5,3), PasswordStringGenerator.generateRandomPassword(),401}
        };

    }

}

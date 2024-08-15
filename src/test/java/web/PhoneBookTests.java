package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.AlertHandler;
import helpers.EmailGenerator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

public class PhoneBookTests extends BaseTest {

    @Test
    public void successfullLogin(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField("bigbrother@gmail.com")
                .fillPasswordField(" Tr43123456!")
                .clickByLoginButton();
        //TASK 2
        boolean result = ContactsPage
                .isElementPersist(getDriver().findElement(By.xpath("//button[contains(text(),'Sign Out')]")));

        Assert.assertTrue(result);
    }

    @Test
    public void unsRegistrWithoutPassword(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage.fillEmailField(EmailGenerator.generateEmail(5,5,4)).clickByRegistrationButton();
        String expectedTextAlert = "Wrong";
       boolean isAlertHandled =  AlertHandler.handleAlert(alert,expectedTextAlert);
       Assert.assertTrue(isAlertHandled);
    }

}

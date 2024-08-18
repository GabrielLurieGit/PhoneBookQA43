package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.AlertHandler;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactsPage;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

public class PhoneBookTests extends BaseTest implements TestHelper {

    @Test
    public void successfullLogin(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReaderXML.getProperties(MY_USER,XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE))
                .clickByLoginButton();
        //TASK 2
        ContactsPage contactsPage = new ContactsPage(getDriver());
        WebElement signOutButton = contactsPage.getSignOutButton();
        boolean result = ContactsPage
                .isElementPersist(signOutButton);
        Assert.assertTrue(result);
    }

    @Test
    public void unsuccessfulRegWithoutPassword(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage.fillEmailField(EmailGenerator.generateEmail(5,5,4)).clickByRegistrationButtonAlert();
        String expectedTextAlert = VALIDATION_MESSAGE1;
       boolean isAlertHandled =  AlertHandler.handleAlert(alert,expectedTextAlert);
       Assert.assertTrue(isAlertHandled);
    }

    @Test void unsuccessfulLoginInvalidPassword(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField(EmailGenerator.generateEmail(5,5,3))
                .fillPasswordField(PropertiesReaderXML.getProperties(INVALID_PASS,XML_DATA_FILE))
                .clickByLoginButtonAlert();
        String expectedTextAlert = VALIDATION_MESSAGE1;
        boolean isAlertHandled =  AlertHandler.handleAlert(alert,expectedTextAlert);
        Assert.assertTrue(isAlertHandled);
    }

    @Test void unsuccessfulLoginInvalidEmail(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage.
                fillEmailField(PropertiesReaderXML.getProperties(INVALID_EMAIL,XML_DATA_FILE))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByLoginButtonAlert();
        String expectedTextAlert = VALIDATION_MESSAGE1;
        boolean isAlertHandled =  AlertHandler.handleAlert(alert,expectedTextAlert);
        Assert.assertTrue(isAlertHandled);
    }

}

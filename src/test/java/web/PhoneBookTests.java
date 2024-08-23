package web;

import config.BaseTest;
import enums.TopMenuItem;
import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
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

@Test
    public void loginWithoutPassword(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        Alert alert = loginPage
                .fillEmailField(EmailGenerator.generateEmail(5,5,4))
                .clickByLoginButtonAlert();
        String expectedTextAlert = VALIDATION_MESSAGE1;
        boolean isAlertHandled =  AlertHandler.handleAlert(alert,expectedTextAlert);
        Assert.assertTrue(isAlertHandled);
    }
@Test
    public void loginOfAnExistingUserAddContact(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReaderXML.getProperties(MY_USER,XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE))
                .clickByLoginButton();
        AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),"Text descr");
    System.out.println("Contact" + contact.toString());
    addPage.fillContactFormAndSave(contact);
    ContactsPage contactsPage = new ContactsPage(getDriver());
    System.out.println("Contacts size" + contactsPage.getContactListSize());
    TakeScreen.takeScreenShot(getDriver(),"loginOfAnExistingUserAddContact");
    Assert.assertTrue(contactsPage.getDataFromContactList(contact));
    }




    @Test //needs to be fixed
    public void loginOfAnExistingUserAddEditContact(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage.fillEmailField(PropertiesReaderXML.getProperties(MY_USER,XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE))
                .clickByLoginButton();

        AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);

        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),"Text description");
        System.out.println("Contact current" + contact.toString());
        addPage.fillContactFormAndSave(contact);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement nameContact = wait.
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//h2[contains(text(),'"+contact.getName().toString()+"')]")));
        nameContact.click();
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Edit')]")));
        editButton.click();
        WebElement lastNameField = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")));
        lastNameField.clear();
        lastNameField.sendKeys("McGregor");
        String lastNameFieldValue = lastNameField.getAttribute("value");
        contact.setLastName(lastNameFieldValue);
        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Save')]")));
        saveButton.click();
        ContactsPage contactsPage = new ContactsPage(getDriver());
        System.out.println("Contact after editing" + contact.toString());
    }


    @Test
    public void createAndDeleteContactUsingSerialization() throws IOException, ClassNotFoundException {
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = BasePage.openTopMenuItem(TopMenuItem.LOGIN);
        loginPage
                .fillEmailField(PropertiesReaderXML.getProperties(MY_USER,XML_DATA_FILE))
                .fillPasswordField(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE))
                .clickByLoginButton();
        AddPage addPage = BasePage.openTopMenuItem(TopMenuItem.ADD);
        Contact contact = new Contact(NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),"Text descr");
        System.out.println("Contact" + contact.toString());
        addPage.fillContactFormAndSave(contact);
        Contact.serializationContact(contact,"initContact.dat");
        Contact deserealizedContact = Contact.deserializationContact("initContact.dat");
    }




}

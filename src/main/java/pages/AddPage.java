package pages;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPage extends BasePage{
    @FindBy(xpath = "//input[@placeholder='Name']") ////input[@class='UiEmailSignupstyles__InputEmail-sc-9nggyw-1 ZIPtJ']
    WebElement nameField;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='Phone']")
    WebElement phoneField;

    @FindBy(xpath = "//input[@placeholder='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Address']")
    WebElement addressField;

    @FindBy(xpath = "//input[@placeholder='description']")
    WebElement descriptionField;

    @FindBy(xpath = "//b[contains(text(),Save)]")
    WebElement saveButton;


    public void fillContactFormAndSave(Contact contact){
        nameField.sendKeys(contact.getName());
        lastNameField.sendKeys(contact.getLastName());
        if(contact.getPhone().length() >= 10 || contact.getPhone().length() <= 15){
            phoneField.sendKeys(contact.getPhone());
        }else{throw new IllegalArgumentException("Phone number issue...");}
        emailField.sendKeys(contact.getEmail());
        addressField.sendKeys(contact.getAddress());
        descriptionField.sendKeys(contact.getDescription());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement saveButton =
//                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),Save)")));
//        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        saveButton.click();
    }



//    public AddPage fillEmailField(String email){
//        emailField.sendKeys(email);
//        return this;
//    }

    public AddPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
    }
}

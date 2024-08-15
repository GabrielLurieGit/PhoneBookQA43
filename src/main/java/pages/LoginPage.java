package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name='login']")
    WebElement loginButton;

    @FindBy(xpath = "//button[@name='registration']")
    WebElement registrationButton;

    public LoginPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
    }

    public LoginPage fillEmailField(String email){
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage fillPasswordField(String password){
        passwordField.sendKeys(password);
        return this;
    }

    private Alert getAlertIfPresent(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.alertIsPresent());
        }catch (TimeoutException exception){
            System.out.println("There's no alert..");
            return null;
        }
    }
    public BasePage clickByLoginButton(){
        loginButton.click();
        Alert alert = getAlertIfPresent();
        if(alert != null){
            alert.accept();
            return this;
        }else return new ContactsPage(driver);
    }


    public Alert clickByRegistrationButton() {
        registrationButton.click();
        return getAlertIfPresent();
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MainPage extends BasePage{
// TASK
    @FindBy(xpath = "//a[contains(text(),'LOGIN')]")
    WebElement loginTopMenuButton;
    public MainPage(WebDriver driver){
        setDriver(driver);
        driver.get("https://telranedu.web.app/");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
    }

    public LoginPage clickByLoginButton(){
        loginTopMenuButton.click();
        return new LoginPage(driver);

    }
}


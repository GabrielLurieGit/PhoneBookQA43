package experiments;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestLearningClass {

@Test
    public void registrationPhoneBook(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/");
        WebElement loginTab = driver.findElement(By.xpath("//a[@href='/login']"));
        loginTab.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
        emailField.sendKeys("ggman@gmail.cim");
        WebElement passwordField = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        passwordField.sendKeys("Atdm65321!");
        WebElement registrButton = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='registration']")));
        registrButton.click();
        WebElement signOutButton = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Sign Out')]")));
        boolean res = isElementDisplayed(signOutButton);
        Assert.assertTrue(res);
    }

    public boolean isElementDisplayed(WebElement element){

    try {
        element.isDisplayed();
        return true;
    }catch (NoSuchElementException | NullPointerException exception){
        return false;
    }

    }
}

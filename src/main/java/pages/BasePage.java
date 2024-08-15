package pages;

import enums.TopMenuItem;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected static WebDriver driver;


    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }

    public static boolean isElementPersist(WebElement element){
        try{
            element.isDisplayed();
            return true;
        }catch (NoSuchElementException | NullPointerException exception){
            return false;
        }
    }

    public static <T extends BasePage> T openTopMenuItem(TopMenuItem topMenuItem){
        WebElement menuItem =
                driver.findElement(By.xpath("//a[contains(text(),'"+ topMenuItem +"')]"));
        menuItem.click();
        switch(topMenuItem.toString()){
            case "HOME":
                return (T) new HomePage(driver);
            case "ABOUT":
                return (T) new AboutPage(driver);
            case "LOGIN":
                return (T) new LoginPage(driver);
            case "CONTACTS":
                return (T) new ContactsPage(driver);
            case "ADD":
                return (T) new AddPage(driver);
            default:throw new IllegalArgumentException("Wrong top menu item...");
        }

    }







}

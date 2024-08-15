package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class AlertHandler {
    private WebDriver driver;

    public AlertHandler(WebDriver driver) {
        this.driver = driver;
    }

    public static boolean handleAlert(Alert alert,String expectedText){
        if(alert != null){
           String alertText = alert.getText();
           alert.accept();
           return alertText.contains(expectedText);
        }
        else{
            System.out.println("There's no alert...");
            return false;
        }
    }
}

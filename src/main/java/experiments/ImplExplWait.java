package experiments;

import org.apache.poi.ss.formula.functions.Now;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ImplExplWait {
    public static void main(String[] args) {
        long startTime,endTime,duration;
            startTime = System.currentTimeMillis();
            waitForAnElementExplicit();
            endTime = System.currentTimeMillis();
            duration = endTime-startTime;
        System.out.println("Expl : " + duration);

        startTime = System.currentTimeMillis();
        waitForAnElementImplicit();
        endTime = System.currentTimeMillis();
        duration = endTime-startTime;
        System.out.println("Impl : " + duration);


    }
    public static void waitForAnElementExplicit(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        WebElement button = driver.findElement(By.xpath("//button"));
        button.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement divContainer = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@id='finish']")));
        divContainer.click();
        driver.quit();
    }

    public static void waitForAnElementImplicit(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
        WebElement button = driver.findElement(By.xpath("//button"));
        button.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement divContainer = driver.findElement(By.xpath("//div[@id='finish']"));
        divContainer.click();
        driver.quit();
    }
}

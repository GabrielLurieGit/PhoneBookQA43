package helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TakeScreen {
    @Attachment(value = "TestFailure",type = "image/png")
    public static byte[] takeScreenShot(WebDriver driver,String testName){
        Allure.step("Try to take a screenshot...");
        try{
            String screenshotname = testName+"_"+System.currentTimeMillis()+".png";
            File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShotFile,new File("screenshots/"+screenshotname));
            return Files.readAllBytes(Paths.get("screenshots\\"+ screenshotname));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

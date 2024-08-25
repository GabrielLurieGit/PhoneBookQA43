package pages;

import enums.ContactField;
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
import java.util.List;

public class ContactsPage extends BasePage{

    @FindBy(xpath = "//button[contains(text(),'Sign Out')]")
    WebElement signOutButton;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;


    public WebElement getSignOutButton() {
        return signOutButton;
    }

    public ContactsPage(WebDriver driver){
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
    }

    protected List<WebElement> getContactsList(){
        return driver.findElements(By.xpath("//div[@class='contact-item_card__2SOIM']"));
    }
    public int getContactListSize(){

        return getContactsList().size();
    }

    public boolean getDataFromContactList(Contact contact){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement nameContact = wait.
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//h2[contains(text(),'"+contact.getName().toString()+"')]")));
        nameContact.click();
        WebElement editButton = driver.findElement(By.xpath("//button[contains(text(), 'Edit')]"));
        editButton.click();
        WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
        String elementNameValue = elementName.getAttribute("value");
        WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        String elementLastNameValue = elementLastName.getAttribute("value");
        WebElement elementPhone = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
        String elementPhoneValue = elementPhone.getAttribute("value");
        WebElement elementEmail = driver.findElement(By.xpath("//input[@placeholder='email']"));
        String elementEmailValue = elementEmail.getAttribute("value");
        WebElement elementAddress = driver.findElement(By.xpath("//input[@placeholder='Address']"));
        String elementAddressValue = elementAddress.getAttribute("value");
        WebElement elementDescription = driver.findElement(By.xpath("//input[@placeholder='desc']"));
        String elementDescriptionValue = elementDescription.getAttribute("value");
        Contact listContact = new Contact();
        listContact.setName(elementNameValue);
        listContact.setLastName(elementLastNameValue);
        listContact.setPhone(elementPhoneValue);
        listContact.setEmail(elementEmailValue);
        listContact.setAddress(elementAddressValue);
        listContact.setDescription(elementDescriptionValue);
        boolean res = listContact.equals(contact);
        return  res;
    }


    public String findOpenContactAndChangeFieldValue(Contact contact, ContactField fieldName,String newValue){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement contactFromTheList = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.
                        xpath("//div[h2[contains(text(),'"+contact.getName().toString()+"')] " +
                                "and h3[contains(text(),'"+contact.getPhone().toString()+"')]]")));
        contactFromTheList.click();
        WebElement editButton = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
        editButton.click();
        switch (fieldName){
            case NAME:
                WebElement elementName = driver.findElement(By.xpath("//input[@placeholder='Name']"));
                elementName.clear();
                elementName.sendKeys(newValue);
                break;
            case LAST_NAME:
                WebElement elementLastName = driver.findElement(By.xpath("//input[@placeholder='LastName']"));
                elementLastName.clear();
                elementLastName.sendKeys(newValue);
                break;
            case PHONE_NUMBER:
                WebElement elementPhoneNumber = driver.findElement(By.xpath("//input[@placeholder='Phone']"));
                elementPhoneNumber.clear();
                elementPhoneNumber.sendKeys(newValue);
                break;
            case EMAIL:
                WebElement elementEmail = driver.findElement(By.xpath("//input[@placeholder='email']"));
                elementEmail.clear();
                elementEmail.sendKeys(newValue);
                break;
            case ADDRESS:
                WebElement elementAddress = driver.findElement(By.xpath("//input[@placeholder='Address']"));
                elementAddress.clear();
                elementAddress.sendKeys(newValue);
                break;
            case DESCRIPTION:
                WebElement elementDesc = driver.findElement(By.xpath("//input[@placeholder='desc']"));
                elementDesc.clear();
                elementDesc.sendKeys(newValue);
                break;
            default: throw new IllegalArgumentException("Wrong field name..." + fieldName);
        }
        return newValue;
    }


    public void clickSaveButton(String newValue){
        saveButton.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        WebElement contactFromTheList = wait.
                until(ExpectedConditions.visibilityOfElementLocated(By.
                        xpath("//div[text()='"+newValue+"'] | //h2[text()='"+newValue+"'] | //h3[text()= '"+newValue+"']")));
        wait.until(ExpectedConditions.visibilityOf(contactFromTheList));
    }



}

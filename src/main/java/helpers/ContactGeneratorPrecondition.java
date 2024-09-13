package helpers;

import com.thoughtworks.qdox.model.expression.Add;
import io.qameta.allure.Step;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import javax.print.attribute.standard.MediaSize;

public class ContactGeneratorPrecondition{

    private Contact contact;

    public Contact getContact() {
        return contact;
    }
    @Step("Creating a new contact entity")

    @BeforeMethod
    public void createNewContact(){
        contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),
                "Text desc"
        );

    }

}

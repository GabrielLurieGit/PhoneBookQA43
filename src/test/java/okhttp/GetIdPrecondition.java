package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import static interfaces.TestHelper.GSON;
import static interfaces.TestHelper.JSON;

public class GetIdPrecondition implements TestHelper {
    String id;
    @BeforeTest
    public void createNewContactGetIdPrecondition() throws IOException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),
                "Description");
        System.out.println(contact.toString());
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+ADD_NEW_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(),ContactResponseModel.class);
        System.out.println("RESPONSE: " + contactResponseModel.getMessage());
        id = IdExtractor.getID(contactResponseModel.getMessage());
    }

    public String getId() {
        return id;
    }
}

package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateContactTest implements TestHelper {

    @Test
    public void updateContactTestPositive() throws IOException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress()
        );
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + UPDATE_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .post(requestBody).build();
        Response response = CLIENT.newCall(request).execute();

        ContactResponseModel contactResponseModel
                = GSON.fromJson(response.body().string(), ContactResponseModel.class);
        String msg = contactResponseModel.getMessage();
        System.out.println(msg);
        String id = IdExtractor.getID(msg);
        System.out.println("ID "+id);

        Contact contactModified = new Contact(id,
                NameAndLastNameGenerator.generateName(),
                contact.getLastName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getAddress(),
                "updated"
        );

        System.out.println("Modified contact: " + contactModified.toString());
        RequestBody updatedBody = RequestBody.create(GSON.toJson(contactModified), JSON);
        Request updateRequest = new Request.Builder()
                .url(BASE_URL + UPDATE_CONTACT)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token", XML_DATA_FILE))
                .put(updatedBody).build();
        Response resp = CLIENT.newCall(updateRequest).execute();
        Assert.assertEquals(resp.code(), 200);

    }
}
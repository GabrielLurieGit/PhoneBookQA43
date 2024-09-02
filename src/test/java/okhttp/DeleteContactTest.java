package okhttp;

import helpers.*;
import interfaces.TestHelper;
import models.Contact;
import models.ContactResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactTest implements TestHelper {
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


    @Test
    public void deleteContactById() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+id)
                .addHeader(AUTHORIZATION_HEADER,PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .delete()
                .build();
        Response response= CLIENT.newCall(request).execute();
        System.out.println(response.body().string());
        Assert.assertTrue(response.isSuccessful());
    }


    @Test
    public void deleteContactByIdNegative() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+id)
                .addHeader(AUTHORIZATION_HEADER,"222")
                .delete()
                .build();
        Response response= CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(),ErrorModel.class);
        System.out.println(errorModel.getError());
        System.out.println(errorModel.getMessage());
        Assert.assertEquals(errorModel.getStatus(),401);
    }
}

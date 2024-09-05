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
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class DeleteContactTest extends GetIdPrecondition implements TestHelper {

    SoftAssert softAssert = new SoftAssert();

    @Test
    public void deleteContactById() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+getId())
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
                .url(BASE_URL+DELETE_CONTACT+getId())
                .addHeader(AUTHORIZATION_HEADER,"222")
                .delete()
                .build();
        Response response= CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(),ErrorModel.class);
        System.out.println(errorModel.getError());
        System.out.println(errorModel.getMessage());
        Assert.assertEquals(errorModel.getStatus(),401);
    }


    @Test
    public void deleteContactByIdWrongId() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_CONTACT+"wrong-id1234")
                .addHeader(AUTHORIZATION_HEADER,PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
        System.out.println(errorModel.getError());
        System.out.println(errorModel.getMessage());
        Assert.assertEquals(errorModel.getStatus(),400);
    }

@Test
    public void deleteAllContacts() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+DELETE_ALL_CONTACTS)
                .addHeader(AUTHORIZATION_HEADER,PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .delete()
                .build();
        Response response = CLIENT.newCall(request).execute();
        ContactResponseModel contactResponseModel = GSON.fromJson(response.body().string(), ContactResponseModel.class);
         System.out.println(contactResponseModel);
        Assert.assertTrue(response.isSuccessful());
    }


}

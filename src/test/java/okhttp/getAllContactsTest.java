package okhttp;

import com.mysql.cj.xdevapi.Client;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import models.Contact;
import models.ContactListModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class getAllContactsTest implements TestHelper {


    @Test
            public void getAllConctacts() throws IOException {
        Request request = new Request.Builder()
                .url(BASE_URL+GET_ALL_CONTACTS)
                .addHeader(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .get()
                .build();
        Response response = CLIENT.newCall(request).execute();
        String responseBody = response.body().string();
        System.out.println(responseBody);
        ContactListModel contacts = GSON.fromJson(responseBody, ContactListModel.class);
        for(Contact contact: contacts.getContacts()){
            System.out.println(contact.getEmail());
            System.out.println("===============================");
            Assert.assertTrue(response.isSuccessful());
        }
    }



}

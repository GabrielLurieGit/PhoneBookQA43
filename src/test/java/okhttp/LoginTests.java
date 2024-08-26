package okhttp;

import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests implements TestHelper {

    @Test
    public void loginPositive() throws IOException {
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .password(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE));
        System.out.println("Reqest model : "+ requestModel.getUsername()+ " "+requestModel.getPassword());
        RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+LOGIN_PATH)
                .post(requestBody)
                .build();
        System.out.println("Request "+request.toString());
        Response response = CLIENT.newCall(request).execute();
        System.out.println(response.code());
        if(response.isSuccessful()){
            AuthenticationResponseModel responseModel =
                    GSON.fromJson(response.body().string(), AuthenticationResponseModel.class);
            PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
            propertiesWriterXML.setProperty("token", responseModel.getToken(), false, XML_DATA_FILE);
            Assert.assertTrue(response.isSuccessful());
        }else {
            System.out.println(response.code());
            ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
            System.out.println(errorModel.toString());
        }


    }
    @Test
    public void loginNegative() throws IOException {
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .password("111111");
        System.out.println("Reqest model : "+ requestModel.getUsername()+ " "+requestModel.getPassword());
        RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+LOGIN_PATH)
                .post(requestBody)
                .build();
        System.out.println("Request "+request.toString());
        Response response = CLIENT.newCall(request).execute();
        System.out.println(response.code());
        if(!response.isSuccessful()){
            ErrorModel errorModel = GSON.fromJson(response.body().string(), ErrorModel.class);
            System.out.println(errorModel.getStatus());
            Assert.assertEquals(response.code(), 401);
        }else {}

    }



}

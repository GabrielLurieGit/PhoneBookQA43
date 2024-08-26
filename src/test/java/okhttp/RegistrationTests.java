package okhttp;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
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

public class RegistrationTests implements TestHelper {

@Test
    public void registrationTestPositive() throws IOException {
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(5,5,3))
                .password(PasswordStringGenerator.generateRandomPassword());
        RequestBody requestBody = RequestBody.create(GSON.toJson(requestModel),JSON);
        Request request = new Request.Builder()
                .url(BASE_URL+REGISTRATION_PATH)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        String result = response.body().string();
    System.out.println(result);
        if(response.isSuccessful()){
            AuthenticationResponseModel responseModel = GSON.fromJson(result, AuthenticationResponseModel.class);
            Assert.assertTrue(response.isSuccessful());

        }else {
            ErrorModel errorModel = GSON.fromJson(response.body().string(),ErrorModel.class);
            System.out.println(response.code());
        }
    }
}

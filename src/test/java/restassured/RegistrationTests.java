package restassured;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTests implements TestHelper {
    @Test
    public void registrationPositive(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(5,5,3))
                .password(PasswordStringGenerator.generateRandomPassword());
       String token = given().body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().assertThat().statusCode(200)
                .extract().path("token");
        System.out.println(token);
    }
}

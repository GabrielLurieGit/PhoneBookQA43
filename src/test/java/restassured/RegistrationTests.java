package restassured;

import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.ErrorModel;
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



    @Test
    public void registrationNegative_WOPassword(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(EmailGenerator.generateEmail(5,5,3));
        ErrorModel errorModel = given().body(requestModel).contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().log().all()
                .statusCode(400)
                .extract()
                .as(ErrorModel.class);
        System.out.println(errorModel.getError());
    }


    @Test
    public void registrationNegativeInvalidEmail(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperties(INVALID_EMAIL,XML_DATA_FILE))
                .password(PropertiesReaderXML.getProperties(MY_PASSWORD,XML_DATA_FILE));
        ErrorModel errorModel = given().body(requestModel).contentType(ContentType.JSON)  //Response response =
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().assertThat().statusCode(400)
                .extract() // extract().response();
                .as(ErrorModel.class);
        System.out.println(errorModel.getError());
        //System.out.println(response.getBody().asString());
        // String response = jsonPath().getString("message.username")
        //Assert.assertTrue(message.contains(""must be a well-formed))
       // System.out.println("Time: " + response.getTime());
       // System.out.println("Time: " + response.getHeaders());
       // System.out.println("Time: " + response.getStatusLine());
    }



    @Test
    public void registrationNegativeDuplicateUser(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperties(MY_USER,XML_DATA_FILE))
                .password(PropertiesReaderXML.getProperties(INVALID_PASS2,XML_DATA_FILE)); //but with MY_PASSWORD 400
                //.username(MY_USER)
               // .password(MY_PASSWORD);
      ErrorModel errorModel = given().body(requestModel).contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+REGISTRATION_PATH)
                .then().log().all()
                .statusCode(409)
                .extract()
                .as(ErrorModel.class);
        System.out.println(errorModel.getMessage());

    }
}

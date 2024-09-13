package restassured;

import helpers.EmailGenerator;
import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SecurityTests implements TestHelper {

    //SELECT * FROM contacts WHERE username = 'admin' or 1=1;
    @Test
    public void loginPositive(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username("<script>alert('test')</script>")
                .password(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE));
        ErrorModel errorModel = given().body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_PATH)
                .then().log().all()
                .statusCode(401)
                .extract()
                .as(ErrorModel.class);
        System.out.println("Status " +errorModel.getStatus());
    }


    @Test
    public void brutalForceTest(){
        int maxAttemptsBefore = 5;
        int totalAttempts = 7;


        for(int i = 0; i< maxAttemptsBefore;i++){
            AuthenticationRequestModel request = new AuthenticationRequestModel(EmailGenerator.generateEmail(5,5,3),"");

            given().body(request).contentType(ContentType.JSON)
                    .when()
                    .post(BASE_URL+LOGIN_PATH)
                    .then().assertThat().statusCode(401);
        }


        for(int i = maxAttemptsBefore; i< totalAttempts;i++){
            AuthenticationRequestModel request = new AuthenticationRequestModel("<script>","<>");
            given().body(request).contentType(ContentType.JSON)
                    .when()
                    .post(BASE_URL+LOGIN_PATH)
                    .then().assertThat().statusCode(429);
        }
    }

}

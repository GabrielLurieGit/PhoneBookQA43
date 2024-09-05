package restassured;

import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest implements TestHelper {
    @Test
            public void loginPositive(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .password(PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE));
      AuthenticationResponseModel response = given().body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_PATH)
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(AuthenticationResponseModel.class);
      response.getToken();
        PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
        propertiesWriterXML.setProperty("tokenrestassured",response.getToken(),false,XML_DATA_FILE);
    }


    @Test
    public void loginNegative(){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE))
                .password("blah_blah_blah");
       ErrorModel errorModel = given()
                .body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_PATH)
                .then().log().all()
                .statusCode(401)
                .extract()
                .as(ErrorModel.class);
        System.out.println(errorModel.getMessage());
    }
}

package restassured;

import config.TestData;
import helpers.PropertiesReaderXML;
import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthenticationRequestModel;
import models.AuthenticationResponseModel;
import models.ErrorModel;
import org.testng.annotations.Test;
import restassured.steps.ApiSteps;

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


    @Test(dataProvider = "loginData",dataProviderClass = TestData.class)
    public void loginNegative(String username, String password,int statusCode){
        AuthenticationRequestModel requestModel = AuthenticationRequestModel
                .username(username)
                .password(password);
       ErrorModel errorModel = given()
                .body(requestModel)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+LOGIN_PATH)
                .then().log().all()
                .statusCode(statusCode)
                .extract()
                .as(ErrorModel.class);
        System.out.println(errorModel.getMessage());
    }

ApiSteps apiSteps = new ApiSteps();
    @Test(description = "login positive steps")
    public void loginPositiveSteps(){
       String username = PropertiesReaderXML.getProperties("myuser",XML_DATA_FILE);
       String password = PropertiesReaderXML.getProperties("mypass",XML_DATA_FILE);

      AuthenticationRequestModel requestModel = apiSteps.preparedRequest(username,password);
      Response response = apiSteps.sendLoginRequest(requestModel,BASE_URL+LOGIN_PATH);
     AuthenticationResponseModel responseModel = response.as(AuthenticationResponseModel.class);
     apiSteps.saveToken(responseModel.getToken());
    }


}

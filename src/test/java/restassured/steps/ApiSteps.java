package restassured.steps;

import helpers.PropertiesWriterXML;
import interfaces.TestHelper;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthenticationRequestModel;

import static io.restassured.RestAssured.given;

public class ApiSteps implements TestHelper {
@Step("Prepare authentication request with username {0}")
    public AuthenticationRequestModel preparedRequest(String username,String password){
        return AuthenticationRequestModel.username(username).password(password);
    }

@Step("Send login request to endpoint: {0}")
    public Response sendLoginRequest(AuthenticationRequestModel requestModel, String endpoint){
    return given()
            .body(requestModel)
            .contentType(ContentType.JSON)
            .when()
            .post(endpoint)
            .then()
            .log().all()
            .statusCode(200)
            .extract().response();
    }
@Step("Save authentication token")
    public void saveToken(String token){
        PropertiesWriterXML propertiesWriterXML = new PropertiesWriterXML();
        propertiesWriterXML.setProperty("token",token,false,XML_DATA_FILE);
    }

}

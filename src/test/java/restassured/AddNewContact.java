package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewContact extends ContactGeneratorPrecondition implements TestHelper {
    @Test
    public void addNewContact(){
        Contact contact = getContact();
       Response response = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("tokenrestassured",XML_DATA_FILE))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().post(BASE_URL+ADD_NEW_CONTACT)
                .then().log().all().assertThat().statusCode(200).extract().response();
     Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test
    public void addNewContactMessage(){
        Contact contact = getContact();
        Response response = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("tokenrestassured",XML_DATA_FILE))
                .body(contact)
                .contentType(ContentType.JSON)
                .when().post(BASE_URL+ADD_NEW_CONTACT)
                .then().log().all().assertThat().statusCode(200).extract().response();
        Assert.assertTrue(response.getBody().path("message").toString().contains("was added"));

    }
}

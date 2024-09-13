package restassured;

import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateContact implements TestHelper {
    @Test
    public void updateContactPositive(){
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),
                "Text desc"
        );
        RestAssured.baseURI = BASE_URL+UPDATE_CONTACT;
       String message = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .body(contact)
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then().extract().response().path("message");
      String id = IdExtractor.getID(message);
      contact.setId(id);
      contact.setEmail("updatedemail@gmail.com");

     Response response = given().header(AUTHORIZATION_HEADER,PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
              .body(contact).contentType(ContentType.JSON)
              .when().put()
              .then().assertThat().extract().response();
        System.out.println("Response " + response.getBody().path("message").toString());
        Assert.assertEquals(response.getBody().path("message"),"Contact was updated");

    }
}

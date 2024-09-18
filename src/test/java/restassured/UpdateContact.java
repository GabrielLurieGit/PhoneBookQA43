package restassured;

import db.DatabaseConnection;
import db.DatabaseReader;
import helpers.*;
import interfaces.TestHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;

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
       String message = given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("tokenrestassured",XML_DATA_FILE))
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



    @Test
    public void updateContactLocal() throws SQLException {
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),
                "Text desc"
        );
        RestAssured.baseURI = BASE_URL+ADD_NEW_CONTACT;
      String response =  given().header(AUTHORIZATION_HEADER,PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .body(contact).contentType(ContentType.JSON)
                .when()
                .post()
                .then().extract().path("message");
      String id = IdExtractor.getID(response);
        contact.setId(id);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.contactRecorder(contact);
        contact.setEmail("ffddzxcwdy@mail.test");
        given().header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .body(contact).contentType(ContentType.JSON)
                .when()
                .put()
                .then().statusCode(200);
        Contact changedContact = DatabaseReader.readContactFromDb(id);
        Assert.assertNotEquals(changedContact.getEmail(),contact.getEmail());
    }
}

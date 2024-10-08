package restassured;

import db.DatabaseConnection;
import helpers.PropertiesReaderXML;
import interfaces.TestHelper;
import io.restassured.response.Response;
import models.Contact;
import models.ContactListModel;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static io.restassured.RestAssured.given;

public class GetAllContacts implements TestHelper {
    @Test
    public void getAllContacts() throws SQLException {
     ContactListModel contactListModel = given()
                .header(AUTHORIZATION_HEADER, PropertiesReaderXML.getProperties("token",XML_DATA_FILE))
                .when()
                .get(BASE_URL+GET_ALL_CONTACTS)
                .then().log().all()
                .assertThat()
                .statusCode(200).extract().as(ContactListModel.class);
        DatabaseConnection databaseConnection = new DatabaseConnection();

     for(Contact contact :contactListModel.getContacts()){
         databaseConnection.contactRecorder(contact);
         System.out.println(contact.getEmail());
         System.out.println(contact.getId());
         System.out.println("=====================================");

     }

    }


}

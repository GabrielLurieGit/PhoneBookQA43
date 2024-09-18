package db;

import models.Contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    public void contactRecorder(Contact contact) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/phonebook";
        String username = "root";
        String password = "Datmd56123";

        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "INSERT INTO contacts(id, name, lastname, email, phone,address,description) "
                + "VALUES(?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE "
                + "name = VALUES(name), lastName = VALUES(lastName), email = VALUES(email),phone = VALUES(phone)," +
                "address = VALUES(address), description = VALUES(description)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, contact.getId());
        preparedStatement.setString(2, contact.getName());
        preparedStatement.setString(3, contact.getLastName());
        preparedStatement.setString(4, contact.getEmail());
        preparedStatement.setString(5, contact.getPhone());
        preparedStatement.setString(6, contact.getAddress());
        preparedStatement.setString(7, contact.getDescription());
        int row = preparedStatement.executeUpdate();
        if(row>0){
            System.out.println("Successful!");
        }
        else {
            System.out.println("Unsuccessful...");
        }
        preparedStatement.close();
        connection.close();
    }


}

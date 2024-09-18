package db;

import models.Contact;

import java.sql.*;

public class DatabaseReader {
   static String url = "jdbc:mysql//localhost:3306";
   static String username = "root";
   static String password = "text";

    public static Contact readContactFromDb(String id) throws SQLException {
        Connection connection = DriverManager.getConnection(url,username,password);
        String query = "SELECT * FROM contacts WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,id);
       ResultSet resultSet = preparedStatement.executeQuery();
       Contact contact = null;
       if(resultSet.next()){
           contact = new Contact();
           contact.setId(resultSet.getString("id"));
           contact.setName(resultSet.getString("name"));
           contact.setLastName(resultSet.getString("lastName"));
           contact.setEmail(resultSet.getString("email"));
           contact.setPhone(resultSet.getString("phone"));
           contact.setAddress(resultSet.getString("address"));
           contact.setDescription(resultSet.getString("description"));
       }
       else {
           System.out.println("id issue: "+id);
       }
       preparedStatement.close();
       connection.close();
       return contact;
    }
}

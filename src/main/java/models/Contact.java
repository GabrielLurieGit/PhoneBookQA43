package models;

import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import kotlin.jvm.internal.SerializedIr;

import java.io.*;
import java.util.Objects;

public class Contact implements Serializable {
     String name;
     String lastName;
     String phone;
     String email;
     String address;
     String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return Objects.equals(getName(), contact.getName()) && Objects.equals(getLastName(), contact.getLastName()) && Objects.equals(getPhone(), contact.getPhone()) && Objects.equals(getEmail(), contact.getEmail()) && Objects.equals(getAddress(), contact.getAddress()) && Objects.equals(getDescription(), contact.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getPhone(), getEmail(), getAddress(), getDescription());
    }

    public Contact() {
    }

    public Contact(String name, String lastName, String phone, String email, String address, String description) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
    }


    public static void serializationContact(Contact contact,String path) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));
        outputStream.writeObject(contact);
    }

    public static Contact deserializationContact(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        return (Contact)inputStream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact contact = new Contact();
        contact.setName(NameAndLastNameGenerator.generateName());
        contact.setLastName(NameAndLastNameGenerator.generateName());
        contact.setPhone(PhoneNumberGenerator.generatePhoneNumber());
        contact.setEmail(EmailGenerator.generateEmail(5,5,3));
        contact.setAddress(AddressGenerator.generateAddress());
        contact.setDescription("testdesc");
        System.out.println(contact.toString());
        serializationContact(contact,"testcontact.ser");
        System.out.println("DE: "+deserializationContact("testcontact.ser").toString());








    }
}

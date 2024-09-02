package models;

import java.util.List;

public class ContactListModel {
    private List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public ContactListModel() {
    }

    @Override
    public String toString() {
        return "ContactListModel{" +
                "contacts=" + contacts +
                '}';
    }
}

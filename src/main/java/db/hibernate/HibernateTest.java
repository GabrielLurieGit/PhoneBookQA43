package db.hibernate;

import helpers.AddressGenerator;
import helpers.EmailGenerator;
import helpers.NameAndLastNameGenerator;
import helpers.PhoneNumberGenerator;
import jakarta.persistence.Query;
import models.Contact;
import org.hibernate.Session;

import java.util.UUID;

public class HibernateTest {


    public static void main(String[] args) {
        Contact initContact = addNewContact();
     Contact cont = getContactById(initContact.getId());
        System.out.println(cont.toString());

    }

    public static Contact addNewContact(){
        Contact contact = new Contact(
                NameAndLastNameGenerator.generateName(),
                NameAndLastNameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(5,5,3),
                AddressGenerator.generateAddress(),
                "Text desc"
        );
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            String id = String.valueOf(UUID.randomUUID());
            contact.setId(id);
            System.out.println("ID: "+id);
            session.save(contact);
            session.getTransaction().commit();
        }catch (Throwable throwable){}
        return contact;
    }


    public static Contact getContactById(String id){
        Contact contact = null;
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Query query = session.createQuery("FROM Contact WHERE id = :id");
            query.setParameter("id",id);
            contact = (Contact) query.getSingleResult();
            session.getTransaction().commit();
        }catch (Throwable throwable){}
        return contact;
    }


    public static void deleteContactById(String id){
        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
           Contact contact = session.get(Contact.class,id);
           if(contact != null){
               session.detach(contact);
           }else {
               System.out.println("no contact found with id"+id);
           }
           session.getTransaction().commit();
        }catch (Throwable throwable){
            System.out.println(throwable.fillInStackTrace());
        }
    }
}

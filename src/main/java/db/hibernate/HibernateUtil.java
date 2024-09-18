package db.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try{
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        }catch (Throwable throwable){
            System.out.println("Something went wrong...");
        }
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}

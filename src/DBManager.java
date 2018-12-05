import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBManager {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            initializeSessionFactory();
        }
        return sessionFactory;
    }

    private static void initializeSessionFactory() {
        Configuration configuration = new Configuration();
//        File file = new File("something");
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$"+file.getAbsolutePath());
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

}

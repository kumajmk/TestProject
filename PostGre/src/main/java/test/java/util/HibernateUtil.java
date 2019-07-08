package test.java.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import test.java.entity.Color;



public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	private static Properties properties;
	private static final Configuration conf=new Configuration();
	
	//static InputStream in = this.getClass().getClassLoader().getResourceAsStream("src/main/java/hibernateN1.properties");
    
    static{
        try{
        	// Programmatic Way
        	
        	/*properties = new Properties();
        	
        	properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        	properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        	properties.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1/test");
        	properties.setProperty("hibernate.connection.username", "root");
        	properties.setProperty("hibernate.connection.password", "root");
        	properties.setProperty("show_sql", "true");*/
        	
        	/*InputStream in = 
        			   HibernateUtil.class.getResourceAsStream("/hibernateN.properties");
        	   System.out.println(in.read());
        			//Reader fr = new InputStreamReader(in, "utf-8");
        			properties.load(new InputStreamReader(in, "utf-8"));*/
        	
        	conf.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        	conf.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        	conf.setProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1/postgres");
        	conf.setProperty("hibernate.connection.username", "postgres");
        	conf.setProperty("hibernate.connection.password", "root");
        	conf.setProperty("show_sql", "true");
        	StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(conf.getProperties()).build();

        	/*sessionFactory = conf
        			
        			.addAnnotatedClass(User.class)
        			
        			
        			.buildSessionFactory(registry);
        	*/
        	// System.out.println(in.read());
        	sessionFactory = conf.addAnnotatedClass(Color.class).
        			buildSessionFactory(registry);
        	System.out.println("Connection created successfully");
  
 
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }   
    }
     
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown(){
    	getSessionFactory().close();
    }
    
    public static void main(String args[]) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Color colorObj=new Color();
    	System.out.println(session);
    	session.beginTransaction();
    	colorObj.setColor_name("green");
    	session.persist(colorObj);
    	session.getTransaction().commit();
    	session.beginTransaction();
    	colorObj.setColor_name("Yellow");
    	session.persist(colorObj);
    	session.getTransaction().commit();
    	HibernateUtil.getSessionFactory().close();
    	
    	
    	
    }

}
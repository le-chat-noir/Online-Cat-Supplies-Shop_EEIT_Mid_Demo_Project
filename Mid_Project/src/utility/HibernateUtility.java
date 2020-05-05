package utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtility {
	private static final SessionFactory factory = createSessionFactory();
	
	private static SessionFactory createSessionFactory() {
		
		StandardServiceRegistry serviceReg = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(serviceReg).buildMetadata().buildSessionFactory();
		
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}

	public static void closeSessionFactory() {
		if(factory!=null) {
			factory.close();
		}
	}
}

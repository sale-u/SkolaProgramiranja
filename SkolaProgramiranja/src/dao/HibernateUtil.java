package dao;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	
	private static SessionFactory sf = null;
	
	private static SessionFactory createSessionFactory() {
		return new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		
		if (sf == null) {
			sf = createSessionFactory();
		}
		return sf;
	}

}

package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;
import model.UserDetails;

public class LoginDao {
	
	SessionFactory sf = new Configuration().configure().buildSessionFactory();
	
	public void upisiUserIuserDetails(User user, UserDetails details) {
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.save(details);
			session.getTransaction().commit();
			System.out.println("Uspeno upisan user");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("NIJE upisan user! " + e);
		} finally {
			session.close();
		}
		
	}
	

}

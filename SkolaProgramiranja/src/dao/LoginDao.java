package dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;
import model.UserDetails;

public class LoginDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	public void upisiUserIuserDetails(User user, UserDetails details) {
		
		// U bazu/tabele User i UserDetails upisuje vec napravljene konkretne User user i njemu pripadajuci UserDetails details
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.save(details);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan user");
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("NIJE upisan user! " + e);
		} finally {
			session.close();
		}
		
	}

	public User vratiAkoPostojiUser(String userName, String password) {
		
		// vraca konkretni objekat User user sa zadatim userName i password ako takav postoji u bazi/tabeli user
		// Ako ga ne pronadje, vraca null
		
		User user = new User();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			String hql = "FROM User WHERE userName = :un AND password = :pass";
			Query query = session.createQuery(hql);
			query.setParameter("un", userName);
			query.setParameter("pass", password);
			List<User> users = query.getResultList();
			if(users.size() == 1) {
				System.out.println("Postoji user");
				user = users.get(0);
				session.getTransaction().commit();
				return user;
			} else {
				session.getTransaction().commit();
				System.out.println("Nije pronasao usera...");
				return null;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Problem u metodi vratiAkoPostojiUser()" + e);
			return null;
		} finally {
			session.close();
		}
	}
	

}

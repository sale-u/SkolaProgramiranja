package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.User;
import model.UserDetails;

public class AddStudentDao {
	
	SessionFactory sf = new Configuration().configure().buildSessionFactory();

	public boolean ubaciKonkretnogUsera(User user, UserDetails konkretniUser) {
		
		// perzistira u SQL bazi novog User user kao i povezanog novog konkretnog korisnika
		// Konkretni korisnik moze biti neki od tri tipa (tri subklase superklase UserDetails)
		// pa konkretniUser stavljamo da je tipa UserDetails

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.save(konkretniUser);	// neki od 3 subklase
			// konkretniUser pripada nekoj od subklasa superklase UserDetails
			// konkretniUser.idUserDetails je foreign kljuc prema UserDetails.idUserDetails
			// i on ce biti automatski popunjen sa auto-inkrement brojem koji je tokom save() dodeljen polju UserDetails.idUserDetails 
			session.getTransaction().commit();
			System.out.println("Uspesno upisan novi konkretni user");
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("NIJE upisan novi konkretni user! " + e);
			return false;
		} finally {
			session.close();
		}

	}

}

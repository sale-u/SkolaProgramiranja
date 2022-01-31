package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Administrator;
import model.Profesor;
import model.Student;
import model.User;
import model.UserDetails;

public class AddStudentDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();
	
	// ===========================================================================
	
	public boolean ubaciStudenta(User user, Student student) {
		
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.save(student);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan student");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan student! " + e);	
			return false;
		}finally {
			session.close();
		}
		
	}

	//===========================================================================

	public boolean ubaciProfesora(User user, Profesor profesor) {
		
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.save(profesor);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan profesor");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan profesor! " + e);	
			return false;
		}finally {
			session.close();
		}
	}
	
	// ===========================================================================

	public boolean ubaciAdministrator(User user, Administrator administrator) {
		
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(user);
			session.save(administrator);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan administrator");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan administrator! " + e);	
			return false;
		}finally {
			session.close();
		}
	}
	
	
	
	// ===========================================================================
	// ===========================================================================

	public boolean ubaciKonkretnogUsera(User user, UserDetails konkretniUser) {
		
		// Moja univerzalna verzija metode koja cuva sve tri vrste objekta (prema subklasi)
		
		// perzistira u SQL bazi novog User user kao i povezanog novog konkretnog korisnika
		// Konkretni korisnik moze biti neki od tri tipa (tri subklase superklase UserDetails)
		// pa konkretniUser stavljamo da je tipa UserDetails

		System.out.println("*************** Moja univerzalna metoda boolean ubaciKonkretnogUsera() ************");
		
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

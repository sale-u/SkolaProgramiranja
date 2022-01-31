package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Predmet;
import model.Smer;

public class PredmetSmerDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();

	public boolean snimiPredmet(Predmet predmet) {

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(predmet);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan predmet");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan predmet! " + e);	
			return false;
		}finally {
			session.close();
		}
	}
	
	// ==========================================================================================

	public boolean snimiSmer(Smer smer) {

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(smer);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan smer");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan smer! " + e);	
			return false;
		}finally {
			session.close();
		}
		
		
	// ==========================================================================================
		
		
	}
	

	


}

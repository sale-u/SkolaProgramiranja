package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import model.Predmet;
import model.Profesor;
import wrapper.ProfesorWrapper;

public class PrikaziProfesoreDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();

	public List<ProfesorWrapper> vratiSveProfesore() {
		
		List<ProfesorWrapper> listaProfesora = new ArrayList<>(); // ovde ce ici lista ProfesorWrapper objekata
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			String sql = "SELECT ud.idUserDetails,\r\n"
					+ "	   u.userName,\r\n"
					+ "    u.password,\r\n"
					+ "    ud.firstName,\r\n"
					+ "    ud.lastName,\r\n"
					+ "    ud.email,\r\n"
					+ "    ud.mobilePhone,\r\n"
					+ "    ud.country,\r\n"
					+ "    ud.city,\r\n"
					+ "    ud.street,\r\n"
					+ "    p.identificationNo\r\n"
					+ "FROM user u\r\n"
					+ "INNER JOIN userdetails ud ON ud.user_idUser = u.idUser\r\n"
					+ "INNER JOIN profesor p ON p.idUserDetails = ud.idUserDetails;";
			
			Query query = session.createNativeQuery(sql);
			List<Object[]> resultList = query.getResultList();	// vraca listu nizova objekata
			
			for (Object[] o : resultList) {
				
				ProfesorWrapper profesor = new ProfesorWrapper();
				
				if (o[0] != null) {profesor.setIdProfesor((int) o[0]);}
				if (o[1] != null) {profesor.setUserName((String) o[1]);}
				if (o[2] != null) {profesor.setPassword((String) o[2]);}
				if (o[3] != null) {profesor.setFirstName((String) o[3]);}
				if (o[4] != null) {profesor.setLastName((String) o[4]);}
				if (o[5] != null) {profesor.setEmail((String) o[5]);}
				if (o[6] != null) {profesor.setMobilePhone((String) o[6]);}
				if (o[7] != null) {profesor.setCountry((String) o[7]);}
				if (o[8] != null) {profesor.setCity((String) o[8]);}
				if (o[9] != null) {profesor.setStreet((String) o[9]);}
				if (o[10] != null) {profesor.setIdentificationNo((String) o[10]);}
				
				listaProfesora.add(profesor);
			}		
			session.getTransaction().commit();
			System.out.println("Uspesno vracena lista profesora");
			return listaProfesora;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa vratiSveProfesore()! " + e);
			return null;
		} finally {
			session.close();
		}
	}
	
	// ===========================================================================

	public List<Predmet> vratiSvePredmete() {
		
		List<Predmet> listaPredmeta = new ArrayList<>();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			String hql = "FROM Predmet";
			Query query = session.createQuery(hql);
			listaPredmeta = query.getResultList();
			
			session.getTransaction().commit();
			System.out.println("Uspesno vracena lista predmeta");
			return listaPredmeta;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa vratiSvePredmete()! " + e);
			return null;
		} finally {
			session.close();
		}
		
	}

	public Profesor vratiProfesoraPoID(String idProfesor) {

		Profesor prof = new Profesor();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			prof = session.get(Profesor.class, Integer.parseInt(idProfesor));
			Hibernate.initialize(prof.getPredmetiKojePredaje());
			session.getTransaction().commit();
			System.out.println("Uspesno vracen profesor");
			return prof;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa vratiProfesoraPoID()! " + e);
			return null;
		} finally {
			session.close();
		}
	}
	

	public void dodajPredmetProfesoru(String idProfesor, String idPredmet) {
		// na osnovu idProfesor i idPredmet, iz baze povlacimo kompletne objekte profesor i predmet
		// i onda ih spajamo
		
		Profesor prof = new Profesor();
		Predmet predmet = new Predmet();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			// vadimo profesora iz baze na osnovu idProfesor
			prof = session.get(Profesor.class, Integer.parseInt(idProfesor));
			// i njegovu listu predmetiKojePredaje
			Hibernate.initialize(prof.getPredmetiKojePredaje());
			// vadimo predmet iz baze na osnovu idPredmet
			predmet = session.get(Predmet.class, Integer.parseInt(idPredmet));
			System.out.println("Prof=" + prof.getIdUserDetails() + " predmet=" + predmet.getIdPredmet() + " > " + predmet.getNazivPredmeta());;
			
			if (!prof.getPredmetiKojePredaje().contains(predmet)) {
				// Ako taj predmet nije vec dodeljen profesoru
				// dodajemo predmet u listu predmetiKojePredaje 
				prof.getPredmetiKojePredaje().add(predmet);
				session.saveOrUpdate(prof);
				session.getTransaction().commit();
				System.out.println("Uspesno dodeljen predmet profesoru");
			} else {
				System.out.println("Taj predmet " + predmet.getNazivPredmeta() + " je VEC dodeljen profesoru " + prof.getIdUserDetails());
			}	
		} catch (PersistenceException e) {
			// Relacija profesor->predmet je OneToMany pa nije moguce da jedan predmet predaje vise profesora!
			session.getTransaction().rollback();
			System.out.println("Predmet " + predmet.getNazivPredmeta() + " nije moguce dodati profesoru idProfesor=" 
						+ prof.getIdUserDetails() + " jer taj predmet vec predaje neki drugi profesor >>> " + e);
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa dodajPredmetProfesoru()! " + e);
		} finally {
			session.close();
		}
		
	}

	
	public void oduzmiPredmetProfesoru(String idProfesor, String idPredmet) {

		// na osnovu idProfesor i idPredmet, iz baze povlacimo kompletne objekte profesor i predmet
		// i onda iz profesorove liste skidamo predmet
		
		Profesor prof = new Profesor();
		Predmet predmet = new Predmet();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			// vadimo profesora iz baze na osnovu idProfesor
			prof = session.get(Profesor.class, Integer.parseInt(idProfesor));
			// i njegovu listu predmetiKojePredaje
			Hibernate.initialize(prof.getPredmetiKojePredaje());
			// vadimo predmet iz baze na osnovu idPredmet
			predmet = session.get(Predmet.class, Integer.parseInt(idPredmet));
			System.out.println("Prof=" + prof.getIdUserDetails() + " predmet=" + predmet.getIdPredmet() + " > " + predmet.getNazivPredmeta());;
			
			if (prof.getPredmetiKojePredaje().contains(predmet)) {
				prof.getPredmetiKojePredaje().remove(predmet);
				session.saveOrUpdate(prof);
				session.getTransaction().commit();
				System.out.println("Uspesno oduzet predmet profesoru");
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa oduzmiPredmetProfesoru()! " + e);
		} finally {
			session.close();
		}
		
	}
	

}

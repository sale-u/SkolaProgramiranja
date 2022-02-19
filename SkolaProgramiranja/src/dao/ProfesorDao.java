package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.PolozeniIspiti;
import model.Predmet;
import model.Profesor;
import model.Smer;
import model.Student;
import model.User;
import model.UserDetails;

public class ProfesorDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();
	

	public Profesor vratiProfesoraPoId(int idUserDetails) {

		Session session = sf.openSession();
		session.beginTransaction();
		
		Profesor p = new Profesor();
				
		try {
			
			p = session.get(Profesor.class, idUserDetails);
			Hibernate.initialize(p.getPredmetiKojePredaje());
			
			System.out.println("Uspesno vracen profa");
			session.getTransaction().commit();
			return p;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiProfesoraPoId! " + e);	
			return null;
		}finally {
			session.close();
		}
		
		
	}

	public List<Smer> vratiSveSmerovePredmeta(String idPredmeta) {
		
		// vrati listu smerova na kojima se slusa predmet idPredmet

		Predmet predmet = new Predmet();
		List<Smer> smeroviList = new ArrayList<Smer>();
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		try {
			predmet=session.get(Predmet.class,Integer.parseInt(idPredmeta));
			Hibernate.initialize(predmet.getSmeroviNaKojimaJePredmet());
			smeroviList = predmet.getSmeroviNaKojimaJePredmet();
			
			session.getTransaction().commit();
			System.out.println("Uspesno preuzeti smerovi na kojima je predmet " + predmet.getIdPredmet());
			return smeroviList;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiSveSmerovePredmeta! " + e);	
			return null;
		}finally {
			session.close();
		}
		
	}

	public List<Student> vratiSveStudenteSmera(List<Smer> smeroviPredmeta) {
		
		// vraca sve studente koji se nalaze na svim smerovima koji su u listi smeroviPredmeta
		
		List<Student> studenti = new ArrayList<Student>(); 
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			
			// U objektu Student imamo atribut Smer smer
			
			String hql = "from Student where smer in (:listaSmerova)";
			Query query = session.createQuery(hql);
			query.setParameter("listaSmerova", smeroviPredmeta);
			studenti = query.getResultList();

			
			session.getTransaction().commit();
			System.out.println("Uspesno preuzeti studenti sa liste smerova");
			return studenti;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiSveStudenteSmera! " + e);	
			return null;
		}finally {
			session.close();
		}
		
		
	}

	public Predmet vratiPredmetPoId(String idPredmeta) {

		Session session = sf.openSession();
		session.beginTransaction();
		
		Predmet predmet = new Predmet();
				
		try {
			
			predmet = session.get(Predmet.class, Integer.parseInt(idPredmeta));
	
			System.out.println("Uspesno vracen predmet");
			session.getTransaction().commit();
			return predmet;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiPredmetPoId! " + e);	
			return null;
		}finally {
			session.close();
		}
		
	}

	public void upisiOcenu(int predmetId, int studentId, int ocena) {

		Session session = sf.openSession();
		session.beginTransaction();
		
		Predmet predmet = new Predmet();
		Student student = new Student();
				
		try {
			
			predmet = session.get(Predmet.class, predmetId);
			student = session.get(Student.class, studentId);
			
			// proveravamo da li je kombinacija student-predmet vec upisana u tabeli PolozeniIspiti
			String hql = "from PolozeniIspiti where student = :st and predmet = :pr";
			Query query = session.createQuery(hql);
			query.setParameter("st", student);
			query.setParameter("pr", predmet);
			if (query.getResultList().size() > 0) {
				System.out.println("Nije moguce upisati ocenu student-predmet jer vec postoji");
			} else {
				PolozeniIspiti polozeniIspiti = new PolozeniIspiti();
				polozeniIspiti.setPredmet(predmet);
				polozeniIspiti.setStudent(student);
				polozeniIspiti.setOcena(ocena);
				polozeniIspiti.setDatumPolaganja(new Date());
	
			session.save(polozeniIspiti);	
			System.out.println("Uspesno upisana ocena");
			}
			session.getTransaction().commit();
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u upisiOcenu! " + e);	
		}finally {
			session.close();
		}
		
	}

	public List<PolozeniIspiti> vratiPolozeneIspite(String idPredmet, String studentId) {
		// ako se prosledi idPredmet = null onda pravi listu za sve predmete
		// ako se prosledi studentId = null onda pravi listu za sve studente

		List<PolozeniIspiti> polozeniIspiti = new ArrayList<>(); 
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			
			Predmet predmet = null;
			Student student = null;

			String hql = "FROM PolozeniIspiti WHERE 5=5 ";
			if (idPredmet != null) {
				predmet = session.get(Predmet.class, Integer.parseInt(idPredmet));
				hql += "AND predmet = :pr ";
			}
			if (studentId != null) {
				student = session.get(Student.class, Integer.parseInt(studentId));
				hql += "AND student = :st ";
			}
			Query query = session.createQuery(hql);
			if (idPredmet != null) {
				query.setParameter("pr", predmet);
			}
			if (studentId != null) {
				query.setParameter("st", student);
			}
			
			polozeniIspiti = query.getResultList();
			
			session.getTransaction().commit();
			System.out.println("Uspesno preuzeti preuzeti ispiti");
			return polozeniIspiti;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiPolozeneIspite! " + e);	
			return null;
		}finally {
			session.close();
		}
		
	}

}

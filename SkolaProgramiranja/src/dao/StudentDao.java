package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Predmet;
import model.Smer;
import model.Student;

public class StudentDao {

	SessionFactory sf = HibernateUtil.getSessionFactory();

	public List<Student> vratiSveStudente() {

		
		List<Student> listaStudenata = new ArrayList<>();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			String hql = "FROM Student";
			Query query = session.createQuery(hql);
			listaStudenata = query.getResultList();
			
			session.getTransaction().commit();
			System.out.println("Uspesno vracena lista studenata");
			return listaStudenata;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa vratiSveStudente()! " + e);
			return null;
		} finally {
			session.close();
		}
	}

	public void poveziSmerStudent(String idSmer, String idStudent) {

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			
			Student student = session.get(Student.class, Integer.parseInt(idStudent));
			Smer smer = session.get(Smer.class, Integer.parseInt(idSmer));
		
			student.setSmer(smer);
			session.update(student);
			System.out.println("Uspesno povezan smer i student");
			session.getTransaction().commit();
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u poveziSmerStudent! " + e);	

		}finally {
			session.close();
		}
		
	}
	

	public void razveziSmerStudent(String idStudent) {
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			
			Student student = session.get(Student.class, Integer.parseInt(idStudent));
			
			// za razvezivanje je dovoljno samo da u Studentu stavimo null u polje smer
			student.setSmer(null);
			session.update(student);
			System.out.println("Uspesno razvezan smer i student");
			session.getTransaction().commit();
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u razveziSmerStudent! " + e);	

		}finally {
			session.close();
		}
		
	}
	
	
	
	
}

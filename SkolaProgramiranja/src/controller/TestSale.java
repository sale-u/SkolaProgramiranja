package controller;

import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.UserType;
import model.Address;
import model.Contact;
import model.PolozeniIspiti;
import model.Predmet;
import model.Profesor;
import model.Smer;
import model.Student;
import model.User;

public class TestSale {

	final static SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {

		// proba kreiranja MySQL baze programerska_skola na osnovu kreiranih model klasa i Hibernatea
		// samo da ubacimo jednog admin usera u User entitet
		
		ubaciUsera("Admin1", "123", UserType.ADMINISTRACIJA);	// idUser = 1
		ubaciUsera("Stud1", "456", UserType.STUDENT);			// idUser = 2	
		ubaciUsera("Prof1", "789", UserType.PROFESOR);		// idUser = 3
		ubaciUsera("Prof2", "142", UserType.PROFESOR);		// idUser = 4
		ubaciUsera("Stud2", "999", UserType.STUDENT);			// idUser = 5
	
		List<Predmet> predmetiNaSmeru = new ArrayList<>();
		Predmet predmetProf1 = ubaciPredmet("Java osnove", "JO123"); 
		predmetiNaSmeru.add(predmetProf1);
		predmetiNaSmeru.add(ubaciPredmet("JDBC", "JD456"));
		predmetiNaSmeru.add(ubaciPredmet("Hibernate", "HB345"));

		Smer smer1 = ubaciSmer("Java Junior", "JJ890", predmetiNaSmeru);
		
		Profesor prof1 = ubaciProfesora(3, "CC123", "Profesor1",
				"Profesoric1", "Beograd", "prof1@gmail.com", predmetiNaSmeru);
		
		Predmet predmetProf2 = ubaciPredmet("WEB java", "WB123");
		predmetiNaSmeru.add(predmetProf2);
		Smer smer2 = ubaciSmer("Java Advanced", "JA888", predmetiNaSmeru);
		
		predmetiNaSmeru = new ArrayList<>();
		predmetiNaSmeru.add(predmetProf2);
		Profesor prof2 = ubaciProfesora(4, "DD123", "Profesor2", "Profesoric2", "Nis", 
				"prof2@gmail.com", predmetiNaSmeru);
		
		Student student1 = ubaciStudenta(2, "ABC123", "Student1", "Studentic1", "Beograd", "stud1@gmail.com", smer1);
		Student student2 = ubaciStudenta(5, "DEF123", "Student2", "Studentic2", "Beograd", "stud2@gmail.com", smer2);
		
		Date date1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
		poloziIspit(student1, predmetProf1, date1, 8);
		poloziIspit(student1, predmetProf2, new Date(), 6);
		poloziIspit(student2, predmetProf1, new Date(), 7);
		poloziIspit(student2, predmetProf2, new Date(), 10);
		// pokusavamo isti par student<->predmet da upisemo -> ODBIJA SE
		poloziIspit(student1, predmetProf1, new Date(), 8);
		poloziIspit(student2, predmetProf2, new Date(), 6);
		

	}
	
	// ==========================================================================================
	
	public static User ubaciUsera(String userName, String password, UserType tipUsera) {
		
		// ubacujemo jednog usera u entitet/tabelu User

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserType(tipUsera);;

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(user);
			System.out.println("Ubacen user " + user.getUserName());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("*** Nesto je puklo u ubaciUsera");
		} finally {
			session.close();
		}
		return user;
	}
	
	// ==========================================================================================
	
	public static Student ubaciStudenta(int idUser, String indexNo, String firstName, String lastName,
			String city, String email, Smer smer) {
		
		// ubacujemo Studenta u entitet/tabele UserDetails i subklasu Student
		// idUser preuzima iz vec napravljenog User.idUser
		
		User user = null;
		Student student = null;
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			user = session.get(User.class, idUser);	// prvo proveravamo da li u tabeli User postoji idUser i da li je Student
			if (user != null) {
				System.out.println("Pronadjen User sa idUser = " + idUser);
				if (user.getUserType() != UserType.STUDENT) {
					System.out.println("*** Ali on NIJE Student !");
					throw new IllegalArgumentException("*** User NIJE student");
				}
			} else {
				System.out.println("*** NIJE pronadjen User sa idUser = " + idUser);
				throw new IllegalArgumentException("*** Nije nadjen user!");
			}
			student = new Student();
			student.setUser(user);		// novog Studenta vezujemo sa pronadjenim User user
			student.setIndexNo(indexNo);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			Address address = new Address();	// address objekat kao embedded
			address.setCity(city);
			student.setAddress(address);
			Contact contact = new Contact();	// contact objekat kao embedded
			contact.setEmail(email);
			student.setContact(contact);
			student.setSmer(smer);
			
			session.save(student);				// Hibernate ce se sam postarati da prvo ubaci novi red u UserDetails
			tx.commit();						// pa potom i novi red u subklasi Student
			System.out.println("Uspesno upisan Student : " + student.getIdUserDetails() + " >>> " + student.getFirstName());
		} catch (Exception e) {
			tx.rollback();
			System.out.println("*** Nesto je puklo u ubaciStudenta >>> " + e.getMessage());
		} finally {
			session.close();
		}
		return student;
	}

	// ==========================================================================================
	
	public static Smer ubaciSmer(String nazivSmera, String sifraSmera, List<Predmet> predmetiNaSmeru) {
		
		// ubacujemo jednan smer u entitet/tabelu Smer
		// i povezujemo ga sa listom predmeta koji se slusaju na tom smeru
		// a takodje azuriramo i objekte predmeta koji se slusaju na ovom smeru

		Smer smer = new Smer();

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			if (predmetiNaSmeru == null || predmetiNaSmeru.isEmpty()) {
				throw new IllegalArgumentException("*** Lista predmeta na smeru je prazna!");
			}
			
			// perzistiramo objekat smer sa opstim podacima (sve osim List <Predmet>)
			smer.setNazivSmera(nazivSmera);
			smer.setSifraSmera(sifraSmera);
			session.save(smer);
//			Hibernate.initialize(smer.getPredmetiNaSmeru());	// jer je lista predmetiNaSmeru po dfltu LAZY
			
			// Kreiramo parove smer<->Predmet i azuriramo Liste u oba objekta Smer i Predmet 
			for (Predmet p : predmetiNaSmeru) {
				Predmet pr = session.get(Predmet.class, p.getIdPredmet()); // poziv iz baze - ponovo je perzistentan
				Hibernate.initialize(pr.getSmeroviNaKojimaJePredmet());	// jer smeroviNaKojimaJePredmet po dfltu LAZY
				pr.getSmeroviNaKojimaJePredmet().add(smer);		// azuriramo listu smerova na predmetu
				smer.getPredmetiNaSmeru().add(pr);				// azuriramo listu predmeta na smeru
			}
			System.out.println("Ubacen smer " + smer.getNazivSmera() + " i azuriran sa predmetima");
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("*** Nesto je puklo u ubaciSmer >>> " + e.getMessage());
		} finally {
			session.close();
		}
		return smer;
	}
	
	// ==========================================================================================
	
	public static Predmet ubaciPredmet(String nazivPredmeta, String sifraPredmeta) {
		
		// ubacujemo jedan predmet u entitet/tabelu Predmet

		Predmet predmet = new Predmet();
		predmet.setNazivPredmeta(nazivPredmeta);
		predmet.setSifraPredmeta(sifraPredmeta);

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(predmet);
			System.out.println("Ubacen predmet " + predmet.getNazivPredmeta());
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			System.out.println("*** Nesto je puklo u ubaciPredmet");
		} finally {
			session.close();
		}
		return predmet;
	}
	
	// ==========================================================================================
	
	public static Profesor ubaciProfesora(int idUser, String identificationNo, String firstName, String lastName,
			String city, String email, List<Predmet> predmetiKojePredaje) {
		
		// ubacujemo Profesora u entitet/tabele UserDetails i subklasu Profesor
		// idUser preuzima iz vec napravljenog User.idUser
		// povezujemo profesora sa listom predmeta koje predaje
		
		User user = null;
		Profesor profesor = null;
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			user = session.get(User.class, idUser);	// prvo proveravamo da li u tabeli User postoji idUser i da li je Profesor
			if (user != null) {
				System.out.println("Pronadjen User sa idUser = " + idUser);
				if (user.getUserType() != UserType.PROFESOR) {
					System.out.println("*** Ali on NIJE Profesor !");
					throw new IllegalArgumentException("*** User NIJE profesor");
				}
			} else {
				System.out.println("*** NIJE pronadjen User sa idUser = " + idUser);
				throw new IllegalArgumentException("*** Nije nadjen user!");
			}
			profesor = new Profesor();
			profesor.setUser(user);		// novog Profesora vezujemo sa pronadjenim User user
			profesor.setIdentificationNo(identificationNo);
			profesor.setFirstName(firstName);
			profesor.setLastName(lastName);
			Address address = new Address();	// address objekat kao embedded
			address.setCity(city);
			profesor.setAddress(address);
			Contact contact = new Contact();	// contact objekat kao embedded
			contact.setEmail(email);
			profesor.setContact(contact);
			profesor.setPredmetiKojePredaje(predmetiKojePredaje);
			
			session.save(profesor);				// Hibernate ce se sam postarati da prvo ubaci novi red u UserDetails
			tx.commit();						// pa potom i novi red u subklasi Profesor
			System.out.println("Uspesno upisan Profesor : " + profesor.getIdUserDetails() + " >>> " + profesor.getFirstName());
		} catch (Exception e) {
			tx.rollback();
			System.out.println("*** Nesto je puklo u ubaciProfesora >>> " + e.getMessage());
		} finally {
			session.close();
		}
		return profesor;
	}
	
	// ==========================================================================================
	
	public static boolean poloziIspit(Student student, Predmet predmet, Date datumPolaganja, int ocena) {
		
		// ubacujemo polozeni ispit u entitet/tabelu PolozeniIspiti
		// ali se prethodno ispituje da je par student<->predmet vec postoji u tabeli
		// jer ako postoji onda se ne moze upisati

		PolozeniIspiti polozeniIspit = null;
		Student stud = null;
		Predmet pred = null;
		
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			stud = session.get(Student.class, student.getIdUserDetails());
			pred = session.get(Predmet.class, predmet.getIdPredmet());
			if (stud == null || pred == null) {
				throw new IllegalArgumentException("*** Neodgovarajuci parametri za polaganje ispita");
			}
			// Ispitujemo da li vec postoji trazeni par Student.idUserDetails <-> Predmet.idPredmet
			String sql = "SELECT * FROM polozeniispiti "
					+ "WHERE student_idUserDetails = :studParam AND "
					+ "predmet_idPredmet = :predParam";
			Query query = session.createNativeQuery(sql);
			query.setParameter("studParam", stud.getIdUserDetails());
			query.setParameter("predParam", pred.getIdPredmet());
			if (!query.getResultList().isEmpty()) {		// vec postoji par student<->predmet pa se ne moze ponovo upisivati
				throw new IllegalArgumentException("*** Student " + stud.getFirstName() + " je vec polagao ispit " + pred.getNazivPredmeta());
			}

			// par student<->predmet ne postoji pa se moze upisati
			polozeniIspit = new PolozeniIspiti();
			polozeniIspit.setStudent(stud);
			polozeniIspit.setPredmet(pred);
			polozeniIspit.setOcena(ocena);
			polozeniIspit.setDatumPolaganja(datumPolaganja);
			session.save(polozeniIspit);
			System.out.println("Upisano polaganje studenta " + stud.getFirstName() + " ispita " + pred.getNazivPredmeta());
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
			System.out.println("*** Nesto je puklo u poloziIspit >>> " + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}	
	
	
}

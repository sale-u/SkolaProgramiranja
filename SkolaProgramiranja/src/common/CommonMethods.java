package common;

import model.Address;
import model.Administrator;
import model.Contact;
import model.Profesor;
import model.Student;
import model.User;
import model.UserDetails;
import model.UserType;

public class CommonMethods {
	

	public User popuniUsera(String userName, String password, UserType userType) {
		
		// Pravi new User user na osnovu prosledjenih podataka

		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserType(userType);
		
		return user;
	}
	
	// ==========================================================================================
	
	public Student popuniStudent(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user, String indexNo) {
		
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		
		Student student = new Student();
		Address adresa = new Address();
		Contact contact = new Contact();
		
		adresa.setCountry(country);
		adresa.setCity(city);
		adresa.setStreet(street);
		
		contact.setEmail(email);
		contact.setMobilePhone(mobilePhone);
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setUser(user);
		student.setIndexNo(indexNo);
		student.setAddress(adresa);
		student.setContact(contact);
		
		return student;
	}
	
	// ==========================================================================================

	public Profesor popuniProfesor(String firstName, String lastName, String email, String mobilePhone, String country,
			String city, String street, User user, String identificationNo) {
		
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		
		Profesor profesor = new Profesor();
		Address adresa = new Address();
		Contact contact = new Contact();
		
		adresa.setCountry(country);
		adresa.setCity(city);
		adresa.setStreet(street);
		
		contact.setEmail(email);
		contact.setMobilePhone(mobilePhone);
		
		profesor.setFirstName(firstName);
		profesor.setLastName(lastName);
		profesor.setUser(user);
		profesor.setIdentificationNo(identificationNo);
		profesor.setAddress(adresa);
		profesor.setContact(contact);
		
		return profesor;
	}

	// ==========================================================================================
	
	public Administrator popuniAdministrator(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user, String identificationNumber) {
		
		Administrator administrator = new Administrator();
		Address adresa = new Address();
		Contact contact = new Contact();
		
		adresa.setCountry(country);
		adresa.setCity(city);
		adresa.setStreet(street);
		
		contact.setEmail(email);
		contact.setMobilePhone(mobilePhone);
		
		administrator.setFirstName(firstName);
		administrator.setLastName(lastName);
		administrator.setUser(user);
		administrator.setIdentificationNumber(identificationNumber);
		administrator.setAddress(adresa);
		administrator.setContact(contact);
		
		return administrator;
	}
	
	
	// ==========================================================================================
	// ==========================================================================================

	public UserDetails popuniUserDetails(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user) {
		
		// Moja univerzalna metoda koja radi posao za sve tri subklase
		
		// UserDetails je superklasa klasama Student, Profesor, Administracija
		// Prema tome, moguce je napisati UserDetails details = new Student();
		// i vratiti referencu UserDetails details i potom castovati nazad u Student klasu.
		// Na osnovu user tipa procitanog iz objekta user (user.getUserType())
		// odredjujemo koju klasu cemo konkretno instancirati (Student, Profesor ili Administracija).
		// Na tu instancu ce ukazivati referenca UserDetails details

		System.out.println("*************** Moja univerzalna metoda UserDetails popuniUserDetails() ************");
		
		UserDetails details = null;
		
		switch (user.getUserType()) {
			case STUDENT:
				details = new Student();
				break;
			case PROFESOR:
				details = new Profesor();
				break;
			case ADMINISTRACIJA:
				details = new Administrator();
				break;
			default:	
		}
		
		Address adresa = new Address();
		Contact contact = new Contact();
		
		adresa.setCountry(country);
		adresa.setCity(city);
		adresa.setStreet(street);
		
		contact.setEmail(email);
		contact.setMobilePhone(mobilePhone);
		
		details.setFirstName(firstName);
		details.setLastName(lastName);
		details.setUser(user);
		details.setAddress(adresa);
		details.setContact(contact);
		
		return details;		// vracamo referencu UserDetails details koja ukazuje na konkretan objekat subklase
	}

	// ==========================================================================================
	
	
	
}

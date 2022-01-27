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

	public UserDetails popuniUserDetails(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user) {
		
		// UserDetails je superklasa klasama Student, Profesor, Administracija
		// Prema tome, moguce je napisati UserDetails details = new Student();
		// i vratiti referencu UserDetails details i potom castovati nazad u Student klasu.
		// Na osnovu user tipa procitanog iz objekta user (user.getUserType())
		// odredjujemo koju klasu cemo konkretno instancirati (Student, Profesor ili Administracija).
		// Na tu instancu ce ukazivati referenca UserDetails details

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

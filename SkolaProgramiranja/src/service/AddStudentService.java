package service;

import common.CommonMethods;
import dao.AddStudentDao;
import model.Administrator;
import model.Profesor;
import model.Student;
import model.User;
import model.UserDetails;
import model.UserType;

public class AddStudentService {
	
	AddStudentDao dao = new AddStudentDao();
	CommonMethods common = new CommonMethods();
	
	public User popuniUsera(String userName, String password, UserType userType) {
			return common.popuniUsera(userName, password, userType);
	}
	
	// =====================================================================================
	
	public Student popuniStudent(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user,  String indexNo) {
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		return common.popuniStudent(firstName, lastName, email, mobilePhone, country, city, street, user, indexNo);
	}
	
	// =====================================================================================

	public boolean ubaciStudenta(User user, Student student) {
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		return dao.ubaciStudenta(user, student);
	}
	
	// =====================================================================================

	public Profesor popuniProfesor(String firstName, String lastName, String email, String mobilePhone, String country,
			String city, String street, User user, String identificationNo) {
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		return common.popuniProfesor(firstName, lastName, email, mobilePhone, country, city, street, user, identificationNo);
	}
	
	// =====================================================================================

	public boolean ubaciProfesora(User user, Profesor profesor) {
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		return dao.ubaciProfesora(user, profesor);
	}
	
	// =====================================================================================

	public Administrator popuniAdministrator(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user, String identificationNumber) {
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		return common.popuniAdministrator(firstName, lastName, email, mobilePhone, country, city, street, user, identificationNumber);
	}
	
	// =====================================================================================

	public boolean ubaciAdministrator(User user, Administrator administrator) {
		// predavaceva verzija metode za svaki u od subklasa kojima pripadaju objekti
		return dao.ubaciAdministrator(user, administrator);
		
	}
	
	
	// =====================================================================================
	// =====================================================================================

	public UserDetails popuniUserDetails(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user) {
		// moja univerzalna metoda
		return common.popuniUserDetails( firstName, lastName, email, mobilePhone,
				country, city, street, user);
	}
	
	// =====================================================================================

	public boolean ubaciKonkretnogUsera(User user, UserDetails konkretniUser) {
		// moja univerzalna metoda
		return dao.ubaciKonkretnogUsera(user,konkretniUser);
	}
	
	// ======================================================================================
	
	

}

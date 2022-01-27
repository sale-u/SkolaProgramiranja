package service;

import common.CommonMethods;
import dao.AddStudentDao;
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

	public UserDetails popuniUserDetails(String firstName, String lastName, String email, String mobilePhone,
			String country, String city, String street, User user) {
		return common.popuniUserDetails( firstName, lastName, email, mobilePhone,
				country, city, street, user);
	}

	public boolean ubaciKonkretnogUsera(User user, UserDetails konkretniUser) {
		return dao.ubaciKonkretnogUsera(user,konkretniUser);
	}

}

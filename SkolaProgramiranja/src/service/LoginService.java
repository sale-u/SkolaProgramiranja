package service;

import common.Validacija;
import dao.LoginDao;
import model.User;
import model.UserDetails;

public class LoginService {
	
	LoginDao dao = new LoginDao();
	Validacija validacija = new Validacija();
	
	public void upisiUseraUbazu(User user, UserDetails details) {
		dao.upisiUserIuserDetails(user, details);
	}

}

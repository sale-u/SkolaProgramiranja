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

	public boolean proveriUserIPassPrazni(String userName, String password) {
		return validacija.proveriUserIPassPrazni(userName, password);
	}

	public User vratiAkoPostojiUser(String userName, String password) {
		return dao.vratiAkoPostojiUser(userName, password);
	}

}

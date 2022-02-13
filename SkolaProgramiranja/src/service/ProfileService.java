package service;

import dao.ProfileDao;
import model.User;
import model.UserDetails;

public class ProfileService {
	
	ProfileDao dao = new ProfileDao();

	public UserDetails vratiUserDetailsZaUsera(String idUser) {
		return dao.vratiUserDetailsZaUsera(idUser);
	}

	public void azurirajUserDetails(String idUserDetails, String firstName, String lastName, String country,
			String city, String street, String mobilePhone, String email) {
		dao.azurirajUserDetails( idUserDetails,  firstName,  lastName,  country,
				 city,  street,  mobilePhone,  email);
		
	}

	public User vratiUserZaidUsera(String idUser) {
		return dao.vratiUserZaidUsera(idUser);
	}

}

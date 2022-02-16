package service;

import java.util.List;

import dao.ProfesorDao;
import dao.ProfileDao;
import model.Predmet;
import model.Profesor;
import model.Smer;
import model.Student;
import model.UserDetails;

public class ProfesorService {
	
	ProfesorDao dao = new ProfesorDao();
	ProfileDao pDao = new ProfileDao();

	public Profesor vratiProfesoraPoId(int idUserDetails) {
		return dao.vratiProfesoraPoId(idUserDetails);
	}

	public UserDetails vratiUserDetailsZaUsera(String idUser) {
		return pDao.vratiUserDetailsZaUsera(idUser);
	}

	public List<Smer> vratiSveSmerovePredmeta(String idPredmeta) {
		return dao.vratiSveSmerovePredmeta(idPredmeta);
	}

	public List<Student> vratiSveStudenteSmera(List<Smer> smeroviPredmeta) {
		return dao.vratiSveStudenteSmera(smeroviPredmeta);
	}

	public Predmet vratiPredmetPoId(String idPredmeta) {
		return dao.vratiPredmetPoId(idPredmeta);
	}

}

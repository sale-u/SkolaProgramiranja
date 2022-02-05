package service;

import java.util.List;

import dao.PrikaziProfesoreDao;
import model.Predmet;
import model.Profesor;
import wrapper.ProfesorWrapper;

public class PrikaziProfesoreService {
	
	PrikaziProfesoreDao dao = new PrikaziProfesoreDao();

	public List<ProfesorWrapper> vratiSveProfesore() {
		return dao.vratiSveProfesore();
	}

	public List<Predmet> vratiSvePredmete() {
		return dao.vratiSvePredmete();
	}

	public Profesor vratiProfesoraPoID(String idProfesor) {
		return dao.vratiProfesoraPoID(idProfesor);
	}

	public void dodajPredmetProfesoru(String idProfesor, String idPredmet) {
		dao.dodajPredmetProfesoru(idProfesor, idPredmet);		
	}

	public void oduzmiPredmetProfesoru(String idProfesor, String idPredmet) {
		dao.oduzmiPredmetProfesoru(idProfesor, idPredmet);
		
	}

}

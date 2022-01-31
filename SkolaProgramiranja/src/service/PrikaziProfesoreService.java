package service;

import java.util.List;

import dao.PrikaziProfesoreDao;
import wrapper.ProfesorWrapper;

public class PrikaziProfesoreService {
	
	PrikaziProfesoreDao dao = new PrikaziProfesoreDao();

	public List<ProfesorWrapper> vratiSveProfesore() {
		return dao.vratiSveProfesore();
	}

}

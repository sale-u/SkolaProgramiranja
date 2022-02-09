package service;

import java.util.List;

import common.CommonMethods;
import dao.PredmetSmerDao;
import model.Predmet;
import model.Smer;

public class PredmetSmerService {
	
	PredmetSmerDao dao = new PredmetSmerDao();
	CommonMethods common = new CommonMethods();
	
	
	public Predmet popuniPredmet(String nazivPredmeta, String sifraPredmeta) {
		return common.popuniPredmet(nazivPredmeta, sifraPredmeta);
	}


	public boolean snimiPredmet(Predmet predmet) {
		return dao.snimiPredmet(predmet);
	}

	// ==========================================================================================
	
	public Smer popuniSmer(String nazivSmera, String sifraSmera) {
		return common.popuniSmer(nazivSmera, sifraSmera);
	}


	public boolean snimiSmer(Smer smer) {
		return dao.snimiSmer(smer);
	}


	public List<Predmet> vratiSvePredmete() {
		return dao.vratiSvePredmete();
	}


	public List<Smer> vratiSveSmerove() {
		return dao.vratiSveSmerove();
	}


	public void poveziPredmetIsmer(String idPredmet, String idSmer) {
		dao.poveziPredmetIsmer(idPredmet, idSmer);
		
	}


	public void ukloniPredmetSaSmera(String idSmer, String idPredmet) {
		dao.ukloniPredmetSaSmera( idSmer,  idPredmet);
		
		
		
	}
	
	// ==========================================================================================

}

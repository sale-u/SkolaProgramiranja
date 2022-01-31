package service;

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
	
	// ==========================================================================================

}

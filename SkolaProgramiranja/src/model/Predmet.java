package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Predmet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPredmet;
	private String nazivPredmeta;
	private String sifraPredmeta;
	@ManyToMany
	private List<Smer> smeroviNaKojimaJePredmet = new ArrayList<>();
	
	// =========================================================================================
	
	public int getIdPredmet() {
		return idPredmet;
	}
	public void setIdPredmet(int idPredmet) {
		this.idPredmet = idPredmet;
	}
	public String getNazivPredmeta() {
		return nazivPredmeta;
	}
	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}
	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}
	public List<Smer> getSmeroviNaKojimaJePredmet() {
		return smeroviNaKojimaJePredmet;
	}
	public void setSmeroviNaKojimaJePredmet(List<Smer> smeroviNaKojimaJePredmet) {
		this.smeroviNaKojimaJePredmet = smeroviNaKojimaJePredmet;
	}
	
}

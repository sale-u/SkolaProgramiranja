package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Smer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSmer;
	private String nazivSmera;
	private String sifraSmera;
	@ManyToMany(mappedBy = "smeroviNaKojimaJePredmet") // , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Predmet> predmetiNaSmeru = new ArrayList<>();
	
	// =========================================================================================	
	
	public int getIdSmer() {
		return idSmer;
	}
	public void setIdSmer(int idSmer) {
		this.idSmer = idSmer;
	}
	public String getNazivSmera() {
		return nazivSmera;
	}
	public void setNazivSmera(String nazivSmera) {
		this.nazivSmera = nazivSmera;
	}
	public String getSifraSmera() {
		return sifraSmera;
	}
	public void setSifraSmera(String sifraSmera) {
		this.sifraSmera = sifraSmera;
	}
	public List<Predmet> getPredmetiNaSmeru() {
		return predmetiNaSmeru;
	}
	public void setPredmetiNaSmeru(List<Predmet> predmetiNaSmeru) {
		this.predmetiNaSmeru = predmetiNaSmeru;
	}
	
}

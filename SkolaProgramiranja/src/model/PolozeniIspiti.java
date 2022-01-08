package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class PolozeniIspiti {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPolozeniIspit;
	@ManyToOne
	private Student student;
	@ManyToOne
	private Predmet predmet;
	private int ocena;
	private Date datumPolaganja;
	
	// =========================================================================================
	
	public int getIdPolozeniIspit() {
		return idPolozeniIspit;
	}
	public void setIdPolozeniIspit(int idPolozeniIspit) {
		this.idPolozeniIspit = idPolozeniIspit;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Predmet getPredmet() {
		return predmet;
	}
	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public Date getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(Date datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
}

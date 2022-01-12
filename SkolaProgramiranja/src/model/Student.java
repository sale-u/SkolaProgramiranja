package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Student extends UserDetails {
	
	private String indexNo;
	@ManyToOne				// moze biti vise studenata na jednom smeru
	private Smer smer;
	
	// =========================================================================================
	
	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public Smer getSmer() {
		return smer;
	}
	public void setSmer(Smer smer) {
		this.smer = smer;
	}

}

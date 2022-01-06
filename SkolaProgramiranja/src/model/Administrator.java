package model;

import javax.persistence.Entity;

@Entity
public class Administrator extends UserDetails {
	
	private String identificationNumber;
	
	// =========================================================================================

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
}

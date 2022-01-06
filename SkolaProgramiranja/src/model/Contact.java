package model;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	
	private String email;
	private String mobilePhone;
	
	// =========================================================================================
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
}

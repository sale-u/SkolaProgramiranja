package model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int idUserDetails;
	private String firstName;
	private String lastName;
	@OneToOne
	private User user;
	@Embedded
	private Address address;
	@Embedded
	private Contact contact;
	
	// =========================================================================================
	
	public int getIdUserDetails() {
		return idUserDetails;
	}
	public void setIdUserDetails(int idUserDetails) {
		this.idUserDetails = idUserDetails;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
}

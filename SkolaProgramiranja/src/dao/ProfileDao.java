package dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Address;
import model.Contact;
import model.User;
import model.UserDetails;

public class ProfileDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();

	public UserDetails vratiUserDetailsZaUsera(String idUser) {
		
		UserDetails detalj = new UserDetails();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			User user = session.get(User.class, Integer.parseInt(idUser));
			// iz UserDetails izvlacimo podatke navodjenjem kompletnog objekta User (vidi model.userDetails) a ne sa idUser
			String hql = "from UserDetails where user = :user";
			Query query = session.createQuery(hql);
			query.setParameter("user", user);
			
			detalj = (UserDetails) query.getSingleResult();
			
			session.getTransaction().commit();
			System.out.println("Uspesno vraceni UserDetails");
			return detalj;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa vratiUserDetailsZaUsera()! " + e);	
			return null;
		}finally {
			session.close();
		}

	}

	public void azurirajUserDetails(String idUserDetails, String firstName, String lastName, String country,
			String city, String street, String mobilePhone, String email) {

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			UserDetails details = session.get(UserDetails.class, Integer.parseInt(idUserDetails));
			details.setFirstName(firstName);
			details.setLastName(lastName);
			Address address = new Address();
			address.setCity(city);
			address.setCountry(country);
			address.setStreet(street);
			details.setAddress(address);
			Contact contact = new Contact();
			contact.setEmail(email);
			contact.setMobilePhone(mobilePhone);
			details.setContact(contact);
			
			session.update(details);
			
			session.getTransaction().commit();
			System.out.println("Uspesno azuriran UserDetails");
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa azurirajUserDetails()! " + e);	
		}finally {
			session.close();
		}		
	}

	public User vratiUserZaidUsera(String idUser) {
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			User user = session.get(User.class, Integer.parseInt(idUser));

			session.getTransaction().commit();
			System.out.println("Uspesno vraceni User");
			return user;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa vratiUserZaidUsera()! " + e);	
			return null;
		}finally {
			session.close();
		}
	}
	
	

}

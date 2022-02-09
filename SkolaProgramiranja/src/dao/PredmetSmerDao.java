package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Predmet;
import model.Profesor;
import model.Smer;

public class PredmetSmerDao {
	
	SessionFactory sf = HibernateUtil.getSessionFactory();

	public boolean snimiPredmet(Predmet predmet) {

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(predmet);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan predmet");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan predmet! " + e);	
			return false;
		}finally {
			session.close();
		}
	}
	
	// ==========================================================================================

	public boolean snimiSmer(Smer smer) {

		Session session = sf.openSession();
		session.beginTransaction();
		try {
			session.save(smer);
			session.getTransaction().commit();
			System.out.println("Uspesno upisan smer");
			return true;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("NIJE upisan smer! " + e);	
			return false;
		}finally {
			session.close();
		}
		
		
	// ==========================================================================================
		
		
	}

	public List<Predmet> vratiSvePredmete() {
		
		List<Predmet> sviPredmeti = new ArrayList<>();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			String hql = "from Predmet";
			Query query = session.createQuery(hql);
			sviPredmeti = query.getResultList();
			// za sada ne povlacimo List<Smer> smeroviNaKojimaJePredmet
			// predavac je odlucio da to uradi u metodi vratiSveSmerove()
			
			session.getTransaction().commit();
			System.out.println("Uspesno preuzeti smerovi");
			return sviPredmeti;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiSvePredmete! " + e);	
			return null;
		}finally {
			session.close();
		}

	}

	public List<Smer> vratiSveSmerove() {

		List<Smer> sviSmerovi = new ArrayList<>();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			String hql = "from Smer";
			Query query = session.createQuery(hql);
			sviSmerovi = query.getResultList();
			for (Smer s: sviSmerovi) {
				// inicijalizaciju liste List<Predmet> predmetiNaSmeru 
				// moramo da radimo za svaki objekat Smer u listi sviSMerovi
				Hibernate.initialize(s.getPredmetiNaSmeru());
			}
			
			session.getTransaction().commit();
			System.out.println("Uspesno preuzeti smerovi");
			return sviSmerovi;
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u vratiSveSmerove! " + e);	
			return null;
		}finally {
			session.close();
		}
	}

	public void poveziPredmetIsmer(String idPredmet, String idSmer) {
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			
			Predmet predmet = session.get(Predmet.class, Integer.parseInt(idPredmet));
			Smer smer = session.get(Smer.class, Integer.parseInt(idSmer));
			
			Hibernate.initialize(predmet.getSmeroviNaKojimaJePredmet());
			
			if (!predmet.getSmeroviNaKojimaJePredmet().contains(smer)) { 	
				// ako na predmetu ne postoji ovaj smer u njegovoj listi, onda ga mozemo dodati
				// lista smeroviNaKojimaJePredmet je glavna u ManyToMany relaciji predmet-smer
				predmet.getSmeroviNaKojimaJePredmet().add(smer);
				session.update(predmet);
				System.out.println("Uspesno povezani smerovi i predmeti");
			} else {
				System.out.println("Ne mogu povezati smer i predmet jer su vec povezani");
			}
			session.getTransaction().commit();
			
		} catch (Exception e) {		
			session.getTransaction().rollback();
			System.out.println("Nesto je puklo u poveziPredmetIsmer! " + e);	

		}finally {
			session.close();
		}
	}

	public void ukloniPredmetSaSmera(String idSmer, String idPredmet) {
		
		// na osnovu idSmer i idPredmet, iz baze povlacimo kompletne objekte smer i predmet
		// i onda iz predmet liste List<Smer> smeroviNaKojimaJePredmet skidamo smer
		
		Smer smer = new Smer();
		Predmet predmet = new Predmet();
		
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			// vadimo smer iz baze na osnovu idSmer
			smer = session.get(Smer.class, Integer.parseInt(idSmer));
			
			// vadimo predmet iz baze na osnovu idPredmet
			predmet = session.get(Predmet.class, Integer.parseInt(idPredmet));
			// i njegovu listu smeroviNaKojimaJePredmet
			Hibernate.initialize(predmet.getSmeroviNaKojimaJePredmet());

			System.out.println("Smer=" + smer.getIdSmer() + " predmet=" + predmet.getIdPredmet() + " > " + predmet.getNazivPredmeta());;
			
			// proveravamo da li predmet u svojoj listi List<Smer> smeroviNaKojimaJePredmet ima ovaj konkretni smer
			if (predmet.getSmeroviNaKojimaJePredmet().contains(smer)) {
				predmet.getSmeroviNaKojimaJePredmet().remove(smer);
				session.saveOrUpdate(predmet);
				session.getTransaction().commit();
				System.out.println("Uspesno skinut predmet sa smera");
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Nesto nije u redu sa ukloniPredmetSaSmera()! " + e);
		} finally {
			session.close();
		}
	}
	

	


}

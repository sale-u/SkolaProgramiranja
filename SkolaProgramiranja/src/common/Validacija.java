package common;

public class Validacija {

	public boolean proveriUserIPassPrazni(String userName, String password) {

		boolean provera = false;
		
		if(userName == null || userName.isEmpty()) {	// moze ili .isEmpty() ili .equals("")
			provera = true;
		}
		
		if(password == null || password.equals("")) {
			provera = true;
		}
		
		return provera;
	}

}

package controller;

import java.io.IOException;

// Validacija username : min 4 char
// Validacija password: min 7 char, min 1 veliko slovo, min 1 broj

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDetails;
import model.UserType;
import service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "ovo je servlet za login", urlPatterns = { "/LoginControllerPath" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dobrodosli u doPost");
		
		// povezivanje na service
		LoginService service = new LoginService();
		
		// prihvatamo parametre iz request objecta
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		System.out.println("UN: " + userName);
		System.out.println("Password: " + password);
		
		// 1.  Proveriti da li su userName i password null ili prazni?
		boolean daLiSuUserIPassPrazni = service.proveriUserIPassPrazni(userName, password);
		
		if (daLiSuUserIPassPrazni) {
			// 1a. Ako su userName ili pass prazni ili null onda vrati odgovor da mora popuniti ta polja
			response.sendRedirect("stranice/prazan_unos.html");
		} else {
			// 1b. Ako nisu prazni i null idemo dalje
			// 2.  Da li postoje ukucani userName i pass u bazi?
			User user = service.vratiAkoPostojiUser(userName, password);
			if (user != null) {
				
				// 2b  Ako postoji user onda ga prebaci na njegovu HTML stranu
				HttpSession sesija = request.getSession();
				
				// "U kutiju ubacujemo objekat user i na kutiji pisemo user"
				sesija.setAttribute("user", user);
				
				if (user.getUserType() == UserType.ADMINISTRACIJA) {
					response.sendRedirect("view/admin.jsp");
				} else if (user.getUserType() == UserType.STUDENT) {
					response.sendRedirect("view/student.jsp");
				} else if (user.getUserType() == UserType.PROFESOR) {
					response.sendRedirect("view/profesor.jsp");
				} else {
					response.sendRedirect("stranice/loginError.html");
				}
				
				
			} else {
				// 2a. Ako ne postoji u bazi onda vracamo neki odgovor da pokusa ponovo -> ponovo ide na logovanje
				response.sendRedirect("stranice/login.html");
			}
			
			
		}
		
		
		

		
		
		
	}
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Dobrodosli u doGet");
		
//		String reqUserName = request.getParameter("userName");
//		String reqPassword = request.getParameter("password");
//		System.out.println("Upisan User Name = " + reqUserName);
//		System.out.println("Upisan Password = " + reqPassword);
//		
//		User user = null;
//
//		Session session = factory.openSession();
//		Transaction tx = session.beginTransaction();
//		
//		try {
//			String hql = "FROM User WHERE userName = :usrn";
//			Query query = session.createQuery(hql);
//			query.setParameter("usrn", reqUserName);
//			List <User> users = query.getResultList();
//			if (users.isEmpty()) {
//				throw new IllegalArgumentException("User * " + reqUserName + " * nije pronadjen u bazi");
//			}
//			user = users.get(0);
//			System.out.println("Pronadjen user * " + reqUserName + " *");
//			if (!user.getPassword().equals(reqPassword)) {
//				throw new IllegalArgumentException("Ukucan pogresan password * " + reqPassword + " * za usera * " + reqUserName + " *");
//			}
//			System.out.println("BRAVO ! Ispravan password * " + reqPassword + " * za usera * " + reqUserName + " *");
//			System.out.printf("%s\t%s\t%s", user.getUserName(), user.getPassword(), user.getUserType());
//			tx.commit();
//		} catch (Exception e) {
//			tx.rollback();
//			System.out.println("*** Nesto je puklo u loginu Usera >>> " + e.getMessage());
//		} finally {
//			session.close();
//		}

	}

}

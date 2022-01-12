package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

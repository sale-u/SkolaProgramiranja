package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDetails;
import service.ProfileService;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet(description = "view profil", urlPatterns = { "/ProfileController" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProfileService service = new ProfileService();
		
		String idUser = request.getParameter("idUser");
		
		System.out.println("ID user = " + idUser);
		
		// treba nam UserDetails objekat
		UserDetails details = service.vratiUserDetailsZaUsera(idUser);
		User user = service.vratiUserZaidUsera(idUser);
		
		request.setAttribute("details", details);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/profile.jsp");
		dispatcher.forward(request, response);

	}

}

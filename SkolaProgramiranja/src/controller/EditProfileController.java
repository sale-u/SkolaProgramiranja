package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProfileService;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet(description = "editovanje profila (UserDetails)", urlPatterns = { "/EditProfileController" })
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProfileService service = new ProfileService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String mobilePhone = request.getParameter("mobilePhone");
		String email = request.getParameter("email");
		String idUserDetails = request.getParameter("idUserDetails");
		String idUser = request.getParameter("idUser");
		
		service.azurirajUserDetails(idUserDetails, firstName, lastName, country, city, street, mobilePhone, email);
		
		response.sendRedirect("ProfileController?idUser=" + idUser);
		
	}

}

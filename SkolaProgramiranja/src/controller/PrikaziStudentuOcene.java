package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PolozeniIspiti;
import model.User;
import model.UserDetails;
import service.ProfesorService;
import service.ProfileService;

/**
 * Servlet implementation class PrikaziStudentuOcene
 */
@WebServlet(description = "prikazi odredjenom studentu njegove ocene", urlPatterns = { "/PrikaziStudentuOcene" })
public class PrikaziStudentuOcene extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProfileService serviceProfile = new ProfileService();
		ProfesorService serviceProfa = new ProfesorService();
		
		String idUser = request.getParameter("idUser");
		
		System.out.println("ID user = " + idUser);
		
		// treba nam UserDetails objekat
		UserDetails details = serviceProfile.vratiUserDetailsZaUsera(idUser);
		
		List<PolozeniIspiti> polozeniIspiti = serviceProfa.vratiPolozeneIspite(null, details.getIdUserDetails()+"");
		// ako se prosledi idPredmet = null onda pravi listu za sve predmete
		// ako se prosledi studentId = null onda pravi listu za sve studente

		
		request.setAttribute("polozeniIspiti", polozeniIspiti);
		request.setAttribute("details", details);
		
		request.getRequestDispatcher("view/prikazOcenaStudentu.jsp").forward(request, response);
		
		
	}

}

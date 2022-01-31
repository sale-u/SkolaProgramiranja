package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Smer;
import service.PredmetSmerService;

/**
 * Servlet implementation class DodajSmerController
 */
@WebServlet(description = "dodaj Smer", urlPatterns = { "/DodajSmerController" })
public class DodajSmerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PredmetSmerService service = new PredmetSmerService();
		
		String nazivSmera = request.getParameter("nazivSmera");
		String sifraSmera = request.getParameter("sifraSmera");
		
		Smer smer = service.popuniSmer(nazivSmera, sifraSmera);
		
		boolean snimiSmer = service.snimiSmer(smer);
		
		if (snimiSmer) {
			response.sendRedirect("view/admin.jsp");
		} else {
			response.sendRedirect("view/dodajSmer.jsp");
		}
		
		
	}

}

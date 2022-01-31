package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PredmetSmerDao;
import model.Predmet;
import service.PredmetSmerService;

/**
 * Servlet implementation class DodajPredmetController
 */
@WebServlet(description = "dodavanje predmeta", urlPatterns = { "/DodajPredmetController" })
public class DodajPredmetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PredmetSmerService service = new PredmetSmerService();
		
		String nazivPredmeta = request.getParameter("nazivPredmeta");
		String sifraPredmeta = request.getParameter("sifraPredmeta");
		
		Predmet predmet = service.popuniPredmet(nazivPredmeta, sifraPredmeta);
		
		boolean snimiPredmet = service.snimiPredmet(predmet);
		
		if (snimiPredmet) {
			response.sendRedirect("view/admin.jsp");
		} else {
			response.sendRedirect("view/dodajPredmet.jsp");
		}
		
		
	}

}

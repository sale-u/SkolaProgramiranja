package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PredmetSmerService;

/**
 * Servlet implementation class UkloniPredmetSaSmeraController
 */
@WebServlet(description = "raskidanje veze predmeta sa smerom", urlPatterns = { "/UkloniPredmetSaSmeraController" })
public class UkloniPredmetSaSmeraController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PredmetSmerService service = new PredmetSmerService();
		
		String idSmer = request.getParameter("idSmer");
		String idPredmet = request.getParameter("idPredmet");
		
		service.ukloniPredmetSaSmera(idSmer, idPredmet);
		
		response.sendRedirect("SmerPredmetController");
	}

}

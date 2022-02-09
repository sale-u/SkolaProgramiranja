package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Predmet;
import model.Smer;
import service.PredmetSmerService;

/**
 * Servlet implementation class SmerPredmetController
 */
@WebServlet(description = "smerovi i predmeti veze", urlPatterns = { "/SmerPredmetController" })
public class SmerPredmetController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PredmetSmerService service = new PredmetSmerService();

		List<Predmet> sviPredmeti = service.vratiSvePredmete();
		List<Smer> sviSmerovi = service.vratiSveSmerove();
		
		request.setAttribute("sviPredmeti", sviPredmeti);
		request.setAttribute("sviSmerovi", sviSmerovi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/smeroviPredmet.jsp");
		dispatcher.forward(request, response);
		
	
	}

}

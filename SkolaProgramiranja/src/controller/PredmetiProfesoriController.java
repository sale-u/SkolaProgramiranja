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
import model.Profesor;
import service.PrikaziProfesoreService;

/**
 * Servlet implementation class PredmetiProfesoriController
 */
@WebServlet(description = "predmeti i profesori", urlPatterns = { "/PredmetiProfesoriController" })
public class PredmetiProfesoriController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrikaziProfesoreService service = new PrikaziProfesoreService();
		
		String idProfesor = request.getParameter("idProfesor");
		
		System.out.println("ID profe = " + idProfesor);
		
		Profesor profesor = service.vratiProfesoraPoID(idProfesor);
		List<Predmet> sviPredmeti = service.vratiSvePredmete();
		
		request.setAttribute("profesor", profesor);
		request.setAttribute("sviPredmeti", sviPredmeti);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/predmetiProfesori.jsp");
		dispatcher.forward(request, response);
		
	}

}

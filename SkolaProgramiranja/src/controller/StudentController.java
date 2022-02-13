package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Smer;
import model.Student;
import service.PredmetSmerService;
import service.StudentService;

/**
 * Servlet implementation class StudentController
 */
@WebServlet(description = "sve oko studenta", urlPatterns = { "/StudentController" })
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentService serviceStud = new StudentService();
	PredmetSmerService serviceSmer = new PredmetSmerService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Student> sviStudenti = serviceStud.vratiSveStudente();
		List<Smer> sviSmerovi = serviceSmer.vratiSveSmerove();
		
		request.setAttribute("sviStudenti", sviStudenti);
		request.setAttribute("sviSmerovi", sviSmerovi);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/studentSmer.jsp");
		dispatcher.forward(request, response);
		
	}

}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentService;

/**
 * Servlet implementation class VezaStudentSmerController
 */
@WebServlet(description = "povezi studenta i smer", urlPatterns = { "/VezaStudentSmerController" })
public class VezaStudentSmerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentService serviceStud = new StudentService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idSmer = request.getParameter("idSmer");
		String idStudent = request.getParameter("idStudent");
		String button = request.getParameter("submit");
		
		System.out.println("Spajamo smer = " + idSmer + " i studenta = " + idStudent + " button=" + button);
		
		// zavisno od toga koje dugme je pristisnuto POVEZI/RAZVEZI
		// poziva se odgovarajuca akcija i metoda
		if (button.equals("POVEZI")) {
			serviceStud.poveziSmerStudent(idSmer, idStudent);
		} else if (button.equals("RAZVEZI")) {
			// za razvezivanje je dovoljno samo da u Studentu stavimo null u polje smer
			serviceStud.razveziSmerStudent(idStudent);
		} else {
			System.out.println("ERROR - Nepoznato dugme!");
		}
		response.sendRedirect("StudentController");
	}

}

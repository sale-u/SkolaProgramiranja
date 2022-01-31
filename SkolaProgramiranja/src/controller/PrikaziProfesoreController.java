package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PrikaziProfesoreService;
import wrapper.ProfesorWrapper;

/**
 * Servlet implementation class PrikaziProfesoreController
 */
@WebServlet(description = "prikazi profesore", urlPatterns = { "/PrikaziProfesoreController" })
public class PrikaziProfesoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	PrikaziProfesoreService service = new PrikaziProfesoreService();
	
	List<ProfesorWrapper> listaProfesora = service.vratiSveProfesore();
	
	System.out.println("U listi ima " + listaProfesora.size() + " profesora");
		
	}

}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProfesorService;

/**
 * Servlet implementation class ProfesorController
 */
@WebServlet(description = "profesor vidi svoje predmete", urlPatterns = { "/ProfesorController" })
public class ProfesorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProfesorService service = new ProfesorService();
		
		String idUser = request.getParameter("idUser");

	}

}

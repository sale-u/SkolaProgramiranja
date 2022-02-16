package controller;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Predmet;
import model.Smer;
import model.Student;
import service.ProfesorService;

/**
 * Servlet implementation class SpisakStudenataController
 */
@WebServlet(description = "spisak studenata na smeru", urlPatterns = { "/SpisakStudenataController" })
public class SpisakStudenataController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProfesorService service = new ProfesorService();
		
		String idPredmeta = request.getParameter("idPredmeta");
		
		System.out.println("idPredmeta=" + idPredmeta);
		
		
		Predmet predmet = service.vratiPredmetPoId(idPredmeta);
		
		// na osnovu idPredmeta, vraca sve smerove na kojima se slusa ovaj predmet (idPredmet)
		List<Smer> smeroviPredmeta = service.vratiSveSmerovePredmeta(idPredmeta);
		
		// na osnovu svih smerova na kojima je predmet (idPredmet), vraca sve studente sa tih smerova
		List<Student> sviStudenti = service.vratiSveStudenteSmera(smeroviPredmeta); 

		request.setAttribute("predmet", predmet);
		request.setAttribute("sviStudenti", sviStudenti);
		
		request.getRequestDispatcher("view/spisakStudenata.jsp").forward(request, response);
		
	}

}

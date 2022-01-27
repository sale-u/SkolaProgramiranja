package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Profesor;
import model.Student;
import model.User;
import model.UserDetails;
import model.UserType;
import service.AddStudentService;

/**
 * Servlet implementation class AddProfesorController
 */
@WebServlet(description = "dodavanje Profesora", urlPatterns = { "/AddProfesorController" })
public class AddProfesorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ===============================================================================================
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 26.01.22 ovo je predavaceva verzija gde je za svaku subklasu (student, profesor, administracija)
		// pravio posebne metode popuniStudent(), popuniProfesor()...
		// i ubaciStudenta(), ubaciProfesora()
		// Medjutim, kako su ove sve metode jako slicne, ja sam ih malo modifikovao i napravio ih univerzalnim
		// tako da su moje metode:
		// popuniUserDetails() i ubaciKonkretnogUsera()
		// koje koristim u doPost() metodi iznad!
		
		AddStudentService service = new AddStudentService();
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobilePhone = request.getParameter("mobilePhone");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String identificationNo = request.getParameter("identificationNo");
		
		User user = service.popuniUsera(userName,password, UserType.PROFESOR);
		Profesor profesor = service.popuniProfesor(firstName, lastName, email, mobilePhone, country, city, street, user, identificationNo);
		
		boolean snimiProfesora = service.ubaciProfesora(user,profesor);
		
		if(snimiProfesora) {
			response.sendRedirect("view/admin.jsp");
		}else {
			response.sendRedirect("view/addProfesor.jsp");
		}
		
	}

	// ============================================================================================
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// ************************************ MOJA VERZIJA *******************************************
		// moja verzija doPost() metoda gde pozivamo univerzalne metode koje rade za sva tri tipa korisnika:
		// public UserDetails popuniUserDetails()
		// public boolean ubaciKonkretnogUsera()

		AddStudentService service = new AddStudentService();
			
		// prosledjujemo podatke sa WEB stranice addProfesor.jsp
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobilePhone = request.getParameter("mobilePhone");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String identificationNo = request.getParameter("identificationNo");
			
		String pageAfter = "";
			
		// Vec u samoj addProfesor.jsp stranici, u <input polju za userName i password smo stavili atribut required
		// tako da nije moguce uneti podatke a da ova dva polja ostanu prazna userName i password
		// Prilikom submita forme, on ispisuje poruku "Please fill out this field" ukoliko neka od ovih polja je prazna
		// i ne dozvoljava da se ide dalje
		// Tako da nam if() provera da li su userName i password prazni nije potrebna
		// pa nam nije ni potrebna stranica "stranice/prazni_un_pass.html"


		User user = service.popuniUsera(userName, password, UserType.PROFESOR);
				
		// popuniUserDetails() vraca referencu superklase UserDetails koja ukazuje na konkr.objekat subklase
		// pa castovanjem reference superklase vracamo puni pristup atributima konkr.objekta subklase
		Profesor profesor = (Profesor) service.popuniUserDetails(firstName, lastName, email, mobilePhone, country, city, street, user);
		profesor.setIdentificationNo(identificationNo);	// dodatno se setuje ident broj na subklasi Profesor
				
		// ubaciKonkretnogUsera() cuva u SQL bazi novog User user i novog konkretnog usera odgovarajuceg tipa
		boolean snimiProfesora = service.ubaciKonkretnogUsera(user, profesor);
				
		if (snimiProfesora) {
			pageAfter = "view/admin.jsp";
		} else {
			pageAfter = "view/addProfesor.jsp";
		}
		response.sendRedirect(pageAfter);		
	}

	// ===============================================================================================
		
	
	
}

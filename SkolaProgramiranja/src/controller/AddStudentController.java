package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;
import model.User;
import model.UserDetails;
import model.UserType;
import service.AddStudentService;

/**
 * Servlet implementation class AddStudentController
 */
@WebServlet(description = "dodavanje Studenta", urlPatterns = { "/AddStudentController" })
public class AddStudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AddStudentService service = new AddStudentService();
		
		// prosledjujemo podatke sa WEB stranice addStudent.jsp
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String mobilePhone = request.getParameter("mobilePhone");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String street = request.getParameter("street");
		String indexNo = request.getParameter("indexNo");
		
		String pageAfter = "";
		
		// provera da li su prazna polja za userName i password
		if (userName == null || userName.equals("") || password == null || password.equals("")) {
			System.out.println("Polja userName i pasword NE smeju biti prazni");
			pageAfter = "stranice/prazni_un_pass.html";
		} else {
			// OK
			User user = service.popuniUsera(userName, password, UserType.STUDENT);
			
			// popuniUserDetails() vraca referencu superklase UserDetails koja ukazuje na konkr.objekat subklase
			// pa castovanjem reference superklase vracamo puni pristup atributima konkr.objekta subklase
			Student student = (Student) service.popuniUserDetails(firstName, lastName, email, mobilePhone, country, city, street, user);
			student.setIndexNo(indexNo);	// dodatno se setuje broj indexa na subklasi Student
			
			// ubaciKonkretnogUsera() cuva u SQL bazi novog User user i novog konkretnog usera odgovarajuceg tipa
			boolean snimiStudenta = service.ubaciKonkretnogUsera(user, student);
			
			if (snimiStudenta) {
				pageAfter = "view/admin.jsp";
			} else {
				pageAfter = "view/addStudent.jsp";
			}
		}
		response.sendRedirect(pageAfter);		
	}

}

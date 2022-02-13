package service;

import java.util.List;

import dao.StudentDao;
import model.Student;

public class StudentService {
	
	StudentDao dao = new StudentDao();

	public List<Student> vratiSveStudente() {
		return dao.vratiSveStudente();
	}

	public void poveziSmerStudent(String idSmer, String idStudent) {
		dao.poveziSmerStudent( idSmer,  idStudent);
	}

	public void razveziSmerStudent(String idStudent) {
		dao.razveziSmerStudent(idStudent);	
	}
	
	

}

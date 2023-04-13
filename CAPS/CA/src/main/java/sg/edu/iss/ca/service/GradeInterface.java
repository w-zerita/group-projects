package sg.edu.iss.ca.service;

import java.util.List;

import sg.edu.iss.ca.model.Grade;

public interface GradeInterface {
	public List<Grade> viewStudentGrade(int sid);
	public double viewCGPA(List<Grade> grades);
	public Grade findGrade(int sid, int cid);

}

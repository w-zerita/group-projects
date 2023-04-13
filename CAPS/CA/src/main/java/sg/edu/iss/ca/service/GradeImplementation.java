package sg.edu.iss.ca.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.repo.CourseRepository;
import sg.edu.iss.ca.repo.GradeRepository;
import sg.edu.iss.ca.repo.StudentRepository;

@Service
public class GradeImplementation implements GradeInterface {

	
	@Autowired
	CourseRepository crepo;
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	GradeRepository grepo;
	
	@Transactional
	public List<Grade> viewStudentGrade(int sid) {
		return grepo.findGradeByStudentID(sid);
	}

	@Transactional
	public double viewCGPA(List<Grade> grades) {
		double totalGPA = 0;
		double totalCredits = 0;
		for (Grade grade : grades) {
			if (grade.getGrade() != null)
			{
				switch (grade.getGrade()) {
				case "A+":
				case "A":
					totalGPA += 5.0 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case "A-":
					totalGPA += 4.5 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case"B+":
					totalGPA += 4.0 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case"B":
					totalGPA += 3.5 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case "B-":
					totalGPA += 3.0 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case "C+":
					totalGPA += 2.5 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case "C":
					totalGPA += 2.0 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case"D+":
					totalGPA += 1.5 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case"D":
					totalGPA += 1.0 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
				case "F":
					totalGPA += 0.0 * grade.getCourse().getCredits();
					totalCredits += grade.getCourse().getCredits();
					break;
					}
				}
			}
		
		return totalGPA / totalCredits ;
		
	}

	@Transactional
	public Grade findGrade(int sid, int cid) {
		Grade grade = grepo.findGradeByStudentIDAndCourseID(sid, cid);
		return grade;
		
	}




}

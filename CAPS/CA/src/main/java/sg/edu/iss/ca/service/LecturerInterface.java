package sg.edu.iss.ca.service;


import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.model.Student;

public interface LecturerInterface {

	public List<Course> listAllCourse();
	public List<Student> listAllStudent();
	public boolean authenticate(Lecturer lecturer);
	public void addGrade(Grade g);
	public Lecturer findByUserName(String userName);
	public Course findCourseById(Integer id);
	public Grade setGrade(Grade g);
	public boolean checkGrade(Grade g);
	public List<Lecturer> listAllLectures();
	Page<Lecturer>findPaginated(int pageNo, int pageSize);
}

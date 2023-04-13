package sg.edu.iss.ca.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.repo.CourseRepository;
import sg.edu.iss.ca.repo.GradeRepository;
import sg.edu.iss.ca.repo.LecturerRepository;
import sg.edu.iss.ca.repo.StudentRepository;


@Service
public class LecturerImplementation implements LecturerInterface {
	
	@Autowired
	LecturerRepository lrepo;
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	CourseRepository crepo;
	
	@Autowired
	GradeRepository grepo;

	@Transactional
	public List<Course> listAllCourse() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}

	@Transactional
	public List<Student> listAllStudent() {
		// TODO Auto-generated method stub
		return srepo.findAll();
	}

	@Transactional
	public boolean authenticate(Lecturer lecturer) {
		// TODO Auto-generated method stub
		Lecturer fromDb = lrepo.findLecturerByUserNameAndUserPassword(lecturer.getUserName()
				, lecturer.getUserPassword());
		if(fromDb != null) {
			return true;
		}
		else {
			return false;
		}
	}



	@Transactional
	public Lecturer findByUserName(String userName) {
		// TODO Auto-generated method stub
		return lrepo.findLecturerByUserName(userName);
	}

	@Transactional
	public Course findCourseById(Integer id) {
		// TODO Auto-generated method stub
		return crepo.findCourseById(id);
	}

	@Transactional
	public void addGrade(Grade g) {
		grepo.saveAndFlush(g);
		
	}

	@Transactional
	public Grade setGrade(Grade g) {
		Grade temp = grepo.findGradeById(g.getId());
		temp.setGrade(g.getGrade());
		grepo.saveAndFlush(temp);
		return temp;
	}
	
	@Transactional
	public boolean checkGrade(Grade g) {
		Grade temp = grepo.findGradeById(g.getId());
		if(temp.getGrade()!=null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Transactional
	public List<Lecturer> listAllLectures() {
	return lrepo.findAll();
	}


}

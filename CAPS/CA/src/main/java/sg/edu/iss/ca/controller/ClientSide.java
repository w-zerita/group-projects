package sg.edu.iss.ca.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.service.CourseInterface;
import sg.edu.iss.ca.service.LecturerInterface;
import sg.edu.iss.ca.service.StudentInterface;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ClientSide {

	@Autowired
	StudentInterface studentService;

	@Autowired
	CourseInterface courseService;

	@Autowired
	LecturerInterface lectureService;

//	@GetMapping("/listStudent")
//	public List<Student> listStudentJS() {
//
//		ArrayList<Student> existingStudent = (ArrayList<Student>) studentService.findAll();
//		ArrayList<Student> onlyStudent = new ArrayList<Student>();
//		for (Student s : existingStudent) {
//			Student student = new Student();
//			student.setUserName(s.getUserName());
//			student.setFirstName(s.getFirstName());
//			student.setLastName(s.getLastName());
//
//			onlyStudent.add(student);
//		}
//		return onlyStudent;
//	}

	@GetMapping("/listCourse")
	public List<Course> listCourseJS() {

		ArrayList<Course> existingCourse = (ArrayList<Course>) courseService.findAllCourses();
		ArrayList<Course> onlyCourse = new ArrayList<Course>();
		for (Course c : existingCourse) {
			Course course = new Course();
			course.setId(c.getId());
			course.setName(c.getName());
			course.setDescription(c.getDescription());
			course.setCredits(c.getCredits());
			course.setSize(c.getSize());
			course.setEnrollment(c.getEnrollment());

			onlyCourse.add(course);
		}
		return onlyCourse;
	}

	@GetMapping("/listLecture")
	public List<Lecturer> listLectureJS() {

		ArrayList<Lecturer> existingLecturer = (ArrayList<Lecturer>) lectureService.listAllLectures();
		ArrayList<Lecturer> onlyLecturer = new ArrayList<Lecturer>();
		for (Lecturer l : existingLecturer) {
			Lecturer lecturer = new Lecturer();
			lecturer.setFirstName(l.getFirstName());
			lecturer.setLastName(l.getLastName());
			lecturer.setTitle(l.getTitle());

			onlyLecturer.add(lecturer);
		}
		return onlyLecturer;
	}

	@GetMapping("/listCourse/{id}")
	 public List<Lecturer> getStudentById(@PathVariable("id") Integer id) {
	  ArrayList<Lecturer> showLecturers = new ArrayList<Lecturer>();
	  
	  Course course = courseService.findCourseById(id);
	  List<Lecturer> lData = (List<Lecturer>) course.getLecturers();
	  
	  for(Lecturer l : lData) {
	   Lecturer lecturer = new Lecturer();
	   lecturer.setFirstName(l.getFirstName());
	   lecturer.setLastName(l.getLastName());
	   lecturer.setTitle(l.getTitle());
	   
	   showLecturers.add(lecturer);
	  }
	  return showLecturers;
	 }
}

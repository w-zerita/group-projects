package sg.edu.iss.ca.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.repo.GradeRepository;
import sg.edu.iss.ca.service.CourseImplementation;
import sg.edu.iss.ca.service.StudentImplementation;


@Controller
@RequestMapping("/manageEnrolment")
public class AdminMangeEnrolmentController {
	
	@Autowired
    CourseImplementation courseService;
	
	@Autowired
	StudentImplementation studentService;
	
	@Autowired
	GradeRepository grepo;
	
	@RequestMapping("/list")
	public String list(Model model,HttpSession session) {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
        model.addAttribute("courses", courseService.findAllCourses());
		return "admin/adminEnrolment";
	}
	
	@RequestMapping("/enrolled")
	public String viewEnrolment(@RequestParam("courseId") int courseId, Model model,HttpSession session) {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		Course course = courseService.findCourseById(courseId);
        model.addAttribute("course", course);
		return "admin/adminListEnrolledStudents";
	}
	
	@RequestMapping("/enrollStudent")
	public String enroll(@RequestParam("courseId") int courseId, Model model,HttpSession session) {
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		// find all students
		List<Student> allStudents = studentService.findAll();
		
		// find students enrolled in course
		Course course = courseService.findCourseById(courseId);
		List<Student> enrolledStudents = (List<Student>) course.getStudents();
		
		// find students not enrolled in course
		List<Student> unenrolledStudents = new ArrayList<Student>();
		
		for (Student student : allStudents)
		{
			if (!enrolledStudents.contains(student))
			{
				unenrolledStudents.add(student);
			}
		}
		
		model.addAttribute("unenrolledStudents", unenrolledStudents);
		model.addAttribute("course", course);
		return "admin/adminEnrollStudent";
	}
	
	@RequestMapping("/saveEnrollStudent")
	public String saveEnrolment(@RequestParam("studentId") int studentId, @RequestParam("courseId") int courseId, Model model,HttpSession session) 
	{
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		Student studentToEnroll = studentService.findById2(studentId);
		Course courseToEnroll = courseService.findCourseById(courseId);
		
//		List<Course> studentCourses = (List<Course>) studentToEnroll.getLearnings();
//		studentCourses.add(courseToEnroll);
//		
//		studentToEnroll.setLearnings(studentCourses);
//		
//		studentService.save(studentToEnroll);
//		
//		courseToEnroll.setEnrollment(courseToEnroll.getEnrollment() + 1);
//		
//		Grade newEnrollGrade = new Grade(courseToEnroll.getId(), studentToEnroll.getId(), null, courseToEnroll, studentToEnroll);
//		grepo.saveAndFlush(newEnrollGrade);
		
		if(studentService.enroll(courseToEnroll, studentToEnroll)) 
		{     
			model.addAttribute("course", courseToEnroll);
			return "forward:/manageEnrolment/enrolled";		
		}
		else 
		{
			model.addAttribute("course", courseToEnroll);
			return "forward:/manageEnrolment/enrolled";	
		}
	}
	
	@RequestMapping("/removeStudentEnrollment")
	public String removeStudentEnrollment(@RequestParam("studentId") int studentId, @RequestParam("courseId") int courseId, Model model,HttpSession session) 
	{
		if (session.getAttribute("asession") == null) {
			return "forward:/admin/login";
		}
		Student studentToRemove = studentService.findById2(studentId);
		Course courseToRemoveFrom = courseService.findCourseById(courseId);
		
		List<Course> studentCourses = (List<Course>) studentToRemove.getLearnings();
		studentCourses.remove(courseToRemoveFrom);		
		studentToRemove.setLearnings(studentCourses);
		
		studentService.save(studentToRemove);
		Grade gradeToRemove = grepo.findGradeByStudentIDAndCourseID(studentId, courseId);
		grepo.delete(gradeToRemove);
        model.addAttribute("course", courseToRemoveFrom);
		return "forward:/manageEnrolment/enrolled";
	}
	
}

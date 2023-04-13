package sg.edu.iss.ca.controller;


import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.model.Lecturer;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.service.CourseInterface;
import sg.edu.iss.ca.service.GradeInterface;
import sg.edu.iss.ca.service.LecturerInterface;
import sg.edu.iss.ca.service.StudentInterface;


@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired
	LecturerInterface lservice;
	
	@Autowired
	CourseInterface cservice;
	
	@Autowired
	StudentInterface sservice;
	
	@Autowired
	GradeInterface gservice;
	
	
	@RequestMapping(value = "/coursetaught")
	public String listTaughtCourse(Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		else {
			Lecturer l = (Lecturer) session.getAttribute("lsession");
			List<Course> assignedCourse = (List<Course>)l.getTeachings();
			model.addAttribute("enrollCourse", assignedCourse);
			return "lecturer/coursetaught";	
		}
	}
	
	@RequestMapping(value = "/gradecourse")
	public String listCourses(Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		else {
			Lecturer l = (Lecturer) session.getAttribute("lsession");
			List<Course> assignedCourse = (List<Course>)l.getTeachings();
			model.addAttribute("enrollCourse", assignedCourse);
			return "lecturer/gradecourse";	
		}
	}
	
	@RequestMapping(value = "/gradecourse/{id}")
	public String gradeCourse(@PathVariable("id") Integer id, Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		else {
			Course c = cservice.findCourseById(id);
			List <Student> studentInCourse= (List<Student>) c.getStudents();
			model.addAttribute("students",studentInCourse);
			model.addAttribute("course",c);
			return "lecturer/gradestudentlist";	
		}
	}
	
	@RequestMapping(value = "/gradecourse/{id1}/{id2}")
	public String gradeStudent(@PathVariable("id1") Integer id1,@PathVariable("id2") Integer id2, Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		
		if(gservice.findGrade(id2, id1).getGrade()!= null)
		{
			return "lecturer/graded";
		}
		else {
			Course c = cservice.findCourseById(id1);
			Student s = sservice.findById1(id2);
			model.addAttribute("student",s);
			model.addAttribute("course",c);
			Grade g = gservice.findGrade(id2, id1);
			model.addAttribute("grade1", g);
			return "lecturer/gradeform";	
		}
	}
	
	@RequestMapping(value = "/savegrade")
	public String saveGrade(@ModelAttribute("grade1") @Valid Grade grade, HttpSession session, Model model) {
		if(lservice.checkGrade(grade))
		{
			Grade nGrade = lservice.setGrade(grade);
			Course c = nGrade.getCourse();
			int cid = c.getId();
			return "forward:/lecturer/gradecourse/"+cid;	
		}
		else
		{
			return "lecturer/graded";
		}		
	}
	
	
	@RequestMapping(value="/viewstudentlist")
	public String viewStudentList(Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		else {
			List<Student> ss = sservice.findAll();
			model.addAttribute("students",ss);
			return "lecturer/studentlist";	
		}

	}
	@RequestMapping(value="/viewperformance/{id}")
	public String viewPerformance(@PathVariable("id") Integer id,Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		else {
			Student s = sservice.findById1(id);
			List<Grade> grades = (List<Grade>) s.getGrades();
			model.addAttribute("gradelist", grades);
			return "lecturer/viewperformance";	
		}

	}
	

	@RequestMapping(value = "/courseEnrolment/{id}")
	public String courseEnrolment(@PathVariable("id") Integer id
			, Model model,HttpSession session) {
		if (session.getAttribute("lsession") == null) {
			return "forward:/lecturer/login";
		}
		else {
			Course course = lservice.findCourseById(id);
			List<Student> enrollStudent= (List<Student>) course.getStudents();
			
			model.addAttribute("enrollStudent", enrollStudent);
			return "lecturer/enrollcourse";	
		}
	}
	
	@RequestMapping(value = "/login")
	public String login(@ModelAttribute("lecturer") Lecturer lecturer, Model model, HttpSession session) {
		if (session.getAttribute("lsession") != null) {
			return "lecturer/lecturerhome";
		}
		else {
			Lecturer l = new Lecturer();
			model.addAttribute("lecturer", l);
			return "lecturer/lecturerlogin";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(@ModelAttribute("lecturer") Lecturer lecturer, Model model, HttpSession session) {
		
		if (session.getAttribute("lsession") != null) {
			session.removeAttribute("lsession");
			return "forward:/lecturer/authenticate";
		}
		else {
			return "forward:/lecturer/authenticate";
		}
	}
	
	@RequestMapping(value="/authenticate")
	public String authenticate(@ModelAttribute("lecturer") @Valid Lecturer lecturer
			, BindingResult bindingResult, Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "lecturer/lecturerlogin";
		}
		
		if (session.getAttribute("lsession") != null) {
			return "lecturer/lecturerlogin";
		}
		else if (lservice.authenticate(lecturer)) {
			Lecturer l = lservice.findByUserName(lecturer.getUserName());
			session.setAttribute("lsession", l);
			return "lecturer/lecturerhome";
		}
		else {
			return "lecturer/lecturerlogin";
		}
	}
}

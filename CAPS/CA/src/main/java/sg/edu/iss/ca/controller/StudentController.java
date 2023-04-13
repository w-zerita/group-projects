package sg.edu.iss.ca.controller;

import java.util.ArrayList;
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
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.service.CourseInterface;
import sg.edu.iss.ca.service.GradeInterface;
import sg.edu.iss.ca.service.StudentImplementation;
import sg.edu.iss.ca.service.StudentInterface;


@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentInterface sservice;
	
	@Autowired
	private GradeInterface gservice;
	
	@Autowired
	private CourseInterface cservice;
	
	
	@Autowired
	public void setStudentITF(StudentImplementation ss) {
		this.sservice = ss;
	}
	
	@RequestMapping(value="/viewcourse")
	public String listCourse(Model model,HttpSession session) {
		if (session.getAttribute("ssession") == null) {
			return "forward:/student/login";
		}
		else {
			Student s = (Student) session.getAttribute("ssession");
			List<Course> totallist = sservice.viewcourse();
			List<Course> enrolllist =  new ArrayList<Course>();
			List<Course> takenlist = (List<Course>) s.getLearnings();
			for (Course c : totallist) {
				if(takenlist.contains(c))
				{
					continue;
				}
				else {
					enrolllist.add(c);
				}
			}
			model.addAttribute("enrolllist", enrolllist);
			return "student/viewcourse";	
		}

	}
	
	@RequestMapping(value="/viewgrade")
	public String viewGrade(Model model ,HttpSession session) {
		if (session.getAttribute("ssession") == null) {
			return "forward:/student/login";
		}
		else {
			
			Student s = (Student) session.getAttribute("ssession");
			List<Grade> grades =gservice.viewStudentGrade(s.getId());
			model.addAttribute("grades", grades);
			double cgpao = gservice.viewCGPA(grades);
			double cgpa = (double)(Math.round(cgpao*100))/100;
			model.addAttribute("cgpa",cgpa);
			return "student/viewgrade";
		}
		
	}
	
	
	@RequestMapping(value = "/enroll/{id}")
	public String enroll(@PathVariable("id") Integer id, HttpSession session) {
		if (session.getAttribute("ssession") == null) {
			return "forward:/student/login";
		}
		else {			
			
			Student s = (Student) session.getAttribute("ssession");
			Course c = cservice.findCourseById(id);
			List<Course> courses= (List<Course>) s.getLearnings();
			if(courses.contains(c))
			{
				return "enrollerror";
			}
			else {
				if(sservice.enroll(cservice.findCourseById(id), s))
				{
					return "forward:/student/viewenrollment";
					}
				else 
				{
					return "student/fullenrolment";
					}
				}			
			}	
		}
	
	@RequestMapping(value="/viewenrollment")
	public String viewEnrollment(Model model,HttpSession session) {
		if (session.getAttribute("ssession") == null) {
			return "forward:/student/login";
		}
		else {
			Student s = (Student) session.getAttribute("ssession");
			List<Course> takenlist = (List<Course>) s.getLearnings();
			model.addAttribute("takenlist", takenlist);
			return "student/viewenrollment";	
		}

	}
	
	
	@RequestMapping(value = "/login")
	public String login(@ModelAttribute("student") Student student, Model model, HttpSession session) {
		if (session.getAttribute("ssession") != null) {
			return "student/studenthome";
		}
		else {
			Student s = new Student();
			model.addAttribute("student", s);
			return "student/studentlogin";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String logout(@ModelAttribute("student") Student student, Model model, HttpSession session) {
		
		if (session.getAttribute("ssession") != null) {
			session.removeAttribute("ssession");
			return "forward:/student/authenticate";
		}
		else {
			return "forward:/student/authenticate";
		}
	}
	
	@RequestMapping(value = "/authenticate")
	public String authenticate(@ModelAttribute("student") @Valid Student student
			, BindingResult bindingResult, Model model, HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "student/studentlogin";
		}
		
		if (session.getAttribute("ssession") != null) {
			return "student/studenthome";
		}
		else if (sservice.authenticate(student)) {
			Student s = sservice.findByUserName(student.getUserName());
			session.setAttribute("ssession", s);
			return "student/studenthome";
		}
		else
			return "student/studentlogin";
	}
}

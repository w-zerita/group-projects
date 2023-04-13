package sg.edu.iss.ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Grade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int courseID;
	private int studentID;
	private String grade;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;	
	

	

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Grade(int courseID, int studentID, Course course, Student student) {
		this.courseID = courseID;
		this.studentID = studentID;
		this.course = course;
		this.student = student;
	}



	public Grade(int id, int courseID, int studentID, String grade, Course course, Student student) {
		this.id = id;
		this.courseID = courseID;
		this.studentID = studentID;
		this.grade = grade;
		this.course = course;
		this.student = student;
	}
	
	

	public Grade(int courseID, int studentID, String grade) {
		this.courseID = courseID;
		this.studentID = studentID;
		this.grade = grade;
	}

	


	public Grade(int courseID, int studentID, String grade, Course course, Student student) {
		this.courseID = courseID;
		this.studentID = studentID;
		this.grade = grade;
		this.course = course;
		this.student = student;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}



	


	
	
}

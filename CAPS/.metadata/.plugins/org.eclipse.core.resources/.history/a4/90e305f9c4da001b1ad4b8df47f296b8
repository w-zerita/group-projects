package sg.edu.iss.ca.model;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	private String studentNum;


	@DateTimeFormat (pattern= "yyyy-MM-dd")
    private Date  enrollmentDate;
	
	@ManyToMany
	@JoinTable(name="student_course",
	joinColumns = @JoinColumn(name = "student_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Collection<Course> learnings;	
	
	@OneToMany(mappedBy = "student" ,cascade = {CascadeType.REMOVE})
	private Collection<Grade> grades;

	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Student(String userName, String userPassword, String firstName, String lastName, String studentNum,
			Date enrollmentDate, Collection<Course> learnings, Collection<Grade> grades) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentNum = studentNum;
		this.enrollmentDate = enrollmentDate;
		this.learnings = learnings;
		this.grades = grades;
	}


	public Student(int id, String userName, String userPassword, String firstName, String lastName, 
			 Date enrollmentDate, Collection<Course> learnings, Collection<Grade> grades) {
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrollmentDate = enrollmentDate;
		this.learnings = learnings;
		this.grades = grades;
	}


	public Student(String userName, String userPassword, String firstName, String lastName, 
			 Date enrollmentDate) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrollmentDate = enrollmentDate;
	}

	

	public Student(String userName, String userPassword, String firstName, String lastName, String studentNum) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentNum = studentNum;
	}
	
	

	public Student(String userName, String userPassword, String firstName, String lastName, String studentNum,
			Date enrollmentDate) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentNum = studentNum;
		this.enrollmentDate = enrollmentDate;
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserPassword() {
		return userPassword;
	}



	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getEnrollmentDate() {
		return enrollmentDate;
	}



	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}



	public Collection<Course> getLearnings() {
		return learnings;
	}



	public void setLearnings(Collection<Course> learnings) {
		this.learnings = learnings;
	}



	public Collection<Grade> getGrades() {
		return grades;
	}



	public void setGrades(Collection<Grade> grades) {
		this.grades = grades;
	}


	public String getStudentNum() {
		return studentNum;
	}



	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentNum);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(studentNum, other.studentNum);
	}
	
	
}
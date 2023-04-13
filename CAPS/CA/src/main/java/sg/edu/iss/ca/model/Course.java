package sg.edu.iss.ca.model;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private int size;
	@Column(columnDefinition = "integer default 0")
	private int enrollment;
	private int credits;
	private String courseNum;

	@ManyToMany(mappedBy = "learnings")
	private Collection<Student> students;
	@ManyToMany(mappedBy = "teachings")
	private Collection<Lecturer> lecturers ;
	
	@OneToMany(mappedBy = "course",cascade = {CascadeType.REMOVE})
	private Collection<Grade> grades;


	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String name, String description, int size, int enrollment, int credits, String courseNum) {
		super();
		this.name = name;
		this.description = description;
		this.size = size;
		this.enrollment = enrollment;
		this.credits = credits;
		this.courseNum = courseNum;
	}



	public Course(int id, String name, String description, int size, int enrollment, int credits,
			Collection<Student> students, Collection<Lecturer> lecturers, Collection<Grade> grades) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.size = size;
		this.enrollment = enrollment;
		this.credits = credits;
		this.students = students;
		this.lecturers = lecturers;
		this.grades = grades;
	}
	
	

	public Course(String name, String description, int size, int enrollment, int credits) {
		this.name = name;
		this.description = description;
		this.size = size;
		this.enrollment = enrollment;
		this.credits = credits;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	public Collection<Lecturer> getLecturers() {
		return lecturers;
	}

	public void setLecturers(Collection<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}

	public Collection<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Collection<Grade> grades) {
		this.grades = grades;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}


	@Override
	public int hashCode() {
		return Objects.hash(courseNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseNum, other.courseNum);
	}
	
	
}

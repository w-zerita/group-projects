package sg.edu.iss.ca.model;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;


@Entity
public class Lecturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	private String title;
	private String lecturerNum;
	
	@ManyToMany
	@JoinTable(name="lecturer_course",
	joinColumns = @JoinColumn(name = "lecturer_id"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Collection<Course> teachings;


	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecturer(int id, String userName, String userPassword, String firstName, String lastName, 
			String title, Collection<Course> teachings) {
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.teachings = teachings;
	}

	public Lecturer(String userName, String userPassword, String firstName, String lastName, String title,
			Collection<Course> teachings) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.teachings = teachings;
	}


	public Lecturer(String userName, String userPassword, String firstName, String lastName, String lecturerNum) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lecturerNum = lecturerNum;
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




	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}




	public Collection<Course> getTeachings() {
		return teachings;
	}

	public void setTeachings(Collection<Course> teachings) {
		this.teachings = teachings;
	}
	
	public String getLecturerNum() {
		return lecturerNum;
	}



	public void setLecturerNum(String lecturerNum) {
		this.lecturerNum = lecturerNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lecturerNum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecturer other = (Lecturer) obj;
		return Objects.equals(lecturerNum, other.lecturerNum);
	}
	
	

}

package sg.edu.iss.ca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	
	
	
	public Admin(String userName, String userPassword, String firstName, String lastName) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin(int id, String userName, String userPassword, String firstName, String lastName) {
		this.id = id;
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
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


	
	


	
	
}

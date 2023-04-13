package sg.edu.iss.ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
	
	public Lecturer findLecturerByUserNameAndUserPassword(String ln, String pwd);

	public Lecturer findLecturerByUserName(String userName);
	
	public Lecturer findLecturerById(Integer lecturerId);

}

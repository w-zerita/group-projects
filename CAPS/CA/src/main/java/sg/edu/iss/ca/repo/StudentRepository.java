package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ca.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public Student findStudentByUserNameAndUserPassword(String sn, String pwd);

	public Student findStudentByUserName(String userName);
	
    public List<Student> findAllByOrderByFirstNameAsc();

    public List<Student> findByUserNameContainsAllIgnoreCase(String user);
}

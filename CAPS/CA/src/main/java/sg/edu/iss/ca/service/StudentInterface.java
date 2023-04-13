package sg.edu.iss.ca.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.model.Student;

public interface StudentInterface {
	
	public  List<Course> viewcourse();
	public Boolean enroll(Course course,Student s);
	public List<Grade> viewgrade(int id);
	public boolean authenticate(Student student);
	public Student findByUserName(String userName);
	public Student findById1(Integer id);
	public List<Student> findAll();
	

    public Student findById2(int id);

    public void save(Student theStudent);

    public void deleteById(int id);

    public List<Student> searchBy(String studentUser);

    public boolean checkExist(Student student);
    
    Page<Student>findPaginated(int pageNo, int pageSize);

}

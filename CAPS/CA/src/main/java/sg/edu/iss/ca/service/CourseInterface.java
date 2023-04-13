package sg.edu.iss.ca.service;


import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.ca.model.Course;


public interface CourseInterface{
	public Course findCourseById(Integer id);
	
    public List<Course> findAllCourses();

    public void createCourseRecord(Course course);

    public void deleteCourse(Integer courseId);
    
    public boolean checkExist(Course course);
    
	public void save(Course course);
	
	Page<Course>findPaginated(int pageNo, int pageSize);

}

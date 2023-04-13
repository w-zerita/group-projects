package sg.edu.iss.ca.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.ca.CaApplication;
import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Student;

//To connect to Spring Framework
@ExtendWith(SpringExtension.class)
//To connect to project
@SpringBootTest(classes = CaApplication.class)
//To create ordering among test methods
@TestMethodOrder(OrderAnnotation.class)
//To connect to configuration (application.properties)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class StudentRepositoryUnitTests {
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	CourseRepository crepo;
	
	@Test
	@Order(1)
	public void testCreateStudent() {
		Student s1 = new Student("belle", "bellebeauty", "Belle", "Beauty", "s0");
		Student saved = srepo.save(s1);
		assertNotNull(saved);
	}
	
	@Test
	@Order(2)
	public void testCreateCourse() {
		Course c1 = new Course("test", "testing", 45, 20, 5, "c0");
		Course saved = crepo.save(c1);
		assertNotNull(saved);
	}
	
	@Test
	@Order(3)
	public void testCheckEnrolment() {
		String un = "Belle";
		Student s2 = srepo.findStudentByUserName(un);
		List <Course> clist = (List <Course>) s2.getLearnings();
		assertTrue(clist.size()==0);
	}
	
	@Test
	@Order(4)
	public void testStudentCourses() {
		int id = 1;
		String un = "Belle";
		Course c2 = crepo.findCourseById(id);
		Student s3 = srepo.findStudentByUserName(un);
		List <Course> clist = new ArrayList<>();
		clist.add(c2);
		assertEquals(0, s3.getLearnings().size());
		s3.setLearnings(clist);
		srepo.save(s3);
		assertNotNull(srepo.findStudentByUserName(un).getLearnings());
	}
	
}

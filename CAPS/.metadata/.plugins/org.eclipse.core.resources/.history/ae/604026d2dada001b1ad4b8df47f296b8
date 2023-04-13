package sg.edu.iss.ca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.ca.model.Course;
import sg.edu.iss.ca.model.Grade;
import sg.edu.iss.ca.model.Student;
import sg.edu.iss.ca.repo.CourseRepository;
import sg.edu.iss.ca.repo.GradeRepository;
import sg.edu.iss.ca.repo.StudentRepository;

@Service
public class StudentImplementation implements StudentInterface{

	@Autowired
	CourseRepository crepo;
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	GradeRepository grepo;

	@Transactional
	public List<Course> viewcourse() {
		return crepo.findAll();
	}

	@Transactional
	public Boolean enroll(Course c,Student s) {
		if(c.getEnrollment()<c.getSize())
		{

			c.setEnrollment(c.getEnrollment() + 1);
			
			ArrayList<Course> newCourse = new ArrayList<Course>();
			newCourse.addAll(s.getLearnings());
			newCourse.add(c);
			s.setLearnings(newCourse);

			srepo.saveAndFlush(s);
			Grade newEnrollGrade = new Grade(c.getId(), s.getId(), null, c, s);
			grepo.saveAndFlush(newEnrollGrade);
			return true;
		}
		else 
		{
			return false;
		}

		
	}

	@Transactional
	public List<Grade> viewgrade(int sid) {
		return grepo.findGradeByStudentID(sid);
		
	}

	
	@Transactional
	public boolean authenticate(Student student) {
		// TODO Auto-generated method stub
		Student fromDb = srepo.findStudentByUserNameAndUserPassword(student.getUserName()
				, student.getUserPassword());
		if(fromDb != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Transactional
	public Student findByUserName(String userName) {
		// TODO Auto-generated method stub
		return srepo.findStudentByUserName(userName);
	}

	@Transactional
	public Student findById1(Integer id) {
		return srepo.findById(id).get();
	}

	@Transactional
	public List<Student> findAll() {
		return srepo.findAll();
	}



    @Transactional
    public Student findById2(int id) {
        Optional<Student> result = srepo.findById(id);
        Student theStudent = null;

        if (result.isPresent()){
            theStudent = result.get();
        }
        else {
            throw new RuntimeException("Did not find Student " + id);
        }
        return  theStudent;
    }

    @Transactional
    public void save(Student theStudent) {
        srepo.save(theStudent);
    }

    @Transactional
    public void deleteById(int id) {
        srepo.deleteById(id);
    }

    @Transactional
    public List<Student> searchBy(String studentUser) {
        List<Student> results = null;

        if (studentUser != null && (studentUser.trim().length() > 0)) {
            results = srepo.findByUserNameContainsAllIgnoreCase(studentUser);
        }
        else {
            results = findAll();
        }

        return results;
    }
    
	@Transactional
	public boolean checkExist(Student student) {
		if (srepo.findAll().contains(student)) {
			return true;
		} else {
			return false;
		}
	}
	
}

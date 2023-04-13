package sg.edu.iss.ca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ca.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query("Select g from Grade g where g.studentID = :sid")
	 public List<Grade> findGradeByStudentID(@Param("sid") Integer sid);
    
    @Query("Select g from Grade g where g.studentID = :sid and g.courseID = :gid")
	 public Grade findGradeByStudentIDAndCourseID(@Param("sid") Integer sid,@Param("gid") Integer gid);
    
	 public Grade findGradeById(Integer id);

}

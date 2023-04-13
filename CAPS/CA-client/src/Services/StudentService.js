import axios from "axios";

const STUDENT_API_BASE_URL = "http://localhost:8080/api";

class StudentDataService {

  getLecturers() {
    return axios.get(STUDENT_API_BASE_URL + "/listLecture");
  }
  getCourses() {
    return axios.get(STUDENT_API_BASE_URL + "/listCourse");
  }
id
  getLecturersByCourse(id){
    return axios.get(STUDENT_API_BASE_URL + "/listCourse/" + id );
  }

}

export default new StudentDataService();

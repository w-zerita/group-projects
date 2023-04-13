import React, { Component } from 'react';
import StudentService from '../Services/StudentService';
import { Link } from "react-router-dom";

class ListCourse extends Component{

    constructor(props){
        super(props)
        this.state={
            courses:[],
            currentCourse: null,
            currentIndex: -1
        };
    }

    componentDidMount(){
        StudentService.getCourses().then((response)=>{
            this.setState({courses: response.data})
        });
    }
    setActiveCourse(course, index){
        this.setState({
            currentCourse: course,
            currentIndex: index
        });
    }
    

    render(){
        return (
            <div>
                <h1 className="text-center">Course</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Name</td>
                            <td>Description</td>
                            <td>Credit</td>
                            <td>Size</td>
                            <td>Enrollment</td>
                            <td>Lecturers</td>                          
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.courses.map(
                                course =>
                                <tr key ={course.id}>
                                    <td>{course.name}</td>
                                    <td>{course.description}</td>
                                    <td>{course.credits}</td>
                                    <td>{course.size}</td>
                                    <td>{course.enrollment}</td>
                                    <Link
                                        to={"/LecturerCourse/" + course.id}
                                        className="badge badge-warning"
                                    >
                                        Lecturer
                                    </Link>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default ListCourse
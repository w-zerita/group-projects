import React, { Component } from 'react';
import StudentService from '../Services/StudentService';

class listLecturer extends Component{

    constructor(props){
        super(props)
        this.state={
            lecturers:[]

        }
    }

    componentDidMount(){
        StudentService.getLecturers().then((response)=>{
            this.setState({lecturers: response.data})
        });
    }


    render(){
        return (
            <div>
                <h1 className="text-center">Lecturers</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>FirstName</td>
                            <td>LastName</td>
                            <td>Titile</td>                         
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.lecturers.map(
                                lecturer =>
                                <tr key ={lecturer.id}>
                                    <td>{lecturer.firstName}</td>
                                    <td>{lecturer.lastName}</td>
                                    <td>{lecturer.title}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}

export default listLecturer
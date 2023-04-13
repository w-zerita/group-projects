import React from 'react';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import ListCourse from './Component/ListCourse'
import ListLecturer from './Component/ListLecturer'
import LecturerCourse from './Component/LecturerCourse'
function App() {
  return (
    <div className="App">
      <Router>
        <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <div className="navbar-nav mr-auto">
            <li>
              Team5
            </li>
            <li className="nav-item">
              <Link to={"/ListLecturer"} className="nav-link">
                Our Lecturers
              </Link>
            </li>

            <li className="nav-item">
              <Link to={"/ListCourse"} className="nav-link">
                Our Courses 
              </Link>
            </li>
          </div>
        </nav>
        </div>
        <div className="container mt-3">
        <Switch>
          <Route exact path='/ListCourse' component={ListCourse} />
          <Route exact path='/ListLecturer' component={ListLecturer} />
          <Route path='/LecturerCourse/:id' component={LecturerCourse} />
        </Switch>
</div>
      </Router>
    </div>
  );
}
export default App;  
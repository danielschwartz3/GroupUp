import React from 'react';
import './App.css';
import AllCourses from './Components/Dashboard/AllCourses';
import Login from './Components/Login/Login'
import Register from './Components/Register/Register'
import Navbar from './Components/Navbar/Navbar'

function App() {

  return (
    <div className="App">
      <Register/>
      {/* <Navbar/>
      <AllCourses/> */}
    </div>
  );
}

export default App;

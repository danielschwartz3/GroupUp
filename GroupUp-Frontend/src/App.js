import React from 'react';
import './App.css';
import { Provider } from 'react-redux';
import { store } from './redux';
import AllCourses from './Components/Dashboard/AllCourses';
import MyCourses from './Components/Dashboard/MyCourses';
import Login from './Components/Login/Login'
import Register from './Components/Register/Register'
import Navbar from './Components/Navbar/Navbar'

function App() {

  return (
    <Provider store={ store }>
    <div className="App">
      <Navbar/>
      {/* <AllCourses/> */}
      <MyCourses/>
    </div>
    </Provider>
  );
}

export default App;

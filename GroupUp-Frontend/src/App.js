import React, { useState } from 'react';
import './App.css';
import { Provider } from 'react-redux';
import { store } from './redux';
import AllCourses from './Components/Dashboard/AllCourses';
import MyCourses from './Components/Dashboard/MyCourses';
import Login from './Components/Login/Login'
import Register from './Components/Register/Register'
import Navbar from './Components/Navbar/Navbar'
import AllConversations from './Components/Messaging/AllConversations'

function App() {
  const [createRegisterModal, setCreateRegisterModal] = useState(false);
  const [createLoginModal, setCreateLoginModal] = useState(true);
  const [editProfile, setEditProfile] = useState(false);

  const handleCreateModal = () => {
      setCreateRegisterModal(!createRegisterModal);
  };

  const handleLoginModal = () => {
      setCreateLoginModal(!createLoginModal);
  };

  return (
    <Provider store={ store }>
    <div className="App">
      <Navbar
        createRegisterModal={createRegisterModal}
        handleCreateModal={handleCreateModal}
        editProfile={editProfile}
        setEditProfile={setEditProfile}
      />

      <Register
        createRegisterModal={createRegisterModal}
        handleCreateModal={handleCreateModal}
        editProfile={editProfile}
      />

      <Login
        createLoginModal={createLoginModal}
        handleLoginModal={handleLoginModal}
        handleCreateModal={handleCreateModal}
        editProfile={editProfile}
        setEditProfile={setEditProfile}
      />
      
        {/*<AllCourses/>*/}
        {/*<MyCourses/>*/}
      <AllConversations/>
    </div>
    </Provider>
  );
}

export default App;

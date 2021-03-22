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
import Cookies, { set } from 'js-cookie'
import Classmates from './Components/Classmates/Classmates'


function App() {
  const [userName, setUserName] = useState('');
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [institution, setInstitution] = useState('');
  const [password, setPassword] = useState('');
  const [createRegisterModal, setCreateRegisterModal] = useState(false);
  const [createLoginModal, setCreateLoginModal] = useState(Cookies.get('GroupUpUserEmailCookie') === undefined);
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
      userName={userName}
      setUserName={setUserName}
      name={name}
      setName={setName}
      email={email}
      setEmail={setEmail}
      institution={institution}
      password={password}
      setPassword={setPassword}
      setInstitution={setInstitution}
      />

      <Register
      createRegisterModal={createRegisterModal}
      handleCreateModal={handleCreateModal}
      editProfile={editProfile}
      setEditProfile={setEditProfile}
      handleLoginModal={handleLoginModal}
      userName={userName}
      setUserName={setUserName}
      name={name}
      setName={setName}
      email={email}
      setEmail={setEmail}
      password={password}
      setPassword={setPassword}
      institution={institution}
      setInstitution={setInstitution}
      />


      <Login
      createLoginModal={createLoginModal}
      handleLoginModal={handleLoginModal}
      handleCreateModal={handleCreateModal}
      editProfile={editProfile}
      setEditProfile={setEditProfile}
      userName={userName}
      setUserName={setUserName}
      name={name}
      setName={setName}
      email={email}
      setEmail={setEmail}
      password={password}
      setPassword={setPassword}
      institution={institution}
      setInstitution={setInstitution}
      />
      {/*<AllCourses/>*/}
      {/*<MyCourses/>*/}
      <AllConversations/>
      {/* <Classmates/> */}
    </div>
    </Provider>
  );
}

export default App;

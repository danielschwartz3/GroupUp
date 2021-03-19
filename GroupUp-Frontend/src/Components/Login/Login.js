import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, initializeUserCoursesAction } from '../../redux';
import Cookies from 'js-cookie'

const URL = 'http://localhost:8080'

const Login = (props) => {

  function getModalStyle() {
      const top = 50;
      const left = 50;

      return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
      };
  }
  
  const useStyles = makeStyles((theme) => ({
  paper: {
      position: 'absolute',
      width: 500,
      backgroundColor: 'white',
      padding: theme.spacing(2, 4, 3),
  },
  }));

  const Login = () => {
    Cookies.set("GroupUpUserEmailCookie", props.email, {expires: 1})

      axios.post(`${URL}/Login/`, null, {
          params: {
              email: props.email,
              password: props.password,
          }
      }).then(function (response) {
          console.log(response);
          const loggedInUser = {
            userRole: response.data.userRole,
            userName: response.data.userName,
            name: response.data.name,
            email: response.data.email,
            userInstitution: response.data.userInstitution,
          }
          props.setUserName(response.data.userName);
          props.setName(response.data.name);
          props.setEmail(response.data.userEmail);
          props.setInstitution(response.data.userInstitution);

          //console.log(props.intializeUserAction(loggedInUser));
          axios.get(`${URL}/courses/enrolled/${props.email}/`).then((response) => {
              props.initializeUserCoursesAction(response.data);
          })

          // const newStudent = {
              
          //     email: email,
          //     password: password,
          // }
          // const newCourses = [...props.courses, newCourse];
          // props.setCourses(newCourses);
          props.handleLoginModal();
          //props.setEmail('');
          //props.setPassword('');
        })
        .catch(function (error) {
          console.log(error);
          Cookies.remove("GroupUpUserEmailCookie");
        });
  }

    function switchToRegister() {
      props.setEditProfile(false)
      props.handleLoginModal()
      props.handleCreateModal()
    }

    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    const body = (
        <div style={modalStyle} className={classes.paper}>
          <h2 id="simple-modal-title">Login</h2>
          <p id="simple-modal-description">
              Please enter your information to login.
          </p>
          
          <TextField onChange={e => props.setEmail(e.target.value)} style={{ margin: '1%', width: '45%' }} id="email" label="Email" />
          <TextField onChange={e => props.setPassword(e.target.value)} style={{ margin: '1%', width: '45%' }} id="password" label="Password" />
          
          <Button style={{ width: '100%', marginTop: '5%' }} onClick={Login}>Login</Button>
          <Button style={{ width: '100%', marginTop: '5%' }} onClick={switchToRegister}>Register</Button>
        </div>
    );
      

    return (
        <div>
          <Modal
            open={props.createLoginModal}
            onClose={props.handleLoginModal}
            aria-labelledby="Login"
            aria-describedby="Please enter your information to login."
          >
            {body}
          </Modal>
        </div>
    );
}

const mapStateToProps = (state) => ({
  user: state.user
}); 

export default connect(
    mapStateToProps,
    { intializeUserAction, initializeUserCoursesAction }
)(Login);

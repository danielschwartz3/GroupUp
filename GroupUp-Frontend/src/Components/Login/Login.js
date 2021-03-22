import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, initializeUserCoursesAction } from '../../redux';
import Cookies from 'js-cookie'

const URL = 'http://localhost:8080'

const Login = (props) => {

  const [emailErrorText, setEmailErrorText] = useState('');
  const [passwordErrorText, setPasswordErrorText] = useState('');
  const [loginErrorText, setLoginErrorText] = useState('');

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
    if (checkEmail() && props.email !== "" && props.password !== "") {
      setEmailErrorText('')
      setPasswordErrorText('')
      axios.post(`${URL}/Login/`, null, {
          params: {
              email: props.email,
              password: props.password,
          }
      }).then(function (response) {
        if (response.status === 200) {
          console.log(response);
          setLoginErrorText('')
          Cookies.set("GroupUpUserEmailCookie", props.email, {expires: 1})
          props.setUserName(response.data.userName);
          props.setName(response.data.name);
          props.setEmail(response.data.userEmail);
          props.setInstitution(response.data.userInstitution);

          axios.get(`${URL}/courses/enrolled/${props.email}/`).then((response) => {
              props.initializeUserCoursesAction(response.data);
          })
          props.handleLoginModal();
        }
        })
        .catch(function (error) {
          console.log(error);
          setLoginErrorText("Email or password are not correct")
        });
    } else {
      if (props.email === "") {
        setEmailErrorText("Please enter an email")
      } else {
        setEmailErrorText('')
      }
      
      if (props.email !== "" && !checkEmail()) {
        setEmailErrorText("Format should be @mail.mcgill.ca")
      }

      if (props.password === "") {
        setPasswordErrorText("Please enter a password")
      } else {
        setPasswordErrorText('')
      }
    }
  }

    function switchToRegister() {
      props.setEditProfile(false)
      props.handleLoginModal()
      props.handleCreateModal()
    }

    function checkEmail() {
      const regex = /\S+@mail\.mcgill\.ca/;
      return regex.test(props.email) ? true : false;
    }

    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    const body = (
        <div style={modalStyle} className={classes.paper}>
          <h2 id="simple-modal-title">Login</h2>
          <p id="simple-modal-description">
              Please enter your information to login.
          </p>

          {loginErrorText.length !== 0 ? <Alert style={{ marginBottom: '5%' }}  severity="error">{loginErrorText}</Alert> : null}
          
          <TextField error={emailErrorText.length === 0 ? false : true} helperText={emailErrorText} value={props.email} onChange={e => props.setEmail(e.target.value)} style={{ margin: '1%', width: '45%' }} id="email" label="Email" />
          <TextField error={passwordErrorText.length === 0 ? false : true} helperText={passwordErrorText} value={props.password} onChange={e => props.setPassword(e.target.value)} style={{ margin: '1%', width: '45%' }} id="password" label="Password" />
          
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
            disableBackdropClick
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

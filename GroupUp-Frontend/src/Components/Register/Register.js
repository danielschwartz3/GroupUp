import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import Alert from '@material-ui/lab/Alert';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, unintializeUserAction } from '../../redux';
import Cookies from 'js-cookie'

const URL = 'http://localhost:8080'

const Register = (props) => {
    const [nameErrorText, setNameErrorText] = useState('');
    const [emailErrorText, setEmailErrorText] = useState('');
    const [userInstitutionErrorText, setUserInstitutionErrorText] = useState('');
    const [userNameErrorText, setUserNameErrorText] = useState('');
    const [passwordErrorText, setPasswordErrorText] = useState('');
    const [registerErrorText, setRegisterErrorText] = useState('');

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
    
    function logout() {
      Cookies.remove(Cookies.remove('GroupUpUserEmailCookie'))
      props.unintializeUserAction()
      props.handleCreateModal()
      props.handleLoginModal()
      clearErrorTexts()
      clearFields()
    }

    const Edit = () => {
      if (props.name !== "" && checkEmail() && props.email !== "" && props.institution !== "" && props.userName !== "" && checkPassword() && props.password !== "") {
        clearErrorTexts()
        axios.post(`${URL}/account/update`, null, {
            params: {
                email: Cookies.get("GroupUpUserEmailCookie"),
                newUserName: props.userName,
                newName: props.name,
                newEmail: Cookies.get("GroupUpUserEmailCookie"),
                newInstitution: props.institution
            }
        }).then(function (response) {
          if (response.status === 200) {
            props.setUserName(response.data.userName);
            props.setName(response.data.name);
            props.setEmail(response.data.userEmail);
            props.setInstitution(response.data.userInstitution);
            props.handleCreateModal();
            clearFields()
          }
          })
          .catch(function (error) {
            console.log(error);
            clearErrorTexts()
            setRegisterErrorText("Could not edit the account, please try again later")
          });
      } else {
        getErrorTexts()
      }
    }

    const Register = () => {
      if (props.name !== "" && checkEmail() && props.email !== "" && props.institution !== "" && props.userName !== "" && checkPassword() && props.password !== "") {
        clearErrorTexts()
        axios.post(`${URL}/register/newStudent`, null, {
            params: {
                userName: props.userName,
                name: props.name,
                email: props.email,
                institution: props.institution,
                password: props.password,
            }
        }).then(function (response) {
          if (response.status === 200) {
            props.setUserName(response.data.userName);
            props.setName(response.data.name);
            props.setEmail(response.data.userEmail);
            props.setInstitution(response.data.userInstitution);
            props.handleCreateModal();
            Cookies.set("GroupUpUserEmailCookie", props.email, {expires: 1})
            clearFields()
          }
          })
          .catch(function (error) {
            console.log(error);
            clearFields()
            clearErrorTexts()
            setRegisterErrorText("Could not register the account, please try again later")
        });
    } else {
        getErrorTexts()
      }
    }
    
    function checkEmail() {
      const regex = /\S+@mail\.mcgill\.ca/;
      return regex.test(props.email) ? true : false;
    }

    function checkPassword() {
      const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      return regex.test(props.password) ? true : false;
    }

    const Login = () => {
      props.setEditProfile(false)
      props.setEmail('')
      props.setPassword('')
      props.handleLoginModal()
      props.handleCreateModal()
      clearErrorTexts()
    }

    function clearErrorTexts() {
      setNameErrorText('')
      setEmailErrorText('')
      setUserInstitutionErrorText('')
      setUserNameErrorText('')
      setPasswordErrorText('')
      setRegisterErrorText('')
    } 

    function clearFields() {
      props.setName('');
      props.setUserName('');
      props.setEmail('');
      props.setInstitution('');
      props.setPassword('');
    }

    function getErrorTexts() {
      if (props.name === "") {
        setNameErrorText("Please enter a name")
      } else {
        setNameErrorText('')
      }

      if (props.email === "") {
        setEmailErrorText("Please enter an email")
      } else {
        setEmailErrorText('')
      }
      
      if (props.email !== "" && !checkEmail()) {
        setEmailErrorText("Format should be @mail.mcgill.ca")
      }

      if (props.institution === "") {
        setUserInstitutionErrorText("Please enter an institution")
      } else {
        setUserInstitutionErrorText('')
      }

      if (props.userName === "") {
        setUserNameErrorText("Please enter a user name")
      } else {
        setUserNameErrorText('')
      }

      if (props.password === "") {
        setPasswordErrorText("Please enter a password")
      } else {
        setPasswordErrorText('')
      }

      if (props.password !== "" && !checkPassword()) {
        setPasswordErrorText("Should contain eight characters, at least one letter and one number")
      }
    }
    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    function EditActionHeading(props) {
      if (props.editProfile) {
        return (
          <div>
            <div>
              <h2 id="simple-modal-title">Edit Profile</h2>
              <p id="simple-modal-description">
                  Please enter the updated profile information.
              </p>
            </div>
          </div>
        );
      }
      return (
        <div>
          <h2 id="simple-modal-title">Registration</h2>
          <p id="simple-modal-description">
              Please enter the following information to register.
          </p>
        </div>
      );
    }

    function EditActionButton(props) {
      if (props.editProfile) {
        return (
          <div>
            <Button style={{ width: '100%', marginTop: '5%' }} onClick={Edit}>Edit</Button>
            <Button style={{ width: '100%', marginTop: '5%' }} onClick={logout}>Logout</Button>
          </div>
        );
      }
      return (
        <div>
            <Button style={{ width: '100%', marginTop: '5%' }} onClick={Register}>Register</Button>
            <Button style={{ width: '100%', marginTop: '5%' }} onClick={Login}>Login</Button>
        </div>
      );
    }

    const body = (
        <div style={modalStyle} className={classes.paper}>
          <EditActionHeading editProfile={props.editProfile} />
          
          {registerErrorText.length !== 0 ? <Alert style={{ marginBottom: '5%' }}  severity="error">{registerErrorText}</Alert> : null}

          <TextField error={nameErrorText.length === 0 ? false : true} helperText={nameErrorText} value={props.name} onChange={e => props.setName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="name" label="Name" />
          <TextField error={emailErrorText.length === 0 ? false : true} helperText={emailErrorText} value={props.email} onChange={e => props.setEmail(e.target.value)} style={{ margin: '1%', width: '45%' }} id="email" label="Email" />
          <TextField error={userInstitutionErrorText.length === 0 ? false : true} helperText={userInstitutionErrorText} value={props.institution} onChange={e => props.setInstitution(e.target.value)} style={{ margin: '1%', width: '92%' }} id="institution" label="Institution" />
          <TextField error={userNameErrorText.length === 0 ? false : true} helperText={userNameErrorText} value={props.userName} onChange={e => props.setUserName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="userName" label="Username" />
          <TextField error={passwordErrorText.length === 0 ? false : true} helperText={passwordErrorText} value={props.password} onChange={e => props.setPassword(e.target.value)} style={{ margin: '1%', width: '45%' }} id="password" label="Password" />
          
          <EditActionButton editProfile={props.editProfile} />
        </div>
    );
      

    return (
        <div>
          <Modal
            open={props.createRegisterModal}
            onClose={props.handleCreateModal}
            aria-labelledby="Registration"
            aria-describedby="Please enter the following information to register for GroupUp"
            disableBackdropClick={props.editProfile ? false : true}
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
    { intializeUserAction, unintializeUserAction }
)(Register);
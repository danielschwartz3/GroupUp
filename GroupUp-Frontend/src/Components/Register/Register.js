import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, unintializeUserAction } from '../../redux';
import Cookies from 'js-cookie'

const URL = 'http://localhost:8080'

const Register = (props) => {

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
      props.setEmail('')
      props.setPassword('')
    }

    const Edit = () => {
        axios.post(`${URL}/account/update`, null, {
            params: {
                email: Cookies.get("GroupUpUserEmailCookie"),
                newUserName: props.userName,
                newName: props.name,
                newEmail: Cookies.get("GroupUpUserEmailCookie"),
                newInstitution: props.institution
            }
        }).then(function (response) {
            console.log(response);
            props.setUserName(response.data.userName);
            props.setName(response.data.name);
            props.setEmail(response.data.userEmail);
            props.setInstitution(response.data.userInstitution);
            props.handleCreateModal();
            props.setName('');
            props.setUsername('');
            props.setEmail('');
            props.setInstitution('');
            props.setPassword('');
          })
          .catch(function (error) {
            console.log(error);
          });
    }

    const Register = () => {
      axios.post(`${URL}/register/newStudent`, null, {
          params: {
              userName: props.userName,
              name: props.name,
              email: props.email,
              institution: props.institution,
              password: props.password,
          }
      }).then(function (response) {
          props.setUserName(response.data.userName);
          props.setName(response.data.name);
          props.setEmail(response.data.userEmail);
          props.setInstitution(response.data.userInstitution);
          props.handleCreateModal();
          Cookies.set("GroupUpUserEmailCookie", props.email, {expires: 1})
        })
        .catch(function (error) {
          console.log(error);
      });
    }

    const Login = () => {
      props.setEditProfile(false)
      props.setEmail('')
      props.setPassword('')
      props.handleLoginModal()
      props.handleCreateModal()
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
          
          <TextField value={props.name} onChange={e => props.setName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="name" label="Name" />
          <TextField value={props.email} onChange={e => props.setEmail(e.target.value)} style={{ margin: '1%', width: '45%' }} id="email" label="Email" />
          <TextField value={props.institution} onChange={e => props.setInstitution(e.target.value)} style={{ margin: '1%', width: '92%' }} id="institution" label="Institution" />
          <TextField value={props.userName} onChange={e => props.setUserName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="userName" label="Username" />
          <TextField onChange={e => props.setPassword(e.target.value)} style={{ margin: '1%', width: '45%' }} id="password" label="Password" />
          
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
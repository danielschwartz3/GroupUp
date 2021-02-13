import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import { connect } from 'react-redux';
import { intializeUserAction, initializeUserCoursesAction } from '../../redux';

const URL = 'http://localhost:8080'


const Login = (props) => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const { user } = props;


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
      console.log(user);
    //   var greg = {
    //     userRole: "Student",
    //     userName: "testStudent2",
    //     name: "testName",
    //     email: "testEmail@mail.mcgill.ca",
    //     userInstitution: "testInstitution"
    // }
    //   props.intializeUserAction(greg);
        axios.post(`${URL}/Login/`, null, {
            params: {
                email: email,
                password: password,
            }
        }).then(function (response) {
            console.log(response);
            props.intializeUserAction(response.data);

            axios.get(`${URL}/courses/enrolled/${email}/`).then((response) => {
                props.initializeUserCoursesAction(response.data);
            })

            // const newStudent = {
                
            //     email: email,
            //     password: password,
            // }
            // const newCourses = [...props.courses, newCourse];
            // props.setCourses(newCourses);
            handleCreateModal();
            
            setEmail('');
           
            setPassword('');
          })
          .catch(function (error) {
            console.log(error);
          });
    }

    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    const [createLoginModal, setCreateLoginModal] = useState(props.data);

    const handleCreateModal = () => {
        setCreateLoginModal(!createLoginModal);
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>
          <h2 id="simple-modal-title">Login</h2>
          <p id="simple-modal-description">
              Please enter your information to login.
          </p>
          
           <TextField onChange={e => setEmail(e.target.value)} style={{ margin: '1%', width: '45%' }} id="email" label="Email" />
          <TextField onChange={e => setPassword(e.target.value)} style={{ margin: '1%', width: '45%' }} id="password" label="Password" />
          
          <Button style={{ width: '100%', marginTop: '5%' }} onClick={Login}>Login</Button>
        </div>
    );
      

    return (
        <div>
          <Button onClick={handleCreateModal}>login</Button>
          <Modal
            open={Login}
            onClose={handleCreateModal}
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
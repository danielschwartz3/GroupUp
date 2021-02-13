import React, { useState } from 'react'
import axios from 'axios'
import { Button, Modal, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

const URL = 'http://localhost:8080'

const Register = (props) => {
    const [userName, setUsername] = useState('');
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [institution, setInstitution] = useState('');
    const [password, setPassword] = useState('');

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

    const Register = () => {
        axios.post(`${URL}/register/newStudent`, null, {
            params: {
                userName: userName,
                name: name,
                email: email,
                institution: institution,
                password: password,
            }
        }).then(function (response) {
            console.log(response);
            const newStudent = {
                userName: userName,
                name: name,
                email: email,
                institution: institution,
                password: password,
            }
            // const newCourses = [...props.courses, newCourse];
            // props.setCourses(newCourses);
            handleCreateModal();
            setName('');
            setUsername('');
            setEmail('');
            setInstitution('');
            setPassword('');
          })
          .catch(function (error) {
            console.log(error);
          });
    }

    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    const [createRegisterModal, setCreateRegisterModal] = useState(props.data);

    const handleCreateModal = () => {
        setCreateRegisterModal(!createRegisterModal);
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>
          <h2 id="simple-modal-title">Registration</h2>
          <p id="simple-modal-description">
              Please enter the following information to register.
          </p>
          
          <TextField onChange={e => setName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="name" label="Name" />
          <TextField onChange={e => setEmail(e.target.value)} style={{ margin: '1%', width: '45%' }} id="email" label="Email" />
          <TextField onChange={e => setInstitution(e.target.value)} style={{ margin: '1%', width: '92%' }} id="institution" label="Institution" />
          <TextField onChange={e => setUsername(e.target.value)} style={{ margin: '1%', width: '45%' }} id="userName" label="Username" />
          <TextField onChange={e => setPassword(e.target.value)} style={{ margin: '1%', width: '45%' }} id="password" label="Password" />
          
          <Button style={{ width: '100%', marginTop: '5%' }} onClick={Register}>Register</Button>
        </div>
    );
      

    return (
        <div>
          <Button onClick={handleCreateModal}>Register</Button>
          <Modal
            open={Register}
            onClose={handleCreateModal}
            aria-labelledby="Registration"
            aria-describedby="Please enter the following information to register for GroupUp"
          >
            {body}
          </Modal>
        </div>
    );
}


export default Register;
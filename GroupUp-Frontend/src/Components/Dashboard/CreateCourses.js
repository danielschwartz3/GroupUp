import React, { useState } from 'react'
import axios from 'axios'
import { connect } from 'react-redux';
import { createCourseAction } from '../../redux';
import { Button, Modal, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

const URL = 'http://localhost:8080'

const CreateCourses = (props) => {
    const [courseID, setCourseID] = useState('');
    const [courseFaculty, setCourseFaculty] = useState('');
    const [courseYear, setCourseYear] = useState('');
    const [courseSemester, setCourseSemester] = useState('');
    const [courseSection, setCourseSection] = useState('');
    const [courseName, setCourseName] = useState('');

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

    const createCourse = () => {
        axios.post(`${URL}/newcourse`, null, {
            params: {
                courseID: courseID,
                faculty: courseFaculty,
                id: null,
                name: courseName,
                section: courseSection,
                semester: courseSemester,
                year: courseYear,
            }
        }).then(function (response) {
            console.log(response);
            const newCourse = {
              courseID: courseID,
              faculty: courseFaculty,
              id: response.data.id,
              name: courseName,
              section: courseSection,
              semester: courseSemester,
              year: courseYear,
            }
            props.createCourseAction(newCourse);
            handleCreateModal();
            setCourseID('');
            setCourseFaculty('');
            setCourseYear('');
            setCourseSemester('');
            setCourseSection('');
            setCourseName('');
          })
          .catch(function (error) {
            console.log(error);
          });
    }

    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    const [createCourseModal, setCreateCourseModal] = useState(props.data);

    const handleCreateModal = () => {
        setCreateCourseModal(!createCourseModal);
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>
          <h2 id="simple-modal-title">New Course</h2>
          <p id="simple-modal-description">
              Please enter the following information for the new course.
          </p>
          <TextField onChange={e => setCourseName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseName" label="Name" />
          <TextField onChange={e => setCourseID(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseID" label="Code" />
          <TextField onChange={e => setCourseSection(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseSection" label="Section" />
          <TextField onChange={e => setCourseSemester(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseSemester" label="Semester" />
          <TextField onChange={e => setCourseYear(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseYear" label="Year" />
          <TextField onChange={e => setCourseFaculty(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseFaculty" label="Faculty" />
          <Button style={{ width: '100%', marginTop: '5%' }} onClick={createCourse}>Create</Button>
        </div>
    );
      

    return (
        <div>
          <Button onClick={handleCreateModal}>Create Course</Button>
          <Modal
            open={createCourseModal}
            onClose={handleCreateModal}
            aria-labelledby="Create a new course"
            aria-describedby="Please enter the following information for the new course"
          >
            {body}
          </Modal>
        </div>
    );
}


export default connect(
  null,
  { createCourseAction }
)(CreateCourses);
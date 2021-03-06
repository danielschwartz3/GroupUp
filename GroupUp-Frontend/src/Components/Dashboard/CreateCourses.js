import React from 'react'
import axios from 'axios'
import { connect } from 'react-redux';
import { createCourseAction, editCourseAction } from '../../redux';
import { Button, Modal, TextField } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

const URL = 'http://localhost:8080'

const CreateCourses = (props) => {

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
                courseID: props.courseID,
                faculty: props.courseFaculty,
                id: null,
                name: props.courseName,
                section: props.courseSection,
                semester: props.courseSemester,
                year: props.courseYear,
            }
        }).then(function (response) {
            console.log(response);
            const newCourse = {
              courseID: props.courseID,
              faculty: props.courseFaculty,
              id: response.data.id,
              name: props.courseName,
              section: props.courseSection,
              semester: props.courseSemester,
              year: props.courseYear,
            }
            props.createCourseAction(newCourse);
            props.handleCreateModal();
            props.setCourseID('');
            props.setCourseFaculty('');
            props.setCourseYear('');
            props.setCourseSemester('');
            props.setCourseSection('');
            props.setCourseName('');
          })
          .catch(function (error) {
            console.log(error);
          });
    }

    const editCourse = () => {
      axios.put(`${URL}/updatecourse/${props.selectedId}`, null, {
          params: {
              courseID: props.courseID,
              faculty: props.courseFaculty,
              id: props.selectedId,
              name: props.courseName,
              section: props.courseSection,
              semester: props.courseSemester,
              year: props.courseYear,
          }
      }).then(function (response) {
          console.log(response);
          const newCourse = {
            courseID: props.courseID,
            faculty: props.courseFaculty,
            id: response.data.id,
            name: props.courseName,
            section: props.courseSection,
            semester: props.courseSemester,
            year: props.courseYear,
          }
          props.editCourseAction(newCourse);
          props.handleCreateModal();
          props.setCourseID('');
          props.setCourseFaculty('');
          props.setCourseYear('');
          props.setCourseSemester('');
          props.setCourseSection('');
          props.setCourseName('');
          props.setSelectedId(null);
        })
        .catch(function (error) {
          console.log(error);
        });
  }

    const classes = useStyles();

    const [modalStyle] = React.useState(getModalStyle);

    function CourseActionHeading(props) {
      if (props.editMode) {
        return <h2 id="simple-modal-title">Update Course</h2>;
      }
      return <h2 id="simple-modal-title">New Course</h2>;
    }

    function CourseActionDescription(props) {
      if (props.editMode) {
        return <p id="simple-modal-description">Please enter the updated course information.</p>;
      }
      return <p id="simple-modal-description">Please enter the following information for the new course.</p>;
    }

    function CourseActionButton(props) {
      if (props.editMode) {
        return <Button style={{ width: '100%', marginTop: '5%' }} onClick={editCourse}>Edit</Button>;
      }
      return <Button style={{ width: '100%', marginTop: '5%' }} onClick={createCourse}>Create</Button>;
    }
    
    const body = (
        <div style={modalStyle} className={classes.paper}>
          <CourseActionHeading editMode={props.editMode} />
          <CourseActionDescription editMode={props.editMode} />
          <TextField value={props.courseName} onChange={e => props.setCourseName(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseName" label="Name" />
          <TextField value={props.courseID} onChange={e => props.setCourseID(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseID" label="Code" />
          <TextField value={props.courseSection} onChange={e => props.setCourseSection(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseSection" label="Section" />
          <TextField value={props.courseSemester} onChange={e => props.setCourseSemester(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseSemester" label="Semester" />
          <TextField value={props.courseYear} onChange={e => props.setCourseYear(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseYear" label="Year" />
          <TextField value={props.courseFaculty} onChange={e => props.setCourseFaculty(e.target.value)} style={{ margin: '1%', width: '45%' }} id="courseFaculty" label="Faculty" />
          <CourseActionButton editMode={props.editMode} />
        </div>
    );
      
    return (
        <div>
          <Button onClick={props.handleCreateModal}>Create Course</Button>
          <Modal
            open={props.createCourseModal}
            onClose={props.handleCreateModal}
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
  { createCourseAction, editCourseAction }
)(CreateCourses);
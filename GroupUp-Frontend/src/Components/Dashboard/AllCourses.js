import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import { registerCourseAction, getCoursesAction, deleteCourseAction } from '../../redux';
import CreateCourses from './CreateCourses';
import { Button } from '@material-ui/core';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const URL = 'http://localhost:8080';

const AllCourses = (props) => {
    const [courseID, setCourseID] = useState('');
    const [courseFaculty, setCourseFaculty] = useState('');
    const [courseYear, setCourseYear] = useState('');
    const [courseSemester, setCourseSemester] = useState('');
    const [courseSection, setCourseSection] = useState('');
    const [courseName, setCourseName] = useState('');
    const [selectedId, setSelectedId] = useState(null);
    const [createCourseModal, setCreateCourseModal] = React.useState(false);
    const [editMode, setEditMode] = React.useState(false);

    const handleCreateModal = () => {
        setEditMode(false);
        setCourseID('');
        setCourseFaculty('');
        setCourseYear('');
        setCourseSemester('');
        setCourseSection('');
        setCourseName('');
        setSelectedId(null);
        setCreateCourseModal(!createCourseModal);
    };

    const { courses, email } = props;

    useEffect(() => {
        getData();
    }, [])

    const getData = async () => {
        const response = await axios.get(URL + '/courses');
        props.getCoursesAction(response.data)
    }

    const editData = (id) => {
        const course = courses.filter(course => id === course.id)[0];
        handleCreateModal();
        setCourseID(course.courseID);
        setCourseFaculty(course.faculty);
        setCourseYear(course.year);
        setCourseSemester(course.semester);
        setCourseSection(course.section);
        setCourseName(course.name);
        setSelectedId(course.id);
        setEditMode(true);
    }

    const removeData = (id) => {
        axios.delete(`${URL}/deletecourse/${id}`).then(() => {
            console.log(id);
            props.deleteCourseAction(id);
        });
    }

    const registerCourse = (id) => {
        /*
        axios.post(`${URL}/register/student/course/`, null, {
            params: {
                email: 'jay.abi-saad@mail.mcgill.ca',
                id: id
            }
        }).then(function (response) {
            console.log(response);
            const course = courses.filter(course => id === course.id)[0];
            console.log(course)
            props.registerCourseAction(course);
        })
          .catch(function (error) {
            console.log(error);
        });
        */
    }

    return (
        <div>
            <TableContainer component={Paper}>
                {courses.length != 0 ? 
                    <Table size="small" aria-label="a dense table">
                        <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell align="right">Course Code</TableCell>
                            <TableCell align="right">Section</TableCell>
                            <TableCell align="right">Semester</TableCell>
                            <TableCell align="right">Year</TableCell>
                            <TableCell align="right">Faculty</TableCell>
                        </TableRow>
                        </TableHead>
                            <TableBody>
                                {courses && courses.map(({ courseID, faculty, id, name, section, semester, year }) => (
                                    <TableRow key={id}>
                                    <TableCell component="th" scope="row">{name}</TableCell>
                                    <TableCell align="right">{courseID}</TableCell>
                                    <TableCell align="right">{faculty}</TableCell>
                                    <TableCell align="right">{year}</TableCell>
                                    <TableCell align="right">{semester}</TableCell>
                                    <TableCell align="right">{section}</TableCell>
                                    <TableCell align="right">
                                        <Button className='button' color="primary" onClick={() => registerCourse(id)}>Register</Button>
                                    </TableCell>
                                    <TableCell align="right">
                                        <Button className='button' onClick={() => editData(id)}>Edit</Button>
                                    </TableCell>
                                    <TableCell align="right">
                                        <Button className='button' color="secondary" onClick={() => removeData(id)}>Delete</Button>
                                    </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody> 
                    </Table> : 
                    <div>
                        <h4>
                            There's currently no courses available!
                        </h4>
                    </div>
                }
            </TableContainer>
            <CreateCourses
                createCourseModal={createCourseModal}
                setCreateCourseModal={setCreateCourseModal}
                handleCreateModal={handleCreateModal}
                courseID={courseID}
                setCourseID={setCourseID}
                courseFaculty={courseFaculty}
                setCourseFaculty={setCourseFaculty}
                courseYear={courseYear}
                setCourseYear={setCourseYear}
                courseSemester={courseSemester}
                setCourseSemester={setCourseSemester}
                courseSection={courseSection}
                setCourseSection={setCourseSection}
                courseName={courseName}
                setCourseName={setCourseName}
                selectedId={selectedId}
                setSelectedId={setSelectedId}
                editMode={editMode}
                setEditMode={setEditMode}
            />
        </div>
    );
}

const mapStateToProps = (state) => ({
    courses: state.courses,
    email: state.email
}); 

export default connect(
    mapStateToProps,
    { registerCourseAction, getCoursesAction, deleteCourseAction }
)(AllCourses);
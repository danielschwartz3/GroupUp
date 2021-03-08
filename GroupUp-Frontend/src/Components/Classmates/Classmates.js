import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import { getStudentAction } from '../../redux';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const URL = 'http://localhost:8080';

const Classmates = (props) => {
    const [courseID, setCourseID] = useState('');
    const [student, setStudent] = useState('');

    const handleCreateModal = () => {
        setCourseID('');
        setStudent('');
    };

    const {students} = props;

    useEffect(() => {
        getData();
    }, [])

    const getData = async () => {
        const response = await axios.get(`${URL}/courses/${courseID}/students`);
        props.getStudentAction(response.data)
    }


    return (
        <div>
            <TableContainer component={Paper}>
                {students.length != 0 ? 
                    <Table size="small" aria-label="a dense table">
                        <TableHead>
                        <TableRow>
                            <TableCell align="right">Course ID</TableCell>
                            <TableCell align="right">Student</TableCell>
                        </TableRow>
                        </TableHead>
                            <TableBody>
                                {students => (
                                    <TableRow key={courseID}>
                                    <TableCell component="th" scope="row">{courseID}</TableCell>
                                    <TableCell align="right">{students}</TableCell>
                                    </TableRow>
                                )}
                            </TableBody> 
                    </Table> : 
                    <div>
                        <h4>
                            There's currently no students enrolled!
                        </h4>
                    </div>
                }
            </TableContainer>
        </div>
    );
}

const mapStateToProps = (state) => ({
    students: state.students
}); 

export default connect(
    mapStateToProps,
    { getStudentAction }
)(Classmates);

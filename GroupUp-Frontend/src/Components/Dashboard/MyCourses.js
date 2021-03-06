import React, { useEffect } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import { unregisterCourseAction, getRegisteredAction } from '../../redux';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { Button } from '@material-ui/core';

const URL = 'http://localhost:8080'

const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
});

function createData(name, code, section, semester, year, faculty) {
    return { name, code, section, semester, year, faculty };
}
  
const rows = [
    createData('Software Practice', 'ECSE 428', '001', 'Winter', 2021, 'Eng'),
    createData('Design Lol', 'ECSE 343', '001', 'Winter', 2021, 'Eng'),
    createData('DPM', 'ECSE 427', '001', 'Winter', 2021, 'Eng'),
];


const MyCourses = (props) => {
    const classes = useStyles();
    const { registeredCourses, email } = props;

    const removeCourse = (id) => {
        console.log(id)
        props.unregisterCourseAction(id)
    }

    useEffect(() => {
        getData();
    }, [])

    const getData = async () => {
        /*
        const response = await axios.get(`${URL}/courses/enrolled/jay.abi-saad@mail.mcgill.ca`);
        props.getRegisteredAction(response.data)
        */
    }

    return (
        <div>
            <TableContainer component={Paper}>
                {registeredCourses.length != 0 ? 
                    <Table className={classes.table} size="small" aria-label="a dense table">
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
                                {registeredCourses && registeredCourses.map(({ courseID, faculty, id, name, section, semester, year }) => (
                                    <TableRow key={id}>
                                    <TableCell component="th" scope="row">{name}</TableCell>
                                    <TableCell align="right">{courseID}</TableCell>
                                    <TableCell align="right">{faculty}</TableCell>
                                    <TableCell align="right">{year}</TableCell>
                                    <TableCell align="right">{semester}</TableCell>
                                    <TableCell align="right">{section}</TableCell>
                                    <TableCell align="right">
                                        <Button className='button' color="secondary" onClick={() => removeCourse(id)}>Unregister</Button>
                                    </TableCell>
                                    </TableRow>
                                ))}
                            </TableBody> 
                    </Table> : 
                    <div>
                        <h4>
                            You're currently not registered for any courses!
                        </h4>
                    </div>
                }
            </TableContainer>
        </div>
    );
}

const mapStateToProps = (state) => ({
    registeredCourses: state.registeredCourses,
    email: state.email
}); 

export default connect(
    mapStateToProps,
    { unregisterCourseAction, getRegisteredAction }
)(MyCourses);
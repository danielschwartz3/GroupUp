import React, { useState } from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import { unregisterCourseAction } from '../../redux';
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

function createData(code, section, semester, year, faculty) {
    return { code, section, semester, year, faculty };
}
  
const rows = [
    createData('ECSE 428', '001', 'Winter', 2021, 'Eng'),
    createData('ECSE 343', '001', 'Winter', 2021, 'Eng'),
    createData('ECSE 427', '001', 'Winter', 2021, 'Eng'),
];

const MyCourses = (props) => {
    const classes = useStyles();
    const { registeredCourses } = props;

    const removeCourse = (courseID) => {
        console.log(courseID)
        props.unregisterCourseAction(courseID)
    }

    return (
        <div>
            <TableContainer component={Paper}>
                <Table className={classes.table} size="small" aria-label="a dense table">
                    <TableHead>
                    <TableRow>
                        <TableCell>Course Code</TableCell>
                        <TableCell align="right">Section</TableCell>
                        <TableCell align="right">Semester</TableCell>
                        <TableCell align="right">Year</TableCell>
                        <TableCell align="right">Faculty</TableCell>
                        <TableCell align="right"></TableCell>
                    </TableRow>
                    </TableHead>
                    <TableBody>
                    {registeredCourses.map((row) => (
                        <TableRow key={row.code}>
                        <TableCell component="th" scope="row">
                            {row.code}
                        </TableCell>
                        <TableCell align="right">{row.section}</TableCell>
                        <TableCell align="right">{row.semester}</TableCell>
                        <TableCell align="right">{row.year}</TableCell>
                        <TableCell align="right">{row.faculty}</TableCell>
                        <TableCell align="right">
                            <Button className='button' color="secondary" onClick={() => removeCourse(row.code)}>Remove</Button>
                        </TableCell>
                        </TableRow>
                    ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

const mapStateToProps = (state) => ({
    registeredCourses: state.registeredCourses
}); 

export default connect(
    mapStateToProps,
    { unregisterCourseAction }
)(MyCourses);
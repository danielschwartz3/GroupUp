import React, { useState, useEffect } from 'react';
import axios from 'axios';
import CreateCourses from './CreateCourses';
import { Button } from '@material-ui/core';

const URL = 'http://localhost:8080';

const AllCourses = () => {
    const [createCourseModal, setCreateCourseModal] = React.useState(false);

    const handleCreateModal = () => {
        setCreateCourseModal(!createCourseModal);
    };

    const [courses, setCourses] = useState([]);

    useEffect(() => {
        getData();
    }, [])

    const getData = async () => {
        const response = await axios.get(URL + '/courses');
        setCourses(response.data);
    }

    const removeData = (courseID) => {
        axios.delete(`${URL}/deletecourse/${courseID}`).then(() => {
            const del = courses.filter(course => courseID !== course.courseID);
            setCourses(del);
        });
    }

    const renderHeader = () => {
        let headerElement = ['Code', 'Section', 'Semester', 'Year', 'Faculty', 'Action'];

        return headerElement.map((key, index) => {
            console.log(key);
            return <th key={index}>{key.toUpperCase()}</th>;
        })
    }

    const renderBody = () => {
        return courses && courses.map(({ courseID, faculty, year, semester, section }) => {
            return (
                <tr key={courseID}>
                    <td>{courseID}</td>
                    <td>{faculty}</td>
                    <td>{year}</td>
                    <td>{semester}</td>
                    <td>{section}</td>
                    <td>
                        <Button className='button' onClick={() => removeData(courseID)}>Delete</Button>
                    </td>
                </tr>
            );
        });
    }

    return (
        <div>
            <table>
                <thead>
                    <tr>{renderHeader()}</tr>
                </thead>
                <tbody>
                    {renderBody()}
                </tbody>
            </table>
            <CreateCourses
                createCourseModal={createCourseModal}
                setCreateCourseModal={setCreateCourseModal}
                handleCreateModal={handleCreateModal}
                courses={courses}
                setCourses={setCourses}
            />
        </div>
    );
}


export default AllCourses;
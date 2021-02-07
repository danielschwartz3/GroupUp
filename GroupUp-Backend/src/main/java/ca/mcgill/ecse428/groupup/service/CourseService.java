package ca.mcgill.ecse428.groupup.service;


import ca.mcgill.ecse428.groupup.dao.CourseRepository;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    
    @Transactional
    public Course createCourse(String courseID, String faculty, String semester, String year, String courseSection, String courseName) {
        Course course;
        String error = "";
        if (courseID == null || courseID.trim().length() == 0) {
            error += "CourseID cannot be empty";
        }
        if (faculty == null || faculty.trim().length() == 0) {
            error += "faculty cannot be empty";
        }
        if (semester == null || semester.trim().length() == 0) {
            error += "semester cannot be empty";
        }
        if (year == null || year.trim().length() == 0) {
            error += "year cannot be empty";
        }
        if (courseSection == null || courseSection.trim().length() == 0) {
            error += "section number cannot be empty";
        }
        if(courseName == null || courseName.trim().length() == 0) {
            error += "course name cannot be empty";
        }

        if (error.length() != 0) {
            throw new IllegalArgumentException(error);
        }
        if(courseRepository.existsByCourseID(courseID)) {
            throw new IllegalArgumentException("Course ID: " + courseID + " already exists");
        }
        course = new Course();
        course.setCourseID(courseID);
        course.setFaculty(faculty);
        course.setCourseSection(courseSection);
        course.setSemester(Semester.valueOf(semester));
        course.setYear(year);
        course.setCourseName(courseName);

        courseRepository.save(course);

        if (!courseRepository.existsByCourseID(courseID)) {
            throw new PersistenceException("Failed to persist course");
        }
        return course;

    }

    @Transactional
    public Course updateCourse(Course course) {
        String courseID = course.getCourseID();
        if (courseID == null || courseID.trim().length() == 0) {
            throw new IllegalArgumentException("CourseID cannot be empty");
        }
        Course persitedCourse = courseRepository.findById(courseID).orElse(null);
        if (persitedCourse == null) {
            throw new IllegalArgumentException("The course with courseID: " + courseID + " does not exist. Please add the course first");
        }
        String newFaculty = course.getFaculty();
        if (newFaculty != null && newFaculty.trim().length() != 0) {
            persitedCourse.setFaculty(newFaculty);
        }
        String newSem = course.getSemester().toString();
        if (newSem != null && newSem.trim().length() != 0) {
            persitedCourse.setSemester(Semester.valueOf(newSem));
        }
        String newYear = course.getYear();
        if (newYear != null && newYear.trim().length() != 0) {
            persitedCourse.setYear(newYear);
        }
        String newCrsSection = course.getCourseSection();
        if (newCrsSection != null && newCrsSection.trim().length() != 0) {
            persitedCourse.setCourseSection(newCrsSection);
        }
        String courseName = course.getCourseName();
        if(courseName != null && courseName.trim().length() != 0) {
            persitedCourse.setCourseName(courseName);
        }
        courseRepository.save(persitedCourse);

        return persitedCourse;
    }
    
    public Student registerStudent(Student student, Course course) {
    	if(student == null) throw new IllegalArgumentException("Student does not exist");
    	if(course == null) throw new IllegalArgumentException("Course does not exist");
    	if(course.getStudents().contains(student))throw new IllegalArgumentException("Student already in the course");
    	course.addStudent(student);
    	courseRepository.save(course);
    	return student;
    }

    @Transactional
    public Course getCourseByID(String courseID) {
        Course course = courseRepository.findById(courseID).orElse(null);
        if (course == null) {
            throw new IllegalArgumentException("The course with courseID: " + courseID + " does not exist. Please add the course first");
        }
        return course;
    }

    @Transactional
    public List<Course> getAllCourses() {
        return toList(courseRepository.findAll());
    }

    @Transactional
    public boolean deleteCourse(String courseID) {
        courseRepository.deleteById(courseID);
        return courseRepository.existsByCourseID(courseID);
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}

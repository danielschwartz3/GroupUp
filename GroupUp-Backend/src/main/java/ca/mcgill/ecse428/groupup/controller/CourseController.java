package ca.mcgill.ecse428.groupup.controller;

import ca.mcgill.ecse428.groupup.dto.CourseDTO;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.service.CourseService;
import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = {"/newcourse", "/newcourse/"})
    public CourseDTO createCourse(@RequestParam("courseID") String courseID,
                                  @RequestParam("faculty") String faculty,
                                  @RequestParam("year") String year,
                                  @RequestParam("semester") String semester,
                                  @RequestParam("section") String section,
                                  @RequestParam("name") String courseName) {
        Course course = courseService.createCourse(courseID, faculty, semester, year, section, courseName);
        CourseDTO courseDTO = new CourseDTO(course.getCourseID(), course.getFaculty(), course.getYear(), course.getSemester().toString(), course.getCourseSection(), course.getCourseName());

        return courseDTO;

    }

    @GetMapping(value = {"/courses", "courses/"})
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getCourseID(), course.getFaculty(), course.getYear(), course.getSemester().toString(), course.getCourseSection(), course.getCourseName());
            courseDTOs.add(courseDTO);
        }

        return courseDTOs;
    }

    @GetMapping(value = {"/courses/{courseID}", "/courses/{courseID}/"})
    public CourseDTO getCourseOfId(@PathVariable("courseID") String courseID) {
        Course course = courseService.getCourseByID(courseID);
        CourseDTO courseDTO = new CourseDTO(course.getCourseID(), course.getFaculty(), course.getYear(), course.getSemester().toString(), course.getCourseSection(), course.getCourseName());

        return courseDTO;
    }

    @PutMapping(value = {"/updatecourse/{courseID}", "/updatecourse/{courseID}/"})
    public CourseDTO updateCourseOfId(@PathVariable(value = "courseID", required = false) String courseID,
                                      @RequestParam(value = "faculty", required = false) String faculty,
                                      @RequestParam(value = "year", required = false) String year,
                                      @RequestParam(value = "semester", required = false) String semester,
                                      @RequestParam(value = "section", required = false) String section,
                                      @RequestParam(value = "name", required = false) String name) {
        Course course = new Course();
        course.setCourseID(courseID);
        course.setFaculty(faculty);
        course.setCourseSection(section);
        course.setYear(year);
        course.setSemester(Semester.valueOf(semester));
        course.setCourseName(name);

        Course updatedCourse = courseService.updateCourse(course);

        CourseDTO courseDTO = new CourseDTO(updatedCourse.getCourseID(), updatedCourse.getFaculty(), updatedCourse.getYear(), updatedCourse.getSemester().toString(), updatedCourse.getCourseSection(), course.getCourseName());

        return courseDTO;


    }
    @DeleteMapping(value = {"/deletecourse/{courseID", "/deletecourse/{courseID}" })
    public void deleteCourseOfId(@PathVariable("courseID") String courseID) {
        courseService.deleteCourse(courseID);
    }

}

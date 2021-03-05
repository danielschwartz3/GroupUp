package ca.mcgill.ecse428.groupup.controller;

import ca.mcgill.ecse428.groupup.dto.CourseDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.CourseService;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.utility.DTOUtil;
import ca.mcgill.ecse428.groupup.utility.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private AccountService accountService;

    @PostMapping(value = {"/newcourse", "/newcourse/"})
    public CourseDTO createCourse(@RequestParam("courseID") String courseID, @RequestParam("faculty") String faculty,
                                  @RequestParam("year") String year, @RequestParam("semester") String semester,
                                  @RequestParam("section") String section, @RequestParam("name") String courseName) {
        Course course = courseService.createCourse(courseID, faculty, semester.toUpperCase(), year, section, courseName);
        CourseDTO courseDTO = DTOUtil.convertToDTO(course);
        return courseDTO;
    }

    @PostMapping(value = {"/register/student/course", "/register/student/course/"})
    public CourseDTO registerStudent(@RequestParam("email") String email,
                                     @RequestParam("id") int id) {
        Account account = accountService.getAccountByID(email);
        Student student = (Student) account.getUserRole();
        Course course = courseService.getCourseByID(id);
        course = courseService.registerStudent(student, course);
        return DTOUtil.convertToDTO(course);
    }

    @PostMapping(value = {"/unregister/student/course", "/unregister/student/course/"})
    public CourseDTO unregisterStudent(@RequestParam("email") String email, @RequestParam("id") int id) {
        Account account = accountService.getAccountByID(email);
        Student student = (Student) account.getUserRole();
        Course course = courseService.getCourseByID(id);
        course = courseService.unregisterStudent(student, course);
        return DTOUtil.convertToDTO(course);
    }

    @GetMapping(value = {"/courses", "courses/"})
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = DTOUtil.convertToDTO(course);
            courseDTOs.add(courseDTO);
        }

        return courseDTOs;
    }

    @GetMapping(value = {"/courses/{id}", "/courses/{id}/"})
    public CourseDTO getCourseOfId(@PathVariable("id") int id) {
        Course course = courseService.getCourseByID(id);
        CourseDTO courseDTO = DTOUtil.convertToDTO(course);

        return courseDTO;
    }

    @GetMapping(value = {"/courses/enrolled/{email}", "/courses/enrolled/{email}/"})
    public List<CourseDTO> getEnrolledCourses(@PathVariable("studentId") String email){
        Account account = accountService.getAccountByID(email);
        int studentId = account.getUserRole().getId();
        List<Course> courses = courseService.getEnrolledCourses(studentId);
        List<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getId(), course.getCourseID(), course.getFaculty(), course.getYear(), course.getSemester().toString(), course.getCourseSection(), course.getCourseName());
            courseDTOs.add(courseDTO);
        }
        return courseDTOs;
    }

    @GetMapping(value = {"/coursesbyyear", "/coursesbyyear/"})
    public List<CourseDTO> getCourseOfYear(@RequestParam("year") String year) {
        List<Course> courses = courseService.getCoursesByYear(year);
        return DTOUtil.convertToDTO(courses);
    }

    @GetMapping(value = {"/coursesbysemester", "/coursesbysemester/"})
    public List<CourseDTO> getCourseOfSemester(@RequestParam("semester") String semester) {
        List<Course> courses = courseService.getCoursesBySemester(semester.toUpperCase());
        return DTOUtil.convertToDTO(courses);
    }

    @GetMapping(value = {"courses/byYearAndSemester", "courses/byYearAndSemester/"})
    public List<CourseDTO> getCoursesByYearBySemester(@RequestParam("year") String year,
                                                      @RequestParam("semester") String semester) {
        List<Course> courses = courseService.getCoursesByYearBySemester(year, semester.toUpperCase());
        return DTOUtil.convertToDTO(courses);
    }

    @GetMapping(value = {"courses/byYearAndSemesterAndSection", "courses/byYearAndSemesterAndSection/"})
    public List<CourseDTO> getCoursesByYearBySemesterBySection(@RequestParam("year") String year,
                                                               @RequestParam("semester") String semester,
                                                               @RequestParam("section") String section) {
        List<Course> courses = courseService.getCoursesByYearBySemesterBySection(year, semester, section);
        return DTOUtil.convertToDTO(courses);
    }

    @PutMapping(value = {"/updatecourse/{id}", "/updatecourse/{id}/"})
    public CourseDTO updateCourseOfId(@PathVariable(value = "id", required = false) int id,
                                      @RequestParam(value = "courseID", required = false) String courseID,
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
        course.setSemester(Semester.valueOf(semester.toUpperCase()));
        course.setCourseName(name);

        Course updatedCourse = courseService.updateCourse(id, course);
        CourseDTO courseDTO = DTOUtil.convertToDTO(updatedCourse);

        return courseDTO;

    }

    @DeleteMapping(value = {"/deletecourse/{id}", "/deletecourse/{id}"})
    public void deleteCourseOfId(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
    }

}

package ca.mcgill.ecse428.groupup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.groupup.dto.AccountDTO;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.service.CourseService;
import ca.mcgill.ecse428.groupup.service.StudentService;
import ca.mcgill.ecse428.groupup.utility.DTOUtil;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
  @Autowired
  private StudentService studentService;

  @Autowired
  private CourseService courseService;

  @GetMapping(value = {"/courses/{id}/students", "/courses/{id}/students/"})
  public List<AccountDTO> getStudentsByCourseID(@PathVariable("id") int id) {
    Course course = courseService.getCourseByID(id);
    List<Student> classMembers = studentService.getStudentsByCourse(course);
    List<AccountDTO> memberDto = new ArrayList<>();
    for (Student student : classMembers)
      memberDto.add(DTOUtil.convertToDTO(student.getAccount()));
    return memberDto;
  }

  @GetMapping(value = {"/all/students", "/all/students/"})
  public List<AccountDTO> getAllStudents() {
    List<Student> students = studentService.getAllStudents();
    List<AccountDTO> studentsDto = new ArrayList<>();
    for (Student student : students)
      studentsDto.add(DTOUtil.convertToDTO(student.getAccount()));
    return studentsDto;
  }

  @GetMapping(value = {"/student/{email}", "/student/{email}/"})
  public AccountDTO getStudentByEmail(@PathVariable("email") String email) {
    Student std = studentService.getStudentByEmail(email);
    AccountDTO stdDTO = DTOUtil.convertToDTO(std.getAccount());
    return stdDTO;
  }

  @GetMapping(value = {"/students/{name}", "/students/{name}/"})
  public List<AccountDTO> getStudentByName(@PathVariable("name") String name) {
    List<Student> students = studentService.getStudentByName(name);
    List<AccountDTO> studentsDto = new ArrayList<>();
    for (Student student : students)
      studentsDto.add(DTOUtil.convertToDTO(student.getAccount()));
    return studentsDto;
  }



}

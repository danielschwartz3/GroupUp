package ca.mcgill.ecse428.groupup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse428.groupup.dto.StudentDTO;
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
	public List<StudentDTO> getStudentsByCourseID(int id){
		Course course = courseService.getCourseByID(id);
		List<Student> classMembers = studentService.getStudentsByCourse(course);
		List<StudentDTO> memberDto = new ArrayList<>();
		for(Student student: classMembers)memberDto.add(DTOUtil.convertToDTO(student));
		return memberDto;
	}
}
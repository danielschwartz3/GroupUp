package ca.mcgill.ecse428.groupup.controller;

import ca.mcgill.ecse428.groupup.dto.AccountDTO;
import ca.mcgill.ecse428.groupup.dto.CourseDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Admin;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;

public class DTOUtil {
	 /**
     * Helper method for Account controller
     */
	static AccountDTO convertToDTO(Account account){
        if(account == null){
            throw new IllegalArgumentException("No account");
        }
        String userRole = "";
        if(account.getUserRole() instanceof Student){
            userRole = "Student";
        }else if (account.getUserRole() instanceof Admin){
            userRole = "Admin";
        }
        AccountDTO accountDTO = new AccountDTO(userRole,account.getUserName(),account.getName(), 
                                                account.getEmail(), account.getInstitution());
        return accountDTO;
    }
	
	static CourseDTO convertToDTO(Course course) {
		if(course == null){
            throw new IllegalArgumentException("No course");
        }
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setCourseID(course.getCourseID());
		courseDTO.setYear(course.getYear());
		courseDTO.setFaculty(course.getFaculty());
		courseDTO.setSection(course.getCourseSection());
		courseDTO.setSemester(course.getSemester().toString());
		courseDTO.setName(course.getCourseName());
		return courseDTO;
	}
}

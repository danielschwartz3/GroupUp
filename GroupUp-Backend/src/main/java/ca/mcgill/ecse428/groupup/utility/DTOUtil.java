package ca.mcgill.ecse428.groupup.utility;

import ca.mcgill.ecse428.groupup.dto.AccountDTO;
import ca.mcgill.ecse428.groupup.dto.CourseDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Admin;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DTOUtil {
    /**
     * Helper method for Account controller
     */
    public static AccountDTO convertToDTO(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("No account");
        }
        String userRole = "";
        if (account.getUserRole() instanceof Student) {
            userRole = "Student";
        } else if (account.getUserRole() instanceof Admin) {
            userRole = "Admin";
        }
        AccountDTO accountDTO = new AccountDTO(userRole, account.getUserName(), account.getName(),
                account.getEmail(), account.getInstitution());
        return accountDTO;
    }

    public static CourseDTO convertToDTO(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("No course");
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCourseID(course.getCourseID());
        courseDTO.setYear(course.getYear());
        courseDTO.setFaculty(course.getFaculty());
        courseDTO.setSection(course.getCourseSection());
        courseDTO.setSemester(course.getSemester().toString());
        courseDTO.setName(course.getCourseName());
        courseDTO.setId(course.getId());
        return courseDTO;
    }

    public static List<CourseDTO> convertToDTO(List<Course> courses) {
        if (courses == null) {
            throw new IllegalArgumentException("List of courses is null");
        }
        List<CourseDTO> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            courseDtos.add(convertToDTO(course));
        }
        return courseDtos;
    }

}

package ca.mcgill.ecse428.groupup.gherkin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ca.mcgill.ecse428.groupup.dao.AccountRepository;
import ca.mcgill.ecse428.groupup.dao.CourseRepository;
import ca.mcgill.ecse428.groupup.dao.StudentRepository;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.CourseService;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration
@SpringBootTest
public class SpringWrapper {
	@Autowired
	AccountService testAccountService;
	@Autowired
	CourseService testCourseService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AccountRepository accountRepository;
}

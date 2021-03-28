package ca.mcgill.ecse428.groupup.gherkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ca.mcgill.ecse428.groupup.dao.AccountRepository;
import ca.mcgill.ecse428.groupup.dao.CourseRepository;
import ca.mcgill.ecse428.groupup.dao.StudentRepository;
import ca.mcgill.ecse428.groupup.dao.ChatRepository;
import ca.mcgill.ecse428.groupup.dao.MessageRepository;
import ca.mcgill.ecse428.groupup.service.AccountService;
import ca.mcgill.ecse428.groupup.service.StudentService;
import ca.mcgill.ecse428.groupup.service.CourseService;
import ca.mcgill.ecse428.groupup.service.ChatService;
import ca.mcgill.ecse428.groupup.service.MessageService;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration
@SpringBootTest
public class SpringWrapper {
	@Autowired
	AccountService testAccountService;
	@Autowired
	StudentService testStudentService;
	@Autowired
	CourseService testCourseService;
	@Autowired
	ChatService testChatService;
	@Autowired
	MessageService testMessageService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessageRepository messageRepository;
}

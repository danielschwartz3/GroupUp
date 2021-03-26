package ca.mcgill.ecse428.groupup.utility;

import ca.mcgill.ecse428.groupup.dto.AccountDTO;
import ca.mcgill.ecse428.groupup.dto.ChatDTO;
import ca.mcgill.ecse428.groupup.dto.CourseDTO;
import ca.mcgill.ecse428.groupup.dto.MessageDTO;
import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Message;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DTOUtil {
  /**
   * Helper method for Account controller
   */
  public static AccountDTO convertToDTO(Account account) {
    if (account == null) {
      throw new IllegalArgumentException("No account");
    }
    String userRole = account.getUserRole().userType();
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

  public static List<CourseDTO> convertToCourseDTOs(List<Course> courses) {
    if (courses == null) {
      throw new IllegalArgumentException("List of courses is null");
    }
    List<CourseDTO> courseDtos = new ArrayList<>();
    for (Course course : courses) {
      courseDtos.add(convertToDTO(course));
    }
    return courseDtos;
  }

  public static ChatDTO convertToDTO(Chat chat) {
    ChatDTO dto = new ChatDTO();
    dto.setId(chat.getId());
    Set<String> members = new HashSet<>();
    for (Account m : chat.getMembers())
      members.add(m.getUserName());
    dto.setMembers(members);
    dto.setChatName(chat.getName());
    return dto;
  }

  public static MessageDTO convertToDTO(Message message) {
    if (message == null)
      throw new IllegalArgumentException("message is null");
    MessageDTO dto = new MessageDTO();
    dto.setId(message.getId());
    dto.setContent(message.getContent());
    dto.setLocation(message.getLocation().getId());
    dto.setSender(message.getSender().getUserName());
    dto.setSendDate(message.getSendDate());
    return dto;
  }

  public static List<MessageDTO> convertToMessageDTOs(List<Message> messages) {
    if (messages == null)
      throw new IllegalArgumentException("message list is null");
    List<MessageDTO> dtos = new ArrayList<>();
    for (Message message : messages)
      dtos.add(convertToDTO(message));
    return dtos;
  }

}

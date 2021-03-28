package ca.mcgill.ecse428.groupup.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import ca.mcgill.ecse428.groupup.model.Account;
import ca.mcgill.ecse428.groupup.model.Chat;
import ca.mcgill.ecse428.groupup.model.Course;
import ca.mcgill.ecse428.groupup.model.Message;
import ca.mcgill.ecse428.groupup.model.Student;
import ca.mcgill.ecse428.groupup.utility.Semester;

public class TestUtil {
	static String CANDIDATE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final Semester[] SEMESTERS = {Semester.FALL,Semester.WINTER,Semester.SUMMER};
	private static final String[] YEARS = {"2019","2020","2021","2022"};
	private static final String[] FALC = {"Engineering","Science","Art"};
	
	public static Student generateStudentAccount(Random r) {
		Student s = TestUtil.generateStudent(r);
		Account ac = TestUtil.generateAccount(r);
		s.setAccount(ac);
		ac.setUserRole(s);
		return s;
	}
	
	public static Student generateStudent(Random random) {
		Student student = new Student();
		return student;
	}
	
	public static Account generateAccount(Random random) {
		Account account = new Account();
		account.setEmail(generateString(random, 30));
		account.setInstitution("McGill");
		account.setName(generateString(random,15));
		account.setPassword(generateString(random,12));
		account.setUserName(generateString(random, 10));
		return account;
	}
	
	public static Course generateCourse(Random random) {
		Course course = new Course();
		course.setCourseID(generateString(random,8));
		course.setCourseName(generateString(random,20));
		course.setCourseSection("001");
		course.setYear(YEARS[random.nextInt(YEARS.length)]);
		course.setFaculty(FALC[random.nextInt(FALC.length)]);
		course.setSemester(SEMESTERS[random.nextInt(SEMESTERS.length)]);
		return course;
	}
	
	public static Message generateMessage(Random random, Chat chat) {
		Message message = new Message();
		message.setSendDate(generateDate(random));
		message.setContent(generateString(random,50));
		message.setLocation(chat);
		return message;
	}
	
	public static String generateString(Random random, int n) {
		StringBuilder str = new StringBuilder();
		for(int i = 0 ; i < n ; i++) {
			int index = (int) (random.nextFloat() * CANDIDATE.length());
			str.append(CANDIDATE.charAt(index));
		}
		return str.toString();
	}
	
	public static long getRandomNumber(long min, long max) {
	    return (long)(Math.random() * (max - min)) + min;
	}
	
	public static Date generateDate(Random random) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		c.add(Calendar.MONTH, -1);
		long min = c.getTimeInMillis();
		long max = System.currentTimeMillis();
		return new Date(getRandomNumber(min,max));
	}
}

package ca.mcgill.ecse428.groupup.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue("Student")
public class Student extends UserRole{
//attributes unique to this class added later
	
	Set<Course> courses;
	
	/**
	 * @return the attendCourses
	 */
	@ManyToMany(mappedBy="students", fetch = FetchType.EAGER)
	@JsonBackReference
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param attendCourses the attendCourses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
		course.getStudents().remove(this);
	}
	
	public Student() {
		// TODO Auto-generated constructor stub
		this.courses = new HashSet<>();
	}
}
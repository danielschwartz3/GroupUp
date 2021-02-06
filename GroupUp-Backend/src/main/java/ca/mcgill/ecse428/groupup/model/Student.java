package ca.mcgill.ecse428.groupup.model;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Student extends UserRole{
//attributes unique to this class added later
	
	Set<Course> courses;

	/**
	 * @return the attendCourses
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	public Set<Course> getCourses() {
		return courses;
	}

	/**
	 * @param attendCourses the attendCourses to set
	 */
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
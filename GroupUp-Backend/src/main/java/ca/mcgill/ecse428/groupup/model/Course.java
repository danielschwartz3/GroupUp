package ca.mcgill.ecse428.groupup.model;

import ca.mcgill.ecse428.groupup.utility.Semester;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Course {
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  int id;
  String faculty;
  String courseID;
  @Enumerated(EnumType.ORDINAL)
  Semester semester;
  String year;
  String courseSection;
  String courseName;
  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonManagedReference
  Set<Student> students;

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public String getCourseID() {
    return courseID;
  }

  public void setCourseID(String courseID) {
    this.courseID = courseID;
  }

  public Semester getSemester() {
    return semester;
  }

  public void setSemester(Semester semester) {
    this.semester = semester;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getCourseSection() {
    return courseSection;
  }

  public void setCourseSection(String courseSection) {
    this.courseSection = courseSection;
  }

  public Course() {
    this.students = new HashSet<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void addStudent(Student student) {
    this.students.add(student);
    student.getCourses().add(this);
  }

  public void removeStudent(Student student) {
    this.students.remove(student);
    student.getCourses().remove(this);
  }
}

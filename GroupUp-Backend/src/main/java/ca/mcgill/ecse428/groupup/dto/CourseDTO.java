package ca.mcgill.ecse428.groupup.dto;

public class CourseDTO {
    String courseID;
    String faculty;
    String year;
    String semester;
    String section;

    public CourseDTO() {};

    public CourseDTO(String courseID, String faculty, String year, String semester, String section) {
        this.courseID = courseID;
        this.faculty = faculty;
        this.year = year;
        this.semester = semester;
        this.section = section;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}

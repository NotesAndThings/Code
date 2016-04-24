package jarune.com.notesandthingsv4;

import java.io.Serializable;

/**
 * Created by spencernelson on 4/20/16.
 */
public class Course implements Serializable{
    private String ID;
    private String University;
    private String CourseNumber;
    private String Name;
    private String Instructor;
    private String Semester;
    private String Year;

    public Course(String id, String university, String courseNumber, String name, String instructor, String semester, String year) {
        ID = id;
        University = university;
        CourseNumber = courseNumber;
        Name = name;
        Instructor = instructor;
        Semester = semester;
        Year = year;
    }

    public String getCourseNumber() {
        return CourseNumber;
    }

    public String getID() {
        return ID;
    }

    public String getInstructor() {
        return Instructor;
    }

    public String getName() {
        return Name;
    }

    public String getSemester() {
        return Semester;
    }

    public String getUniversity() {
        return University;
    }

    public String getYear() {
        return Year;
    }

    public void setCourseNumber(String courseNumber) {
        CourseNumber = courseNumber;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setInstructor(String instructor) {
        Instructor = instructor;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public void setUniversity(String university) {
        University = university;
    }

    public void setYear(String year) {
        Year = year;
    }

}

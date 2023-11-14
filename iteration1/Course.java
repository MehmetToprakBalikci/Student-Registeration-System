import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



// Course class
class Course {
    private String courseCode;
    private String courseName;
    private int courseCredit;
    private Grade grade;

    // Constructor
    public Course(String courseCode, String courseName, int courseCredit) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.grade = new Grade();
    }
}

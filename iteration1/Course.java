import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



// Course class
class Course {
    private String courseCode;
    private String courseName;
    private int courseCredit;
    private int courseYear;
    private CourseSection section;
    //private Grade grade;
    private Lecturer lecturer;


    // Constructor
    public Course(String courseCode, String courseName, int courseCredit, int courseYear, CourseSection section, Lecturer lecturer) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.courseYear = courseYear;
        this.section = section;
        this.lecturer=lecturer;
        //this.grade = new Grade();
    }

    public boolean checkYearMatching(int year){
        if(courseYear == year){
            return true;
        }
        return false;
    }

    public boolean checkCourseSection(List<Course> registrationCompleteCourse, List<Course> registrationWaitingCourse, List<Course> cancelWaitingCourse){
        return false;
    }

    public boolean checkPreRequisite(){
        return false;
    }

    public String getCourseString(){
        return 0;
    }

    public String getCourseTranscriptFormatString(){
        return 0;
    }

    public int getCourseCredit() {
        return courseCredit;
    }
}

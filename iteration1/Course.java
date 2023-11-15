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
    private List<Course> preRequisite;


    // Constructor
    public Course(String courseCode, String courseName, int courseCredit, int courseYear, CourseSection section, Lecturer lecturer) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.courseYear = courseYear;
        this.section = section;
        this.lecturer = lecturer;
        //this.grade = new Grade();
    }

    // Check if this student in the appropriate semester to take this course?
    public boolean checkYearMatching(int year){
        if(courseYear == year){
            return true;
        }
        return false;
    }

    // Check if this course conflicts with any other courses section
    public boolean checkCourseSection(List<Course> registrationCompleteCourse, List<Course> registrationWaitingCourse, List<Course> cancelWaitingCourse){
        for (Course course : registrationCompleteCourse) {
            if (course.getSection().compareAvailabilty(this.section)) {
                return false;
            }
        }
        for (Course course : registrationWaitingCourse) {
            if (course.getSection().compareAvailabilty(this.section)) {
                return false;
            }
        }
        for (Course course : cancelWaitingCourse) {
            if (course.getSection().compareAvailabilty(this.section)) {
                return false;
            }
        }
        return true;
    }


    public boolean checkPreRequisite(List<Course> completedCourses){
        return false;
    }

    public String getCourseString(){
        return "";
    }

    public String getCourseTranscriptFormatString(){
        return "";
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public CourseSection getSection() {
        return section;
    }
}

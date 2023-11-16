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
    public Course(String courseCode, String courseName, int courseCredit, int courseYear, CourseSection section, Lecturer lecturer,List<Course> preRequisite) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
        this.courseYear = courseYear;
        this.section = section;
        this.lecturer = lecturer;
        this.preRequisite = preRequisite;
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
            if (course.getSection().compareAvailability(this.section)) {
                return false;
            }
        }
        for (Course course : registrationWaitingCourse) {
            if (course.getSection().compareAvailability(this.section)) {
                return false;
            }
        }
        for (Course course : cancelWaitingCourse) {
            if (course.getSection().compareAvailability(this.section)) {
                return false;
            }
        }
        return true;
    }

    // Check if given student can take this course, according to prerequisite and given completed courses
    public boolean checkPreRequisite(List<Course> completedCourses){
        if(preRequisite.isEmpty()){
            return true;
        }

        List<Course> copy = new ArrayList<>(preRequisite);

        for (Course course : completedCourses) {
            copy.removeIf(preCourse -> course.getCourseCode().equals(preCourse.getCourseCode()));
            if(copy.isEmpty()){
                return true;
            }
        }

        return false;
    }

    // Returns course information, code and 
    @Override
    public String toString(){
        return courseCode + " " + courseName;
    }
    public String toStringFormatted(int format){
        if(format == 1){
            return courseCode + " " + courseName;
        }
        else {
            return courseCode + " " + courseName + " " + this.lecturer.getName() + " " + this.lecturer.getLastName();
        }
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

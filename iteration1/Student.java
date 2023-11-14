import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



// Student class
class Student extends Person {
    private List<Course> availableCourseList;
    private List<Course> registrationWaitingCourses;

    // Method to create student
    public static Student create(String name, String lastName) {
        return new Student(name, lastName);
    }

    // Constructor
    private Student(String name, String lastName) {
        super(name, lastName);
        this.availableCourseList = new ArrayList<>();
        this.registrationWaitingCourses = new ArrayList<>();
    }

    // Method to add course to available list
    public void addToAvailableCourseList(Course course) {
        availableCourseList.add(course);
    }

    // Placeholder for starting student actions
    public void startAction() {
        // Implementation needed
    }

    // Get courses waiting for registration
    public List<Course> getRegistrationWaitingCourses() {
        return registrationWaitingCourses;
    }

    // Update course waiting for registration
    public void updateRegisterWaitingCourse(Course course) {
        // Implementation needed
    }

    // Get courses to cancel waiting for registration
    public List<Course> getCancelWaitingCourses() {
        // Implementation needed
        return new ArrayList<>();
    }

    // Update cancel waiting course
    public void updateCancelWaitingCourse(Course course) {
        // Implementation needed
    }
}

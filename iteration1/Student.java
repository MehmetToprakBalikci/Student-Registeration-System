import java.util.ArrayList;
import java.util.List;


// Student class
class Student extends Person implements User{
    private List<Course> availableCourseList;
    private List<Course> registrationWaitingCourses;

    // Method to create student
   /* public static Student create(String name, String lastName) {
        return new Student(name, lastName);
    }*/

    // Constructor
    private Student(String name, String lastName,String username,String password) {
        super(name, lastName,username,password);
        this.availableCourseList = new ArrayList<>();
        this.registrationWaitingCourses = new ArrayList<>();
    }

    @Override
    public String[] getActionList() {
        return new String[0];
    }

    @Override
    public void runUserAction(int actionNumber) {

    }


    @Override
    public void startActions(Controller controller) {
        String[] actionList= getActionList();
        int actionNumber =controller.getAction(actionList);
        runUserAction(actionNumber);
    }

    @Override
    Person signIn(String username, String password) {
        return null;
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

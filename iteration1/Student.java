import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


// Student class
class Student extends Person implements User{
    private List<Course> currentAvailableCourses; // Courses that the student is able to register
    private List<Course> registrationWaitingCourses; //Courses that are waiting to be registered
    private List<Course> registrationCompleteCourses; // Courses that finished registration
    private List<Course> cancelWaitingCourses; // Courses that are waiting to be canceled 
    private String studentID; // Additional field for student ID
    
    // Method to create student
   /* public static Student create(String name, String lastName) {
        return new Student(name, lastName);
    }*/

    // Constructor
    public Student(String name, String lastName,String username,String password, String studentID) {
        super(name, lastName,username,password);
        this.studentID = studentID;
        this.currentAvailableCourses = new ArrayList<>();
        this.registrationCompleteCourses = new ArrayList<>();
        this.cancelWaitingCourses = new ArrayList<>();
        this.registrationWaitingCourses = new ArrayList<>();
    }
/*
    @Override
    public String[] getActionList() {
        return new String[0];
    }
*/
    //@Override
 //   public void runUserAction(int actionNumber) {

//    }
@Override
    public String[] getActionList() {
    	
    	String[] actionList = new String[8];
    	
    	actionList[0] = "Select an action.";
    	actionList[1] = "1) Check courses available to register.";
    	actionList[2] = "2) Check your accepted courses.";
        actionList[3] = "3) See courses waiting to be registered.";
        actionList[4] = "4) See courses waiting to be canceled.";
        actionList[5] = "5) User transcript.";
    	actionList[6] = "6) Advisor information.";
    	actionList[7] = "7) Log out.";
    	
        return actionList;
    }
   public String[] toStringAdvisor() {
   String[] returnedVal = new String[3];
   returnedVal[0] = this.name;
    returnedVal[1] = this.lastName;
       returnedVal[2] = this.studentID;
    return returnedVal;
   }


    @Override
    public void startActions(Controller controller) {
        String[] actionList= getActionList();
        int actionNumber = controller.printListReturnSelection(actionList);
        runUserAction(actionNumber, controller);
    }


    // Method to add course to available list
    public void addToAvailableCourseList(Course course) {
        availableCourseList.add(course);
    }

    @Override
    public void runUserAction(int actionNumber, Controller controller) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'runUserAction'");
    }
}
/*
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
*/

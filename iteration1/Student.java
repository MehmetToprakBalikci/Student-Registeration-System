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
    private Student(String name, String lastName,String username,String password, String studentID) {
        super(name, lastName,username,password, studentID);
        this.availableCourseList = new ArrayList<>();
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

   public String[] toStringAdvisor() {
   String[] returnedVal = new String[3]
   returnedVal[0] = this.name;
    returnedVal[1] = this.surname;
       returnedVal[2] = this.studentID;
    return returnedVal;
   }

/*
    @Override
    public void startActions(Controller controller) {
        String[] actionList= getActionList();
        int actionNumber =controller.getAction(actionList);
        runUserAction(actionNumber);
    }
*/
    @Override
        public boolean signIn(String username, String password) {
        // Implement the JSON file reading and checking logic here
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("data.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray persons = (JSONArray) jsonObject.get("persons");

            for (Object o : persons) {
                JSONObject person = (JSONObject) o;
                String uname = (String) person.get("username");
                String pwd = (String) person.get("password");

                if (uname.equals(username) && pwd.equals(password)) {
                    // Assign the properties to the fields if needed
                    this.name = (String) person.get("name");
                    this.lastName = (String) person.get("lastName");
                    // ... other fields
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to add course to available list
    public void addToAvailableCourseList(Course course) {
        availableCourseList.add(course);
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

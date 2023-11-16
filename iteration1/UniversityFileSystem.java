import java.util.ArrayList;
import java.util.List;

// Interface for file system operations
public class UniversityFileSystem {

    List<Person> personList;//obsolete now??
    List<Course> courseList;
    List<Transcript> transcriptList;
    List<Person> studentList;
    List<Person> staffList;

    public UniversityFileSystem() {
        staffList = new ArrayList<>();
        courseList = new ArrayList<>();
        transcriptList = new ArrayList<>();
        studentList = new ArrayList<>();

    }

    void saveFiles() {

    }

    /*void create() {

    }*/

    void loadFiles() {
        //readCourses() Move advisor info
        //readTranscripts Make jsons
        //readStudents() Check type here
        //readStaff() Discriminate between types here

    }

    public Person getSignedPerson(String[] userInfo, Controller currentController) {
     Person person = null;
        int errorCode = checkUsernamePasswordLength(userInfo);
         if(errorCode == 0) {
            for (int i = 0; i < personList.size(); i++) {
                person = personList.get(i);
                String userName=person.getUserName();
                String password=person.getPassword();
                if (userName.equals(userInfo[0]) && password.equals(userInfo[1])){
                    return person;
                }
            }
         }
         else {
            if(errorCode == 1)
             currentController.printErrorMessage("Username too long");
            else if(errorCode == 2)
            currentController.printErrorMessage("Password too long");
         }
         if (person == null) {
            currentController.printErrorMessage("Username or Password mismatch");
         }
         
      return null;

    }
    private int checkUsernamePasswordLength(String[] userInfo){
        int errorNum = 0;
        //Add errors
        return errorNum;
    }
    // Methods for file operations need to be implemented
}

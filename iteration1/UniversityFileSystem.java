import java.util.ArrayList;
import java.util.List;

// Interface for file system operations
public class UniversityFileSystem {
    List<Person> personList;
    List<Person>courseList;

    public UniversityFileSystem() {
        personList = new ArrayList<>();
        courseList=new ArrayList<>();
    }

    void saveFiles() {

    }

    /*void create() {

    }*/

    void loadFiles() {


    }

    public Person getSignedPerson(String[] userInfo) {
        for (Person person:personList) {
            String userName=person.getUserName();
            String password=person.getPassword();
            if (userName.equals(userInfo[0]) && password.equals(userInfo[1])){
                return person;
            }
        }
      return null;

    }
    // Methods for file operations need to be implemented
}

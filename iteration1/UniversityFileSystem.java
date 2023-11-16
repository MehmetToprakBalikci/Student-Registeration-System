import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    public  static void readCourses() {
        File directoryPath = new File("Courses");
        File[] fileList =directoryPath .listFiles();
        try{
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject)object;

                // getting course attributes
                String courseCode = (String) jsonObject.get("courseCode");
                String courseName = (String) jsonObject.get("courseName");
                String courseCredit = (String) jsonObject.get("courseCredit");
                String courseYear = (String) jsonObject.get("courseYear");

                JSONArray prerequisitesArray = (JSONArray) jsonObject.get("prerequisites");
                JSONArray courseSectionArray = (JSONArray) jsonObject.get("courseSection");


                System.out.println("Course Code  -> " + courseCode + " Course Name ->  " + courseName +
                        " Course year -> " + courseYear + " Course Credit -> " + courseCredit);
                System.out.println("Prerequisites -> "  + prerequisitesArray.toJSONString());
                Object [] objects = prerequisitesArray.toArray();
                Object [] courseSectionObjects = courseSectionArray.toArray();

                for (Object obj : objects) {
                    System.out.println((String)obj);
                }

                System.out.println("CourseSection -> "  + courseSectionArray.toJSONString());
                for (Object obj : courseSectionObjects) {
                    System.out.println(obj);
                }


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (File file : fileList) {
            System.out.println(file);
        }

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

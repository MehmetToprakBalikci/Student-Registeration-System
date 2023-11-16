import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
                JSONArray lecturerArray = (JSONArray) jsonObject.get("lecturer");


                // printing all values
                System.out.println("Course Code  -> " + courseCode + " Course Name ->  " + courseName +
                        " Course year -> " + courseYear + " Course Credit -> " + courseCredit);
                System.out.println("Prerequisites -> "  + prerequisitesArray.toJSONString());


                System.out.println("CourseSection -> "  + courseSectionArray.toJSONString());
                System.out.println("Lecturer -> " + lecturerArray.toJSONString());

                Object [] prerequisitesObjects = prerequisitesArray.toArray();
                Object [] courseSectionObjects = courseSectionArray.toArray();
                Object []  lecturerObjects = lecturerArray.toArray();
                ArrayList<String> preRequisites = new ArrayList<>();
                ArrayList<Long> courseSectionInfo = new ArrayList<>();
                ArrayList<String> lecturerInfo = new ArrayList<>();


                for (Object obj : prerequisitesObjects) {
                    preRequisites.add((String)obj);
                    System.out.println((String)obj);
                }

                System.out.println("CourseSection -> "  + courseSectionArray.toJSONString());
                for (Object obj : courseSectionObjects) {
                    courseSectionInfo.add((Long)obj);
                    System.out.println(obj);
                }
                // courseSection object is created
                CourseSection courseSection = new CourseSection(courseSectionInfo.get(0).intValue(),courseSectionInfo.get(1).intValue());

                for (Object obj : lecturerObjects) {
                    lecturerInfo.add((String)obj);
                    System.out.println((String)obj);
                }
                // lecturer object is created
                Lecturer lecturer = new Lecturer(lecturerInfo.get(0),lecturerInfo.get(1),lecturerInfo.get(2));


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
    public static void readStudents() {

        File directoryPath = new File("Students");
        File[] fileList =directoryPath .listFiles();
        try{
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject)object;

                String name = (String) jsonObject.get("name");
                String lastName = (String) jsonObject.get("lastName");
                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");

                JSONArray cancelWaitingCoursesJsonArray = (JSONArray) jsonObject.get("cancelWaitingCourses");
                JSONArray registrationCompleteCoursesJsonArray = (JSONArray) jsonObject.get("registrationCompleteCourses");
                JSONArray registrationWaitingCoursesJsonArray = (JSONArray) jsonObject.get("registrationWaitingCourses");

                Object [] cancelWaitingCoursesObjectsArray = cancelWaitingCoursesJsonArray.toArray();
                Object [] registrationCompleteCoursesObjectsArray = registrationCompleteCoursesJsonArray.toArray();
                Object []  registrationWaitingCoursesObjectsArray =  registrationWaitingCoursesJsonArray.toArray();

                ArrayList<String> cancelWaitingCoursesArrayList = new ArrayList<>();
                ArrayList<String> registrationCompleteCoursesArrayList = new ArrayList<>();
                ArrayList<String> registrationWaitingCourseArrayList = new ArrayList<>();


                System.out.println("Name : " + name + " Lastname : " + lastName + " Username : " + username + " Password : " + password   );

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

    public static HashMap<Integer, Transcript> readTranscripts() {
        HashMap<Integer, Transcript> transcripts = new HashMap<Integer, Transcript>();
        File directoryPath = new File("Transcripts");
        File[] fileList = directoryPath.listFiles();
        try {
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject) object;

                // getting course attributes
                long studentID = (long) (jsonObject.get("studentID"));

                JSONArray coursesArray = (JSONArray) jsonObject.get("listOfCourses");
                JSONArray gradesArray = (JSONArray) jsonObject.get("listOfGrades");


                System.out.println("Student id  -> " + studentID);

                Object[] courseObjects = coursesArray.toArray();
                Object[] gradeObjects = gradesArray.toArray();

                List<String> coursesList = new ArrayList<>();
                List<Integer> gradesList = new ArrayList<>();


                for (Object obj : courseObjects) {
                    coursesList.add((String) obj);
                }
                for(int i = 0; i < coursesList.size(); i++)
                    System.out.println(coursesList.get(i));

                for (Object obj : gradeObjects) {
                    gradesList.add((int)obj);
                }
                for(int i = 0; i < gradesList.size(); i++)
                    System.out.println(gradesList.get(i));


                //puts individual transcript data together to make an object and adds to hashmap
                transcripts.put((int) studentID, createTranscript(coursesList, gradesList));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return transcripts;

    }

    private static Transcript createTranscript(List<String> listOfCourseCodes, List<Integer> listOfNumericalGrades) {
        List<Grade> listOfGrades = new ArrayList<>();
        List<Course> listOfCourses = new ArrayList<>();

        for (int i = 0; i < listOfNumericalGrades.size(); i++) {
            Grade grade = new Grade(listOfNumericalGrades.get(i));
            listOfGrades.add(grade);
        }

        for (int i = 0; i < listOfCourseCodes.size(); i++) {
            //Course course = createCourse(); TODO
            //listOfGrades.add(course);
        }

        Transcript transcript = new Transcript(listOfCourses, listOfGrades);
        return  transcript;
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

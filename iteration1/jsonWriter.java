import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jsonWriter {


    public void saveFiles(Person person) {
        Student student;

        //check for type
        if(!(person instanceof  Student)) {
            System.out.println("Wrong type of person!");
            return;
        }
        else {
            student = (Student) person;
        }

        //Find the file to be updated
        File directoryPath = new File("iteration1/Students");
        File[] fileList = directoryPath.listFiles();
        if (fileList == null) {
            System.out.println("Check the students directory position!!");
            return;
        }

        //set file name to student id + json
        String fileName = student.getStudentID() + ".json";

        try {
            File file = null;
            JSONParser jsonParser = new JSONParser();
            for (File f : fileList) {
                //validate it's the correct file
                if (!f.getName().equals(fileName)) {
                    break;
                }
                else
                    file = f;
            }
            //null check
            if(file == null) {
                System.out.println("Student file does not exist!");
                return;
            }

                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject obj = (JSONObject) object;


                //prepare student data to be put into json
                String userName = student.getUserName();
                String password = student.getPassword();
                String id = student.getStudentID();
                List<Course> courses = student.getCurrentTranscript().getListOfCourses();
                List<Grade> grades = student.getCurrentTranscript().getListOfGrades();
                String advID = (student.getCurrentAdvisor()).getLecturerID();

                //make a list of course codes
                List<String> cancelWaitingCourses = new ArrayList<>();
                for(Course course : student.getCancelWaitingCourses())
                    cancelWaitingCourses.add(course.getCourseCode());

                //make a list of course codes
                List<String> registrationCompleteCourses = new ArrayList<>();
                for(Course course : student.getRegistrationCompleteCourses())
                    registrationCompleteCourses.add(course.getCourseCode());

                //make a list of course codes
                List<String> registrationWaitingCourses = new ArrayList<>();
                for(Course course : student.getRegistrationWaitingCourses())
                    registrationWaitingCourses.add(course.getCourseCode());


                obj.put("username", userName);
                obj.put("password", password);
                obj.put("studentId", id);
                obj.put("advisorId", advID);
                obj.put("cancelWaitingCourses", cancelWaitingCourses);
                obj.put("registrationCompleteCourses", registrationCompleteCourses);
                obj.put("registrationWaitingCourses", registrationWaitingCourses);




        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

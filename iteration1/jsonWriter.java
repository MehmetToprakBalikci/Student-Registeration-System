import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        //set file name to student id
        String fileName = student.getStudentID();

        try {
            File file = null;
            JSONParser jsonParser = new JSONParser();
            for (File f : fileList) {
                //validate it's the correct file
                if ((f.getName().contains(fileName))) {
                    file = f;
                    break;
                }
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
                String name = (String) obj.get("name");
                String lastName = (String) obj.get("lastName");
                String userName = student.getUserName();
                String password = student.getPassword();
                String id = student.getStudentID();
                List<Course> courses = student.getCurrentTranscript().getListOfCourses();
                List<Grade> grades = student.getCurrentTranscript().getListOfGrades();
                String advID = (student.getCurrentAdvisor()).getLecturerID();
                String classLevel = String.valueOf(student.getCurrentTranscript().getYear());

                //make a list of course codes
                List<String> cancelWaitingCourses = new ArrayList<>();
                for(Course course : student.getCancelWaitingCourses())
                    if(course != null)
                        cancelWaitingCourses.add(course.getCourseCode());

                //make a list of course codes
                List<String> registrationCompleteCourses = new ArrayList<>();
                for(Course course : student.getRegistrationCompleteCourses())
                    if(course != null)
                        registrationCompleteCourses.add(course.getCourseCode());

                //make a list of course codes
                List<String> registrationWaitingCourses = new ArrayList<>();
                for(Course course : student.getRegistrationWaitingCourses()) {
                    if(course != null)
                        registrationWaitingCourses.add(course.getCourseCode());
                }

                String format = new String();
                format += "{\n" + "  \"type\": \"student\",\n";
                format += "  \"name\": \"" + name + "\",\n";
                format += "  \"lastName\": \"" + lastName + "\",\n";
                format += "  \"username\": \"" + userName + "\",\n";
                format += "  \"password\": \"" + password + "\",\n";
                format += "  \"studentId\": \"" + id + "\",\n";

                format += "  \"Transcript\": {\n";
                format += "    \"listOfCourses\": [\n      ";
                for(int i = 0; i < student.getCurrentTranscript().getListOfCourses().size(); i++) {
                    format += "\""
                            + student.getCurrentTranscript().getListOfCourses().get(i).getCourseCode()
                            + "\"";
                    if(i != student.getCurrentTranscript().getListOfCourses().size()-1)
                        format += ",";
                    else
                        format += "\n    ],\n";
                }

                format += "    \"listOfGrades\": [\n      ";
                for(int i = 0; i < student.getCurrentTranscript().getListOfGrades().size(); i++) {
                    format += student.getCurrentTranscript().getListOfGrades().get(i).getNumericalGrade();
                    if(i != student.getCurrentTranscript().getListOfGrades().size()-1)
                        format += ",";
                    else
                        format += "\n    ]\n";
                }
                format += "  },\n";

                format += "  \"advisorId\": \"" + advID + "\",\n";
                format += "  \"classLevel\": \"" + classLevel + "\",\n";

                format += "  \"cancelWaitingCourses\": [\n";
                for(int i = 0; i < cancelWaitingCourses.size(); i++) {
                    format += "    \"" + cancelWaitingCourses.get(i) + "\"";
                    if(i != cancelWaitingCourses.size()-1)
                        format += ",\n";
                }
                format += "  \n  ],\n";

                format += "  \"registrationCompleteCourses\": [\n";
                for(int i = 0; i < registrationCompleteCourses.size(); i++) {
                    format += "    \"" + registrationCompleteCourses.get(i) + "\"";
                    if(i != registrationCompleteCourses.size()-1)
                        format += ",\n";
                }
                format += "  \n   ],\n";

                format += "  \"registrationWaitingCourses\": [\n";
                for(int i = 0; i < registrationWaitingCourses.size(); i++) {
                    format += "    \"" + registrationWaitingCourses.get(i) + "\"";
                    if(i != registrationWaitingCourses.size()-1)
                        format += ",\n";
                }
                format += "  \n  ]\n}";

            try {

                String dir = "iteration1/Students";
                FileWriter fileWriter = new FileWriter(new File(dir,student.getStudentID() + ".json"));
                fileWriter.write(format);
                fileWriter.close();

            } catch (IOException e) {
                System.out.println("Encountered error while saving json!");
            }




        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

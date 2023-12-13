import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWriter {
    private Person person;
    private static JsonWriter singletonJSonWriter;

    private JsonWriter() {

    }

    public JsonWriter getInstance() {
        if (singletonJSonWriter == null) {
            return new JsonWriter();
        }
        return singletonJSonWriter;
    }

    private JsonWriter(Person person) {
        this.person = person;
    }

    public static JsonWriter getInstance(Person user) {
        if (singletonJSonWriter == null) {
            return new JsonWriter(user);
        }
        return singletonJSonWriter;
    }


    public void saveFiles() {
        // if student enters the system
        if (person instanceof Student) {
            updateStudentFile((Student) person);
            // if advisor enters the system
        } else
            updateStudentFilesAsAdvisor();

    }

    private void updateStudentFilesAsAdvisor() {
        if (!(person instanceof Advisor)) return;
        Advisor advisor = (Advisor) person;
        for (Student student : advisor.getStudentList()) {
            updateStudentFile(student);
        }
    }

    private void updateStudentFile(Student student) {
        //check for type
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
            if (file == null) {
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
            for (Course course : student.getCancelWaitingCourses())
                if (course != null)
                    cancelWaitingCourses.add(course.getCourseCode());

            //make a list of course codes
            List<String> registrationCompleteCourses = new ArrayList<>();
            for (Course course : student.getRegistrationCompleteCourses())
                if (course != null)
                    registrationCompleteCourses.add(course.getCourseCode());

            //make a list of course codes
            List<String> registrationWaitingCourses = new ArrayList<>();
            for (Course course : student.getRegistrationWaitingCourses()) {
                if (course != null)
                    registrationWaitingCourses.add(course.getCourseCode());
            }

            StringBuilder format = new StringBuilder();
            format.append("{\n" + "  \"type\": \"student\",\n");
            format.append("  \"name\": \"").append(name).append("\",\n");
            format.append("  \"lastName\": \"").append(lastName).append("\",\n");
            format.append("  \"username\": \"").append(userName).append("\",\n");
            format.append("  \"password\": \"").append(password).append("\",\n");
            format.append("  \"studentId\": \"").append(id).append("\",\n");

            format.append("  \"Transcript\": {\n");
            format.append("    \"listOfCourses\": [\n      ");
            for (int i = 0; i < student.getCurrentTranscript().getListOfCourses().size(); i++) {
                format.append("\"").append(student.getCurrentTranscript().getListOfCourses().get(i).getCourseCode()).append("\"");
                if (i != student.getCurrentTranscript().getListOfCourses().size() - 1)
                    format.append(",");
                else
                    format.append("\n    ],\n");
            }

            format.append("    \"listOfGrades\": [\n      ");
            for (int i = 0; i < student.getCurrentTranscript().getListOfGrades().size(); i++) {
                format.append(student.getCurrentTranscript().getListOfGrades().get(i).getNumericalGrade());
                if (i != student.getCurrentTranscript().getListOfGrades().size() - 1)
                    format.append(",");
                else
                    format.append("\n    ]\n");
            }
            format.append("  },\n");

            format.append("  \"advisorId\": \"").append(advID).append("\",\n");
            format.append("  \"classLevel\": \"").append(classLevel).append("\",\n");

            format.append("  \"cancelWaitingCourses\": [\n");
            for (int i = 0; i < cancelWaitingCourses.size(); i++) {
                format.append("    \"").append(cancelWaitingCourses.get(i)).append("\"");
                if (i != cancelWaitingCourses.size() - 1)
                    format.append(",\n");
            }
            format.append("  \n  ],\n");

            format.append("  \"registrationCompleteCourses\": [\n");
            for (int i = 0; i < registrationCompleteCourses.size(); i++) {
                format.append("    \"").append(registrationCompleteCourses.get(i)).append("\"");
                if (i != registrationCompleteCourses.size() - 1)
                    format.append(",\n");
            }
            format.append("  \n   ],\n");

            format.append("  \"registrationWaitingCourses\": [\n");
            for (int i = 0; i < registrationWaitingCourses.size(); i++) {
                format.append("    \"").append(registrationWaitingCourses.get(i)).append("\"");
                if (i != registrationWaitingCourses.size() - 1)
                    format.append(",\n");
            }
            format.append("  \n  ]\n}");

            try {

                String dir = "iteration1/Students";
                FileWriter fileWriter = new FileWriter(new File(dir, student.getStudentID() + ".json"));
                fileWriter.write(format.toString());
                fileWriter.close();

            } catch (IOException e) {
                System.out.println("Encountered error while saving json!");
            }


        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FileSystem {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        fileSystem.loadFiles();
        System.out.println("ALL COURSES: ");
        for (Course course : fileSystem.systemCourses) {
            System.out.println(course.toString());
            System.out.println(course.getLecturer());
            System.out.println(course.getPreRequisite());
            System.out.println("---------------------------");

        }
        System.out.println("ALL STUDENTS: ");
        for (Student student : fileSystem.getStudents()) {
            System.out.println(student.toString());
            System.out.println(student.getCurrentAdvisor());
            System.out.println("AvailableCourses: " + student.getCurrentAvailableCourses());
            System.out.println("---------------------------");

        }
        System.out.println("ALL ADVISORS");
        for (Advisor advisor : fileSystem.getAdvisors()) {
            System.out.println(advisor.toString());
            System.out.println("Students of advisor: " + advisor.getStudentList());
            System.out.println("---------------------------");
        }


    }

    private final List<Person> personList; // all persons

    // course part
    private final List<Course> systemCourses;  // all coursesList
    private final List<List<String>> coursesPrerequisitesCodes;// will used for filling the courses prequesites lists after all processes
    private final List<String> coursesLecturerIds;  // will used for assigning courses lecturer after all processes

    //Students Lists Parts, assign finishing all the processes
    private final List<List<String>> studentsCancelWaitingCourseCodes;
    private final List<List<String>> studentsRegistrationCompleteCourses;
    private final List<List<String>> studentsRegistrationWaitingCourses;
    private final List<String> studentsAdvisorIdsList;

    // advisor
    private List<List<String>> advisorsStudentIdList;

    public FileSystem() {
        personList = new ArrayList<>();
        systemCourses = new ArrayList<>();
        coursesPrerequisitesCodes = new ArrayList<>();
        coursesLecturerIds = new ArrayList<>();
        studentsCancelWaitingCourseCodes = new ArrayList<>();
        studentsRegistrationCompleteCourses = new ArrayList<>();
        studentsRegistrationWaitingCourses = new ArrayList<>();
        studentsAdvisorIdsList = new ArrayList<>();
        advisorsStudentIdList = new ArrayList<>();


    }

    public void loadFiles() {
        readCourses();
        readStudents();
        readStaffs();
        //advisor object will be added;
        updateCourses();
        // fill the remain parts of student
        updateStudents();
        // fill the remain parts of advisor
        updateAdvisors();


    }

    private void updateAdvisors() {
        // advisor's students will be updated here
        List<Advisor> advisors = getAdvisors();
        int index = 0;
        for (Advisor advisor : advisors) {
            // {"101,102,103"}
            List<String> currentAdvisorsStudentStringList = advisorsStudentIdList.get(index);
            //  {s1,s2,s3}
            ArrayList<Student> currentAdvisorsStudentList = getStudentList(currentAdvisorsStudentStringList);
            advisor.setStudentList(currentAdvisorsStudentList);
            index++;


        }
    }

    private ArrayList<Student> getStudentList(List<String> currentAdvisorsStudentStringList) {
        ArrayList<Student> students = new ArrayList<>();
        for (String studentId : currentAdvisorsStudentStringList) {
            Student student = getStudent(studentId);
            students.add(student);

        }
        return students;

    }

    private Student getStudent(String studentId) {
        List<Student> students = getStudents();
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                return student;
            }

        }
        return null;

    }

    private List<Advisor> getAdvisors() {
        List<Advisor> advisors = new ArrayList<>();
        for (Person person : personList) {
            if (person instanceof Advisor) {
                advisors.add((Advisor) person);
            }


        }
        return advisors;
    }

    private void updateStudents() {
        //updateTranscripts();
        updateStudentsAdvisor(studentsAdvisorIdsList);
        updateStudentsWaitingCourseLists(studentsRegistrationWaitingCourses);
        updateStudentsRegistrationCompleteCourseLists(studentsRegistrationCompleteCourses);
        updateStudentsCancelWaitingCourseLists(studentsCancelWaitingCourseCodes);
        updateStudentsAvailableCourses();

    }

    private void updateStudentsAvailableCourses() {
        List<Student> students = getStudents();
        for (Student student : students) {
            List<Course> availableCourseList = getAvailableCourses(student);
            student.setCurrentAvailableCourses(availableCourseList);

        }
    }

    private void updateStudentsAdvisor(List<String> studentsAdvisorIdsStringList) {
        List<Student> students = getStudents();
        int index = 0;
        for (Student student : students) {
            String currentStudentsAdvisorId = studentsAdvisorIdsStringList.get(index);
            Advisor advisor = findAdvisor(currentStudentsAdvisorId);
            student.setCurrentAdvisor(advisor);
            index++;

        }

    }

    private Advisor findAdvisor(String currentStudentsAdvisorId) {
        List<Advisor> advisors = getAdvisors();
        for (Advisor advisor : advisors) {
            if (advisor.getLecturerID().equals(currentStudentsAdvisorId)) {
                return advisor;
            }

        }
        return null;
    }

    private void updateStudentsCancelWaitingCourseLists(List<List<String>> studentsCancelWaitingCourseCodes) {
        List<Student> students = getStudents();
        int index = 0;
        for (Student student : students) {
            List<String> courseListString = studentsCancelWaitingCourseCodes.get(index);
            List<Course> waitingCourses = convertToCourseList(systemCourses, courseListString);
            student.setCancelWaitingCourses(waitingCourses);
            index++;
        }
    }

    private List<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for (Person person : personList) {
            if (person instanceof Student) {
                students.add((Student) person);
            }
        }
        return students;
    }

    private void updateStudentsRegistrationCompleteCourseLists(List<List<String>> studentsRegistrationCompleteCourses) {
        List<Student> students = getStudents();
        int index = 0;
        for (Student student : students) {
            List<String> courseCompletedStringList = studentsRegistrationCompleteCourses.get(index);
            List<Course> waitingCourses = convertToCourseList(systemCourses, courseCompletedStringList);
            student.setRegistrationCompleteCourses(waitingCourses);
            index++;

        }
    }

    private List<Course> convertToCourseList(List<Course> systemCourses, List<String> courseCodeListString) {
        List<Course> courseList = new ArrayList<>();
        for (String courseCode : courseCodeListString) {
            Course course = convertToCourse(courseCode, systemCourses);
            courseList.add(course);

        }
        return courseList;

    }

    private Course convertToCourse(String courseCode, List<Course> systemCourses) {
        for (Course course : systemCourses) {
            if (courseCode.equals(course.getCourseCode())) {
                return course;
            }

        }
        return null;
    }

    private void updateStudentsWaitingCourseLists(List<List<String>> studentsRegistrationWaitingCourses) {
        List<Student> students = getStudents();
        int index = 0;
        for (Student student : students) {
            List<String> courseWaitingStringList = studentsRegistrationWaitingCourses.get(index);
            List<Course> waitingCourses = convertToCourseList(systemCourses, courseWaitingStringList);
            student.setRegistrationWaitingCourses(waitingCourses);
            index++;
        }
    }

    private void updateCourses() {
        // bütün lecturerlar
        List<Lecturer> lecturers = getLecturers();

        // bütün lecturerlar ama courseun oluşturduğumuz sıradaki lecturer id sırası
        List<Lecturer> orderedLecturers = getOrderedLecturerList(lecturers);
        int index = 0;
        for (Course course : systemCourses) {
            Lecturer lecturer = orderedLecturers.get(index);
            course.setLecturer(lecturer);
            index++;

        }
        index = 0;
        // assign prerequisites
        for (Course course : systemCourses) {
            List<String> prerequisiteCodesString = coursesPrerequisitesCodes.get(index);
            List<Course> prerequisiteCodesList = getprerequisiteCodesList(systemCourses, prerequisiteCodesString);
            course.setPreRequisite(prerequisiteCodesList);
            index++;

        }


    }

    private List<Course> getprerequisiteCodesList(List<Course> systemCourses, List<String> prerequisiteCodesString) {
        List<Course> prerequisiteCodesList = new ArrayList<>();
        for (String courseCode : prerequisiteCodesString) {
            // get the course code string on the systemCourse and return the object
            Course course = getCourse(systemCourses, courseCode);
            prerequisiteCodesList.add(course);
        }
        return prerequisiteCodesList;

    }

    private Course getCourse(List<Course> systemCourses, String courseCode) {
        for (Course course : systemCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }

        }
        return null;
    }

    private List<Lecturer> getOrderedLecturerList(List<Lecturer> lecturers) {
        List<Lecturer> orderedLecturers = new ArrayList<>();
        for (String id : coursesLecturerIds) {
            Lecturer lecturer = getLecturer(lecturers, id);
            orderedLecturers.add(lecturer);
        }

        return orderedLecturers;


    }

    private Lecturer getLecturer(List<Lecturer> lecturers, String id) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getLecturerID().equals(id)) {
                return lecturer;
            }

        }

        return null;
    }


    private List<Lecturer> getLecturers() {
        // use the lecturerid list;
        List<Lecturer> lecturers = new ArrayList<>();
        for (Person person : personList) {
            if (person instanceof Lecturer) {
                lecturers.add((Lecturer) person);
            }
        }
        return lecturers;
    }

    private void readStaffs() {
        readAdvisors();
        readLecturers();
    }

    private void readLecturers() {
        File directoryPath = new File("iteration1/Lecturers");
        File[] fileList = directoryPath.listFiles();
        try {
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject) object;

                String name = (String) jsonObject.get("name");
                String lastName = (String) jsonObject.get("lastName");
                String lecturerId = (String) jsonObject.get("lecturerId");
                Lecturer lecturer = new Lecturer(name, lastName, lecturerId);
                personList.add(lecturer);

            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }


    private void readAdvisors() {
        File directoryPath = new File("iteration1/Advisors");
        File[] fileList = directoryPath.listFiles();
        try {
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject) object;

                String name = (String) jsonObject.get("name");
                String lastName = (String) jsonObject.get("lastName");
                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");
                String lecturerId = (String) jsonObject.get("lecturerId");
                JSONArray advisorsStudentIds = (JSONArray) jsonObject.get("Students");
                List<String> advisorsStudentIdListString = getStringList(advisorsStudentIds);
                Advisor advisor = new Advisor(name, lastName, username, password, lecturerId, new ArrayList<>());
                personList.add(advisor);
                advisorsStudentIdList.add(advisorsStudentIdListString);


            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }


    private void readStudents() {
        File directoryPath = new File("iteration1/Students");
        File[] fileList = directoryPath.listFiles();
        try {
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject) object;

                String name = (String) jsonObject.get("name");
                String lastName = (String) jsonObject.get("lastName");
                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");
                String studentID = (String) jsonObject.get("studentId");
                String advisorID = (String) jsonObject.get("advisorId");
                JSONObject transcriptJson = (JSONObject) jsonObject.get("Transcript");
                JSONArray cancelWaitingCourses = (JSONArray) jsonObject.get("cancelWaitingCourses");
                JSONArray registrationCompleteCourses = (JSONArray) jsonObject.get("registrationCompleteCourses");
                JSONArray registrationWaitingCourses = (JSONArray) jsonObject.get("registrationWaitingCourses");

                //  List<String>TranscriptString
                List<String> studentsCancelWaitingCoursesCodes = getStringList(cancelWaitingCourses);
                List<String> studentsRegistrationCompleteCoursesCodes = getStringList(registrationCompleteCourses);
                List<String> studentsRegistrationWaitingCoursesCodes = getStringList(registrationWaitingCourses);
                Transcript transcript = getTranscript(transcriptJson);

                studentsCancelWaitingCourseCodes.add(studentsCancelWaitingCoursesCodes);
                studentsRegistrationCompleteCourses.add(studentsRegistrationCompleteCoursesCodes);
                studentsRegistrationWaitingCourses.add(studentsRegistrationWaitingCoursesCodes);
                studentsAdvisorIdsList.add(advisorID);
                Student student = new Student(name, lastName, username, password, studentID, transcript, new Advisor());
                personList.add(student);


            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Course> getAvailableCourses(Student student) {
        List<Course> availableCourses = new ArrayList<>();
        Transcript transcript = student.getCurrentTranscript();
        // int semester = transcript.calculateSemesterFromCredit();
        int year = 3;
        for (Course course : systemCourses) {
            if (course.checkYearMatching(year) && course.checkCourseSection(student.getRegistrationCompleteCourses(), student.getRegistrationWaitingCourses(), student.getCancelWaitingCourses())) {
                availableCourses.add(course);

            }
        }
        return availableCourses;


    }

    private Transcript getTranscript(JSONObject transcriptJson) {
        ArrayList<String> transcriptCoursesString = (ArrayList<String>) transcriptJson.get("listOfCourses");
        ArrayList<Long> transcriptLongGrades = (ArrayList<Long>) transcriptJson.get("listOfGrades");
        ArrayList<Integer> transcriptIntegerGrades = convertLongToInteger(transcriptLongGrades);
        //  System.out.println(transcriptCoursesString);
        //System.out.println(transcriptIntegerGrades);
        ArrayList<Course> courses = new ArrayList<>();
        for (String courseCode : transcriptCoursesString) {
            Course course = getCourse(systemCourses, courseCode);
            courses.add(course);
        }
        ArrayList<Grade> grades = new ArrayList<>();
        for (int currentGrade : transcriptIntegerGrades) {
            Grade grade = new Grade(currentGrade);
            grades.add(grade);
        }
        return new Transcript(courses, grades);
    }

    private ArrayList<Integer> getIntegerList(JSONObject transcriptJson) {
        Object o = transcriptJson.get("listOfGrades");
        return new ArrayList<>();

    }

    private ArrayList<Integer> convertLongToInteger(ArrayList<Long> transcriptIntegerGrades) {
        ArrayList<Integer> integerGrades = new ArrayList<>();
        for (Long grade : transcriptIntegerGrades) {
            integerGrades.add(grade.intValue());
        }
        return integerGrades;

    }

    private void readCourses() {
        File directoryPath = new File("iteration1/Courses");
        File[] fileList = directoryPath.listFiles();
        HashMap<String, Course> Courses = new HashMap<>();
        try {
            JSONParser jsonParser = new JSONParser();
            for (File file : fileList) {
                FileReader fileReader = new FileReader(file);
                Object object = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject) object;

                // getting course attributes
                String courseCode = (String) jsonObject.get("courseCode");
                String courseName = (String) jsonObject.get("courseName");
                Long courseCredit = (Long) jsonObject.get("courseCredit");
                Long courseYear = (Long) jsonObject.get("courseYear");
                String lecturerId = (String) jsonObject.get("lecturerId");
                Long courseDay = (Long) jsonObject.get("courseDay");
                Long courseHour = (Long) jsonObject.get("courseHour");
                JSONArray prerequisitesArray = (JSONArray) jsonObject.get("prerequisites");
                List<String> currentCoursesPrerequisitesCodes = getStringList(prerequisitesArray);
                coursesPrerequisitesCodes.add(currentCoursesPrerequisitesCodes);
                coursesLecturerIds.add(lecturerId);
                Course course = new Course(courseCode, courseName, courseCredit.intValue(), courseYear.intValue(), courseDay.intValue(), courseHour.intValue(), new Lecturer(), new ArrayList<>());
                systemCourses.add(course);

            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getStringList(JSONArray prerequisitesArray) {

        List<String> prerequisiteCodeStringList = new ArrayList<>();
        Object[] prerequisitesObjects = prerequisitesArray.toArray();
        for (Object obj : prerequisitesObjects) {
            prerequisiteCodeStringList.add((String) obj);
        }
        return prerequisiteCodeStringList;
    }
}

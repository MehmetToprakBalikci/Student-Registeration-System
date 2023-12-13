import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
// Writes the test results to output:
public class TestStudentAndAdvisor {
    String getTestString(int testType, int testNum){
        String testString = null;
        if(testType == 1){
            switch(testNum){
                case 1 :  testString = "1 2 2 1 8 2";
                break;
                case 2 : testString = "1 3 2 1 8 2";
                break;
                case 3 : testString = "1 4 2 1 8 2";
                break;
                case 4 : testString = "1 5 2 1 8 2 ";
                break;
                case 5 : testString = "1 6 2 1 8 2";
                break; 
            }
        }
        else if(testType == 2){
            switch(testNum){
                case 1 :  testString = "2 2 8 2";
                break;
                case 2 : testString = "2 3 8 2";
                break;
                case 3 : testString = "2 4 8 2";
                break;
                case 4 : testString = "2 5 8 2";
                break;
                case 5 : testString = "2 6 8 2";
                break; 
            }
        }
        else if(testType == 3){
            switch(testNum){
                case 1 :  testString = "1 1 1 1 2 3 3";
                break;
                case 2 : testString = "1 1 2 1 2 3 3";
                break;
                case 3 : testString = "1 2 1 1 3 3 3";
                break;
                case 4 : testString = "1 2 2 2 3 3 3";
                break;
                case 5 : testString = "1 2 3 2 3 3 3";
                break; 
            }
        } 
        return testString;
    }
    
    @Test 
    void studentRunUserActionRegistrationWaitingCourses(){
        assertTrue(startTestProcedure(getTestString(1, 1), 1, 1));
        assertTrue(startTestProcedure(getTestString(1, 2), 1, 2));
        assertTrue(startTestProcedure(getTestString(1, 3), 1, 3));
        assertTrue(startTestProcedure(getTestString(1, 4), 1, 4));
        assertTrue(startTestProcedure(getTestString(1, 5), 1, 5));
    }
    @Test
    void studentRunUserActionCancelWaitingCourses(){
        assertTrue(startTestProcedure(getTestString(2, 1), 2, 1));
        assertTrue(startTestProcedure(getTestString(2, 2), 2, 2));
        assertTrue(startTestProcedure(getTestString(2, 3), 2, 3));
        assertTrue(startTestProcedure(getTestString(2, 4), 2, 4));
        assertTrue(startTestProcedure(getTestString(2, 5), 2, 5));
    }
    @Test 
    void AdvisorRunUserActionwaitingRegistrationSelection(){
        assertTrue(startTestProcedure(getTestString(3, 1) ,3 , 1));
        assertTrue(startTestProcedure(getTestString(3, 2) ,3 , 2));
        assertTrue(startTestProcedure(getTestString(3, 3) ,3 , 3));
        assertTrue(startTestProcedure(getTestString(3, 4) ,3 , 4));
        assertTrue(startTestProcedure(getTestString(3, 5) ,3 , 5));
    }

    private static boolean startTestProcedure(String userInpuString, int testType, int testNum) {
        Controller thisController = Controller.getInstance(getTestUserInput(userInpuString));
        
        ArrayList<Student> studentList = new ArrayList<>();
        Advisor advisor1 = new Advisor("Mustafa", "Ağaoğlu","username3","password3", "1", studentList);

        Course c1 = new Course("CSE3001", "Database Systems", 7, 3, 1, 2, advisor1, null, null,10);
        Course c2 = new Course("CSE3002", "Operating Systems", 7, 3, 1, 2, advisor1, null, null, 10);
        Course c3 = new Course("CSE3003", "OOP", 5, 3, 1, 2, advisor1, null, null, 10);
        Course c4 = new Course("CSE3004", "Digital Logic Design", 6, 3, 1, 2, advisor1, null, null, 10);
        Course c5 = new Course("CSE3005", "Simulation and Modelling", 5, 3, 1, 2, advisor1, null, null, 10);
        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(c1);
        Grade g1 = new Grade(50);
        Grade g2 = new Grade(60);
        ArrayList<Grade> gradeList = new ArrayList<>();
        gradeList.add(g1);
        Transcript t = new Transcript(courseList,gradeList);
        courseList = new ArrayList<>();
        gradeList = new ArrayList<>();
        courseList.add(c2);
        gradeList.add(g2);
        Student student1 = new Student("Arda", "Öztürk", "username1", "password1", "150121051", t, advisor1);
        Student student2 = new Student("Furkan", "Gökgöz", "username2", "password2", "150120076", t, advisor1);


        
        studentList.add(student1);
        studentList.add(student2);
        advisor1.setStudentList(studentList);
        
        if(testType == 1){
            student1.addElementToCurrentAvailableCourses(c1);
            student1.addElementToCurrentAvailableCourses(c2);
            student1.addElementToCurrentAvailableCourses(c3);
            student1.addElementToCurrentAvailableCourses(c4);
            student1.addElementToCurrentAvailableCourses(c5);
        }
        else if(testType == 2){
        student1.addElementToRegistrationCompleteCourses(c1);
        student1.addElementToRegistrationCompleteCourses(c2);
        student1.addElementToRegistrationCompleteCourses(c3);
        student1.addElementToRegistrationCompleteCourses(c4);
        student1.addElementToRegistrationCompleteCourses(c5);
        }
        else if(testType == 3){
            student1.addElementToRegistrationWaitingCourses(c1);
            student1.addElementToRegistrationWaitingCourses(c2);
            student2.addElementToRegistrationWaitingCourses(c3);
            student2.addElementToRegistrationWaitingCourses(c4);
            student2.addElementToRegistrationWaitingCourses(c5);
        }
        
        if(testType == 1 || testType == 2)
            student1.startActions(thisController);
        else if(testType == 3){
            advisor1.startActions(thisController);
        }
         boolean testPass = false;
         if (testType == 1) {
            switch(testNum){
                case 1 :  testPass = student1.removeElementFromRegistrationWaitingCourses(c1);
                break;
                case 2 : testPass = student1.removeElementFromRegistrationWaitingCourses(c2);
                break;
                case 3 : testPass = student1.removeElementFromRegistrationWaitingCourses(c3);
                break;
                case 4 : testPass = student1.removeElementFromRegistrationWaitingCourses(c4);
                break;
                case 5 : testPass = student1.removeElementFromRegistrationWaitingCourses(c5);
                break; 
            }
        }
        else if(testType == 2){
            switch(testNum){
                case 1 :  testPass = student1.removeElementFromCancelWaitingCourses(c1);
                break;
                case 2 : testPass = student1.removeElementFromCancelWaitingCourses(c2);
                break;
                case 3 : testPass = student1.removeElementFromCancelWaitingCourses(c3);
                break;
                case 4 : testPass = student1.removeElementFromCancelWaitingCourses(c4);
                break;
                case 5 : testPass = student1.removeElementFromCancelWaitingCourses(c5);
                break; 
            }
        }
        else if(testType == 3){
            switch(testNum){
                case 1 :  testPass = student1.removeElementFromRegistrationCompleteCourses(c1);
                break;
                case 2 : testPass = student1.removeElementFromRegistrationCompleteCourses(c2);
                break;
                case 3 : testPass = student2.removeElementFromRegistrationCompleteCourses(c3);
                break;
                case 4 : testPass = student2.removeElementFromCurrentAvailableCourses(c4);
                break;
                case 5 : testPass = student2.removeElementFromCurrentAvailableCourses(c5);
                break; 
            }
        }
        deleteFile();
        return testPass;
    }

    private static void deleteFile() {
        File file = new File("testInput.txt");
        file.delete();
    }

    private static Scanner getTestUserInput(String consoleInputString) {
        Scanner newScanner = null;
        File file = new File("testInput.txt");
        try {
            file.createNewFile();
            FileWriter myWriter = new FileWriter("testInput.txt");
            myWriter.write(consoleInputString);
            newScanner = new Scanner(file);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return newScanner;
    }
        
}

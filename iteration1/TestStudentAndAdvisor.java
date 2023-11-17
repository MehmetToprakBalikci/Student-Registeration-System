import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestStudentAndAdvisor {
    static FileWriter testOutputFile;
    public static void main(String args[]){
        try {
            File file = new File("testOutputFile.txt");
            file.createNewFile();
            testOutputFile = new FileWriter("testOutputFile.txt");
        
            testOutputFile.write("Testing class Student runUserAction : registrationWaitingCourses part for StudentTest...\n"); 
        for(int i = 1; i <= 5; i++){
            startTestProcedure(1, i);
        }
        testOutputFile.write("Testing class Student runUserAction : cancelWaitingCourses part for StudentTest...\n");
        for(int i = 1; i <= 5; i++){
            startTestProcedure(2, i);
        }
        testOutputFile.write("Testing class Advisor runUserAction : Course waitingRegistration Selection Part...\n");
        for(int i = 1; i <= 5; i++){
            startTestProcedure(3, i);
        }
        testOutputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startTestProcedure(int testType,int testNum) {
        Controller thisController = new Controller(getTestUserInput(testType, testNum));
        
        ArrayList<Student> studentList = new ArrayList<>();
        Advisor advisor1 = new Advisor("Mustafa", "Ağaoğlu","username3","password3", "1", studentList);

        Course c1 = new Course("CSE3001", "Database Systems", 7, 3, 1, 2, advisor1, null);
        Course c2 = new Course("CSE3002", "Operating Systems", 7, 3, 1, 2, advisor1, null);
        Course c3 = new Course("CSE3003", "OOP", 5, 3, 1, 2, advisor1, null);
        Course c4 = new Course("CSE3004", "Digital Logic Design", 6, 3, 1, 2, advisor1, null);
        Course c5 = new Course("CSE3005", "Simulation and Modelling", 5, 3, 1, 2, advisor1, null);
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
        
        checkResult(testPass, testNum);
        deleteFile();
    }

    private static void checkResult(boolean testPass, int testNum) {
        if(testPass){
            try {
                testOutputFile.write("Test " + testNum + " passed!\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
           try {
            testOutputFile.write("Test " + testNum + " failed!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

    private static void deleteFile() {
        File file = new File("testInput.txt");
        file.delete();
    }

    private static Scanner getTestUserInput(int testType,int testNum) {
        String testString = null;
        if(testType == 1){
            switch(testNum){
                case 1 :  testString = "1 2 7";
                break;
                case 2 : testString = "1 3 7";
                break;
                case 3 : testString = "1 4 7";
                break;
                case 4 : testString = "1 5 7";
                break;
                case 5 : testString = "1 6 7";
                break; 
            }
        }
        else if(testType == 2){
            switch(testNum){
                case 1 :  testString = "2 2 7";
                break;
                case 2 : testString = "2 3 7";
                break;
                case 3 : testString = "2 4 7";
                break;
                case 4 : testString = "2 5 7";
                break;
                case 5 : testString = "2 6 7";
                break; 
            }
        }
        else if(testType == 3){
            switch(testNum){
                case 1 :  testString = "1 1 1 1 2 3 2";
                break;
                case 2 : testString = "1 1 2 1 2 3 2";
                break;
                case 3 : testString = "1 2 1 1 3 3 2";
                break;
                case 4 : testString = "1 2 2 2 3 3 2";
                break;
                case 5 : testString = "1 2 3 2 3 3 2";
                break; 
            }
        }
        Scanner newScanner = null;
        File file = new File("testInput.txt");
        try {
            file.createNewFile();
            FileWriter myWriter = new FileWriter("testInput.txt");
            myWriter.write(testString);
            newScanner = new Scanner(file);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return newScanner;
    }
        
}

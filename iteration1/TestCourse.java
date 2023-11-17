import java.util.ArrayList;
import java.util.List;

public class TestCourse {
    public static void main(String[] args) {
        System.out.printf("Testing Course.java\n");

        Lecturer lecturer1 = new Lecturer("Omer","Korcak", "1000");
        Lecturer lecturer2 = new Lecturer("Betul","Boz", "1001");
        Lecturer lecturer3 = new Lecturer("Mustafa","Agaoglu", "1002");
        List<Course> preRequisites1 = new ArrayList<>();
        Course course1 = new Course("CS101","Introduction to Computer Science", 3,1, 3, 5, lecturer1,preRequisites1);
        Course course2 = new Course("CS102","Computer Programming 1", 5,2, 3, 5, lecturer2,preRequisites1);
        List<Course> preRequisites2 = new ArrayList<>();
        preRequisites2.add(course2);
        Course course3 = new Course("CS103","Computer Programming 2", 4,3, 1, 4, lecturer3,preRequisites2);
        List<Course> preRequisites3 = new ArrayList<>();
        preRequisites2.add(course3);
        Course course4 = new Course("CS104","Principal of Programming Languages", 3,4, 4, 5, lecturer3,preRequisites3);
        Course course5 = new Course("CS105","Systems Programming", 4,4, 2, 8, lecturer1,preRequisites1);

        System.out.println("\nTesting checkYearMatching() function...");
        if(course1.checkYearMatching(1)){
            System.out.println("Test 1 passed");
        }
        else{
            System.out.println("Test 1 failed");
        }

        List<Course> courses1 = new ArrayList<>();
        courses1.add(course1);
        courses1.add(course3);
        List<Course> courses2 = new ArrayList<>();
        courses2.add(course4);
        List<Course> courses3 = new ArrayList<>();

        System.out.println("\nTesting checkCourseSection() function...");
        if( ! course2.checkCourseSection(courses1,courses3,courses2)){
            System.out.println("Test 2 passed");
        }
        else{
            System.out.println("Test 2 failed");
        }

        if(course5.checkCourseSection(courses1,courses3,courses2)){
            System.out.println("Test 3 passed");
        }
        else{
            System.out.println("Test 3 failed");
        }

        System.out.println("\nTesting checkPreRequisite() function...");
        if(course4.checkPreRequisite(courses1)){
            System.out.println("Test 4 passed");
        }
        else{
            System.out.println("Test 4 failed");
        }

        System.out.println("\nTesting toStringFormatted() function...");
        if(course1.toStringFormatted(1).equals("CS101 Introduction to Computer Science")){
            System.out.println("Test 5 passed");
        }
        else{
            System.out.println("Test 5 failed");
        }
    }
}
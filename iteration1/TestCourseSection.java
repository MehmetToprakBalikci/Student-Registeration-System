public class TestCourseSection {
    public static void main(String[] args) {
        System.out.printf("Testing CourseSection.java\n");

        CourseSection section1 = new CourseSection(1,1);
        CourseSection section2 = new CourseSection(1,1);

        System.out.println("\nTesting compareAvailability() function...");
        if( ! section1.compareAvailability(section2)){
            System.out.println("Test 1 passed");
        }
        else{
            System.out.println("Test 1 failed");
        }

        CourseSection section3 = new CourseSection(2,7);

        if(section1.compareAvailability(section3)){
            System.out.println("Test 2 passed");
        }
        else{
            System.out.println("Test 2 failed");
        }

        System.out.println("\nTesting getCourseSectionString() function...");
        if(section1.getCourseSectionString().equals("Monday 8.30")){
            System.out.println("Test 3 passed");
        }
        else{
            System.out.println("Test 3 failed");
        }

        CourseSection section4 = new CourseSection(7,15);

        if(section4.getCourseSectionString().equals("Invalid Day Invalid Hour")){
            System.out.println("Test 4 passed");
        }
        else{
            System.out.println("Test 4 failed");
        }

        if(section3.getCourseSectionString().equals("Tuesday 15.00")){
            System.out.println("Test 5 passed");
        }
        else{
            System.out.println("Test 5 failed");
        }
    }
}

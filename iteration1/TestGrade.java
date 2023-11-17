public class TestGrade {
    public static void main(String[] args) {
        System.out.printf("Testing Grade.java\n");

        Grade grade1 = new Grade(95);

        System.out.println("\nTesting getGradeLetter() function...");
        if(grade1.getGradeLetter().equals("AA")){
            System.out.println("Test passed for 1.grade");
        }
        else{
            System.out.println("Test failed for 1.grade");
        }

        Grade grade2 = new Grade(55);
        if(grade2.getGradeLetter().equals("CC")){
            System.out.println("Test passed for 2.grade");
        }
        else{
            System.out.println("Test failed for 2.grade");
        }

        Grade grade3 = new Grade(0);
        if(grade3.getGradeLetter().equals("FF")){
            System.out.println("Test passed for 3.grade");
        }
        else{
            System.out.println("Test failed for 3.grade");
        }

        Grade grade4 = new Grade(110);
        if(grade4.getGradeLetter().equals("Invalid Grade")){
            System.out.println("Test passed for 4.grade");
        }
        else{
            System.out.println("Test failed for 4.grade");
        }

        Grade grade5 = new Grade(-50);
        if(grade5.getGradeLetter().equals("Invalid Grade")){
            System.out.println("Test passed for 5.grade");
        }
        else{
            System.out.println("Test failed for 5.grade");
        }
    }
}

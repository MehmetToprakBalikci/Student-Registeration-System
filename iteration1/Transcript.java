import java.util.ArrayList;
import java.util.List;

//Transcript class for each student
//Keeps course list and grade list
public class Transcript {

    //Copy constructor,
    //Constructor to be used with an already existing Transcript Object
    private Transcript(Transcript transcript) {
        this.listOfCourses = transcript.listOfCourses;
        this.listOfGrades = transcript.listOfGrades;
        this.studentCredits = transcript.studentCredits;
    }

    //Constructor with parameters
    private Transcript(List<Course> listOfCourses, List<Grade> listOfGrades, int studentCredits) {
        this.listOfCourses = listOfCourses;
        this.listOfGrades = listOfGrades;
        this.studentCredits = studentCredits;
    }

    private List<Course> listOfCourses;
    private List<Grade> listOfGrades;
    private int studentCredits;


    public int calculateCredit() {
        int totalCredit = 0;

        for(int i = 0; i < listOfCourses.size(); i++) {
            totalCredit += listOfCourses.get(i).getCourseCredit();
        }
        return totalCredit;
    }


    //Method to calculate current gpa
    public float calculateGPA() {
        float gpa = 0;

        List<Integer> weightedValues = null;

        //Calculate Weighted Values
        for(int i = 0; i < listOfCourses.size(); i++) {
            int credit = listOfCourses.get(i).getCourseCredit();
            int grade = listOfGrades.get(i).getNumericalGrade();
            weightedValues.add(credit * grade);
        }

        int totalWeightedValues = 0;
        for(int i = 0; i < listOfCourses.size(); i++) {
            totalWeightedValues += weightedValues.get(i);
        }

        //calculate gpa
        gpa = (float)(totalWeightedValues) / ((float)(this.calculateCredit()) * 25);

        return gpa;
    }

    //calculate semester depending on the current credit
    //RIGHT NOW USES A PREDEFINED VALUE FOR EACH SEMESTERS WORTH!!
    public int calculateSemesterFromCredit() {
        int creditPreSemester = 26, semesterLimit = 16;
        int semester = 0;
        int completedCredits = this.calculateCredit();


        //used for instead of switch for future compatibility
        for(int i = 0; i < semesterLimit; i++) {
            if(completedCredits >= creditPreSemester) {
                semester++;
                completedCredits -= creditPreSemester;
            }
        }

        return semester;
    }


    //TODO
    public void getTranscriptString() {
        System.out.println("Cumulative Gpa: " + this.calculateGPA());
        System.out.println("Cumulative Credits: " + this.calculateGPA());
        System.out.println("Current Semester: " + this.calculateSemesterFromCredit());
    }

}

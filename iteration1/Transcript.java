import java.util.ArrayList;
import java.util.List;

//Transcript class for each student
//Keeps course list and grade list
public class Transcript {

    //Copy constructor,
    //Constructor to be used with an already existing Transcript Object
    public Transcript(Transcript transcript) {
        this.listOfCourses = transcript.listOfCourses;
        this.listOfGrades = transcript.listOfGrades;
        this.studentCredits = transcript.studentCredits;
    }

    //Constructor with parameters
    public Transcript(List<Course> listOfCourses, List<Grade> listOfGrades) {
        this.listOfCourses = listOfCourses;
        this.listOfGrades = listOfGrades;
        this.studentCredits = calculateCredit();
    }

    private List<Course> listOfCourses;
    private List<Grade> listOfGrades;
    private int studentCredits;


    public int calculateCredit() {
        int totalCredit = 0;

        if (listOfCourses.isEmpty()) {
            System.out.println("No Courses For Student Found!");
            return -1;
        }

        for(int i = 0; i < listOfCourses.size(); i++) {
            totalCredit += listOfCourses.get(i).getCourseCredit();
        }
        return totalCredit;
    }


    //Method to calculate current gpa
    public float calculateGPA() {
        if (listOfCourses.isEmpty()) {
            System.out.println("No Courses For Student Found!");
            return -1;
        }
        if (listOfGrades.isEmpty()) {
            System.out.println("No Grades For Student Found!");
            return -1;
        }

        float gpa = 0;

        List<Integer> weightedValues = new ArrayList<>();

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

    @Override
    public String toString() {
        float gpa = this.calculateGPA();
        int credits = this.calculateCredit();
        int semester = this.calculateSemesterFromCredit();


        //adjust digit precision
        int digitPrecision = 2;
        int temp = (int) (gpa * Math.pow(10, digitPrecision));
        gpa = (float)((float) temp / Math.pow(10, digitPrecision));


        return "Cumulative Gpa: " + gpa + "\nCumulative Credits: " + credits +
                "\nCurrent Semester: " + semester;
    }
    public String[] getStudentTranscriptStringList(){
        int courseCount = listOfGrades.size();
        String[] transcriptString = new String[courseCount + 1];
        transcriptString[0] = this.toString();
        for(int i = 1; i < courseCount; i++){
            transcriptString[i] = i + "-)" + listOfCourses.get(i).toString() + listOfGrades.get(i).toString();
        }
        return transcriptString;
    }
}

import java.util.ArrayList;
import java.util.List;

//Transcript class for each student
//Keeps course list and grade list
public class Transcript {
    private List<Course> listOfCourses;
    private List<Grade> listOfGrades;
    private int studentCredits;
    private int studentYear;
    private double GPA;

    
    public Transcript() {

    }
    
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
        this.GPA = calculateGPA();
        this.studentYear = calculateYear();
    }

    
    public boolean checkPassedCourses(Course checkedCourse) {
        boolean isAvailable = true;
        int i = 0;
        for (Course currentCourse1 : listOfCourses) {
            if (currentCourse1.equals(checkedCourse)) {
                //if it finds the course then checks whether it is retakable or not then it returns false or true immediately
                isAvailable = listOfGrades.get(i).isRetakableGrade();
                break;
            }
            i++;
        }
        return isAvailable;
    }

    private int calculateYear() {
        int year = 1;
        if (studentCredits >= 180) {
            year = 4;
        } else if (studentCredits >= 120) {
            year = 3;
        } else if (studentCredits >= 60) {
            year = 2;
        }
        if (year != 4 && GPA >= 3) {
            year++;
        }
        return year;
    }


    public int getYear() {
        return this.studentYear;
    }

    public int calculateCredit() throws ArrayIndexOutOfBoundsException {
        int totalCredit = 0;

        if (listOfCourses.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("No Courses For Student Found!");
        }
        int i = 0;
        for (Course listOfCourse : listOfCourses) {
            if (listOfGrades.get(i).getNumericalGrade() >= 35) {
                totalCredit += listOfCourse.getCourseCredit();
            }
            i++;

        }
        return totalCredit;
    }


    //Method to calculate current gpa
    public float calculateGPA() throws ArrayIndexOutOfBoundsException {
        float gpa;
        if (listOfCourses.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("No Courses For Student Found!");
        }
        if (listOfGrades.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("No Grades For Student Found!");
        }


        List<Integer> weightedValues = new ArrayList<>();
        int totalCredit = 0;
        //Calculate Weighted Values
        for (int i = 0; i < listOfCourses.size(); i++) {
            int credit = listOfCourses.get(i).getCourseCredit();
            int grade = listOfGrades.get(i).getNumericalGrade();
            weightedValues.add(credit * grade);
            totalCredit += credit;
        }

        int totalWeightedValues = 0;
        for (int i = 0; i < listOfCourses.size(); i++) {
            totalWeightedValues += weightedValues.get(i);
        }

        //calculate gpa 
        gpa = (float) (totalWeightedValues) / ((float) (totalCredit) * 25);

        return gpa;
    }

    public List<Course> getListOfCourses() {
        return listOfCourses;
    }
    public List<Grade> getListOfGrades() {
        return listOfGrades;
    }

    //calculate semester depending on the current credit
    //RIGHT NOW USES A PREDEFINED VALUE FOR EACH SEMESTERS WORTH!!
    //Moved to calculateYear()
    /*  public int calculateSemesterFromCredit() {
        int creditPreSemester = 3, semesterLimit = 16;
        int semester = 1;
        int completedCredits = this.calculateCredit();


        //used for instead of switch for future compatibility
        for (int i = 0; i < semesterLimit; i++) {
            if (completedCredits >= creditPreSemester) {
                semester++;
                completedCredits -= creditPreSemester;
            }
        }

        return semester;
    } */

    @Override
    public String toString() {
        String s = null;
        try {
            float gpa = this.calculateGPA();
            int credits = this.calculateCredit();
            int year = this.calculateYear();


            //adjust digit precision
            int digitPrecision = 2;
            int temp = (int) (gpa * Math.pow(10, digitPrecision));
            gpa = (float) ((float) temp / Math.pow(10, digitPrecision));


            s = "Cumulative Gpa: " + gpa + "\nCumulative Credits: " + credits +
                    "\nCurrent Year: " + year;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }
        return s;
    }

    public String[] getStudentTranscriptStringList() {
        int courseCount = listOfGrades.size();
        String[] transcriptString = new String[courseCount + 1];
        transcriptString[0] = this.toString();
        for (int i = 1; i < courseCount + 1; i++) {
            transcriptString[i] = i + "-)" + listOfCourses.get(i - 1).toString() + " " + listOfGrades.get(i - 1).toString();
        }
        return transcriptString;
    }
}

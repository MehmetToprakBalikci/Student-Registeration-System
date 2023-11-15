import java.util.List;

public class Transcript {

    //Copy constructor,
    //Constructor to be used with an already existing Transcript Object
    private Transcript(Transcript transcript) {
        this.listOfCourses = transcript.listOfCourses;
        this.listOfGrades = transcript.listOfGrades;
        this.studentCredits = transcript.studentCredits;
    }

    private List<Course> listOfCourses;
    private List<Grade> listOfGrades;
    private int studentCredits;

}

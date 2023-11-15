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


    //TODO
    public void calculateTranscriptFromCourseInside() {

    }
    //TODO
    public void getTranscriptString() {

    }

}

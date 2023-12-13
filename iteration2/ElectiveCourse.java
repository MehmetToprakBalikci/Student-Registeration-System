import java.util.List;

public class ElectiveCourse extends Course{
    private final String TYPE;






    public ElectiveCourse(String courseCode, String courseName, int courseCredit, int courseYear, int section1, int section2, Lecturer lecturer, List<Course> preRequisite, String type) {
        super(courseCode, courseName, courseCredit, courseYear, section1, section2, lecturer, preRequisite);
        this.TYPE = type;
    }

}

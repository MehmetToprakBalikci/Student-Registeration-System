import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// CourseSection class
class CourseSection {
    private int dayNumber;
    private int sectionNumber;

    // Constructor
    public CourseSection(int dayNumber, int sectionNumber) {
        this.dayNumber = dayNumber;
        this.sectionNumber = sectionNumber;
    }

    // Compare to see if this course section is available or not
    public boolean compareAvailability(CourseSection sectionToCompare){
        if (this.dayNumber == sectionToCompare.dayNumber && this.sectionNumber == sectionToCompare.sectionNumber) {
            return false;
        } else {
            return true;
        }
    }

    public String getCourseSectionString(){
        return "";
    }
}
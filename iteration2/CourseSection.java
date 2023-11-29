

// CourseSection class
class CourseSection {
    public int getDayNumber() {
        return dayNumber;
    }

    private int dayNumber;

    public int getSectionNumber() {
        return sectionNumber;
    }

    private int sectionNumber;

    // Constructor
    public CourseSection(int dayNumber, int sectionNumber) {
        this.dayNumber = dayNumber;
        this.sectionNumber = sectionNumber;
    }

    // Compare to see if this course section is available or not
    public boolean compareAvailability(CourseSection sectionToCompare){
        return !(this.dayNumber == sectionToCompare.dayNumber && this.sectionNumber == sectionToCompare.sectionNumber);
    }

    public String getCourseSectionString(){
        String day = "";
        String hour = "";

        if (dayNumber == 1) {
            day = "Monday";
        } else if (dayNumber == 2) {
            day = "Tuesday";
        } else if (dayNumber == 3) {
            day = "Wednesday";
        } else if (dayNumber == 4) {
            day = "Thursday";
        } else if (dayNumber == 5) {
            day = "Friday";
        } else {
            day = "Invalid Day";
        }

        // 8.30  9.30  10.30  11.30  13  14  15  16  17  18
        // 1     2     3      4      5   6   7   8   9   10
        if (sectionNumber >= 1 && sectionNumber <= 4) {
            hour = String.format("%d.30", sectionNumber + 7);
        } else if(sectionNumber < 15){
            hour = String.format("%d.00", sectionNumber + 8);
        }
        else {
            hour = "Invalid Hour";
        }


        return day + " " + hour;
    }
}
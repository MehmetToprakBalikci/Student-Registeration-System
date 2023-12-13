import java.util.ArrayList;
import java.util.List;


// Course class
class Course {
    private final String COURSE_CODE;
    private final String COURSE_NAME;
    private final int COURSE_CREDIT;
    private int capacity;
    private int numberOfStudents;

    private String type;

    public int getCourseYear() {
        return COURSE_YEAR;
    }

    private final int COURSE_YEAR;
    private final CourseSection SECTION;
    private Lecturer lecturer;
    private Assistant assistant;
    private List<Course> preRequisite;

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public List<Course> getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(List<Course> preRequisite) {
        this.preRequisite = preRequisite;
    }

    // Constructor
    public Course(String courseCode, String courseName, int courseCredit, int courseYear, int section1, int section2, Lecturer lecturer, List<Course> preRequisite) {
        this.COURSE_CODE = courseCode;
        this.COURSE_NAME = courseName;
        this.COURSE_CREDIT = courseCredit;
        this.COURSE_YEAR = courseYear;
        this.SECTION = new CourseSection(section1, section2);
        this.lecturer = lecturer;
        this.preRequisite = preRequisite;
        setType("n");
    }

    public Course(String courseCode, String courseName, int courseCredit, int courseYear, int section1, int section2, Lecturer lecturer, Assistant assistant, List<Course> preRequisite) {
        this.COURSE_CODE = courseCode;
        this.COURSE_NAME = courseName;
        this.COURSE_CREDIT = courseCredit;
        this.COURSE_YEAR = courseYear;
        this.assistant = assistant;
        this.SECTION = new CourseSection(section1, section2);
        this.lecturer = lecturer;
        this.preRequisite = preRequisite;
        setType("n");
    }

    public Course(String courseCode, String courseName, int courseCredit, int courseYear, int section1, int section2, Lecturer lecturer, Assistant assistant, List<Course> preRequisite, int capacity) {
        this.COURSE_CODE = courseCode;
        this.COURSE_NAME = courseName;
        this.COURSE_CREDIT = courseCredit;
        this.COURSE_YEAR = courseYear;
        this.SECTION = new CourseSection(section1, section2);
        this.lecturer = lecturer;
        this.preRequisite = preRequisite;
        this.capacity = capacity;
        setType("n");
    }


    // Check if this student in the appropriate semester to take this course?
    public boolean checkYearMatching(int year) {
        return COURSE_YEAR <= year;
    }

    // Check if this course conflicts with any other courses section returns 0 for available course
    public String checkCourseSection(List<Course> registrationCompleteCourses, List<Course> registrationWaitingCourses, List<Course> cancelWaitingCourses) {
        for (Course course : registrationCompleteCourses) {
            if (this.equals(course)) {
                return "This course:(" + course.toString() + ")" + "already exist in completedRegistrationCourses.Try to select another course";
            }
            if (!course.getSection().compareAvailability(this.SECTION)) {
                return "This course is conflicting with the time of the course : " + course.toString() + " inside your courses that are registered!";
            }
        }
        for (Course course : registrationWaitingCourses) {
            if (this.equals(course)) {
                return "This course:(" + course.toString() + ")" + " already exist in registrationWaitingCourses.Try to select another course";
            }
            if (!course.getSection().compareAvailability(this.SECTION)) {
                return "This course is conflicting with the time of the course : " + course.toString() + " inside your courses that are waiting to be registered!";
            }
        }
        for (Course course : cancelWaitingCourses) {
            if (this.equals(course)) {
                return "This course:(" + course.toString() + ")" + " already exist in cancelWaitingCourses.Try to select another course";
            }
            if (!course.getSection().compareAvailability(this.SECTION)) {
                return "This course is conflicting with the time of the course : " + course.toString() + " inside your courses that are waiting to be canceled!";
            }
        }
        if (isFull()) {
            return "this course's capacity full!.";
        }
        return null;
    }


    public boolean equals(Course course2) {
        return course2 != null && this.COURSE_NAME.equals(course2.getCourseName());
    }

    // Check if given student can take this course, according to prerequisite and given completed courses
    public boolean checkPreRequisite(List<Course> completedCourses, List<Grade> gradeList) {
        if (preRequisite == null) {
            return true;
        }
        if (preRequisite.isEmpty()) {
            return true;
        }
        List<Course> copy = new ArrayList<>(preRequisite);

        for (Course course : completedCourses) {
            for (int i = 0; i < copy.size(); i++) {
                Course preRequisiteCurrent = copy.get(i);
                if (course.equals(preRequisiteCurrent) && gradeList.get(i).isPassedGrade()) {
                    copy.remove(preRequisiteCurrent);
                }
            }
            if (copy.isEmpty()) {
                return true;
            }
        }

        return false;
    }

    // Returns course information, code and name
    public String toString() {
        return COURSE_CODE + " " + COURSE_NAME;
    }

    public String courseStatus() {
        return "NumberOfStudent: " + numberOfStudents + " " + "Capacity: " + capacity;
    }

    public int getCourseCredit() {
        return COURSE_CREDIT;
    }

    public String getCourseCode() {
        return COURSE_CODE;
    }

    public CourseSection getSection() {
        return SECTION;
    }

    public String getCourseName() {
        return COURSE_NAME;
    }

    public void increaseStudentNumber() {
        numberOfStudents++;
    }

    public boolean isFull() {
        return numberOfStudents >= capacity;
    }

    public void decreaseStudentNumber() {
        numberOfStudents--;
    }

    public void setType(String type) {
        switch (type) {
            case "t" -> this.type = "t";
            case "nt" -> this.type = "nt";
            case "n" -> this.type = "n";
            default -> System.out.println("Type does not exist! instead try:\nt, nt or n.");
        }
    }
}
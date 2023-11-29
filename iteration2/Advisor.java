import java.util.ArrayList;

class Advisor extends Lecturer implements User {
    private ArrayList<Student> studentList;

    // Constructor
    public Advisor(String name, String lastName, String username, String password, String lecturerID, ArrayList<Student> studentList) {
        super(name, lastName, username, password, lecturerID);
        this.studentList = new ArrayList<>();
    }

    public Advisor() {

    }

    // Get advisor information as a String
    @Override
    public String toString() {
        return "Advisor: " + this.name + " " + this.lastName;
    }

    // Placeholder for starting advisor actions
    @Override
    public void startActions(Controller controller) {
        while (true) {
            String[] actionList = getActionList();
            int actionNumber = controller.printListReturnSelection(actionList, -1);
            if (actionNumber != 2) {
                runUserAction(actionNumber, controller);
            } else return;
        }
    }

    // First menu for advisor
    @Override
    public String[] getActionList() {

        String[] actionList = new String[3];

        actionList[0] = "Select an action.";
        actionList[1] = "1) See students.";
        actionList[2] = "2) Log out.";

        return actionList;
    }

    // Further menu's and actions
    @Override
    public void runUserAction(int actionNumber, Controller controller) {
        // Student selection part
        switch (actionNumber) {

            case 1:


                while (true) {

                    int studentSize = studentList.size();
                    String[] studentMenuList = new String[studentSize + 2];

                    studentMenuList[0] = "Select a student for action.";
                    studentMenuList[studentSize + 1] = studentSize + 1 + ") Go back.";

                    for (int i = 1; i <= studentSize; i++) {

                        studentMenuList[i] = i + ") " + studentList.get(i - 1).toString();
                    }

                    actionNumber = controller.printListReturnSelection(studentMenuList, -1);
                    if (actionNumber == studentSize + 1) {    // If action is go back
                        return;
                    }
                    Student selectedStudent = studentList.get(actionNumber - 1);

                    boolean controlFlag = true;
                    while (controlFlag) {
                        // Course selection part
                        int registrationWaitingCoursesSize = selectedStudent.getRegistrationWaitingCourses().size();
                        int cancelWaitingCoursesSize = selectedStudent.getCancelWaitingCourses().size();

                        String[] courseMenuList = new String[registrationWaitingCoursesSize + cancelWaitingCoursesSize + 2];
                        courseMenuList[0] = selectedStudent.toString() + "\nChoose a course for action.";    // Title for course list
                        courseMenuList[courseMenuList.length - 1] = courseMenuList.length - 1 + ") Go back.";


                        for (int j = 1; j <= registrationWaitingCoursesSize; j++) {
                            if (j == 1) {
                                courseMenuList[j] = "Registration Waiting Courses:\n" + j + ") " + selectedStudent.getRegistrationWaitingCourses().get(j - 1).toString();
                            } else {
                                courseMenuList[j] = j + ") " + selectedStudent.getRegistrationWaitingCourses().get(j - 1).toString();
                            }
                        }

                        for (int j = registrationWaitingCoursesSize + 1; j < courseMenuList.length - 1; j++) {
                            if (j == registrationWaitingCoursesSize + 1) {
                                courseMenuList[j] = "Cancel Waiting Courses:\n" + j + ") " + selectedStudent.getCancelWaitingCourses().get(j - 1 - registrationWaitingCoursesSize).toString();
                            } else {
                                courseMenuList[j] = j + ") " + selectedStudent.getCancelWaitingCourses().get(j - 1 - registrationWaitingCoursesSize).toString();
                            }
                        }


                        actionNumber = controller.printListReturnSelection(courseMenuList, -1);
                        Course selectedCourse;

                        if (actionNumber == courseMenuList.length - 1) {        // Go back
                            controlFlag = false;
                            continue;
                        } else if (actionNumber <= registrationWaitingCoursesSize) {
                            selectedCourse = selectedStudent.getRegistrationWaitingCourses().get(actionNumber - 1);    // Get registration waiting course
                        } else {
                            selectedCourse = selectedStudent.getCancelWaitingCourses().get(actionNumber - registrationWaitingCoursesSize - 1);    // Get cancel waiting course
                        }


                        // Course Action Part
                        String[] courseActionMenuList = new String[3];

                        if (actionNumber <= registrationWaitingCoursesSize) {    // Registration waiting course
                            courseActionMenuList[0] = selectedStudent.toString() + "\n" + selectedCourse.toString();
                            courseActionMenuList[1] = "1) Accept registration request.";
                            courseActionMenuList[2] = "2) Reject registration request.";

                            if (controller.printListReturnSelection(courseActionMenuList, -1) == 1) {    // Accept registration
                                selectedStudent.removeElementFromRegistrationWaitingCourses(selectedCourse);
                                selectedStudent.addElementToRegistrationCompleteCourses(selectedCourse);
                            } else {
                                selectedStudent.removeElementFromRegistrationWaitingCourses(selectedCourse);
                                selectedStudent.addElementToCurrentAvailableCourses(selectedCourse);
                            }
                        } else if (actionNumber < courseMenuList.length - 1) {        // Cancel waiting course
                            courseActionMenuList[0] = selectedStudent.toString() + "\n" + selectedCourse.toString();
                            courseActionMenuList[1] = "1) Accept cancel request.";
                            courseActionMenuList[2] = "2) Reject cancel request.";

                            if (controller.printListReturnSelection(courseActionMenuList, -1) == 1) {
                                selectedStudent.removeElementFromCancelWaitingCourses(selectedCourse);
                                selectedStudent.addElementToCurrentAvailableCourses(selectedCourse);
                            } else {
                                selectedStudent.removeElementFromCancelWaitingCourses(selectedCourse);
                                selectedStudent.addElementToRegistrationCompleteCourses(selectedCourse);
                            }
                        }


                    }
                }

                // For further implementations
            case 2:
                break;
        }
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

}
    
    



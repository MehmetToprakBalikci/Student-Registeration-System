import java.util.ArrayList;
import java.util.List;


//for iteration 2, the default constructor will be updated to include "sentMEssages" and "receivedMessages" lists.
//also, at the bottom, there will be two new methods: sendMessage and receiveMessage.

// Student class
class Student extends Person implements User {


    private List<Course> currentAvailableCourses; // Courses that the student is able to register
    private List<Course> registrationWaitingCourses; //Courses that are waiting to be registered
    private List<Course> registrationCompleteCourses; // Courses that finished registration
    private List<Course> cancelWaitingCourses; // Courses that are waiting to be canceled
    private final Transcript CURRENT_TRANSCRIPT;
    private String userName;
    private String password;
    private final String STUDENT_ID; // Additional field for student ID
    private Advisor currentAdvisor;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;


    // Constructor
    public Student(String name, String lastName, String username, String password, String studentID, Transcript currentTranscript, Advisor currentAdvisor) {
        super(name, lastName);
        this.userName = username;
        this.password = password;
        this.STUDENT_ID = studentID;
        this.CURRENT_TRANSCRIPT = currentTranscript;
        this.currentAdvisor = currentAdvisor;
        this.currentAvailableCourses = new ArrayList<>();
        this.registrationCompleteCourses = new ArrayList<>();
        this.cancelWaitingCourses = new ArrayList<>();
        this.registrationWaitingCourses = new ArrayList<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

    @Override
    public String[] getActionList() {

        String[] actionList = new String[9];

        actionList[0] = "\nSelect an action.";
        actionList[1] = "1) Check courses available to register.";
        actionList[2] = "2) Check your registered courses.";
        actionList[3] = "3) See courses waiting to be registered.";
        actionList[4] = "4) See courses waiting to be canceled.";
        actionList[5] = "5) User transcript.";
        actionList[6] = "6) Advisor information.";
        actionList[7] = "7) See messages.";
        actionList[8] = "8) Log out.";

        return actionList;
    }

    @Override
    public String toString() {

        return "Name: " + this.getFirstName() + ", Last Name: " + this.getLastName() + ", ";
    }


    @Override
    public void startActions(Controller controller) {
        String[] actionList = getActionList();
        int actionNumber;
        final int maxAction = 8;
        do {
            actionNumber = controller.printListReturnSelection(actionList, -1);
            runUserAction(actionNumber, controller);
        } while (actionNumber != maxAction);

    }


    @Override
    public void runUserAction(int actionNumber, Controller controller) {
        // Student selection part
        int currentUserSelection;
        Course currentCourse;
        switch (actionNumber) {
         /*   String[] courseInfoString = new String[6];
            courseInfoString[0] = "Select your course action";
            courseInfoString[1] = "1-)Return back to available course menu page";
            courseInfoString[2] = "2-)register to  the course";
            courseInfoString[3] = "3-)See your course's Lecturer";
            courseInfoString[4] = "4-)See your course's advisor";
            courseInfoString[5] = "5-) Return back to first menu page"; */
            case 1:
                currentUserSelection = controller.printListReturnSelection(
                        getCourseReturnListString("Courses that are available to you select one:", currentAvailableCourses), -1);
                if (currentUserSelection != 1) {
                    currentCourse = currentAvailableCourses.get(currentUserSelection - 2);
                    currentUserSelection = controller.printListReturnSelection(getCourseInfoString(currentCourse), -1);
                    switch (currentUserSelection) {
                        case 1: // return back to available course menu page
                            runUserAction(1, controller);
                            break;
                        case 2: // register to the course
                            String courseSectAvailabilityStr = currentCourse.checkCourseSection(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses);
                            if(currentCourse.checkTechnicalElectiveCount(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses) != null && courseSectAvailabilityStr == null) {
                                courseSectAvailabilityStr = "";
                                courseSectAvailabilityStr = currentCourse.checkTechnicalElectiveCount(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses);
                            }
                            else if(currentCourse.checkNonTechnicalElectiveCount(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses) != null && courseSectAvailabilityStr == null) {
                                courseSectAvailabilityStr = "";
                                courseSectAvailabilityStr = currentCourse.checkNonTechnicalElectiveCount(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses);
                            }
                            if (courseSectAvailabilityStr == null) {
                                removeElementFromCurrentAvailableCourses(currentCourse);
                                registrationWaitingCourses.add(currentCourse);
                                controller.printSuccessMessage(currentCourse + " has been sent to your advisor " + this.currentAdvisor.getFirstName() + " " + this.currentAdvisor.getLastName());
                            } else {
                                controller.printErrorMessage(courseSectAvailabilityStr);
                            }
                            runUserAction(1, controller);
                            break;

                        case 3: // see your course lecturer
                            controller.printList(getCoursesLecturerInfo(currentCourse));
                            runUserAction(1, controller);
                            break;
                        case 4: // see your course assistant
                            controller.printList(getCoursesAssistantInfo(currentCourse));
                            runUserAction(1, controller);
                            break;
                        case 5: // return back to first page
                            return;
                            

                    }


                }
                break;
            case 2:
                currentUserSelection = controller.printListReturnSelection(
                        getCourseReturnListString("Courses that have finalized registration, choose course to cancel:", registrationCompleteCourses), -1);
                if (currentUserSelection != 1) {
                    currentCourse = registrationCompleteCourses.get(currentUserSelection - 2);
                    removeElementFromRegistrationCompleteCourses(currentCourse);
                    cancelWaitingCourses.add(currentCourse);
                    controller.printSuccessMessage(currentCourse + "is successfully added to cancelWaiting.\n");
                }
                break;
            case 3:
                controller.printList(
                        getCourseListString("Courses that are waiting to be finalized by your advisor ", registrationWaitingCourses));
                break;
            case 4:
                controller.printList(
                        getCourseListString("Courses that are waiting to be canceled by your " + currentAdvisor.toString(), cancelWaitingCourses));
                break;
            case 5:
                controller.printList(CURRENT_TRANSCRIPT.getStudentTranscriptStringList());
                break;
            case 6:
                controller.printList(stringToList(currentAdvisor.toString()));
                break;
            case 7: 
                while (true) {
                                String[] messageList = new String[2];
                                
                                String[] messageMenuList = new String[5];
                                messageMenuList[0] = "Select an action.";
                                messageMenuList[1] = "1) See sent messages.";
                                messageMenuList[2] = "2) See received messages.";
                                messageMenuList[3] = "3) Send message to your advisor.";
                                messageMenuList[4] = "4) Go back.";
                                
                                actionNumber = controller.printListReturnSelection(messageMenuList, -1);
                                
                                if (actionNumber == 4) {
                                    return;
                                }
                                
                                
                                else if (actionNumber == 2) {
                                    while (true) {
                                        String[] receivedMessagesList = new String[receivedMessages.size()+2];
                                        receivedMessagesList[0] = "Received messages:";
                                        receivedMessagesList[receivedMessagesList.length - 1] = receivedMessagesList.length - 1 + ") Go back.";
                                        
                                        if (receivedMessages.size() != 0) {
                                            for (int i = 1; i<=receivedMessages.size(); i++) {
                                                receivedMessagesList[i] = i + ") " + receivedMessages.get(i-1).toString();
                                            }
                                            actionNumber = controller.printListReturnSelection(receivedMessagesList, -1);
                                            if (actionNumber == receivedMessagesList.length - 1) break;
                                            else {
                                                messageList[0] = receivedMessages.get(actionNumber-1).toString() + "\n\n" + receivedMessages.get(actionNumber-1).getMessage();
                                                messageList[1] = "1) Go back.";
                                                receivedMessages.get(actionNumber-1).readMessage();
                                                actionNumber = controller.printListReturnSelection(messageList, -1);
                                                if (actionNumber == 1) continue;
                                            }
                                        }
                                        else {
                                            receivedMessagesList[0] = "There is no received messages.";
                                            actionNumber = controller.printListReturnSelection(receivedMessagesList, -1);
                                            break;
                                        }
                                    }
                                    continue;
                                }
                                
                                else if (actionNumber == 1) {
                                    while (true) {
                                        
                                        String[] sentMessagesList = new String[sentMessages.size()+2];
                                        sentMessagesList[0] = "Sent messages:";
                                        sentMessagesList[sentMessagesList.length - 1] = sentMessagesList.length - 1 + ") Go back.";
                                        
                                        if (sentMessages.size() != 0) {
                                            for (int i = 1; i<=sentMessages.size(); i++) {
                                                sentMessagesList[i] = i + ") " + sentMessages.get(i-1).toString();
                                            }
                                            actionNumber = controller.printListReturnSelection(sentMessagesList, -1);
                                            if (actionNumber == sentMessagesList.length - 1) break;
                                            else {
                                                messageList[0] = sentMessages.get(actionNumber-1).toString() + "\n\n" + sentMessages.get(actionNumber-1).getMessage();
                                                messageList[1] = "1) Go back.";
                                                actionNumber = controller.printListReturnSelection(messageList, -1);
                                                if (actionNumber == 1) continue;
                                            }
                                        }
                                        else {
                                            sentMessagesList[0] = "There is no sent messages.";
                                            actionNumber = controller.printListReturnSelection(sentMessagesList, -1);
                                            break;
                                        }
                                    }
                                    continue;
                                }
                                
                                else {
                                    

                                    String[] messageInfo = controller.requestMessageString();
                                    Message message = new Message(messageInfo[0], messageInfo[1], this, currentAdvisor);
                                    sendMessage(message, currentAdvisor);
                                    
                                }
                                
                            }
            default:
                break;
        }

    }

    private String[] getCourseInfoString(Course course) {
        String[] courseInfoString = new String[6];
        courseInfoString[0] = "Select your course action";
        courseInfoString[1] = "1-)Return back to available course menu page";
        courseInfoString[2] = "2-)Register to the " + course.toString();
        courseInfoString[3] = "3-)See the course's Lecturer";
        courseInfoString[4] = "4-)See the course's assistant";
        courseInfoString[5] = "5-)Return back to first menu page";
        return courseInfoString;
    }

    private String[] getCoursesAssistantInfo(Course currentCourse) {
        String[] assistantInfo = new String[1];
        if (currentCourse.getAssistant() == null) {
            assistantInfo[0] = "There is no assistant assigned for this course";
        } else {
            assistantInfo[0] = currentCourse.getAssistant().toString();
        }

        return assistantInfo;
    }

    private String[] getCoursesLecturerInfo(Course currentCourse) {
        String[] lecturerInfo = new String[1];
        lecturerInfo[0] = currentCourse.getLecturer().toString();
        return lecturerInfo;

    }

    private String[] stringToList(String giveString) {
        String[] stringList = new String[1];
        stringList[0] = giveString;
        return stringList;
    }

    private String[] getCourseReturnListString(String titleString, List<Course> coursesList) {
        //TODO NULL CHECK
        int size = coursesList.size();
        String[] courseListString = new String[size + 2];
        courseListString[0] = titleString;
        courseListString[1] = "1-)Return back";
        for (int i = 2; i < coursesList.size() + 2; i++) {
            courseListString[i] = i + "-)" + coursesList.get(i - 2).toString();
        }
        return courseListString;
    }

    private String[] getCourseListString(String titleString, List<Course> coursesList) {
        int size = coursesList.size();
        String[] courseListString = new String[size + 1];
        courseListString[0] = titleString;
        for (int i = 1; i < coursesList.size() + 1; i++) {
            courseListString[i] = i + "-)" + coursesList.get(i - 1).toString();
        }
        return courseListString;
    }

    public boolean removeElementFromCurrentAvailableCourses(Course course) {
        return currentAvailableCourses.remove(course);
    }

    public boolean removeElementFromRegistrationWaitingCourses(Course course) {
        return registrationWaitingCourses.remove(course);
    }

    public boolean removeElementFromRegistrationCompleteCourses(Course course) {
        return registrationCompleteCourses.remove(course);
    }

    public boolean removeElementFromCancelWaitingCourses(Course course) {
        return cancelWaitingCourses.remove(course);
    }

    public void addElementToCurrentAvailableCourses(Course course) {
        currentAvailableCourses.add(course);
    }

    public void addElementToRegistrationWaitingCourses(Course course) {
        registrationWaitingCourses.add(course);
    }

    public void addElementToRegistrationCompleteCourses(Course course) {
        registrationCompleteCourses.add(course);
    }

    public void addElementToCancelWaitingCourses(Course course) {
        cancelWaitingCourses.add(course);
    }

    public List<Course> getCancelWaitingCourses() {
        return cancelWaitingCourses;
    }

    public List<Course> getRegistrationWaitingCourses() {
        return registrationWaitingCourses;
    }

    public boolean checkCourseAvailablity(Course course) {
        boolean isAvailable = true;
        isAvailable = course.checkYearMatching(this.CURRENT_TRANSCRIPT.getYear())
                && course.checkPreRequisite(CURRENT_TRANSCRIPT.getListOfCourses(), CURRENT_TRANSCRIPT.getListOfGrades())
                && CURRENT_TRANSCRIPT.checkPassedCourses(course)
                && course.checkCourseSection(registrationCompleteCourses, registrationWaitingCourses, cancelWaitingCourses) == null
                && !checkExistence(course) && !course.isFull();
        return isAvailable;
    }

    private boolean checkExistence(Course course) {
        boolean exists = false;
        exists = checkListForCourse(cancelWaitingCourses, course) ||
                checkListForCourse(registrationCompleteCourses, course) ||
                checkListForCourse(registrationWaitingCourses, course);
        return exists;
    }

    //Returns true if it finds a course in the list
    private boolean checkListForCourse(List<Course> courseList, Course course) {
        for (Course current : courseList) {
            if (course.equals(current))
                return true;
        }
        return false;
    }

    public boolean compareCredentials(String username, String password) {
        if (this.userName == null || this.password == null) return false;
        return this.userName.equals(username) && this.password.equals(password);

    }

    public void setCurrentAdvisor(Advisor currentAdvisor) {
        this.currentAdvisor = currentAdvisor;
    }

    public String getStudentID() {
        return STUDENT_ID;
    }

    public void setRegistrationWaitingCourses(List<Course> registrationWaitingCourses) {
        this.registrationWaitingCourses = registrationWaitingCourses;
    }

    public void setRegistrationCompleteCourses(List<Course> registrationCompleteCourses) {
        this.registrationCompleteCourses = registrationCompleteCourses;
    }

    public void setCancelWaitingCourses(List<Course> cancelWaitingCourses) {
        this.cancelWaitingCourses = cancelWaitingCourses;
    }

    public Advisor getCurrentAdvisor() {
        return currentAdvisor;
    }

    public void setCurrentAvailableCourses(List<Course> currentAvailableCourses) {
        this.currentAvailableCourses = currentAvailableCourses;
    }

    public Transcript getCurrentTranscript() {
        return CURRENT_TRANSCRIPT;
    }

    public List<Course> getRegistrationCompleteCourses() {
        return registrationCompleteCourses;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    //iteration 2:
    public void sendMessage(Message msg, User advisor) {
        sentMessages.add(msg);
        advisor.receiveMessage(msg);

    }

    public void receiveMessage(Message msg) {
        receivedMessages.add(msg);
    }


    public boolean isTakingCourse(Course course) {
        return registrationCompleteCourses.contains(course) || cancelWaitingCourses.contains(course);
    }
}

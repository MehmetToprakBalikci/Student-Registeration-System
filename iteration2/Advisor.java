import java.util.ArrayList;

//for iteration 2, the default constructor will be updated to include "sentMessages" and "receivedMessages" lists. 
//also, at the bottom, there will be two new methods: sendMessage and receiveMessage.

class Advisor extends Lecturer implements User {
    private ArrayList<Student> studentList;
    private String userName;
    private String password;
    private ArrayList<Message> sentMessages;
    private ArrayList<Message> receivedMessages;

    // Constructors
    public Advisor() {

    }

    public Advisor(String name, String lastName, String username, String password, String lecturerID, ArrayList<Student> studentList) {
        super(name, lastName, lecturerID);
        this.userName = username;
        this.password = password;
        this.studentList = new ArrayList<>();
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

    
    public boolean compareCredentials(String username, String password) {
        if (this.userName == null || this.password == null) return false;
        return this.userName.equals(username) && this.password.equals(password);

    }

    // Get advisor information as a String
    @Override
    public String toString() {
        return "Advisor: " + this.getFirstName() + " " + this.getLastName();
    }

    // Placeholder for starting advisor actions
    @Override
    public void startActions(Controller controller) {
        while (true) {
            String[] actionList = getActionList();
            int actionNumber = controller.printListReturnSelection(actionList, -1);
            if (actionNumber != 3) {
                runUserAction(actionNumber, controller);
            } else return;
        }
    }

    // First menu for advisor
    @Override
    public String[] getActionList() {

        String[] actionList = new String[4];

        actionList[0] = "\nSelect an action.";
        actionList[1] = "1) See students.";
        actionList[2] = "2) See messages.";
        actionList[3] = "3) Log out.";

        return actionList;
    }

    // Further menu's and actions
    @Override
    public void runUserAction(int actionNumber, Controller controller) {
        
        switch (actionNumber) {
        	// See students action
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
                                selectedCourse.increaseStudentNumber();

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
                                selectedCourse.increaseStudentNumber();
                            } else {
                                selectedStudent.removeElementFromCancelWaitingCourses(selectedCourse);
                                selectedStudent.addElementToRegistrationCompleteCourses(selectedCourse);
                                selectedCourse.decreaseStudentNumber();
                            }
                        }


                    }
                } 
		// See messages action
            case 2:
            	while (true) {
        		String[] messageList = new String[2];
            	
            	String[] messageMenuList = new String[5];
            	messageMenuList[0] = "Select an action.";
            	messageMenuList[1] = "1) See sent messages.";
            	messageMenuList[2] = "2) See received messages.";
            	messageMenuList[3] = "3) Send message to a student.";
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
            		int studentSize = studentList.size();
            		String[] studentsToSendMessage = new String[studentSize+2];
            		
            		studentsToSendMessage[0] = "Select a student to send a message.";
            		studentsToSendMessage[studentSize + 1] = studentSize + 1 + ") Go back.";

                    for (int i = 1; i <= studentSize; i++) {

                    	studentsToSendMessage[i] = i + ") " + studentList.get(i - 1).toString();
                    }
                    
                    actionNumber = controller.printListReturnSelection(studentsToSendMessage, -1);
                    
                    String[] messageInfo = controller.requestMessageString();
                    Message message = new Message(messageInfo[0], messageInfo[1], this, studentList.get(actionNumber - 1));
                    sendMessage(message, studentList.get(actionNumber - 1));
                    
            	}
            	
            }
            case 3 :
              
        }
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }


    //iteration 2 part:
    public void sendMessage(Message msg, User student) {
        student.receiveMessage(msg);
        this.sentMessages.add(msg);
    }

    public void receiveMessage(Message msg) {
        this.receivedMessages.add(msg);
    }

}
    
    



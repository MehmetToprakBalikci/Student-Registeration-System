import java.util.ArrayList;

class Advisor extends Lecturer implements User {
	
	private ArrayList<Student> studentList = new ArrayList<>();
	

    // Constructor
    public Advisor(String name, String lastName, String lecturerID, ArrayList<Student> studentList) {
        super(name, lastName, lecturerID);
        this.studentList = studentList;
    }

    // Get advisor information as a String
    public String getAdvisorString() {
        // Implementation needed
        return "Advisor: " + this.name + " " + this.lastName;
    }
    
    // Placeholder for starting advisor actions
    @Override
    public void startActions(Controller controller) {
    	while (true) {
	    	String[] actionList = getActionList();
	        int actionNumber = controller.printListReturnSelection(actionList);
	        if (actionNumber != 2) {
	        	runUserAction(actionNumber, controller);
	        }
	        else return;
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
	    	String[] studentMenuList = new String[studentSize+2];
	    
	    	studentMenuList[0] = "Select a student for action.";
	    	studentMenuList[studentSize+1] = "Go back.";
	    	
	    	for(int i = 1; i<=studentSize; i++) {
	    		
	    		studentMenuList[i] = studentList.get(i-1).toStringAdvisor();	
	    		}
	    		
	    	actionNumber = controller.printListReturnSelection(studentMenuList);
	    	if (actionNumber == studentSize+1) {	// If action is go back
	    		return;
	    	}
	    	Student selectedStudent = studentList.get(actionNumber-1);
	    	
	    	boolean controlFlag = true;
	    	while (controlFlag) {
		    	// Course selection part
		    	int registrationWaitingCoursesSize = selectedStudent.getRegistrationWaitingCourses().size();
		    	int cancelWaitingCoursesSize = selectedStudent.getCancelWaitingCourses().size();
		    	
		    	String[] courseMenuList = new String[registrationWaitingCoursesSize + cancelWaitingCoursesSize + 2];
		    	courseMenuList[0] = selectedStudent.toStringAdvisor() + "\n\nChoose a course for action.";	// Title for course list
		    	courseMenuList[courseMenuList.length -1] = "Go back.";		
		    	
		    	
		    	for(int j = 1; j<=registrationWaitingCoursesSize; j++) {	
		    		if (j == 1) {
		    			courseMenuList[j] = "Registration Waiting Courses:\n" + j + ") " + selectedStudent.getRegistrationWaitingCourses().get(j-1).getCourseString();
		    		}
		    		else {
		    			courseMenuList[j] = j + ") " + selectedStudent.getRegistrationWaitingCourses().get(j-1).getCourseString();
		    		}
		    		}
		    	
		    	for(int j = registrationWaitingCoursesSize+1; j<=courseMenuList.length; j++) {	
		    		if (j == registrationWaitingCoursesSize+1) {
		    			courseMenuList[j] = "Cancel Waiting Courses:\n" + j + ") " + selectedStudent.getCancelWaitingCourses().get(j-1).getCourseString();
		    		}
		        	courseMenuList[j] = j + ") " + selectedStudent.getCancelWaitingCourses().get(j-1).getCourseString();
		        	}
		    	
		    	
		    	actionNumber = controller.printListReturnSelection(courseMenuList);
		    	Course selectedCourse;
		    	
		    	if (actionNumber <= registrationWaitingCoursesSize) {
		    		selectedCourse = selectedStudent.getRegistrationWaitingCourses().get(actionNumber-1);
		    	}
		    	else  {
		    		selectedCourse = selectedStudent.getCancelWaitingCourses().get(actionNumber-registrationWaitingCoursesSize-1);
		    	}
		    	
		    	
		    	// Course Action Part
		    	String[] courseActionMenuList = new String[3];
		    	
		    	if (actionNumber <= registrationWaitingCoursesSize) {	// Registration waiting course
		    		courseActionMenuList[0] = selectedStudent.toStringAdvisor() + "\n" + courseMenuList[actionNumber];
		    		courseActionMenuList[1] = "1) Accept registration request.";
		    		courseActionMenuList[2] = "2) Reject registration request.";
		    		
		    		if (controller.printListReturnSelection(courseActionMenuList) == 1){	// Accept registration
		    			selectedStudent.removeElementFromRegistrationWaitingCourses(selectedCourse);
		    			selectedStudent.addElementToRegistrationCompleteCourses(selectedCourse);
		    		}
		    	}
		    	
		    	else if (actionNumber < courseMenuList.length -1) {		// Cancel waiting course
		    		courseActionMenuList[0] = selectedStudent.toStringAdvisor() + "\n" + courseMenuList[actionNumber];
		    		courseActionMenuList[1] = "1) Accept cancel request.";
		    		courseActionMenuList[2] = "2) Reject cancel request.";
		    		
		    		if (controller.printListReturnSelection(courseActionMenuList) == 1){
		    			selectedStudent.removeElementFromCancelWaitingCourses(selectedCourse);
		    			selectedStudent.addElementToCurrentAvailableCourses(selectedCourse);
		    			//controller.getUniversityFileSystem().calculateStudentAvailableClass(selectedStudent);
		    		}
		    	
		    	}
		    	
		    	else {		// Go back
		    		if (actionNumber != courseMenuList.length-1) {controller.printErrorMessage("Error in Advisor");}
		    		controlFlag = false;
		    	}
		    }
		}
    	
		
		case 2:
		break;
    	}
    }
}
    
    



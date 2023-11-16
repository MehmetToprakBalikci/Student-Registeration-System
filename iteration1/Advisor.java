import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Advisor extends Lecturer implements User {
	
	private ArrayList<Student> studentList = new ArrayList<>();
	

    // Constructor
    public Advisor(String name, String lastName, ArrayList<Student> studentList) {
        super(name, lastName);
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
    	
    	String[] actionList = getActionList();
        int actionNumber = controller.printListReturnSelection(actionList);
        if (actionNumber != 2) {
        	runUserAction(actionNumber, controller);
        }
        
		}    
 
    @Override
    public String[] getActionList() {
    	
    	String[] actionList = new String[3];
    	
    	actionList[0] = "Select an action.";
    	actionList[1] = "1) See students.";
    	actionList[2] = "2) Log out.";
    	
        return actionList;
    }
    
    @Override
    public void runUserAction(int actionNumber, Controller controller) {
    	// Student selection part
    	switch (actionNumber) {
    	
		case 1: int studentSize = studentList.size();
    	String[] studentMenuList = new String[studentSize+2];
    
    	studentMenuList[0] = "Select a student for action.";
    	studentMenuList[studentSize] = "Go back.";
    	
    	for(int i = 1; i<=studentSize; i++) {
    		
    		studentMenuList[i] = studentList.get(i-1).toStringAdvisor();	// ��renci isim soyisim ve numaralar� listeleniyor
    		}
    		
    	actionNumber = controller.printListReturnSelection(studentMenuList);	// liste printlenip ��renci se�imi al�n�yor
    	Student selectedStudent = studentList.get(actionNumber-1);
    	
    	boolean controlFlag2 = true;
    	while (controlFlag2) {
    	// Course selection part
    	int registrationWaitingCoursesSize = selectedStudent.getRegistrationWaitingCourses().size();
    	int cancelWaitingCoursesSize = selectedStudent.getCancelWaitingCourses().size();
    	
    	String[] courseMenuList = new String[registrationWaitingCoursesSize + cancelWaitingCoursesSize + 2];
    	courseMenuList[0] = selectedStudent.toStringAdvisor() + "\n\nChoose a course for action.";	// Title for course list
    	courseMenuList[courseMenuList.length -1] = "Go back.";		
    	
    	
    	for(int j = 1; j<=registrationWaitingCoursesSize; j++) {	// Loop i�inde kurslar printlenecek advisorun se�imi i�in.
    		if (j == 1) {
    			courseMenuList[j] = "Registration Waiting Courses:\n" + j + ") " + selectedStudent.getRegistrationWaitingCourses().get(j-1).getCourseString();
    		}
    		else {
    			courseMenuList[j] = j + ") " + selectedStudent.getRegistrationWaitingCourses().get(j-1).getCourseString();
    		}
    		}
    	
    	for(int j = registrationWaitingCoursesSize+1; j<=courseMenuList.length; j++) {	// Loop i�inde kurslar printlenecek advisorun se�imi i�in.
    		if (j == registrationWaitingCoursesSize+1) {
    			courseMenuList[j] = "Cancel Waiting Courses:\n" + j + ") " + selectedStudent.getCancelWaitingCourses().get(j-1).getCourseString();
    		}
        	courseMenuList[j] = j + ") " + selectedStudent.getCancelWaitingCourses().get(j-1).getCourseString();
        	}
    	
    	
    	actionNumber = controller.printListReturnSelection(courseMenuList);
    	
    	// Course Action Part
    	String[] courseActionMenu = new String[3];
    	
    	if (actionNumber <= registrationWaitingCoursesSize) {	// Registration waiting course
    		
    	}
    	
    	else if (actionNumber < courseMenuList.length -1) {		// Cancel waiting course
    		
    	
    	}
    	
    	else {
    		if (actionNumber != courseMenuList.length-1) {controller.printErrorMessage("Error in Advisor");}
    		controlFlag2 = false;
    	}
    	}
    	
    	
		break;
		
		case 2:
		break;
    	}
    }
}

    
   /* public String[] secondActionList(int actionNumber) {
    	
    	switch (actionNumber) {
		
		case 1: int studentNumber = studentList.size();
    	String[] actionList = new String[studentNumber+1];
    
    	actionList[0] = "Select a student for action.";
    	
    	for(int i = 1; i<studentNumber; i++) {
    		actionList[i] = studentList.get(i).toStringAdvisor();
    		}
    	return actionList;
			
		break;
		
		case 2: 
		break;
	}
    	*/
    	/*int studentNumber = studentList.size();
    	String[] actionList = new String[studentNumber+1];
    
    	actionList[0] = "Select a student for action.";
    	
    	for(int i = 1; i<studentNumber; i++) {
    		actionList[i] = studentList.get(i).toStringAdvisor;
    		}
    	return actionList;
    	
    }*/
    
    
    



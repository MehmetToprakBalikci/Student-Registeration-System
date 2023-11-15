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
            //runUserAction(actionNumber);
            
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
    	
    	switch (actionNumber) {
		
		case 1: int studentNumber = studentList.size();
    	String[] studentMenuList = new String[studentNumber+1];
    
    	studentMenuList[0] = "Select a student for action.";
    	
    	for(int i = 1; i<=studentNumber; i++) {
    		
    		studentMenuList[i] = studentList.get(i-1).toStringAdvisor();	// ��renci isim soyisim ve numaralar� listeleniyor
    		}
    		
    	actionNumber = controller.printListReturnSelection(studentMenuList);	// liste printlenip ��renci se�imi al�n�yor
    	
    	String[] courseMenuList = new String[studentList.get(actionNumber-1).getRegistrationWaitingCourses().size()+1];
    	courseMenuList[0] = "Choose a course for action.";
    	
    	for(int j = 1; j<=courseMenuList.length; j++) {	// Loop i�inde kurslar printlenecek advisorun se�imi i�in.
    	//courseMenuList[j] = studentList.get(actionNumber-1).getRegistrationWaitingCourses().get(j)
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
    
    
    



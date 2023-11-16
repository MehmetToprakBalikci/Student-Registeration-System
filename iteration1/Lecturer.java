
public class Lecturer extends Person {

	private String lecturerID;
	
	public Lecturer(String name, String lastName, String lecturerID) {
		super(name, lastName);
		this.lecturerID = lecturerID;
	}
	@Override
	public String toString(){
		return "Advisor: " + this.name + " " + this.lastName;
	}
	

	@Override
	void startActions(Controller controller) {
		controller.printErrorMessage("You do not have permission to access the system.");
	}
	
	

	


	
	

}

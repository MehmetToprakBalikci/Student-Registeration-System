class Advisor extends Lecturer implements User {
    // Method to create advisor
    public static Advisor create(String name, String lastName) {
        return new Advisor(name, lastName);
    }

    // Constructor
    private Advisor(String name, String lastName) {
        super(name, lastName);
    }

    // Get advisor information as a String
    public String getAdvisorString() {
        // Implementation needed
        return "Advisor: " + this.name + " " + this.lastName;
    }
    // Placeholder for starting advisor actions
    @Override
    public void startActions(Controller controller) {
        String[] actionList= getActionList();
            int actionNumber =controller.getAction(actionList);
            runUserAction(actionNumber);
        }
        // Implementation needed

    
    @Override
    Person signIn(String username, String password) {
		return null;
	}



    @Override
    public String[] getActionList() {
        return new String[0];
    }

    @Override
    public void runUserAction(int actionNumber) {

    }
}



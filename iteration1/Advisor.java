import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Advisor extends Lecturer {
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
    public void startActions() {
        // Implementation needed
    }
}

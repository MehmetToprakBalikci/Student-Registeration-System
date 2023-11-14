import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Person {
    protected String name;
    protected String lastName;
    private String userName;
    private String password;

    // Constructor
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    // Placeholder for person's actions
    public void startActions() {
        // Implementation needed
    }
    
    public boolean compareCredentials(String username, String password) {
    	
    	return true;
    }

    // Method for sign-in process
    abstract Person signIn(String username, String password);
}


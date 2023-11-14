import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Person {
    protected String name;
    protected String lastName;

    // Constructor
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    // Placeholder for person's actions
    public void startActions() {
        // Implementation needed
    }

    // Method for sign-in process
    public boolean signIn(String username, String password) {
        // Implementation needed
        return false;
    }
}

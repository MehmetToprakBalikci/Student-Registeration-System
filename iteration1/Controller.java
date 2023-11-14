import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Controller class
class Controller {
    private FileSystem fileSystem;

    // Constructor
    public Controller(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    // Request credentials from the user
    public void requestCredentials() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        // Further processing needed
    }

    // Other controller methods need to be implemented
}
import java.util.Scanner;

// UI class
public class UI {
    private Controller controller;
    private  Scanner scanner;

    public UI(){
        scanner=new Scanner(System.in);
    }

    public Controller getSystem() {
        return controller;
    }

    // Method to create UI
   /* public static UI create() {
        UI ui = new UI();

        return ui;
    };*/

    public String[] requestCredentials() {
        System.out.println("Enter your userName:");
        String userName=scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        return new String[]{userName,password};
    }

    public void initialize() {
        System.out.println("HI WELCOME TO COURSE REGISTRATION SYSTEM");
    }

    public int requestActionNumber() {
        System.out.println("SELECT WHAT YOU WANT TO DO IN THE SYSTEM");
        return scanner.nextInt();
    }
}


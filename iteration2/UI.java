
import java.util.Scanner;

// UI class
public class UI {
    private static   UI singletonUI;
    private Controller controller;
    private Scanner scanner;

    private UI() {
        scanner = new Scanner(System.in, "UTF-8");
    }

    private UI(Scanner input) {
        scanner = input;
    }

    // UI singleton method is added
    public static UI getInstance(Scanner input) {
        if (singletonUI == null) {
            return new UI(input);
        }
        return singletonUI;
    }

    public static UI getInstance() {
        if (singletonUI == null) {
            return new UI();
        }
        return singletonUI;
    }

    public Controller getSystem() {
        return controller;
    }

    public String[] requestCredentials() {
        scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Enter your userName:");
        String userName = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        return new String[]{userName, password};
    }

    public void initialize() {
        System.out.println("HI WELCOME TO COURSE REGISTRATION SYSTEM");
    }

    public int requestActionNumber() {
        System.out.println("SELECT WHAT YOU WANT TO DO IN THE SYSTEM");
        return scanner.nextInt();
    }

    public void printConsoleErrorMessage(String errorMessage) {
        printString(errorMessage);
    }


    public void callEndMessage(int status) {
        System.out.println("Press enter to close this program...");
        scanner.nextLine();
        scanner.nextLine();
        scanner.close();
        System.exit(status);
    }

    //First string of the array is the title string
    public int printConsoleListReturnSelection(String[] stringList, int errorInt) {
        // int chosenInt = -1;
        if (errorInt == 0) {
            try {
                throw new Exception("emptyListStringException");
            } catch (Exception e) {
                callEndMessage(1);
            }
        }
        do {
            printConsoleList(stringList);
            errorInt = scanner.nextInt();
        } while (errorInt >= stringList.length || errorInt <= 0);

        return errorInt;
    }

    public void printConsoleList(String[] stringList) {
        for (int i = 0; i < stringList.length; i++) {
            if (stringList[i] != null) {
                System.out.println(stringList[i]);
            }
        }
        System.out.println("--------------------------");
        return;
    }

    private void printString(String outputStr) {
        System.out.println(outputStr);
    }

    public void printConsoleSuccessMessage(String successMessage) {
        System.out.println(successMessage);
    }

    public String[] requestMessageStringFromUser() {
        Scanner scanner2 = new Scanner(System.in, "UTF-8");
        String[] message = new String[2];
        printString("Write your message title:");
        String title = scanner2.nextLine();
        printString("Write your message:");
        String msg = scanner2.nextLine();
        message[0] = title;
        message[1] = msg;
        return message;
    }
}


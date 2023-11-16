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

    public void printConsoleErrorMessage(String errorMessage) {
        printString(errorMessage);
    }


    public void callEndMessage(int status) {
        System.out.println("Press enter to close this program...");
        scanner.next();
        System.exit(status);
    }
    
    //First string of the array is the title string
    public int printConsoleListReturnSelection(String[] stringList){
        int chosenInt = -1; //
        if (chosenInt == 0) {
            try {
                throw new Exception("emptyListStringException");
            } catch (Exception e) {
                callEndMessage(1);
            }
        }
        do{
            printConsoleList(stringList);
            chosenInt = scanner.nextInt();
        }while(chosenInt >= stringList.length || chosenInt <= 0);

        return chosenInt;
    }

    public void printConsoleList(String[] stringList) {
        for(int i = 0; i < stringList.length; i++){
            System.out.println(stringList[i]);
        }
        System.out.println("--------------------------");
        return;
    }

    private void printString(String outputStr){
         System.out.println(outputStr);
    }
}


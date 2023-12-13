import java.util.*;

// Controller class
class Controller {
    private static Controller singletonController;
    private static UniversityFileSystem UNIVERSITY_FILE_SYSTEM;
    private static JsonWriter jsonWriter;
    private static UI ui;
    private User user;

    //Default Constructor
    private Controller() {
        UNIVERSITY_FILE_SYSTEM = UniversityFileSystem.getInstance();
        ui = UI.getInstance();
        ui.initialize();
    }

    public static Controller getInstance() {
        if (singletonController == null) {
            return new Controller();
        }
        return singletonController;
    }

    //Constructor
    private Controller(Scanner input) {
        UNIVERSITY_FILE_SYSTEM = UniversityFileSystem.getInstance();
        ui = UI.getInstance(input);
        ui.initialize();
    }

    public static Controller getInstance(Scanner input) {
        if (singletonController == null) {
            return new Controller(input);
        }
        return singletonController;
    }


    public void start() {
        // load all json course and person files
        UNIVERSITY_FILE_SYSTEM.loadFiles();
        /*
        String[] userInfo = UI.requestCredentials();
        user = UNIVERSITY_FILE_SYSTEM.getSignedPerson(userInfo, this);
        if (user != null)
        user.startActions(this);
        */
        String[] startMenu = new String[3];
        startMenu[0] = "Select an action.";
        startMenu[1] = "1) Log in.";
        startMenu[2] = "2) Exit.";
        int actionNumber = 1;
        while (true) {
            actionNumber = ui.printConsoleListReturnSelection(startMenu, -1);
            if (actionNumber == 2) break;

            String[] userInfo = ui.requestCredentials();
            user = UNIVERSITY_FILE_SYSTEM.getSignedPerson(userInfo, this);
            if (user != null)
                user.startActions(this);
        }

        jsonWriter = JsonWriter.getInstance((Person) user);
        jsonWriter.saveFiles();
        ui.callEndMessage(0);
    }


    public void printErrorMessage(String errorMessage) {
        ui.printConsoleErrorMessage(errorMessage);
    }

    //Error int -1 for no error
    public int printListReturnSelection(String[] stringsList, int errorInt) {
        return ui.printConsoleListReturnSelection(stringsList, errorInt);
    }

    public void printList(String[] stringList) {
        ui.printConsoleList(stringList);
    }

    public void printSuccessMessage(String successMessage) {
        ui.printConsoleSuccessMessage(successMessage);
    }

    public String[] requestMessageString() {
        return ui.requestMessageStringFromUser();
    }
}




import java.util.*;

// Controller class
class Controller {
    private final UniversityFileSystem universityFileSystem;
    private jsonWriter jsonWriter;
    private final UI ui;
    private Person person;
    //Default Constructor
    public Controller() {
        universityFileSystem = new UniversityFileSystem();
        ui = new UI();
        ui.initialize();
    }
    //Constructor
    public Controller(Scanner input) {
        universityFileSystem = new UniversityFileSystem();
        ui = new UI(input);
        ui.initialize();
    }



    public void start() {
        // load all json course and person files
        universityFileSystem.loadFiles();
        do {
            String[] userInfo = ui.requestCredentials();
            person = universityFileSystem.getSignedPerson(userInfo, this);
        } while (person == null);
        person.startActions(this);
        jsonWriter = new jsonWriter(person);
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
    
}

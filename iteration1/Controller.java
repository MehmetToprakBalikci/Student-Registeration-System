import java.util.*;
// Controller class
class Controller {
    private UniversityFileSystem universityFileSystem;
    private UI ui;
    private Person person;

    public Controller() {
        universityFileSystem = new UniversityFileSystem();
        ui = new UI();
        ui.initialize();
    }

    public Controller(Scanner input) {
        universityFileSystem = new UniversityFileSystem();
        ui = new UI(input);
        ui.initialize();
    }


    // Constructor
    public void start() {
        // load all json course and person files
        universityFileSystem.loadFiles();
        do {
            String[] userInfo = ui.requestCredentials();
            person = universityFileSystem.getSignedPerson(userInfo, this);
        } while (person == null);
        person.startActions(this);
        universityFileSystem.saveFiles();
        ui.callEndMessage(0);
    }
    // Request credentials from the user
    public void printErrorMessage(String errorMessage){
        ui.printConsoleErrorMessage(errorMessage);
    }
    public int printListReturnSelection(String[] stringsList) {
        return ui.printConsoleListReturnSelection(stringsList);
    }
    public void printList(String[] stringList){
        ui.printConsoleList(stringList);
    }
    // Other controller methods need to be implemented
}
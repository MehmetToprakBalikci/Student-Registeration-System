import java.util.*;

// Controller class
class Controller {
    private final UniversityFileSystem UNIVERSITY_FILE_SYSTEM;
    private jsonWriter jsonWriter;
    private final UI UI;
    private Person person;
    //Default Constructor
    public Controller() {
    	UNIVERSITY_FILE_SYSTEM = new UniversityFileSystem();
    	UI = new UI();
        UI.initialize();
    }
    //Constructor
    public Controller(Scanner input) {
    	UNIVERSITY_FILE_SYSTEM = new UniversityFileSystem();
    	UI = new UI(input);
        UI.initialize();
    }



    public void start() {
        // load all json course and person files
    	UNIVERSITY_FILE_SYSTEM.loadFiles();
        do {
            String[] userInfo = UI.requestCredentials();
            person = UNIVERSITY_FILE_SYSTEM.getSignedPerson(userInfo, this);
        } while (person == null);
        person.startActions(this);
        jsonWriter = new jsonWriter(person);
        jsonWriter.saveFiles();
        UI.callEndMessage(0);
    }

    
    public void printErrorMessage(String errorMessage) {
    	UI.printConsoleErrorMessage(errorMessage);
    }

    //Error int -1 for no error
    public int printListReturnSelection(String[] stringsList, int errorInt) {
        return UI.printConsoleListReturnSelection(stringsList, errorInt);
    }

    public void printList(String[] stringList) {
    	UI.printConsoleList(stringList);
    }
    
}

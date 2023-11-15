import java.util.Scanner;

// Controller class
class Controller {
    private UniversityFileSystem universityFileSystem;

    public UI getUi() {
        return ui;
    }

    private UI ui;
    private Person person;

    public Controller() {
        universityFileSystem = new UniversityFileSystem();
        ui = new UI();
        ui.initialize();
    }

    // Constructor

    public void start() {
        // load all json course and person files
        universityFileSystem.loadFiles();
        do {
            String[] userInfo = ui.requestCredentials();
            person = universityFileSystem.getSignedPerson(userInfo);
            if (person == null) {
                System.out.println("Wrong userName or Password try again!!");
            }
        } while (person == null);
        person.startActions(this);


    }

    // Request credentials from the user


    public int getAction(String[] actionList) {
        int actionSize = actionList.length;
        int selection;
        boolean isInvalid;
        do {
            selection = ui.requestActionNumber();
            isInvalid = isInvalidSelection(actionSize, selection);
            if (isInvalid) {
                System.out.println("TRY TO SELECT VALID ACTION AGAIN");
            }
        } while (isInvalid);


        return selection;
    }

    private boolean isInvalidSelection(int actionSize, int selection) {
        return selection < 0 || selection > actionSize;
    }

    // Other controller methods need to be implemented
}
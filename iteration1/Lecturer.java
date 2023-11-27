
public class Lecturer extends Person {

    private String lecturerID;

    public String getLecturerID() {
        return lecturerID;
    }

    public Lecturer(String name, String lastName, String lecturerID) {
        super(name, lastName);
        this.lecturerID = lecturerID;
    }

    public Lecturer(String name, String lastName, String username, String password, String lecturerID) {
        super(name, lastName);
        this.lecturerID = lecturerID;
        this.setUserName(username);
        this.setPassword(password);
        //

    }

    public Lecturer() {


    }

    @Override
    public String toString() {
        return "Lecturer: " + this.name + " " + this.lastName;
    }


    @Override
    void startActions(Controller controller) {
        controller.printErrorMessage("You do not have permission to access the system.");
    }


}

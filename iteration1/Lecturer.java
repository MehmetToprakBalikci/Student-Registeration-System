
public class Lecturer extends Person {

    private String lecturerID;

    public String getLecturerID() {
        return lecturerID;
    }

    public Lecturer(String name, String lastName, String lecturerID) {
        super(name, lastName);
        this.lecturerID = lecturerID;
    }


    public Lecturer() {


    }

    @Override
    public String toString() {
        return "Lecturer: " + this.getFirstName() + " " + this.getLastName();
    }


}

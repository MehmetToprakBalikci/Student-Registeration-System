
public class Lecturer extends Staff {

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
        return "Lecturer: " + this.name + " " + this.lastName;
    }




}

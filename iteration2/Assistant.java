import java.util.ArrayList;
import java.util.List;

class Assistant extends Staff{
    private String assistantID;
    // Constructors
    public Assistant() {

    }

    public Assistant(String name, String lastName, String username, String password, String assistantID, List<Student> studentList) {
        super(name, lastName);
        this.assistantID = assistantID;
    }


//Assistant class, currently has no methods.
}
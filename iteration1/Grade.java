import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Grade class
class Grade {
    private int numericalGrade;

    // Contructor
    public Grade(int numericalGrade) {
        this.numericalGrade = numericalGrade;
    }

    // Convert numerical grade to letter grade
    public String getGradeLetter(String numericalGrade) {
        // This needs to be implemented based on your grading system
        if (numericalGrade >= 90 && numericalGrade <= 100) {
            return "AA";
        } else if (numericalGrade >= 80 && numericalGrade < 90) {
            return "BA";
        } else if (numericalGrade >= 70 && numericalGrade < 80) {
            return "BB";
        } else if (numericalGrade >= 60 && numericalGrade < 70) {
            return "CB";
        } else if (numericalGrade >= 50 && numericalGrade < 60) {
            return "CC";
        } else if (numericalGrade >= 40 && numericalGrade < 50) {
            return "DC";
        } else if (numericalGrade >= 35 && numericalGrade < 40) {
            return "DD";
        } else if (numericalGrade < 35 && numericalGrade >= 0) {
            return "FF";
        }

        return "Invalid Grade";
    }

    public int getNumericalGrade() {
        return numericalGrade;
    }
}

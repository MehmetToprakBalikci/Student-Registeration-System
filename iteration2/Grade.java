


// Grade class
class Grade {
    private final int NUMERICAL_GRADE;

    // Contructor
    public Grade(int numericalGrade) {
        this.NUMERICAL_GRADE = numericalGrade;
    }

    public boolean isRetakableGrade() {
        return NUMERICAL_GRADE < 50;
    }

    public boolean isPassedGrade() {
        return NUMERICAL_GRADE >= 35;
    }

    // Convert numerical grade to letter grade
    public String getGradeLetter() {
        if (NUMERICAL_GRADE >= 90 && NUMERICAL_GRADE <= 100) {
            return "AA";
        } else if (NUMERICAL_GRADE >= 80 && NUMERICAL_GRADE < 90) {
            return "BA";
        } else if (NUMERICAL_GRADE >= 70 && NUMERICAL_GRADE < 80) {
            return "BB";
        } else if (NUMERICAL_GRADE >= 60 && NUMERICAL_GRADE < 70) {
            return "CB";
        } else if (NUMERICAL_GRADE >= 50 && NUMERICAL_GRADE < 60) {
            return "CC";
        } else if (NUMERICAL_GRADE >= 40 && NUMERICAL_GRADE < 50) {
            return "DC";
        } else if (NUMERICAL_GRADE >= 35 && NUMERICAL_GRADE < 40) {
            return "DD";
        } else if (NUMERICAL_GRADE < 35 && NUMERICAL_GRADE >= 0) {
            return "FF";
        }

        return "Invalid Grade";
    }

    @Override
    public String toString() {
        return "Grade : " + NUMERICAL_GRADE + ", Letter Grade : " + getGradeLetter();
    }

    public int getNumericalGrade() {
        return NUMERICAL_GRADE;
    }
}

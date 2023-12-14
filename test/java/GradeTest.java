import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test
    void ninetyFiveShouldEqualAA(){
        Grade grade = new Grade(95);
        assertEquals("AA", grade.getGradeLetter());
    }

    @Test
    void negativeValueShouldInvalid(){
        Grade grade = new Grade(-15);
        assertEquals("Invalid Grade", grade.getGradeLetter());
    }

    @Test
    void SixtyShouldEqualCB(){
        Grade grade = new Grade(60);
        assertEquals("CB", grade.getGradeLetter());
    }

    @Test
    void twentySevenShouldNotPass(){
        Grade grade = new Grade(27);
        assertFalse(grade.isPassedGrade());
    }

    @Test
    void fortyFourShouldBeRetakeable(){
        Grade grade = new Grade(44);
        assertTrue(grade.isRetakableGrade());
    }
}
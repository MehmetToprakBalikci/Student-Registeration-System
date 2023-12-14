import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseSectionTest {

    @Test
    void compareShouldBeFalse() {
        CourseSection courseSection1 = new CourseSection(1,3);
        CourseSection courseSection2 = new CourseSection(1,3);
        assertFalse(courseSection1.compareAvailability(courseSection2));
    }

    @Test
    void comparisonShouldBeTrue() {
        CourseSection courseSection1 = new CourseSection(4,7);
        CourseSection courseSection2 = new CourseSection(4,3);
        assertTrue(courseSection1.compareAvailability(courseSection2));
    }

    @Test
    void sectionTwoFourShouldBeTuesday1130() {
        CourseSection courseSection1 = new CourseSection(2,4);
        assertEquals("Tuesday 11.30", courseSection1.getCourseSectionString());
    }

    @Test
    void sectionFiveTenShouldBeFriday1800() {
        CourseSection courseSection1 = new CourseSection(5,10);
        assertEquals("Friday 18.00", courseSection1.getCourseSectionString());
    }

    @Test
    void sectionSevenTwentyShouldBeInvalid() {
        CourseSection courseSection1 = new CourseSection(7,20);
        assertEquals("Invalid Day Invalid Hour", courseSection1.getCourseSectionString());
    }
}
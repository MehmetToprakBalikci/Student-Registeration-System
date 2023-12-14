import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    Lecturer lecturer1 = new Lecturer("Omer", "Korcak", "1000");
    Lecturer lecturer2 = new Lecturer("Betul", "Boz", "1001");
    Lecturer lecturer3 = new Lecturer("Mustafa", "Agaoglu", "1002");
    Assistant assistant1 = new Assistant();
    List<Course> preRequisites1 = new ArrayList<>();
    List<Course> preRequisites2 = new ArrayList<>();
    List<Course> preRequisites3 = new ArrayList<>();
    Course course1 = new Course("CS101", "Introduction to Computer Science", 3, 1, 3, 5, lecturer1, assistant1, preRequisites1,10);
    Course course2 = new Course("CS102", "Computer Programming 1", 5, 2, 3, 5, lecturer2, assistant1, preRequisites1, 30);
    Course course3 = new Course("CS103", "Computer Programming 2", 4, 3, 1, 4, lecturer3, assistant1, preRequisites2, 40);
    Course course4 = new Course("CS104", "Principal of Programming Languages", 3, 4, 4, 5, lecturer3, assistant1, preRequisites3, 65);
    Course course5 = new Course("CS105", "Systems Programming", 4, 4, 2, 8, lecturer1, assistant1, preRequisites1,50);

    List<Course> courses1 = new ArrayList<>();
    List<Course> courses2 = new ArrayList<>();
    List<Course> courses3 = new ArrayList<>();

    @Test
    void coursesSectionShouldConflict() {
        courses1.add(course1);
        courses1.add(course3);
        courses2.add(course4);
        assertNotNull(course2.checkCourseSection(courses1, courses3, courses2));
    }

    @Test
    void coursesSectionShouldNotConflict() {
        courses1.add(course1);
        courses1.add(course3);
        courses2.add(course4);
        assertNull(course5.checkCourseSection(courses1, courses3, courses2));
    }

    @Test
    void testCheckPreRequisite() {
        Grade grade1 = new Grade(75);
        Grade grade2 = new Grade(85);
        List<Grade> grades1 = new ArrayList<>();
        grades1.add(grade1);
        grades1.add(grade2);

        assertTrue(course2.checkPreRequisite(courses1, grades1));
    }

}
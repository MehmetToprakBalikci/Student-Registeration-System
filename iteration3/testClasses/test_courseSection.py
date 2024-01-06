import unittest
from CourseSection import CourseSection


class TestCourseSection(unittest.TestCase):
    def test_compare_should_be_false(self):
        course_section1 = CourseSection(1, 3)
        course_section2 = CourseSection(1, 3)
        self.assertFalse(course_section1.compare_availability(course_section2))

    def test_comparison_should_be_true(self):
        course_section1 = CourseSection(4, 7)
        course_section2 = CourseSection(4, 3)
        self.assertTrue(course_section1.compare_availability(course_section2))

    def test_section_two_four_should_be_tuesday_1130(self):
        course_section1 = CourseSection(2, 4)
        self.assertEqual(course_section1.__str__(), "Tuesday 11.30")

    def test_section_five_ten_should_be_friday_1800(self):
        course_section1 = CourseSection(5, 10)
        self.assertEqual(course_section1.__str__(), "Friday 18.00")

    def test_section_seven_twenty_should_be_invalid(self):
        course_section1 = CourseSection(7, 20)
        self.assertEqual(course_section1.__str__(), "Invalid Day Invalid Hour")


if __name__ == '__main__':
    unittest.main()

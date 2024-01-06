import unittest
from Grade import Grade


class TestGrade(unittest.TestCase):
    def test_is_retakable_grade(self):
        grade1 = Grade(45)
        self.assertTrue(grade1.is_retakable_grade())

        grade2 = Grade(60)
        self.assertFalse(grade2.is_retakable_grade())

    def test_is_passed_grade(self):
        grade1 = Grade(30)
        self.assertTrue(grade1.is_passed_grade())

        grade2 = Grade(40)
        self.assertFalse(grade2.is_passed_grade())

    def test_get_letter_grade(self):
        grade1 = Grade(95)
        self.assertEqual(grade1.get_letter_grade(), "AA")

        grade2 = Grade(75)
        self.assertEqual(grade2.get_letter_grade(), "BB")

        grade3 = Grade(45)
        self.assertEqual(grade3.get_letter_grade(), "DC")

        grade4 = Grade(20)
        self.assertEqual(grade4.get_letter_grade(), "FF")

    def test_get_invalid_grade(self):
        grade5 = Grade(105)
        self.assertEqual(grade5.get_letter_grade(), "Invalid Grade")

    def test_get_numerical_grade(self):
        grade = Grade(85)
        self.assertEqual(grade.get_numerical_grade(), 85)


if __name__ == '__main__':
    unittest.main()
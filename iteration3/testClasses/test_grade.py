import unittest
from Grade import Grade


class TestGrade(unittest.TestCase):
    def test_is_retakable_grade(self):
        grade1 = Grade(45)
        self.assertTrue(grade1.is_retakable_grade())

    def test_is_not_retakable_grade(self):
        grade2 = Grade(60)
        self.assertFalse(grade2.is_retakable_grade())

    def test_is_not_passed_grade(self):
        grade1 = Grade(30)
        self.assertFalse(grade1.is_passed_grade())

    def test_is_passed_grade(self):
        grade2 = Grade(40)
        self.assertTrue(grade2.is_passed_grade())

    def test_get_letter_grade_AA(self):
        grade1 = Grade(95)
        self.assertEqual(grade1.get_letter_grade(), "AA")

    def test_get_letter_grade_FF(self):
        grade4 = Grade(20)
        self.assertEqual(grade4.get_letter_grade(), "FF")

    def test_get_invalid_grade(self):
        grade5 = Grade(105)
        self.assertEqual(grade5.get_letter_grade(), "Invalid Grade")


if __name__ == '__main__':
    unittest.main()
import unittest
from Lecturer import Lecturer
from Assistant import Assistant
from Course import Course
from Grade import Grade


class TestCourse(unittest.TestCase):
    def setUp(self):
        self.lecturer1 = Lecturer("Omer", "Korcak", "1000")
        self.lecturer2 = Lecturer("Betul", "Boz", "1001")
        self.lecturer3 = Lecturer("Mustafa", "Agaoglu", "1002")
        self.assistant1 = Assistant()
        self.pre_requisites1 = []
        self.pre_requisites2 = []
        self.pre_requisites3 = []
        self.course1 = Course("CS101", "Introduction to Computer Science", 3, 1, 3, 5, self.lecturer1, self.assistant1, self.pre_requisites1, 10)
        self.course2 = Course("CS102", "Computer Programming 1", 5, 2, 3, 5, self.lecturer2, self.assistant1, self.pre_requisites1, 30)
        self.course3 = Course("CS103", "Computer Programming 2", 4, 3, 1, 4, self.lecturer3, self.assistant1, self.pre_requisites2, 40)
        self.course4 = Course("CS104", "Principal of Programming Languages", 3, 4, 4, 5, self.lecturer3, self.assistant1, self.pre_requisites3, 65)
        self.course5 = Course("CS105", "Systems Programming", 4, 4, 2, 8, self.lecturer1, self.assistant1, self.pre_requisites1, 50)

        self.tech_elective_course1 = Course("TE101", "Technical Elective 1", 3, 1, 3, 5, self.lecturer1, self.assistant1, [],10)
        self.tech_elective_course2 = Course("TE102", "Technical Elective 2", 3, 1, 3, 5, self.lecturer2, self.assistant1, [],10)
        self.tech_elective_course3 = Course("TE103", "Technical Elective 3", 3, 1, 3, 5, self.lecturer3, self.assistant1, [],10)

        self.courses1 = []
        self.courses2 = []
        self.courses3 = []

    def test_courses_section_should_conflict(self):
        self.courses1.extend([self.course1, self.course3])
        self.courses2.append(self.course4)

        self.assertIsNotNone(self.course2.check_course_section(self.courses1, self.courses3, self.courses2))

    def test_courses_section_should_not_conflict(self):
        self.courses1.extend([self.course1, self.course3])
        self.courses2.append(self.course4)

        self.assertIsNone(self.course5.check_course_section(self.courses1, self.courses3, self.courses2))

    def test_check_pre_requisite(self):
        grade1 = Grade(75)
        grade2 = Grade(85)
        grades1 = [grade1, grade2]

        self.assertFalse(self.course2.check_prerequisite(self.courses1, grades1))


    def test_check_technical_elective_count_within_threshold(self):
        self.courses1.extend([self.tech_elective_course1, self.tech_elective_course2])

        result = self.course1.check_technical_elective_count([], self.courses1, [])

        self.assertIsNone(result)

if __name__ == '__main__':
    unittest.main()
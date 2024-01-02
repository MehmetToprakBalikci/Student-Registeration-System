import logging


class Transcript:
    __list_of_courses, __list_of_grades = [], []
    __student_credits, __student_year, __gpa = int(0), int(1), float(0)

    def __init__(self, list_of_courses, list_of_grades):
        self.__list_of_courses = list_of_courses
        self.__list_of_grades = list_of_grades
        self.calculate_credit()
        self.calculate_gpa()
        self.calculate_year()

    # checks if a course is passed
    def check_passed_courses(self, checked_course) -> bool:
        is_available = True
        for course in self.__list_of_courses:
            if course.equals(checked_course):
                # if it finds the course then checks whether it
                # is retake-able or not then it returns false or true immediately
                is_available = course.is_retakable_grade()
                break

        return is_available

    # returns lists in form of string from transcript
    def get_student_transcript_string_list(self) -> list[str]:
        transcript_string = []
        transcript_string[0] = self.get_transcript_string()

        i = 1
        for i in transcript_string:
            transcript_string[i] = i + "-)" + str(self.__list_of_courses[i - 1]) + " "  # TODO check str function later
            transcript_string[i] += str(self.__list_of_grades[i - 1])

        return transcript_string

    # basically toString method
    def __str__(self):
        digit_precision = 2
        temp_gpa = round(self.__gpa, 2)
        out_string = "Cumulative Gpa: " + str(self.__gpa)
        out_string += "\nCumulative Credits: " + str(self.__student_credits)
        out_string += "\nCurrent Year: " + str(self.__student_year)
        return out_string

    # calculates the credits from taken courses
    def calculate_credit(self):
        if self.__list_of_courses is None:
            logging.warn("No courses for student found!")
            return 0
        elif len(self.__list_of_courses) == 0:
            logging.warn("No courses for student found!")
        if self.__list_of_grades is None:
            logging.warn("No grades for student found!")
            return 0
        elif len(self.__list_of_grades) == 0:
            logging.warn("No grades for student found!")

        for i in range(len(self.__list_of_courses)):
            if self.__list_of_grades[i].get_numerical_grade() >= 35:
                self.__student_credits += self.__list_of_courses.get_course_credit()

    # calculates the gpa from grades
    def calculate_gpa(self):
        total_credit = 0
        if self.__list_of_courses is None:
            logging.warn("No courses for student found!")
            return 0
        elif len(self.__list_of_courses) == 0:
            logging.warn("No courses for student found!")
        if self.__list_of_grades is None:
            logging.warn("No courses for student found!")
        elif len(self.__list_of_grades) == 0:
            logging.warn("No courses for student found!")

        weighted_values = []
        for i in range(len(self.__list_of_grades)):
            credit = self.__list_of_courses[i].getCourseCredit()
            grade = self.__list_of_grades[i].getNumericalGrade()
            weighted_values.append(credit * grade)
            total_credit += credit

        total_weighted_values = 0

        for value in weighted_values:
            total_weighted_values += weighted_values

        self.__gpa = float(total_weighted_values) / (float(total_credit) * 25)

    # calculates the year from credits and gpa
    def calculate_year(self):
        if self.__student_credits > 90:
            self.__student_year = 4
        elif self.__student_credits > 60:
            self.__student_year = 3
        elif self.__student_credits > 30:
            self.__student_year = 2

        if self.__student_year <= 3 <= self.__gpa:
            self.__student_year += 1

    # getters
    def get_gpa(self):
        return self.__gpa

    def get_student_year(self):
        return self.__student_year

    def get_student_credit(self):
        return self.__student_credits

    def get_list_of_grades(self):
        return self.__list_of_grades

    def get_list_of_courses(self):
        return self.__list_of_courses

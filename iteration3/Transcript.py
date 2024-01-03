import logging
from Course import Course


class Transcript:
    __list_of_courses = []
    __list_of_grades = []
    __student_credits: int = 0
    __student_year: int = 1
    __gpa: float = 0

    def __init__(self, list_of_courses, list_of_grades):
        self.__list_of_courses = list_of_courses
        self.__list_of_grades = list_of_grades
        self.__calculate_credit()
        self.__calculate_gpa()
        self.__calculate_year()

    # checks if a course is passed
    def check_passed_courses(self, checked_course: Course) -> bool:
        is_available = True
        for i in range(len(self.__list_of_courses)):
            if self.__list_of_courses[i].equals(checked_course):
                # if it finds the course then checks whether it
                # is retake-able or not then it returns false or true immediately
                is_available = self.__list_of_grades[i].is_retakable_grade()
                break

        return is_available

    # returns lists in form of string from transcript
    def get_student_transcript_string_list(self) -> list[str]:
        transcript_string = [self.__str__()]
        for i in range(len(self.__list_of_courses)):
            transcript_string.append(str(i+1) + "-)" + self.__list_of_courses[i - 1].__str__() + " " +
                                     self.__list_of_grades[i - 1].__str__())

        return transcript_string

    # override str func
    def __str__(self):
        digit_precision = 2
        temp_gpa = round(self.__gpa, digit_precision)
        out_string = "Cumulative Gpa: " + str(temp_gpa)
        out_string += "\nCumulative Credits: " + str(self.__student_credits)
        out_string += "\nCurrent Year: " + str(self.__student_year)
        return out_string

    # calculates the credits from taken courses
    def __calculate_credit(self):
        if self.__list_of_courses is None:
            logging.warning("No courses for student found!")
            return 0
        elif len(self.__list_of_courses) == 0:
            logging.warning("No courses for student found!")
        if self.__list_of_grades is None:
            logging.warning("No grades for student found!")
            return 0
        elif len(self.__list_of_grades) == 0:
            logging.warning("No grades for student found!")

        for i in range(len(self.__list_of_courses)):
            if self.__list_of_grades[i].get_numerical_grade() >= 35:
                self.__student_credits += self.__list_of_courses[i].get_course_credit()

    # calculates the gpa from grades
    def __calculate_gpa(self):
        total_credit = 0
        if self.__list_of_courses is None:
            logging.warning("No courses for student found!")
            return 0
        elif len(self.__list_of_courses) == 0:
            logging.warning("No courses for student found!")
        if self.__list_of_grades is None:
            logging.warning("No courses for student found!")
        elif len(self.__list_of_grades) == 0:
            logging.warning("No courses for student found!")

        weighted_values = []
        for i in range(len(self.__list_of_grades)):
            credit = self.__list_of_courses[i].get_course_credit()
            grade = self.__list_of_grades[i].get_numerical_grade()
            weighted_values.append(credit * grade)
            total_credit += credit

        total_weighted_values = 0

        for i in range(len(weighted_values)):
            total_weighted_values += weighted_values[i]

        self.__gpa = float(total_weighted_values) / (float(total_credit) * 25)

    # calculates the year from credits and gpa
    def __calculate_year(self):
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

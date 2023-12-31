import logging

from CourseSection import CourseSection


# This class only has one __init__ !!
# use this with None and 0 if you wish to initialize this class without assistant or capacity!!

class Course:
    __course_code, __course_name, __type = "", "", ""
    __lecturer, __assistant, __section = None, None, None
    __course_credit, __course_year, __capacity, __number_of_students = int(0)
    __prerequisite: list = []

    # Just use this constructor with assistant and capacity, if assistant and capacity are unused give None and 0!!
    def __init__(self, course_code, course_name, course_credit, course_year, section1, section2, lecturer, prerequisite,
                 assistant, capacity):
        self.__course_code = course_code
        self.__course_name = course_name
        self.__course_credit = course_credit
        self.__course_year = course_year
        self.__section = CourseSection(section1, section2)
        self.__lecturer = lecturer
        self.__prerequisite = prerequisite
        self.__capacity = capacity
        self.__assistant = assistant
        self.set_type("n")

    def check_course_section(self, registration_complete_courses: list,
                             registration_waiting_courses: list,
                             cancel_waiting_courses: list):
        for course in registration_complete_courses:
            if not isinstance(course, Course):
                logging.error("course object in registration_complete_courses not an instance of Course class!")
            else:
                if self.equals(course):
                    return ("This course:(" + course.get_course_string() +
                            ")" + "already exist in completedRegistrationCourses.Try to select another course")
                if not course.get_course_section().compare_availability(self.__section):
                    return ("This course is conflicting with the time of the course : " + course.get_course_string()
                            + " inside your courses that are registered!")
        for course in registration_waiting_courses:
            if not isinstance(course, Course):
                logging.error("course object in registration_waiting_courses not an instance of Course class!")
            else:
                if self.equals(course):
                    return ("This course:(" + course.get_course_string() +
                            ")" + "already exist in registrationWaitingCourses.Try to select another course")
                if not course.get_course_section().compare_availability(self.__section):
                    return ("This course is conflicting with the time of the course : " + course.get_course_string()
                            + " inside your courses that are waiting to be registered!")
        for course in cancel_waiting_courses:
            if not isinstance(course, Course):
                logging.error("course object in cancel_waiting_courses not an instance of Course class!")
            else:
                if self.equals(course):
                    return ("This course:(" + course.get_course_string() +
                            ")" + "already exist in cancelWaitingCourses.Try to select another course")
                if not course.get_course_section().compare_availability(self.__section):
                    return ("This course is conflicting with the time of the course : " + course.get_course_string()
                            + " inside your courses that are waiting to be cancelled!")
        if self.is_full():
            return "this course's capacity full!."
        return None


    def check_technical_elective_count(self, registration_complete_courses: list,
                                       registration_waiting_courses: list,
                                       cancel_waiting_courses: list):
        amount: int = 0
        threshold: int = 5
        for course in registration_waiting_courses:
            if not isinstance(course, Course):
                logging.error("course object in registration_waiting_courses not an instance of Course class!")
            elif course.__type == "t":
                amount += 1
        for course in registration_complete_courses:
            if not isinstance(course, Course):
                logging.error("course object in registration_complete_courses not an instance of Course class!")
            elif course.__type == "t":
                amount += 1
        for course in cancel_waiting_courses:
            if not isinstance(course, Course):
                logging.error("course object in cancel_waiting_courses not an instance of Course class!")
            elif course.__type == "t":
                amount += 1
        if amount >= threshold:
            return ("This course exceeds the Technical elective course count."
                    " Try cancelling another Technical elective or waiting for the cancellation to be accepted.")
        else:
            return None

    def check_non_technical_elective_count(self, registration_complete_courses: list,
                                           registration_waiting_courses: list,
                                           cancel_waiting_courses: list):
        amount: int = 0
        threshold: int = 3
        for course in registration_waiting_courses:
            if not isinstance(course, Course):
                logging.error("course object in registration_waiting_courses not an instance of Course class!")
            elif course.__type == "nt":
                amount += 1
        for course in registration_complete_courses:
            if not isinstance(course, Course):
                logging.error("course object in registration_complete_courses not an instance of Course class!")
            elif course.__type == "nt":
                amount += 1
        for course in cancel_waiting_courses:
            if not isinstance(course, Course):
                logging.error("course object in cancel_waiting_courses not an instance of Course class!")
            elif course.__type == "nt":
                amount += 1
        if amount >= threshold:
            return ("This course exceeds the Non-Technical elective course count."
                    " Try cancelling another Non-Technical elective or waiting for the cancellation to be accepted.")
        else:
            return None

    def check_prerequisite(self, completed_courses: list, grade_list: list) -> bool:
        if isinstance(self.__prerequisite, Course):
            if self.__prerequisite is None:
                return True
            if not self.__prerequisite:
                return True

        copy = self.__prerequisite

        for course in completed_courses:
            for i in range(len(copy)):
                prerequisite_current: Course = copy[i]
                if course.equals(prerequisite_current) and grade_list[i].is_passed_grade():
                    copy.remove(prerequisite_current)
            if not copy:
                return True
        return False

    def check_year_matching(self, year: int) -> bool:
        return self.__course_year <= year

    def equals(self, c2) -> bool:
        if isinstance(c2, Course):
            return (c2 is not None and
                    self.__course_name == c2.get_course_name())
        else:
            return False

    def set_type(self, ty: str):
        if ty == "t":
            self.__type = "t"
        elif ty == "nt":
            self.__type = "nt"
        elif ty == "n":
            self.__type = "n"
        else:
            logging.error("Type does not exist! instead try:\nt, nt or n.")

    def get_course_name(self):
        return self.__course_name

    def get_course_type(self):
        return self.__type

    def get_course_credit(self):
        return self.__course_credit

    def get_course_code(self):
        return self.__course_code

    def get_course_section(self):
        return self.__section

    def increment_student_amount(self):
        self.__number_of_students += self.__number_of_students

    def decrement_student_amount(self):
        self.__number_of_students -= self.__number_of_students

    def is_full(self):
        return self.__number_of_students >= self.__capacity

    def get_course_string(self):
        return self.__course_code + " " + self.__course_name

    def course_status(self):
        return "NumberOfStudent: " + self.__number_of_students + " " + "Capacity: " + self.__capacity

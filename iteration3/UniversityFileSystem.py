import json
import logging
import os

from Student import Student
from Course import Course
from Advisor import Advisor
from Assistant import Assistant
from Lecturer import Lecturer
from Transcript import Transcript
from Assistant import Assistant


def get_assistant_id(course_json):
    pass


class UniversityFileSystem:
    SINGLETON_UNIVERSITY_FILE_SYSTEM = None

    def __init__(self):
        self.__PERSON_LIST = []
        self.__SYSTEM_COURSES = []
        self.__COURSES_PREREQUISITES_CODES = []
        self.__COURSES_LECTURER_IDS = []
        self.__COURSES_ASSISTANT_IDS = []
        self.__STUDENTS_CANCEL_WAITING_COURSE_CODES = []
        self.__STUDENTS_REGISTRATION_COMPLETE_COURSES = []
        self.__STUDENTS_REGISTRATION_WAITING_COURSES = []
        self.__STUDENTS_ADVISOR_IDS_LIST = []
        self.__ADVISORS_STUDENT_ID_LIST = []

    @classmethod
    def get_instance(cls):
        if cls.SINGLETON_UNIVERSITY_FILE_SYSTEM is None:
            cls.SINGLETON_UNIVERSITY_FILE_SYSTEM = UniversityFileSystem()
        return cls.SINGLETON_UNIVERSITY_FILE_SYSTEM

    def __load_files(self):
        self.__read_courses()
        self.__read_students()
        self.__read_staffs()
        # advisor object will be added;
        self.__update_courses()
        # fill to remain parts of student
        self.__update_students()
        self.__set_each_course_student_number()
        self.__update_advisors()

        logging.info("all files  were successfully read ")

    def __read_courses(self):
        directory_path = "iteration2/Courses"
        try:
            file_list = os.listdir(directory_path)
            if not file_list:
                print("Check the Courses directory position!!")
                logging.error("Check the Courses directory position!!")
                return

            for file_name in file_list:
                file_path = os.path.join(directory_path, file_name)
                with open(file_path, '-r') as file:
                    course_json = json.load(file)

                    # Getting course attributes
                    course_code = course_json.get("courseCode")
                    course_name = course_json.get("courseName")
                    course_credit = course_json.get("courseCredit")
                    course_capacity = course_json.get("courseCapacity")
                    course_year = course_json.get("courseYear")
                    lecturer_id = course_json.get("lecturerId")
                    assistant_id = course_json.get("assistantId")
                    course_day = course_json.get("courseDay")
                    course_hour = course_json.get("courseSection")
                    prerequisites = course_json.get("prerequisites", [])
                    course_type = course_json.get("courseType", "")

                    current_courses_prerequisites_codes = list(map(str, prerequisites))
                    self.__COURSES_PREREQUISITES_CODES.append(current_courses_prerequisites_codes)
                    self.__COURSES_LECTURER_IDS.append(lecturer_id)
                    self.__COURSES_ASSISTANT_IDS.append(assistant_id)

                    # create course object TODO: course constructor will be added
                    course = Course(course_code, course_name, int(course_credit), int(course_year), int(course_hour),
                                    Lecturer(), Assistant(), [], int(course_capacity))
                    if course_type in {"t", "nt"}:
                        course.set_type(course_type)

                    self.__SYSTEM_COURSES.append(course)

        except (IOError, json.JSONDecodeError) as e:
            raise RuntimeError(e)

    # TODO: read students method will be filled -> assigned to Muhammed Enes Gökdeniz
    def __read_students(self):
        pass

    # TODO: read staffs method will be filled ->assigned to Muhammed Enes Gökdeniz
    def __read_staffs(self):
        pass

    def __update_courses(self):
        lecturers = self.__get_lecturers()
        ordered_lecturers = self.__get_ordered_lecturer_list(lecturers)
        index = 0
        for course in self.__SYSTEM_COURSES:
            lecturer = ordered_lecturers[index]
            course.set_lecturer(lecturer)
            index += 1

    def __update_students(self):
        self.__update_students_advisor(self.__ADVISORS_STUDENT_ID_LIST)
        self.__update_students_waiting_course_lists(self.__STUDENTS_REGISTRATION_WAITING_COURSES)
        self.__update_students_registration_complete_course_lists(self.__STUDENTS_REGISTRATION_COMPLETE_COURSES)
        self.__update_students_cancel_waiting_course_lists(self.__STUDENTS_CANCEL_WAITING_COURSE_CODES)
        # no need to that method: design issue !!
        # TODO: get_available_courses method should be implemented in student class
        # self.update_students_available_courses()
        pass

    def __set_each_course_student_number(self):
        for course in self.__SYSTEM_COURSES:
            for student in self.__get_students():
                if student.is_taking_course(course):
                    course.increase_student_number()

    def __update_advisors(self):
        advisors = self.__get_advisors()
        index = 0
        for advisor in advisors:
            # ["101", "102", "103"]
            current_advisors_student_string_list = self.__ADVISORS_STUDENT_ID_LIST[index]
            # [s1, s2, s3]
            current_advisors_student_string_list = self.__get_student_list(current_advisors_student_string_list)
            advisor.set_student_list(current_advisors_student_string_list)
            index += 1

    def __get_students(self):
        return [person for person in self.__PERSON_LIST if isinstance(person, Student)]

    def __get_advisors(self):
        return [person for person in self.__PERSON_LIST if isinstance(person, Advisor)]

    def __get_student_list(self, current_advisors_student_string_list):
        students = []
        for student_id in current_advisors_student_string_list:
            student = self.__get_student(student_id)
            students.append(student)
        return students

    def __get_student(self, student_id):
        students = self.__get_students()
        for student in students:
            if student.get_student_id() == student_id:
                return student

        return None

    def __update_students_advisor(self, advisors_student_id_list):
        students = self.__get_students()
        index = 0
        for student in students:
            current_students_advisor_id = advisors_student_id_list[index]
            advisor = self.__find_advisor(current_students_advisor_id)
            student.set_current_advisor(advisor)
            index += 1

    def __find_advisor(self, current_students_advisor_id):
        advisors = self.__get_advisors()
        for advisor in advisors:
            if advisor.get_lecturer_id() == current_students_advisor_id:
                return advisor

        return None

    def __update_students_waiting_course_lists(self, students_registration_waiting_courses):
        students = self.__get_students()
        index = 0
        for student in students:
            course_waiting_string_list = students_registration_waiting_courses[index]
            student.set_registration_waiting_courses(course_waiting_string_list)
            index += 1

    def __update_students_registration_complete_course_lists(self, students_registration_complete_courses):
        students = self.__get_students()
        index = 0
        for student in students:
            course_waiting_string_list = students_registration_complete_courses[index]
            student.set_registration_waiting_courses(course_waiting_string_list)
            index += 1

    def __update_students_cancel_waiting_course_lists(self, students_cancel_waiting_course_codes):
        students = self.__get_students()
        index = 0
        for student in students:
            course_waiting_string_list = students_cancel_waiting_course_codes[index]
            student.set_registration_waiting_courses(course_waiting_string_list)
            index += 1

    def __get_lecturers(self):
        return [person for person in self.__PERSON_LIST if isinstance(person, Lecturer)]

    def __get_ordered_lecturer_list(self, lecturers):
        ordered_lecturers = []
        for lecturer_id in self.__COURSES_LECTURER_IDS:
            lecturer = self.__get_lecturer(lecturers, lecturer_id)
            ordered_lecturers.append(lecturer)
        return ordered_lecturers

    def __get_lecturer(self, lecturers, lecturer_id):
        for lecturer in lecturers:
            if lecturer.get_lecturer_id == lecturer_id:
                return lecturer

        logging.warning("CHECK BOTH THE ADVISOR ID ON COURSE PART AND ADVISOR!! UNMATCH")
        return None

    def __get_signed_person(self, user_info, current_controller):
        user = None
        error_code = self.__check_user_name_password_length(user_info)
        user_name = user_info[0]
        password = user_info[1]
        if error_code == 0:
            for current_person in self.__PERSON_LIST:
                try:
                    user = current_person
                    if user.compare_credentials(user_name, password):
                        return user
                except AttributeError:
                    pass  # Catch the attribute error when current object is not user

        else:
            if error_code == 1:
                current_controller.print_error_message("Username is too long")
                logging.error("Username is too long")
            elif error_code == 2:
                current_controller.print_error_message("Password is too long")
                logging.error("Password is too long")
            return None

        current_controller.print_error_message("\nUsername or Password mismatch!")
        logging.error("Username or Password mismatch!")
        return None

    def __check_user_name_password_length(self, user_info):
        user_name_len = len(user_info[0])
        password_len = len(user_info[1])
        if user_name_len > 20:
            return 1
        if password_len > 25:
            return 2
        return 0  # valid userName and password

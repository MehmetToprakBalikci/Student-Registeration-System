import json
import os
import logging


class jsonwriter:  # Singleton class to write the updated student data into json files
    _instance = None
    __person = None

    # regular init, just sets person to self
    def __init__(self, person):
        self.__person = person

    # override new method to make singleton
    # * def __new__(cls, person):
    #    if cls._instance is None:
    #       cls._instance = super(jsonwriter, cls).__new__(cls)
    #   return cls._instance

    @classmethod
    def get_instance(cls, user):
        if cls._instance is None:
            cls._instance = jsonwriter(user)
        return cls._instance

    def update_student_file(self, student):
        file_directory = "Students"  # directory for all student files
        file_list = os.listdir(file_directory)  # list all files in directory into files_list
        file_name = str(student.get_student_id())  # file currently in search
        file = None
        for f in file_list:  # iterate through list to find the file in search
            if file_name in f:
                file_path = os.path.join(file_directory, f)
                file = open(file_path)
                break

        if file is None:
            logging.warning("Student does not exist in the file system!")

        json_data = json.load(file)  # loads data to object from json file

        name = json_data["name"]  # load from file
        last_name = json_data["lastName"]
        user_name = student.get_user_name()
        password = student.get_password()
        student_id = student.get_student_id()
        courses = student.get_current_transcript().get_list_of_courses()
        grades = student.get_current_transcript().get_list_of_grades()
        adv_id = student.get_current_advisor().get_lecturer_id()
        t = student.get_current_transcript()
        class_level = str(t.get_student_year())

        cancel_waiting_courses, registration_complete_courses, registration_waiting_courses = [], [], []
        for course in student.get_cancel_waiting_courses():  # make lists and populate for use later
            if course is not None:
                cancel_waiting_courses.append(course)

        for course in student.get_registration_complete_courses():
            if course is not None:
                registration_complete_courses.append(course)

        for course in student.get_registration_waiting_courses():
            if course is not None:
                registration_waiting_courses.append(course)

        format_for_output = ""
        format_for_output += "{\n" + "  \"type\": \"student\",\n"
        format_for_output += "  \"name\": \"" + name + "\",\n"
        format_for_output += "  \"lastName\": \"" + last_name + "\",\n"
        format_for_output += "  \"username\": \"" + user_name + "\",\n"
        format_for_output += "  \"password\": \"" + password + "\",\n"
        format_for_output += "  \"studentId\": \"" + student_id + "\",\n"

        format_for_output += "  \"Transcript\": {\n" + "    \"listOfCourses\": [\n      "

        for i in range(len(student.get_current_transcript().get_list_of_courses())):
            format_for_output += "\"" + (student.get_current_transcript().get_list_of_courses())[
                i].get_course_code() + "\""
            if i != len(student.get_current_transcript().get_list_of_courses()) - 1:
                format_for_output += ","
            else:
                format_for_output += "\n    ],\n"

        format_for_output += "    \"listOfGrades\": [\n      "
        for i in range(len(student.get_current_transcript().get_list_of_grades())):
            format_for_output += "\"" + (t.get_list_of_grades())[
                i].get_numerical_grade() + "\""
            if i != len(student.get_current_transcript().get_list_of_grades()) - 1:
                format_for_output += ","
            else:
                format_for_output += "\n    ],\n"

        format_for_output += "  },\n"
        format_for_output += "  \"advisorId\": \"" + adv_id + "\",\n"
        format_for_output += "  \"classLevel\": \"" + class_level + "\",\n"

        format_for_output += "  \"cancelWaitingCourses\": [\n"
        for i in range(len(cancel_waiting_courses)):
            format_for_output += "    \"" + cancel_waiting_courses[i] + "\""
            if i != len(cancel_waiting_courses) - 1:
                format_for_output += ",\n"
        format_for_output += "  \n  ],\n"

        format_for_output += "  \"registrationCompleteCourses\": [\n"
        for i in range(len(registration_complete_courses)):
            format_for_output += "    \"" + registration_complete_courses[i] + "\""
            if i != len(registration_complete_courses) - 1:
                format_for_output += ",\n"
        format_for_output += "  \n  ],\n"

        format_for_output += "  \"registrationWaitingCourses\": [\n"
        for i in range(len(registration_waiting_courses)):
            format_for_output += "    \"" + registration_waiting_courses[i] + "\""
            if i != len(registration_waiting_courses) - 1:
                format_for_output += ",\n"
        format_for_output += "  \n  ],\n"

        directory_write = "iteration3/Students/" + student_id + ".json"
        file_write = open(directory_write, "w")
        file_write.write(format_for_output)
        file_write.close()
        logging.info("Student file updated!")

    def update_student_files_as_advisor(self):
        from Advisor import Advisor
        if not isinstance(self.__person, Advisor):
            logging.info("Not an instance of Advisor!")
        else:
            for student in self.__person.get_student_list():
                self.update_student_file(student)

    def save_files(self):
        from Advisor import Advisor
        from Student import Student
        if isinstance(self.__person, Student):
            self.update_student_file(self.__person)  # the function takes person instead
            # of student this time! prepare other func accordingly
        else:
#            self.__person.__class__ = Advisor  # added as per the request of Tolga F. :)
            self.update_student_files_as_advisor()



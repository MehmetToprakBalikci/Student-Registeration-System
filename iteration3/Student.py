class Student:
    def __init__(self):
        self.available_courses = None
        self.student_id = None

    def is_taking_course(self, course):
        pass

    def set_current_advisor(self, advisor):
        pass

    def set_registration_waiting_courses(self, course_waiting_string_list):
        pass

    def get_available_courses(self, system_courses):
        for course in system_courses:
            if self.check_course_availability(course):
                self.available_courses.append(course)

    def check_course_availability(self, course):
        pass

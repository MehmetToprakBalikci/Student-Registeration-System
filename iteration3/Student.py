import logging
from Controller import Controller
from Person import Person
from User import User
from Message import Message


class Student(Person, User):

    # String name, String lastName, String username, String password, String studentID, Transcript currentTranscript, Advisor currentAdvisor
    def __init__(self, name, last_name, username, password, student_id, current_transcript, current_advisor):
        super().__init__(name, last_name)
        # private final String
        self.__USER_NAME = username
        # private final String
        self.__PASSWORD = password
        # private final String
        self.__STUDENT_ID = student_id
        # private final Transcript , Transcript of the student
        self.__CURRENT_TRANSCRIPT = current_transcript
        # private Advisor , Advisor of the student
        self.__current_advisor = current_advisor
        # private List<Course> , Courses that the student is able to register
        self.__current_available_courses = []
        # private List<Course> , Courses that are waiting to be registered
        self.__registration_waiting_courses = []
        # private List<Course> , Courses that finished registration
        self.__registration_complete_courses = []
        # private List<Course> , Courses that are waiting to be canceled
        self.__cancel_waiting_courses = []
        # private List<Message> sentMessages , Messages that are sent
        self.__sent_messages = []
        # private List<Message> receivedMessages, Messages that are recieved
        self.__received_messages = []

    def get_action_list(self):
        logging.info("Getting action string list")
        action_list = []
        action_list.append("\nSelect an action.")
        action_list.append("1) Check courses available to register.")
        action_list.append("2) Check your registered courses.")
        action_list.append("3) See courses waiting to be registered.")
        action_list.append("4) See courses waiting to be canceled.")
        action_list.append("5) User transcript.")
        action_list.append("6) Advisor information.")
        action_list.append("7) See messages.")
        action_list.append("8) Log out.")
        return action_list

    def __str__(self):
        return "Name: " + super().get_first_name() + super().get_last_name() + ", StudentID: " + self.get_student_id() + "."

    # Controller controller
    def start_actions(self):
        action_list = self.get_action_list()

        logging.info("Starting actions")
        # int
        action_number = Controller.get_instance().print_list_return_selection(action_list, -1)
        MAX_ACTION = 8

        while action_number != MAX_ACTION:
            if not isinstance(action_number, int):
                logging.error("Action selection is not a number!")
            try:
                self.run_user_action(action_number)
            except ValueError as e:
                Controller.get_instance().print_error_message(str(e))
                raise
            except TypeError as e1:
                Controller.get_instance().print_error_message(str(e1))
                raise
            # except Exception:
            # logging.critical("Something went wrong while running actions of student!")
            # Controller.get_instance().print_error_message("Something went wrong!")
            finally:
                action_number = Controller.get_instance().print_list_return_selection(action_list, -1)

    # int actionNumber, Controller controller
    def run_user_action(self, action_number):
        ##Student selection part
        logging.info("Trying to run the action selected by user")
        current_user_selection = None
        if action_number == 1:
            logging.info("Selected action = 1")

            current_user_selection = Controller.get_instance().print_list_return_selection(
                self.__get_course_return_list_string("Courses that are available to you select one:",
                                                     self.__current_available_courses), -1)
            if not isinstance(current_user_selection, int):
                logging.error("Course selection was not given an integer!")
                raise ValueError("Expected Integer Error")
            if current_user_selection != 1:
                current_course = self.__current_available_courses[current_user_selection - 2]
                current_user_selection = Controller.get_instance().print_list_return_selection(
                    self.__get_course_info_string(current_course), -1)
                if not isinstance(current_user_selection, int):
                    logging.error("Course selection was not given an integer!")
                    raise ValueError("Expected Integer Error")
                if current_user_selection == 1:  # return back to available course menu page
                    logging.info("Selected action in menu2 = 1")
                    self.run_user_action(1)
                elif current_user_selection == 2:  # register to the course
                    logging.info("Selected action in menu2 = 2")
                    course_sect_availability_str = current_course.check_course_section(
                        self.__registration_complete_courses, self.__registration_waiting_courses,
                        self.__cancel_waiting_courses)
                    if ((not (current_course.check_technical_elective_count(self.__registration_complete_courses,
                                                                            self.__registration_waiting_courses,
                                                                            self.__cancel_waiting_courses) is None)) and (
                            course_sect_availability_str is None)):
                        logging.debug("Technical elective check passed")
                        course_sect_availability_str = ""
                        course_sect_availability_str = current_course.check_technical_elective_count(
                            self.__registration_complete_courses, self.__registration_waiting_courses,
                            self.__cancel_waiting_courses)
                    elif ((not (current_course.check_non_technical_elective_count(self.__registration_complete_courses,
                                                                                  self.__registration_waiting_courses,
                                                                                  self.__cancel_waiting_courses) is None)) and (
                                  course_sect_availability_str is None)):
                        logging.debug("Non technical elective check passed")
                        course_sect_availability_str = ""
                        course_sect_availability_str = current_course.check_non_technical_elective_count(
                            self.__registration_complete_courses, self.__registration_waiting_courses,
                            self.__cancel_waiting_courses)
                    if course_sect_availability_str is None:
                        logging.debug("Checked course availability to be alright")
                        self.remove_element_from_current_available_courses(current_course)
                        self.__registration_waiting_courses.append(current_course)
                        Controller.get_instance().print_success_message(
                            current_course.__str__() + " has been sent to your advisor " + self.__current_advisor.get_first_name() + " " + self.__current_advisor.get_last_name())
                    else:
                        logging.debug("Checked course availability to be NOT alright")
                        Controller.get_instance().print_error_message(course_sect_availability_str)
                    self.run_user_action(1)
                elif current_user_selection == 3:  # see your course lecturer
                    logging.info("Selected action in menu2 = 3")
                    Controller.get_instance().print_list(self.__get_courses_lecturer_info(current_course))
                    self.run_user_action(1)
                elif current_user_selection == 4:  # see your course assistant
                    logging.info("Selected action in menu2 = 4")
                    Controller.get_instance().print_list(self.__get_courses_assistant_info(current_course))
                    self.run_user_action(1)
                elif current_user_selection == 5:  # return back to first page
                    logging.info("Selected action in menu2 = 5")
                    return
        elif action_number == 2:
            logging.info("Selected action = 2")
            current_user_selection = Controller.get_instance().print_list_return_selection(
                self.__get_course_return_list_string(
                    "Courses that have finalized registration, choose course to cancel:",
                    self.__registration_complete_courses), -1)
            if not isinstance(current_user_selection, int):
                logging.error("Course selection was not given an integer!")
                raise ValueError("Expected Integer Error")
            if current_user_selection != 1:
                current_course = self.__registration_complete_courses[current_user_selection - 2]
                self.remove_element_from_registration_complete_courses(current_course)
                self.__cancel_waiting_courses.append(current_course)
                Controller.get_instance().print_success_message(
                    current_course.__str__() + "is successfully added to cancelWaiting.\n")
        elif action_number == 3:
            logging.info("Selected action = 3")
            Controller.get_instance().print_list(
                self.__get_course_list_string("Courses that are waiting to be finalized by your advisor ",
                                              self.__registration_waiting_courses))
        elif action_number == 4:
            logging.info("Selected action = 4")
            Controller.get_instance().print_list(self.__get_course_list_string(
                "Courses that are waiting to be canceled by your " + str(self.__current_advisor),
                self.__cancel_waiting_courses))
        elif action_number == 5:
            logging.info("Selected action = 5")
            Controller.get_instance().print_list(self.__CURRENT_TRANSCRIPT.get_student_transcript_string_list())
        elif action_number == 6:
            logging.info("Selected action = 6")
            Controller.get_instance().print_list(self.__string_to_list(str(self.__current_advisor)))
        elif action_number == 7:
            while True:
                logging.info("Getting message selection list")
                message_list = ["", ""]
                message_menu_list = []
                message_menu_list.append("Select an action.")
                message_menu_list.append("1) See sent messages.")
                message_menu_list.append("2) See received messages.")
                message_menu_list.append("3) Send message to your advisor.")
                message_menu_list.append("4) Go back.")
                action_number = Controller.get_instance().print_list_return_selection(message_menu_list, -1)
                if not isinstance(action_number, int):
                    logging.error("Course selection was not given an integer!")
                    raise ValueError("Expected Integer Error")
                if action_number == 4:
                    logging.info("Returned from message seleciton list")
                    return
                elif action_number == 2:
                    logging.info("Selected action in message menu = 2")
                    while True:
                        received_messages_list = ["Received messages:"]
                        logging.info("Checking recieved messages")
                        if len(self.__received_messages) != 0:
                            logging.debug("There are recieved messages!")
                            i = 1
                            for current_recieved_message in self.__received_messages:
                                received_messages_list.append(str(i) + ") " + str(current_recieved_message))
                                i = i + 1
                            received_messages_list.append(str(i) + ") Go back.")
                            action_number = Controller.get_instance().print_list_return_selection(
                                received_messages_list, -1)
                            if not isinstance(action_number, int):
                                logging.error("Course selection was not given an integer!")
                                raise ValueError("Expected Integer Error")
                            if action_number == (len(received_messages_list) - 1):
                                logging.info("Returning back from recieved messages")
                                break
                            logging.info("Reading selected message from recieved messages")
                            message_list[0] = str(self.__received_messages[action_number - 1]) + "\n" + str(
                                self.__received_messages[action_number - 1].get_message())
                            message_list[1] = "1) Go back."
                            self.__received_messages[action_number - 1].read_message()
                            action_number = Controller.get_instance().print_list_return_selection(message_list, -1)
                            if not isinstance(action_number, int):
                                logging.error("Course selection was not given an integer!")
                                raise ValueError("Expected Integer Error")
                            if action_number == 1:
                                continue
                        else:
                            logging.debug("There are no recieved messages!")
                            received_messages_list[0] = "There is no received messages."
                            received_messages_list.append("1-) Go back.")
                            action_number = Controller.get_instance().print_list_return_selection(
                                received_messages_list, -1)
                            if not isinstance(action_number, int):
                                logging.error("Course selection was not given an integer!")
                                raise ValueError("Expected Integer Error")
                            break
                    continue
                elif action_number == 1:
                    logging.info("Selected action in message menu = 1")
                    while True:
                        sent_messages_list = ["Sent messages:"]
                        logging.info("Checking recieved messages")
                        if len(self.__sent_messages) != 0:
                            logging.debug("There are recieved messages!")
                            i = 1
                            for current_sent_message in self.__sent_messages:
                                sent_messages_list.append(str(i) + ") " + str(current_sent_message))
                                i = i + 1
                            sent_messages_list.append(str(i) + ") Go back.")
                            action_number = Controller.get_instance().print_list_return_selection(sent_messages_list,
                                                                                                  -1)
                            if not isinstance(action_number, int):
                                logging.error("Course selection was not given an integer!")
                                raise ValueError("Expected Integer Error")
                            if action_number == (len(sent_messages_list) - 1):
                                logging.info("Returning back from recieved messages")
                                break
                            else:
                                logging.info("Reading selected message from recieved messages")
                                message_list[0] = str(self.__sent_messages[action_number - 1]) + "\n" + str(
                                    self.__sent_messages[action_number - 1].get_message())
                                message_list[1] = "1) Go back."
                                action_number = Controller.get_instance().print_list_return_selection(message_list, -1)
                                if not isinstance(action_number, int):
                                    logging.error("Course selection was not given an integer!")
                                    raise ValueError("Expected Integer Error")
                                if action_number == 1:
                                    continue
                        else:
                            logging.debug("There are no recieved messages!")
                            sent_messages_list[0] = "There is no sent messages."
                            sent_messages_list.append("1-) Go back.")
                            action_number = Controller.get_instance().print_list_return_selection(sent_messages_list,
                                                                                                  -1)
                            if not isinstance(action_number, int):
                                logging.error("Course selection was not given an integer!")
                                raise ValueError("Expected Integer Error")
                            break
                    continue
                else:
                    logging.info("Selected action in message menu = 3")
                    message_info = Controller.get_instance().request_message_string()
                    if message_info is None:
                        logging.error("Given string was empty!")
                        raise ValueError("Empty String")
                    message = Message(message_info[0], message_info[1], self, self.__current_advisor)
                    self.send_message(message, self.__current_advisor)

    def __get_course_info_string(self, course):
        logging.debug("Getting course string menu")
        if course is None:
            logging.critical("Could not found the chosen course!")
            raise TypeError("The given course doesn't exist")
        course_info_string = []
        course_info_string.append("Select your course action")
        course_info_string.append("1-)Return back to available course menu page")
        course_info_string.append("2-)Register to the " + str(course))
        course_info_string.append("3-)See the course's Lecturer")
        course_info_string.append("4-)See the course's assistant")
        course_info_string.append("5-)Return back to first menu page")
        return course_info_string

    def __get_courses_assistant_info(self, current_course):
        logging.debug("Getting assistant string of the course")
        assistant_info = [""]
        if current_course.get_assistant() is None:
            assistant_info[0] = "There is no assistant assigned for this course"
        else:
            assistant_info[0] = str(current_course.get_assistant())
        return assistant_info

    def __get_courses_lecturer_info(self, current_course):
        logging.debug("Getting lecturer string of the course")
        if current_course.get_lecturer() is None:
            raise TypeError("No lecturer of the course")
        lecturer_info = [""]
        lecturer_info[0] = str(current_course.get_lecturer())
        return lecturer_info

    def __string_to_list(self, give_string):
        string_list = [""]
        string_list[0] = give_string
        return string_list

    def __get_course_return_list_string(self, title_string, courses_list):
        logging.debug(
            "Getting course strings of the given course list that is going to be used for asking the user for an input")
        course_list_string = ["", ""]
        course_list_string[0] = title_string
        course_list_string[1] = "1-)Return back"
        i = 2
        for current_course in courses_list:
            course_list_string.append(str(i) + "-)" + str(current_course))
            i = i + 1
        return course_list_string

    def __get_course_list_string(self, title_string, courses_list):
        logging.debug("Getting course strings of the given course list")
        course_list_string = [""]
        course_list_string[0] = title_string
        i = 1
        for current_course in courses_list:
            course_list_string.append(str(i) + "-)" + str(current_course))
            i = i + 1

        return course_list_string

    def check_course_availability(self, course):
        logging.debug("InMethodCCA: Checking if the course is available for student to register")
        is_available = True
        if course.get_type() == "nt":

            is_available = (course.check_prerequisite(
                self.__CURRENT_TRANSCRIPT.get_list_of_courses(), self.__CURRENT_TRANSCRIPT.get_list_of_grades())
                            and self.__CURRENT_TRANSCRIPT.check_passed_courses(course)
                            and (not self.__check_existence(course))
                            and (not course.is_full()))
        else:
            is_available = (course.check_year_matching(self.__CURRENT_TRANSCRIPT.get_student_year())
                            and (course.check_prerequisite(
                        self.__CURRENT_TRANSCRIPT.get_list_of_courses(),
                        self.__CURRENT_TRANSCRIPT.get_list_of_grades()))
                            and self.__CURRENT_TRANSCRIPT.check_passed_courses(course)
                            and (not self.__check_existence(course))
                            and (not course.is_full()))
        if is_available:
            logging.debug("InMethodCCA: The course is available!")
        else:
            logging.debug("InMethodCCA: The course isnt available!")
        return is_available

    def __check_existence(self, course):
        logging.debug("InMethodCE: Checking if the course exists for this student")
        exists = False
        exists = (self.__check_list_for_course(self.__cancel_waiting_courses, course)
                  or self.__check_list_for_course(self.__registration_complete_courses, course)
                  or self.__check_list_for_course(self.__registration_waiting_courses, course))
        if exists:
            logging.debug("InMethodCE: The course exists!")
        else:
            logging.debug("InMethodCE: The course doesnt exist!")
        return exists

    # Returns true if it finds a course in the list
    def __check_list_for_course(self, course_list, course):
        logging.debug("InMethodCLFC: Checking the list if the course exists inside!")
        if course_list == None or len(course_list) == 0:
            logging.debug("Given course list doesnt exist or empty!")
            return False

        for current in course_list:
            if course.equals(current):
                return True
        return False

    def compare_credentials(self, username, password):
        if self.__USER_NAME is None or self.__PASSWORD is None:
            return False
        return self.__USER_NAME == username and self.__PASSWORD == password

    def get_available_courses(self, system_courses):
        available_courses = []
        if system_courses is None or len(system_courses) == 0:
            logging.warning("Given course list doesnt exist or empty!")
            return None
        for course in system_courses:
            if self.check_course_availability(course):
                available_courses.append(course)

        return available_courses

    def send_message(self, message, user):
        logging.info("Sending message to advisor")
        self.__sent_messages.append(message)
        user.receive_message(message)

    def receive_message(self, message):
        logging.info("Recieving message from advisor")
        self.__received_messages.append(message)

    def set_current_advisor(self, current_advisor):
        self.__current_advisor = current_advisor

    def get_student_id(self):
        return self.__STUDENT_ID

    def is_taking_course(self, course):
        return course in self.__registration_complete_courses or course in self.__cancel_waiting_courses

    def set_registration_waiting_courses(self, __registration_waiting_courses):
        self.__registration_waiting_courses = __registration_waiting_courses

    def set_registration_complete_courses(self, registration_complete_courses):
        self.__registration_complete_courses = registration_complete_courses

    def set_cancel_waiting_courses(self, cancel_waiting_courses):
        self.__cancel_waiting_courses = cancel_waiting_courses

    def get_current_advisor(self):
        return self.__current_advisor

    def set_current_available_courses(self, current_available_courses):
        self.__current_available_courses = current_available_courses

    def get_current_transcript(self):
        return self.__CURRENT_TRANSCRIPT

    def get_registration_complete_courses(self):
        return self.__registration_complete_courses

    def get_user_name(self):
        return self.__USER_NAME

    def get_password(self):
        return self.__PASSWORD

    def remove_element_from_current_available_courses(self, course):
        return self.__current_available_courses.remove(course)

    def remove_element_from_registration_waiting_courses(self, course):
        return self.__registration_waiting_courses.remove(course)

    def remove_element_from_registration_complete_courses(self, course):
        return self.__registration_complete_courses.remove(course)

    def remove_element_from_cancel_waiting_courses(self, course):
        return self.__cancel_waiting_courses.remove(course)

    def add_element_to_current_available_courses(self, course):
        self.__current_available_courses.append(course)

    def add_element_to_registration_waiting_courses(self, course):
        self.__registration_waiting_courses.append(course)

    def add_element_to_registration_complete_courses(self, course):
        self.__registration_complete_courses.append(course)

    def add_element_to_cancel_waiting_courses(self, course):
        self.__cancel_waiting_courses.append(course)

    def get_cancel_waiting_courses(self):
        return self.__cancel_waiting_courses

    def get_registration_waiting_courses(self):
        return self.__registration_waiting_courses

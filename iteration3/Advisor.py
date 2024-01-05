import logging
from Controller import Controller
from Message import Message
from Lecturer import Lecturer
from User import User


class Advisor(Lecturer, User):

    def __init__(self, name="", last_name="", username="", password="", lecturer_id="", student_list=None):
        super().__init__(name, last_name, lecturer_id)
        self.__user_name = username
        self.__password = password
        if student_list is None:
            self.__student_list = []
        else:
            self.__student_list = student_list
        self.__sent_messages = []
        self.__received_messages = []

    def compare_credentials(self, username, password):
        if self.__user_name is None or self.__password is None:
            return False
        return self.__user_name == username and self.__password == password

    def __str__(self):
        return f"Advisor: {self.get_first_name()} {self.get_last_name()}"

    def start_actions(self):
        logging.info("Starting actions")
        while True:
            action_list = self.get_action_list()
            action_number = Controller.get_instance().print_list_return_selection(action_list, -1)
            if action_number != 3:
                self.run_user_action(action_number)
            else:
                return

    def get_action_list(self):
        logging.info("Getting action string list")
        return ["\nSelect an action.", "1) See students.", "2) See messages.", "3) Log out."]

    def run_user_action(self, action_number):
        if action_number == 1:
            logging.info("1) See students selected")
            self.__see_students()
        elif action_number == 2:
            logging.info("2) See messages selected")
            self.__see_messages()
        elif action_number == 3:
            logging.info("3) Log out selected")
            return

    def __see_students(self):
        while True:
            student_size = len(self.__student_list)
            student_menu_list = ["Select a student for action."]
            student_menu_list.extend([f"{i + 1}) {self.__student_list[i]}" for i in range(student_size)])
            student_menu_list.append(f"{student_size + 1}) Go back.")

            action_number = Controller.get_instance().print_list_return_selection(student_menu_list, -1)
            if action_number == student_size + 1:
                logging.info(f"{action_number}) Go back selected")
                return
            logging.info(f"{action_number}) Student selected")
            selected_student = self.__student_list[action_number - 1]
            self.__process_student_actions(selected_student)

    def __process_student_actions(self, selected_student):
        control_flag = True
        while control_flag:
            registration_waiting_courses_size = len(selected_student.get_registration_waiting_courses())
            cancel_waiting_courses_size = len(selected_student.get_cancel_waiting_courses())

            course_menu_list = [f"{selected_student}\nChoose a course for accept or reject the request."]

            course_menu_list.extend([f"Registration waiting courses:\n{i + 1}) {selected_student.get_registration_waiting_courses()[i]}" for i in range(registration_waiting_courses_size)])
            course_menu_list.extend([f"Cancel waiting courses:\n{i + 1 + registration_waiting_courses_size}) {selected_student.get_cancel_waiting_courses()[i]}" for i in range(cancel_waiting_courses_size)])

            course_menu_list.append(f"{registration_waiting_courses_size + cancel_waiting_courses_size + 1}) Go back.")

            action_number = Controller.get_instance().print_list_return_selection(course_menu_list, -1)

            if action_number == registration_waiting_courses_size + cancel_waiting_courses_size + 1:
                logging.info(f"{action_number}) Go back selected")
                control_flag = False
                continue
            logging.info(f"{action_number}) Course selected")
            selected_course = (selected_student.get_registration_waiting_courses() + selected_student.get_cancel_waiting_courses())[action_number - 1]
            self.__process_course_actions(selected_student, selected_course, action_number)

    def __process_course_actions(self, selected_student, selected_course, action_number):

        registration_waiting_courses_size = len(selected_student.get_registration_waiting_courses())

        # If the course is registration waiting course
        if action_number <= registration_waiting_courses_size:

            course_action_menu_list = [f"{selected_student}\n{selected_course}", "1) Accept registration request.", "2) Reject registration request.", "3) Go back."]
            action_number = Controller.get_instance().print_list_return_selection(course_action_menu_list, -1)

            if action_number == 1:
                logging.info("1) Registration request accepted")
                selected_student.remove_element_from_registration_waiting_courses(selected_course)
                selected_student.add_element_to_registration_complete_courses(selected_course)
                selected_course.increment_student_amount()
            elif action_number == 2:
                logging.info("2) Registration request rejected")
                selected_student.remove_element_from_registration_waiting_courses(selected_course)
                selected_student.add_element_to_current_available_courses(selected_course)

            else:
                return
        # If the course is cancel waiting course
        else:
            course_action_menu_list = [f"{selected_student}\n{selected_course}", "1) Accept cancel request.", "2) Reject cancel request.", "3) Go back."]

            action_number = Controller.get_instance().print_list_return_selection(course_action_menu_list, -1)

            if action_number == 1:
                logging.info("1) Cancel request accepted")
                selected_student.remove_element_from_cancel_waiting_courses(selected_course)
                selected_student.add_element_to_current_available_courses(selected_course)
                selected_course.decrement_student_amount()
            elif action_number == 2:
                logging.info("2) Cancel request rejected")
                selected_student.remove_element_from_cancel_waiting_courses(selected_course)
                selected_student.add_element_to_registration_complete_courses(selected_course)

            else:
                return

    def __see_messages(self):
        while True:
            message_menu_list = [
                "Select an action.",
                "1) See sent messages.",
                "2) See received messages.",
                "3) Send message to a student.",
                "4) Go back."
            ]

            action_number = Controller.get_instance().print_list_return_selection(message_menu_list, -1)

            if action_number == 4:
                logging.info(f"{action_number}) Go back selected")
                return
            elif action_number == 2:
                logging.info(f"{action_number}) See received messages selected")
                self.__see_received_messages()
            elif action_number == 1:
                logging.info(f"{action_number}) See sent messages selected")
                self.__see_sent_messages()
            else:
                logging.info(f"{action_number}) Send message to a student selected")
                self.__send_message_to_student()

    def __see_received_messages(self):
        while True:
            received_messages_list = [f"Received messages:"]

            if self.__received_messages:
                received_messages_list.extend([f"{i + 1}) {self.__received_messages[i]}" for i in range(len(self.__received_messages))])
                received_messages_list.append(f"{len(self.__received_messages) + 1}) Go back.")

                action_number = Controller.get_instance().print_list_return_selection(received_messages_list, -1)

                if action_number == len(self.__received_messages) + 1:
                    logging.info(f"{action_number}) Go back selected")
                    break
                logging.info(f"{action_number}) Message selected")
                message = self.__received_messages[action_number - 1]
                message_list = [f"{message}\n\n{message.get_message()}", "1) Go back."]
                action_number = Controller.get_instance().print_list_return_selection(message_list, -1)
                if action_number == 1:
                    return
            else:
                received_messages_list[0] = "There is no received messages."
                action_number = Controller.get_instance().print_list_return_selection(received_messages_list, -1)
                break

    def __see_sent_messages(self):
        while True:
            sent_messages_list = [f"Sent messages:"]

            if self.__sent_messages:
                sent_messages_list.extend([f"{i + 1}) {self.__sent_messages[i]}" for i in range(len(self.__sent_messages))])
                sent_messages_list.append(f"{len(self.__sent_messages) + 1}) Go back.")

                action_number = Controller.get_instance().print_list_return_selection(sent_messages_list, -1)

                if action_number == len(self.__sent_messages) + 1:
                    logging.info(f"{action_number}) Go back selected")
                    break
                logging.info(f"{action_number}) Message selected")
                message = self.__sent_messages[action_number - 1]
                message_list = [f"{message}\n\n{message.get_message()}", "1) Go back."]
                action_number = Controller.get_instance().print_list_return_selection(message_list, -1)
                if action_number == 1:
                    return
            else:
                sent_messages_list[0] = "There is no sent messages."
                action_number = Controller.get_instance().print_list_return_selection(sent_messages_list, -1)
                break

    def __send_message_to_student(self):
        student_size = len(self.__student_list)
        student_to_send_message = ["Select a student to send a message."]
        student_to_send_message.extend([f"{i + 1}) {self.__student_list[i]}" for i in range(student_size)])
        student_to_send_message.append(f"{student_size + 1}) Go back.")

        action_number = Controller.get_instance().print_list_return_selection(student_to_send_message, -1)
        if action_number == student_size + 1:
            logging.info(f"{action_number}) Go back selected")
            return
        logging.info(f"{action_number}) Student selected for send message")
        message_info = Controller.get_instance().request_message_string()
        message = Message(message_info[0], message_info[1], self, self.__student_list[action_number - 1])
        self.send_message(message, self.__student_list[action_number - 1])
        logging.info("Message sent")

    def send_message(self, message, student):
        self.__sent_messages.append(message)
        student.receive_message(message)

    def receive_message(self, message):
        self.__received_messages.append(message)

    def get_student_list(self):
        return self.__student_list

    def set_student_list(self, student_list):
        self.__student_list = student_list

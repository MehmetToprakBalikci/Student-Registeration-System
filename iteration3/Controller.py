import logging
import sys

from GUI import GUI
from jsonwriter import jsonwriter
from UI import UI
from UniversityFileSystem import UniversityFileSystem

# Configure basic logging
logging.basicConfig(filename='information.log')


class Controller:
    __singletonController = None
    __user = None

    def __init__(self, input=None):
        try:
            # UI.getInstance(input).initialize() TODO functionality implemented
            self.__gui = GUI.get_instance()
        except Exception as e:
            logging.error("Error initializing Controller: %s", e)
            sys.exit(1)

    @classmethod
    def get_instance(cls, input=None):
        try:
            if cls.__singletonController is None:
                cls.__singletonController = Controller(input)
            return cls.__singletonController
        except Exception as e:
            logging.error("Error getting Controller instance: %s", e)
            sys.exit(1)

    def start(self):

        try:

            UniversityFileSystem.get_instance().load_files()
            while True:
                #action_number = self.__gui.initialize()


                user_info = self.get_menu_selection(0, None)
                if user_info == 2:
                    break
                self.__user = UniversityFileSystem.get_instance().get_signed_person(user_info, self)
                if self.__user:
                    self.__user.start_actions()

            jsonwriter.get_instance(self.__user).save_files()
        except ArithmeticError as e:
            logging.error("Error during Controller's start process: %s", e)

    def print_error_message(self, error_message):
        UI.getInstance().printConsoleErrorMessage(error_message)

    def print_list_return_selection(self, strings_list, error_int):
        return UI.getInstance().printConsoleListReturnSelection(strings_list, error_int)

    def get_menu_selection(self, menu_no: int, strings_list):
        if menu_no == 0:
            return self.__gui.initialize()
        if menu_no == 1:
            return self.__gui.main_menu(strings_list)
        elif menu_no == 2:
            return self.__gui.register_menu(strings_list)
        elif menu_no == 3:
            return self.__gui.course_menu(strings_list)
        elif menu_no == 4:
            return self.__gui.course_popup(strings_list)
        elif menu_no == 5:
            return self.__gui.registered_courses_menu(strings_list)
        elif menu_no == 6:
            return self.__gui.lecturer_info_menu(strings_list)
        elif menu_no == 7:
            return self.__gui.registeration_waiting_courses_menu(strings_list)
        elif menu_no == 8:
            return self.__gui.cancel_waiting_courses_menu(strings_list)
        elif menu_no == 9:
            return self.__gui.transcript_info_menu(strings_list)
        elif menu_no == 10:
            return self.__gui.message_menu()
        elif menu_no == 11:
            return self.__gui.message_send_menu()
        elif menu_no == 12:
            return self.__gui.inbox_menu(strings_list)
        elif menu_no == 13:
            return self.__gui.outbox_menu(strings_list)
        elif menu_no == 14:
            return self.__gui.message_send_popup()
        elif menu_no == 15:
            return self.__gui.message_read_menu(strings_list)

    def print_list(self, string_list):
        try:
            UI.getInstance().printConsoleList(string_list)
        except Exception as e:
            logging.error("Error printing Controller list: %s", e)

    def print_success_message(self, success_message):
        try:
            UI.getInstance().printConsoleSuccessMessage(success_message)
        except Exception as e:
            logging.error("Error printing Controller success message: %s", e)

    def request_message_string(self):
        try:
            return UI.getInstance().requestMessageStringFromUser()
        except Exception as e:
            logging.error("Error requesting Controller message string: %s", e)
            return None

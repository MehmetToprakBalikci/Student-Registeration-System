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
            # elif input is not None :
            #    UI.getInstance().input_str = input
            #    UI.getInstance().input_str_index = 0
            return cls.__singletonController
        except Exception as e:
            logging.error("Error getting Controller instance: %s", e)
            sys.exit(1)

    def start(self):
        try:

            UniversityFileSystem.get_instance().load_files()
            user_info = self.__gui.initialize()

            self.__user = UniversityFileSystem.get_instance().get_signed_person(user_info, self)
            if self.__user:
                self.__user.start_actions()

            jsonwriter.get_instance(self.__user).save_files()
            # TODO logout here
        except ArithmeticError as e:
            logging.error("Error during Controller's start process: %s", e)

    def print_error_message(self, error_message):
        UI.getInstance().printConsoleErrorMessage(error_message)

    def print_list_return_selection(self, strings_list, error_int):
        return UI.getInstance().printConsoleListReturnSelection(strings_list, error_int)

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

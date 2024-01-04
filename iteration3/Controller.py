import logging
import sys

from jsonwriter import jsonwriter
from UI import UI
from UniversityFileSystem import UniversityFileSystem

# Configure basic logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')


class Controller:
    __singletonController = None
    __user = None

    def __init__(self, input=None):
        try:
            UI.getInstance().initialize()
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
            start_menu = ["Select an action.", "1) Log in.", "2) Exit."]
            while True:
                action_number = UI.getInstance().printConsoleListReturnSelection(start_menu, -1)
                if action_number == 2:
                    break

                user_info = UI.getInstance().requestCredentials()
                self.__user = UniversityFileSystem.get_instance().getSignedPerson(user_info, self)
                if self.__user:
                    self.__user.startActions(self)

            Controller.__jsonWriter = jsonwriter.get_instance(self.__user)
            Controller.__jsonWriter.saveFiles()
            UI.getInstance().callEndMessage(0)
        except Exception as e:
            logging.error("Error during Controller's start process: %s", e)

    def print_error_message(self, error_message):
        try:
            UI.getInstance().printConsoleErrorMessage(error_message)
        except Exception as e:
            logging.error("Error printing Controller error message: %s", e)

    def print_list_return_selection(self, strings_list, error_int):
        try:
            return UI.getInstance().printConsoleListReturnSelection(strings_list, error_int)
        except Exception as e:
            logging.error("Error printing Controller list and returning selection: %s", e)
            return -1

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
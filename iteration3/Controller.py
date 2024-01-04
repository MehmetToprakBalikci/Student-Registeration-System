import logging
import sys

import JsonWriter
from UI import UI
from UniversityFileSystem import UniversityFileSystem

# Configure basic logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

class Controller:
    __singletonController = None
    __UNIVERSITY_FILE_SYSTEM = None
    __jsonWriter = None
    __ui = None
    __user = None

    def __init__(self, input=None):
        try:
            Controller.__UNIVERSITY_FILE_SYSTEM = UniversityFileSystem.get_instance()
            Controller.__ui = UI.getInstance(input)
            Controller.__ui.initialize()
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
            Controller.__UNIVERSITY_FILE_SYSTEM.loadFiles()
            startMenu = ["Select an action.", "1) Log in.", "2) Exit."]
            actionNumber = 1
            while True:
                actionNumber = Controller.__ui.printConsoleListReturnSelection(startMenu, -1)
                if actionNumber == 2:
                    break

                userInfo = Controller.__ui.requestCredentials()
                self.__user = Controller.__UNIVERSITY_FILE_SYSTEM.getSignedPerson(userInfo, self)
                if self.__user:
                    self.__user.startActions(self)

            Controller.__jsonWriter = JsonWriter.getInstance(self.__user)
            Controller.__jsonWriter.saveFiles()
            Controller.__ui.callEndMessage(0)
        except Exception as e:
            logging.error("Error during Controller's start process: %s", e)

    def print_error_message(self, errorMessage):
        try:
            Controller.__ui.printConsoleErrorMessage(errorMessage)
        except Exception as e:
            logging.error("Error printing Controller error message: %s", e)

    def print_list_return_selection(self, stringsList, errorInt):
        try:
            return Controller.__ui.printConsoleListReturnSelection(stringsList, errorInt)
        except Exception as e:
            logging.error("Error printing Controller list and returning selection: %s", e)
            return -1

    def print_list(self, stringList):
        try:
            Controller.__ui.printConsoleList(stringList)
        except Exception as e:
            logging.error("Error printing Controller list: %s", e)

    def print_success_message(self, successMessage):
        try:
            Controller.__ui.printConsoleSuccessMessage(successMessage)
        except Exception as e:
            logging.error("Error printing Controller success message: %s", e)

    def request_message_string(self):
        try:
            return Controller.__ui.requestMessageStringFromUser()
        except Exception as e:
            logging.error("Error requesting Controller message string: %s", e)
            return None

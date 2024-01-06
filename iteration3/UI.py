import sys

class UI:
    __singletonUI = None

    def __init__(self, input_string=None):
        self.input_str = input_string
        self.input_str_index = 0

    @classmethod
    def getInstance(cls, input_string=None):
        if cls.__singletonUI is None:
            cls.__singletonUI = UI(input_string)
        return cls.__singletonUI

    def requestCredentials(self):
        userName = input("Enter your userName:\n")
        password = input("Enter your password:\n")
        return [userName, password]

    def initialize(self):
        print("HI WELCOME TO COURSE REGISTRATION SYSTEM")

    def requestActionNumber(self):
        print("SELECT WHAT YOU WANT TO DO IN THE SYSTEM")
        return int(input())

    def printConsoleErrorMessage(self, errorMessage):
        self.__printString(errorMessage)

    def callEndMessage(self, status):
        print("Press enter to close this program...")
        input()
        sys.exit(status)

    def readInput(self):
        """ Reads a line from the input source. """
        if self.input_str is None :
            return input()
        if len(self.input_str) == self.input_str_index :
            print("File reached EOF!")
            raise EOFError
        return_str = self.input_str[self.input_str_index]
        self.input_str_index += 1
        return return_str
        
    def printConsoleListReturnSelection(self, stringList, errorInt):
        if errorInt == 0:
            raise Exception("emptyListStringException")

        while True:
            self.printConsoleList(stringList)

            try:
                choice = self.readInput()
                choice = int(choice)
                if 0 < choice < len(stringList):
                    return choice
            except ValueError:
                print("Invalid input. Please enter a number.")
                

    def printConsoleList(self, stringList):
        for string in stringList:
            if string:
                print(string)
        print("--------------------------")

    def __printString(self, outputStr):
        print(outputStr)

    def printConsoleSuccessMessage(self, successMessage):
        print(successMessage)

    def requestMessageStringFromUser(self):
        title = input("Write your message title:\n")
        msg = input("Write your message:\n")
        return [title, msg]

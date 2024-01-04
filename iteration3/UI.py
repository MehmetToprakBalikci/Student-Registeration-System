import sys

class UI:
    __singletonUI = None

    def __init__(self, input=None):
        self.file = input if input is not None else sys.stdin

    @classmethod
    def getInstance(cls, input=None):
        if cls.__singletonUI is None:
            cls.__singletonUI = UI(input)
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
        if self.file is None :
            return input()
        return self.file.readline().strip()
    
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
            except Exception as e:
                print(f"Error reading input: {e}")
                sys.exit(1)

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

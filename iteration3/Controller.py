class Controller:
    __singletonController = None
    __UNIVERSITY_FILE_SYSTEM = None
    __jsonWriter = None
    __ui = None
    __user = None

    def __init__(self, input=None):
        Controller.__UNIVERSITY_FILE_SYSTEM = UniversityFileSystem.getInstance()
        Controller.__ui = UI.getInstance(input)
        Controller.__ui.initialize()

    @classmethod
    def getInstance(cls, input=None):
        if cls.__singletonController is None:
            cls.__singletonController = Controller(input)
        return cls.__singletonController

    def start(self):
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

    def printErrorMessage(self, errorMessage):
        Controller.__ui.printConsoleErrorMessage(errorMessage)

    def printListReturnSelection(self, stringsList, errorInt):
        return Controller.__ui.printConsoleListReturnSelection(stringsList, errorInt)

    def printList(self, stringList):
        Controller.__ui.printConsoleList(stringList)

    def printSuccessMessage(self, successMessage):
        Controller.__ui.printConsoleSuccessMessage(successMessage)

    def requestMessageString(self):
        return Controller.__ui.requestMessageStringFromUser()

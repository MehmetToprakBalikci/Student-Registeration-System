from User import User


class Message:
    __title = ""
    __message = ""
    __sender: User = None
    __receiver: User = None
    __is_read = False

    # Initializer here
    def __init__(self, title: str, message: str, sender, receiver):
        self.__title = title
        self.__message = message
        self.__sender = sender
        self.__receiver = receiver
        self.__is_read = False

    # sets is read to true to indicate status

    def read_message(self):
        self.__is_read = True

    # getter for message variable

    def get_message(self):
        return self.__message

    # override str function
    def __str__(self):
        s = "Title: " + self.__title + ", " + "Sender: " + self.__sender.__str__()
        return s + ", " + "Receiver: " + self.__receiver.__str__() + ", " + "Read: " + str(self.__is_read)

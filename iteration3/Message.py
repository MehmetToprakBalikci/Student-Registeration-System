class Message:
    __title = ""
    __message = ""
    __sender = None
    __receiver = None
    __is_read = False

    def __init__(self, title, message, sender, receiver):
        self.__title = title
        self.__message = message
        self.__sender = sender
        self.__receiver = receiver
        self.__is_read = False

    def read_message(self):
        self.__is_read = True

    def get_message(self):
        return self.__message

    def get_message_string(self):  # TODO Convert the names of toString methods in user
        s = "Title: " + self.__title + ", " + "Sender: " + self.__sender.toString()
        return s + ", " + "Receiver: " + self.__receiver().toString() + ", " + "Read: " + self.__is_read

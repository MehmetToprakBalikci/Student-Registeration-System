import Staff


class Lecturer(Staff):

    __lecturer_id = ""

    def __init__(self, name, last_name, lecturer_id):
        super(name, last_name)
        self.__lecturer_id = lecturer_id

    def get_lecturer_string(self) -> str:
        return "Lecturer: " + self.get_first_name() + " " + self.get_last_name()

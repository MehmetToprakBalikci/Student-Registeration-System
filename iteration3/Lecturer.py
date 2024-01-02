from Staff import Staff


class Lecturer(Staff):

    __lecturer_id = ""

    def __init__(self, name, last_name, lecturer_id):
        super().__init__(name, last_name)
        self.__lecturer_id = lecturer_id

    def __str__(self):
        return f"Lecturer: {self.get_first_name()} {self.get_last_name()}"

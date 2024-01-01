from Staff import Staff


class Assistant(Staff):
    def __init__(self, name="", last_name="", assistant_id=""):
        super().__init__(name, last_name)
        self.assistant_id = assistant_id

    def get_assistant_id(self):
        return self.assistant_id

    def __str__(self):
        return f"Assistant: {self.get_first_name()} {self.get_last_name()}"

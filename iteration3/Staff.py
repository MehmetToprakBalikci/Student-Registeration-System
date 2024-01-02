from abc import ABC, abstractmethod

from Person import Person


class Staff(Person, ABC):

    def __init__(self, name, last_name):
        super().__init__(name, last_name)


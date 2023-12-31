import logging
import string


class CourseSection:
    __day_number, __section_number = int(0)

    def __init__(self, day_number: int, section_number: int):
        self.__day_number = day_number
        self.__section_number = section_number

    def compare_availability(self, section_to_compare):
        if isinstance(section_to_compare, CourseSection):
            return not (
                        self.__day_number == section_to_compare.get_day_number() and
                        self.__section_number == section_to_compare.get_section_number())
        else:
            logging.error("section_to_compare object is not an instance of CourseSection class!")

    def get_day_number(self):
        return self.__day_number

    def get_section_number(self):
        return self.__section_number

    def get_course_section_string(self):
        day, hour = "", ""

        if self.__day_number == 1:
            day = "Monday"
        elif self.__day_number == 2:
            day = "Tuesday"
        elif self.__day_number == 3:
            day = "Wednesday"
        elif self.__day_number == 4:
            day = "Thursday"
        elif self.__day_number == 5:
            day = "Friday"
        else:
            day = "Invalid Day"

        # 8.30  9.30  10.30  11.30  13  14  15  16  17  18
        # 1     2     3      4      5   6   7   8   9   10
        if 1 <= self.__section_number <= 4:
            hour = str.format("%d.30", self.__section_number + 7)
        elif self.__section_number < 15:
            hour = str.format("%d.00", self.__section_number + 8)
        else:
            hour = "Invalid Hour"

        return day + " " + hour



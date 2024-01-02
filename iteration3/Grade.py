class Grade:
    __numerical_grade = 0

    def __init__(self, numerical_grade):
        self.__numerical_grade = numerical_grade

    def is_retakable_grade(self):
        return self.__numerical_grade < 50

    def is_passed_grade(self):
        return self.__numerical_grade < 35

    def get_letter_grade(self):
        if 90 <= self.__numerical_grade <= 100:
            return "AA"
        elif 80 <= self.__numerical_grade < 90:
            return "BA"
        elif 70 <= self.__numerical_grade < 80:
            return "BB"
        elif 60 <= self.__numerical_grade < 70:
            return "CB"
        elif 50 <= self.__numerical_grade < 60:
            return "CC"
        elif 40 <= self.__numerical_grade < 50:
            return "DC"
        elif 35 <= self.__numerical_grade < 40:
            return "DD"
        elif 35 > self.__numerical_grade >= 0:
            return "FF"
        return "Invalid Grade"

    def __str__(self):
        return f"Grade: {self.__numerical_grade} {", Letter Grade : "} {self.get_letter_grade()}"

    def get_numerical_grade(self):
        return self.__numerical_grade

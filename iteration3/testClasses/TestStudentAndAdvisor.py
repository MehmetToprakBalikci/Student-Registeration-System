import os
import unittest
from Student import Student
from Controller import Controller
from Advisor import Advisor
from Course import Course
from Grade import Grade
from Transcript import Transcript
class TestStudentAndAdvisor(unittest.TestCase) :
    

    def test_student_run_user_action_registration_waiting_courses1(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(1, 1), 1, 1));
    def test_student_run_user_action_registration_waiting_courses2(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(1, 2), 1, 2));
    def test_student_run_user_action_registration_waiting_courses3(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(1, 3), 1, 3));
    def test_student_run_user_action_registration_waiting_courses4(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(1, 4), 1, 4));
    def test_student_run_user_action_registration_waiting_courses5(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(1, 5), 1, 5));
    

    
    def test_student_run_user_action_cancel_waiting_courses1(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(2, 1), 2, 1));
    def test_student_run_user_action_cancel_waiting_courses2(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(2, 2), 2, 2));
    def test_student_run_user_action_cancel_waiting_courses3(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(2, 3), 2, 3));
    def test_student_run_user_action_cancel_waiting_courses4(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(2, 4), 2, 4));
    def test_student_run_user_action_cancel_waiting_courses5(self) :
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(2, 5), 2, 5));
       
    
    def test_advisor_run_user_actionwaiting_registration_selection1(self) : 
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(3, 1) ,3 , 1))
    def test_advisor_run_user_actionwaiting_registration_selection2(self) : 
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(3, 2) ,3 , 2))
    def test_advisor_run_user_actionwaiting_registration_selection3(self) : 
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(3, 3) ,3 , 3))
    def test_advisor_run_user_actionwaiting_registration_selection4(self) : 
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(3, 4) ,3 , 4))
    def test_advisor_run_user_actionwaiting_registration_selection5(self) : 
        self.assertTrue(self.__start_test_procedure(self.__get_test_string(3, 5) ,3 , 5))

    
    def __get_test_string(self, test_type, test_num) :
        test_string = None
        if test_type == 1 :
            
            if test_num == 1 :  
                test_string = "1 2 2 1 8 2"
            if test_num == 2 : 
                test_string = "1 3 2 1 8 2"
            if test_num == 3 :
                test_string = "1 4 2 1 8 2"
            if test_num == 4 :
                test_string = "1 5 2 1 8 2 "
            if test_num == 5 :
                test_string = "1 6 2 1 8 2"
        elif test_type == 2 :

            if test_num == 1 : 
                test_string = "2 2 8 2"
            if test_num == 2 :
                test_string = "2 3 8 2"
            if test_num == 3 :
                test_string = "2 4 8 2"
            if test_num == 4 :
                test_string = "2 5 8 2"
            if test_num == 5 :
                test_string = "2 6 8 2"
        elif test_type == 3 :

            if test_num == 1 : 
                test_string = "1 1 1 1 2 3 3"
            if test_num == 2 :
                test_string = "1 1 2 1 2 3 3"
            if test_num == 3 :
                test_string = "1 2 1 1 3 3 3"
            if test_num == 4 :
                test_string = "1 2 2 2 3 3 3"
            if test_num == 5 :
                test_string = "1 2 3 2 3 3 3"
        return test_string
    

    def __start_test_procedure(self, user_input_string, test_type, test_num) :
        this_controller = Controller.get_instance(self.__get_test_user_input(user_input_string), self.__get_logging_file_name);
        
        student_list = []
        advisor1 = Advisor("Mustafa", "Ağaoğlu","username3","password3", "1", student_list);

        course1 = Course("CSE3001", "Database Systems", 7, 3, 1, 2, advisor1, None, None,10);
        course2 = Course("CSE3002", "Operating Systems", 7, 3, 1, 2, advisor1, None, None, 10);
        course3 = Course("CSE3003", "OOP", 5, 3, 1, 2, advisor1, None, None, 10);
        course4 = Course("CSE3004", "Digital Logic Design", 6, 3, 1, 2, advisor1, None, None, 10);
        course5 = Course("CSE3005", "Simulation and Modelling", 5, 3, 1, 2, advisor1, None, None, 10);
        course_list = []
        course_list.append(course1)
        grade1 = Grade(50);
        grade2 = Grade(60);
        grade_list = []
        grade_list.append(grade1);
        transcript1 = Transcript(course_list,grade_list);
        course_list = [];
        grade_list = []
        course_list.add(course2);
        grade_list.add(grade2);
        student1 = Student("Arda", "Öztürk", "username1", "password1", "150121051", transcript1, advisor1);
        student2 = Student("Furkan", "Gökgöz", "username2", "password2", "150120076", transcript1, advisor1);


        
        student_list.add(student1);
        student_list.add(student2);
        advisor1.set_student_list(student_list);
        
        if test_type == 1 :
            student1.add_element_to_current_available_courses(course1);
            student1.add_element_to_current_available_courses(course2);
            student1.add_element_to_current_available_courses(course3);
            student1.add_element_to_current_available_courses(course4);
            student1.add_element_to_current_available_courses(course5);
        elif test_type == 2 :
            student1.add_element_to_registration_complete_courses(course1);
            student1.add_element_to_registration_complete_courses(course2);
            student1.add_element_to_registration_complete_courses(course3);
            student1.add_element_to_registration_complete_courses(course4);
            student1.add_element_to_registration_complete_courses(course5);
        elif test_type == 3 :
            student1.add_element_to_registration_waiting_courses(course1);
            student1.add_element_to_registration_waiting_courses(course2);
            student2.add_element_to_registration_waiting_courses(course3);
            student2.add_element_to_registration_waiting_courses(course4);
            student2.add_element_to_registration_waiting_courses(course5);
        
        
        if test_type == 1 or test_type == 2 :
            student1.start_actions(this_controller)
        elif test_type == 3 :
            advisor1.start_actions(this_controller)
        

        test_pass = False
        if test_type == 1 :

            if test_num == 1 :
                test_pass = student1.remove_element_from_registration_waiting_courses(course1);
            if test_num == 2 :
                test_pass = student1.remove_element_from_registration_waiting_courses(course2);
            if test_num == 3 : 
                test_pass = student1.remove_element_from_registration_waiting_courses(course3);
            if test_num == 4 :
                test_pass = student1.remove_element_from_registration_waiting_courses(course4);
            if test_num == 5 :
                test_pass = student1.remove_element_from_registration_waiting_courses(course5);
        
        elif test_type == 2 :
            if test_num == 1 :
                test_pass = student1.remove_element_from_cancel_waiting_courses(course1);
            if test_num == 2 :
                test_pass = student1.remove_element_from_cancel_waiting_courses(course2);
            if test_num == 3 :
                test_pass = student1.remove_element_from_cancel_waiting_courses(course3);
            if test_num == 4 :
                test_pass = student1.remove_element_from_cancel_waiting_courses(course4);
            if test_num == 5 :
                test_pass = student1.remove_element_from_cancel_waiting_courses(course5);

        elif test_type == 3 :
            if test_num == 1 :
                test_pass = student1.remove_element_from_registration_complete_courses(course1);
            if test_num == 2 :
                test_pass = student1.remove_element_from_registration_complete_courses(course2);
            if test_num == 3 :
                test_pass = student2.remove_element_from_registration_complete_courses(course3);
            if test_num == 4 :
                test_pass = student2.remove_element_from_current_available_courses(course4);
            if test_num == 5 :
                test_pass = student2.remove_element_from_current_available_courses(course5);
        
        self.__remove_file
        
        return test_pass
    
    def __remove_file(self) :
        os.remove("testInput.txt")

    def __get_test_user_input(self, console_input_string) :

        input_file = open("testInput.txt", "w")
        input_file.write(console_input_string)
        input_file.close()
        
        output_file = open("testInput.txt", "r")
        return output_file
    
    def __get_logging_file_name(self) :
        return "test_student_and_advisor.log"
    
if __name__ == '__main__' :
    unittest.main()
    
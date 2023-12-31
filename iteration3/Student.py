class Student() :

    #String name, String lastName, String username, String password, String studentID, Transcript currentTranscript, Advisor currentAdvisor
    def __init__(self, name, last_name, username, password, student_id, current_transcript, current_advisor):
       
        # private final String
        self.__USER_NAME = username
        # private final String 
        self.__PASSWORD = password
        # private final String
        self.__STUDENT_ID = student_id
        #private final Transcript , Transcript of the student
        self.__CURRENT_TRANSCRIPT = current_transcript
        #private Advisor , Advisor of the student 
        self.__current_advisor = current_advisor
        #private List<Course> , Courses that the student is able to register
        self.__current_available_courses = []
        # private List<Course> , Courses that are waiting to be registered 
        self.__registration_waiting_courses = []
        #private List<Course> , Courses that finished registration
        self.__registration_complete_courses = []
        #private List<Course> , Courses that are waiting to be canceled
        self.__cancel_waiting_courses = []
        #private List<Message> sentMessages , Messages that are sent
        self.__sent_messages = []
        #private List<Message> receivedMessages, Messages that are recieved
        self.__received_messages = []
    

    def get_action_list(self):
        action_list = []
        action_list.append("\nSelect an action.")
        action_list.append("1) Check courses available to register.")
        action_list.append("2) Check your registered courses.")
        action_list.append("3) See courses waiting to be registered.")
        action_list.append("4) See courses waiting to be canceled.")
        action_list.append("5) User transcript.")
        action_list.append("6) Advisor information.")
        action_list.append("7) See messages.")
        action_list.append("8) Log out.")
        return action_list
    
    def __str__(self):
        return "Name: " + self.get_first_name() + self.get_last_name() + ", StudentID: " + self.get_student_id() + "."
    
    #Controller controller
    def start_actions(self, controller):
        action_list = self.get_action_list()
        #int
        action_number = controller.print_list_return_selection(action_list, -1)
        MAX_ACTION = 8
        
        while action_number != MAX_ACTION:
            self.run_user_action(action_number, controller)
            action_number = controller.print_list_return_selection(action_list, -1)

    #int actionNumber, Controller controller
    def run_user_action(self, action_number, controller):
        ##Student selection part
    
        if action_number == 1:
            current_user_selection = controller.print_list_return_selection(
            self.__get_course_return_list_string("Courses that are available to you select one:", self.__current_available_courses), -1)
            if current_user_selection != 1: # First selection
                current_course = self.__current_available_courses[current_user_selection - 2]
                current_user_selection = controller.print_list_return_selection[self.__get_course_info_string(current_course), -1]
                if current_user_selection == 1: # return back to available course menu page
                    self.run_user_action(1, controller)
                elif current_user_selection == 2: # register to the course
                    #string
                    course_sect_availability_str = current_course.check_course_section(self.__registration_complete_courses, self.__registration_waiting_courses, self.__cancel_waiting_courses)
                    if ((not (current_course.check_technical_elective_count(self.__registration_complete_courses, self.__registration_waiting_courses, self.__cancel_waiting_courses) is None)) and (course_sect_availability_str is None)) :
                        course_sect_availability_str = ""
                        course_sect_availability_str = currentCourse.check_technical_elective_count(self.__registration_complete_courses, self.__registration_waiting_courses, self.__cancel_waiting_courses)
                    elif((not (current_course.check_non_technical_elective_count(self.__registration_complete_courses, self.__registration_waiting_courses, self.__cancel_waiting_courses) is None)) and (course_sect_availability_str is None)) :
                        course_sect_availability_str = ""
                        course_sect_availability_str = current_course.check_non_technical_elective_count(self.__registration_complete_courses, self.__registration_waiting_courses, self.__cancel_waiting_courses)
                        if course_sect_availability_str is None:
                            self.remove_element_from_current_available_courses(current_course)
                            self.__registration_waiting_courses.append(current_course)
                            controller.print_success_message(current_course + " has been sent to your advisor " + self.__current_advisor.get_first_name() + " " + self.__current_advisor.get_last_name())
                        else:
                            controller.print_error_message(course_sect_availability_str)
                        
                        self.run_user_action(1, controller)
                elif current_user_selection == 3: # see your course lecturer
                        controller.print_list(self.__get_courses_lecturer_info(current_course))
                        self.run_user_action(1, controller)
                elif current_user_selection == 4: # see your course assistant
                        controller.print_list(self.__get_courses_assistant_info(current_course))
                        self.run_user_action(1, controller)
                elif current_user_selection == 5: # return back to first page
                        return
        elif current_user_selection != 2: 
            current_user_selection = controller.print_list_return_selection(self.__get_course_return_list_string("Courses that have finalized registration, choose course to cancel:", self.__registration_complete_courses), -1)
            if current_user_selection != 1:
                currentCourse = self.__registration_complete_courses.get(current_user_selection - 2)
                self.remove_element_from_registration_complete_courses(current_course)
                self.__cancel_waiting_courses.append(current_course)
                controller.print_success_message(current_course + "is successfully added to cancelWaiting.\n")
        elif current_user_selection != 3: 
            controller.print_list(
                self.__get_course_list_string("Courses that are waiting to be finalized by your advisor ", self.__registration_waiting_courses))

        elif current_user_selection != 4: 
            controller.print_list(self.__get_course_list_string("Courses that are waiting to be canceled by your " + self.__current_advisor.str(), self.__cancel_waiting_courses))
        elif current_user_selection != 5: 
            controller.print_list(self.__CURRENT_TRANSCRIPT.getStudentTranscriptStringList())
        elif current_user_selection != 6: 
            controller.print_list(self.__string_to_list(self.__current_advisor.str()))

        elif current_user_selection != 7: 
            while True :
                message_list = ["", ""]
                
                message_menu_list = []
                message_menu_list.append("Select an action.")
                message_menu_list.append("1) See sent messages.")
                message_menu_list.append("2) See received messages.")
                message_menu_list.append("3) Send message to your advisor.")
                message_menu_list.append("4) Go back.")
                
                action_number = controller.print_list_return_selection(message_menu_list, -1)
                
                if action_number == 4:
                    return
            
                elif action_number == 2:
                    while True :
                        received_messages_list = ["Received messages:"]
                        
                        
                        if self.received_messages.len() != 0 :
                            i = 0
                            for current_recieved_message in self.__received_messages:
                                received_messages_list.append(i + ") " + current_recieved_message.str())
                                i = i+1

                            received_messages_list.append(") Go back.")
                            action_number = controller.print_list_return_selection(received_messages_list, -1)
                            if action_number == (received_messages_list.len() - 1):
                                break
                            else :
                                message_list[0] = self.__received_messages.index(action_number-1).str() + "\n\n" + self.__received_messages.index(action_number-1).str()
                                message_list[1] = "1) Go back."
                                self.__received_messages.index(action_number-1).read_message()
                                action_number = controller.print_list_return_selection(message_list, -1)
                                if action_number == 1:
                                    continue
                        else :
                            received_messages_list[0] = "There is no received messages."
                            action_number = controller.print_list_return_selection(received_messages_list, -1)
                            break
                    continue
                elif action_number == 1: 
                    while True:
                        sent_messages_list = ["Sent messages:"]

                        if self.__sent_messages.len() != 0 : 
                            i = 0
                            for current_sent_message in self.__sent_messages:
                                sent_messages_list.append(i + ") " + current_sent_message.str())
                                i = i+1
                            
                            actionNumber = controller.print_list_return_selection(sent_messages_list, -1)
                            if actionNumber == (sent_messages_list.len() - 1) :
                                break
                            else :
                                message_list[0] = self.__sent_messages.index(action_number-1).str() + "\n\n" + self.__sent_messages.index(action_number-1).str()
                                message_list[1] = "1) Go back."
                                action_number = controller.print_list_return_selection(message_list, -1)
                                if action_number == 1 :
                                    continue
                        else :
                            sent_messages_list[0] = "There is no sent messages."
                            action_number = controller.print_list_return_selection(sent_messages_list, -1)
                            break
                    continue
                else :
                    message_info = controller.request_message_string()
                    message = Message(message_info[0], message_info[1], self, self.__current_advisor)
                    self.send_message(message, self.__current_advisor)

    def __get_course_info_string(self, course) :
        course_info_string = []
        course_info_string.append("Select your course action")
        course_info_string.append("1-)Return back to available course menu page")
        course_info_string.append("2-)Register to the " + self.course.str())
        course_info_string.append("3-)See the course's Lecturer")
        course_info_string.append("4-)See the course's assistant")
        course_info_string.append("5-)Return back to first menu page")
        return course_info_string
    
    def __get_courses_assistant_info(self, current_course) :
        assistant_info = [""]
        if current_course.get_assistant() is None :
            assistant_info[0] = "There is no assistant assigned for this course"
        else :
            assistant_info[0] = current_course.get_assistant().str()
        return assistant_info
    
    def __get_courses_lecturer_info(self, current_course) :
        lecturer_info = [""]
        lecturer_info[0] = current_course.getLecturer().str()
        return lecturer_info
    
    def __string_to_list(self, give_string) :
        string_list = [""]
        string_list[0] = give_string
        return string_list
    
    def __get_course_return_list_string(self, title_string, courses_list) :
        # TODO NULL CHECK
        size = self.courses_list.size()
        course_list_string = ["", ""]
        course_list_string[0] = title_string
        course_list_string[1] = "1-)Return back"
        i = 2
        for current_course in courses_list :
            course_list_string.append(i + "-)" + current_course.str())
            i = i + 1

        return course_list_string

    def __get_course_list_string(self, title_string, courses_list) :

        course_list_string = [""]
        course_list_string[0] = title_string
        i = 1
        for current_course in courses_list :
            course_list_string.append(i + "-)" + current_course.str())
            i = i + 1
        
        return course_list_string
    
    def check_course_availablity(self, course) :
        is_available = True
        if course.get_type() == "nt" :
            is_available = course.check_pre_requisite((self.__CURRENT_TRANSCRIPT.get_list_of_courses(), self.__CURRENT_TRANSCRIPT.get_list_of_grades()
            and self.__CURRENT_TRANSCRIPT.check_passed_courses(course) 
            and (not self.__check_existence(course)) 
            and (not course.is_full())))
        
        else :
            is_available = course.check_year_matching((self.__CURRENT_TRANSCRIPT.get_year()
            and course.check_pre_requisite(self.__CURRENT_TRANSCRIPT.get_list_of_courses(),self. __CURRENT_TRANSCRIPT.get_list_of_grades()) 
            and self.__CURRENT_TRANSCRIPT.check_passed_courses(course) 
            and (not self.__check_existence(course)) 
            and (not course.is_full())))

        return is_available
    
    def __check_existence(self, course) :
        exists = False
        exists = (self.__check_list_for_course(self.__cancel_waiting_courses, course) 
                or self.__check_list_for_course(self.__registration_complete_courses, course) 
                or self.__check_list_for_course(self.__registration_waiting_courses, course))
        return exists
    
    # Returns true if it finds a course in the list
    def __check_list_for_course(self, course_list, course) :
        for current in course_list :
            if course.equals(current) : 
                return True
        return False
    
    def compare_credentials(self, username, password) :
        if self.__USER_NAME is None or self.__PASSWORD is None :
            return False
        return self.__USER_NAME == username and self.__PASSWORD == password

    
    def set_current_advisor(self, current_advisor) :
        self.__current_advisor = current_advisor
    

    def get_student_id(self) :
        return self.__STUDENT_ID
    
    # iteration 2
    def send_message(self, msg, advisor) :
        self.__sent_messages.append(msg)
        advisor.receive_message(msg)


    def receive_message(self, msg) :
        self.__received_messages.append(msg)
    


    def is_taking_course(self, course) :
        return self.__registration_complete_courses.contains(course) or self.__cancel_waiting_courses.contains(course)
    

    def set_registration_waiting_courses(self, __registration_waiting_courses) :
        self.__registration_waiting_courses = __registration_waiting_courses
    

    def set_registration_complete_courses(self, registration_complete_courses) :
        self.__registration_complete_courses = registration_complete_courses
    

    def set_cancel_waiting_courses(self, cancel_waiting_courses) :
        self.__cancel_waiting_courses = cancel_waiting_courses
    

    def get_current_advisor(self) :
        return self.__current_advisor
    

    def set_current_available_courses(self, current_available_courses) :
        self.__current_available_courses = current_available_courses
    

    def get_current_transcript(self) :
        return self.__CURRENT_TRANSCRIPT
    

    def get_registration_complete_courses(self) :
        return self.__registration_complete_courses
    

    def get_user_name(self) :
        return self.__USER_NAME
    

    def get_password(self) :
        return self.__PASSWORD
    


    def remove_element_from_current_available_courses(self, course) :
        return self.__current_available_courses.remove(course)
    

    def remove_element_from_registration_waiting_courses(self, course) :
        return self.__registration_waiting_courses.remove(course)
    

    def remove_element_from_registration_complete_courses(self, course) :
        return self.__registration_complete_courses.remove(course)
    

    def remove_element_from_cancel_waiting_courses(self, course) :
        return self.__cancel_waiting_courses.remove(course)
    

    def add_element_to_current_available_courses(self, course) :
        self.__current_available_courses.append(course)
    

    def add_element_to_registration_waiting_courses(self, course) :
        self.__registration_waiting_courses.append(course)
    

    def add_element_to_registration_complete_courses(self, course) :
        self.__registration_complete_courses.append(course)
    

    def add_element_to_cancel_waiting_courses(self, course) :
        self.__cancel_waiting_courses.append(course)
    

    def get_cancel_waiting_courses(self) :
        return self.__cancel_waiting_courses
    

    def get_registration_waiting_courses(self) :
        return self.__registration_waiting_courses

    def get_available_courses(self, system_courses):
        for course in system_courses:
            if self.check_course_availability(course):
                self.available_courses.append(course)


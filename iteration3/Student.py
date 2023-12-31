class Student() :

    #String name, String lastName, String username, String password, String studentID, Transcript currentTranscript, Advisor currentAdvisor
    def __init__(self, name, lastName, username, password, studentID, currentTranscript, currentAdvisor):
       
        # private final String
        self.__USERNAME = username
        # private final String 
        self.__PASSWORD = password
        # private final String
        self.__STUDENT_ID = studentID
        #private final Transcript , Transcript of the student
        self.__CURRENT_TRANSCRIPT = currentTranscript
        #private Advisor , Advisor of the student 
        self.__currentAdvisor = currentAdvisor
        #private List<Course> , Courses that the student is able to register
        self.__currentAvailableCourses = []
        # private List<Course> , Courses that are waiting to be registered 
        self.__registrationWaitingCourses = []
        #private List<Course> , Courses that finished registration
        self.__registrationCompleteCourses = []
        #private List<Course> , Courses that are waiting to be canceled
        self.__cancelWaitingCourses = []
        #private List<Message> sentMessages , Messages that are sent
        self.__sentMessages = []
        #private List<Message> receivedMessages, Messages that are recieved
        self.__receivedMessages = []
    

    def get_action_list(self):
        actionList = []
        actionList.append("\nSelect an action.")
        actionList.append("1) Check courses available to register.")
        actionList.append("2) Check your registered courses.")
        actionList.append("3) See courses waiting to be registered.")
        actionList.append("4) See courses waiting to be canceled.")
        actionList.append("5) User transcript.")
        actionList.append("6) Advisor information.")
        actionList.append("7) See messages.")
        actionList.append("8) Log out.")
        return actionList
    
    def __str__(self):
        return "Name: " + self.getFirstName() + self.getLastName() + ", StudentID: " + self.get_student_id() + "."
    
    #Controller controller
    def start_actions(self, controller):
        actionList = self.get_action_list()
        #int
        actionNumber = controller.printListReturnSelection(actionList, -1)
        MAX_ACTION = 8
        
        while actionNumber != MAX_ACTION:
            self.run_user_action(actionNumber, controller)
            actionNumber = controller.printListReturnSelection(actionList, -1)

    #int actionNumber, Controller controller
    def run_user_action(self, actionNumber, controller):
        ##Student selection part
        #int
        currentUserSelection
        #Course
        currentCourse
    
        if actionNumber == 1:
            currentUserSelection = controller.printListReturnSelection(
            self.__get_course_return_list_string("Courses that are available to you select one:", self.currentAvailableCourses), -1)
            if currentUserSelection != 1: # First selection
                currentCourse = self.currentAvailableCourses[currentUserSelection - 2]
                currentUserSelection = controller.printListReturnSelection[self.__get_course_info_string(currentCourse), -1]
                if currentUserSelection == 1: # return back to available course menu page
                    self.run_user_action(1, controller)
                elif currentUserSelection == 2: # register to the course
                    #string
                    courseSectAvailabilityStr = currentCourse.checkCourseSection(self.registrationCompleteCourses, self.registrationWaitingCourses, self.cancelWaitingCourses)
                    if ((not (currentCourse.checkTechnicalElectiveCount(self.registrationCompleteCourses, self.registrationWaitingCourses, self.cancelWaitingCourses) is None)) and (courseSectAvailabilityStr is None)) :
                        courseSectAvailabilityStr = ""
                        courseSectAvailabilityStr = currentCourse.checkTechnicalElectiveCount(self.registrationCompleteCourses, self.registrationWaitingCourses, self.cancelWaitingCourses)
                    elif((not (currentCourse.checkNonTechnicalElectiveCount(self.registrationCompleteCourses, self.registrationWaitingCourses, self.cancelWaitingCourses) is None)) and (courseSectAvailabilityStr is None)) :
                        courseSectAvailabilityStr = ""
                        courseSectAvailabilityStr = currentCourse.checkNonTechnicalElectiveCount(self.registrationCompleteCourses, self.registrationWaitingCourses, self.cancelWaitingCourses)
                        if courseSectAvailabilityStr is None:
                            self.remove_element_from_current_available_courses(currentCourse)
                            self.registrationWaitingCourses.add(currentCourse)
                            controller.printSuccessMessage(currentCourse + " has been sent to your advisor " + self.currentAdvisor.getFirstName() + " " + self.currentAdvisor.getLastName())
                        else:
                            controller.printErrorMessage(courseSectAvailabilityStr)
                        
                        self.run_user_action(1, controller)
                elif currentUserSelection == 3: # see your course lecturer
                        controller.printList(self.__get_courses_lecturer_info(currentCourse))
                        self.run_user_action(1, controller)
                elif currentUserSelection == 4: # see your course assistant
                        controller.printList(self.__get_courses_assistant_info(currentCourse))
                        self.run_user_action(1, controller)
                elif currentUserSelection == 5: # return back to first page
                        return
        elif currentUserSelection != 2: 
            currentUserSelection = controller.printListReturnSelection(self.__get_course_return_list_string("Courses that have finalized registration, choose course to cancel:", self.registrationCompleteCourses), -1)
            if currentUserSelection != 1:
                currentCourse = self.registrationCompleteCourses.get(currentUserSelection - 2)
                self.remove_element_from_registration_complete_courses(currentCourse)
                self.cancelWaitingCourses.add(currentCourse)
                controller.printSuccessMessage(currentCourse + "is successfully added to cancelWaiting.\n")
        elif currentUserSelection != 3: 
            controller.printList(
                self.__get_course_list_string("Courses that are waiting to be finalized by your advisor ", self.registrationWaitingCourses))

        elif currentUserSelection != 4: 
            controller.printList(self.__get_course_list_string("Courses that are waiting to be canceled by your " + self.currentAdvisor.toString(), self.cancelWaitingCourses))
        elif currentUserSelection != 5: 
            controller.printList(self.CURRENT_TRANSCRIPT.getStudentTranscriptStringList())
        elif currentUserSelection != 6: 
            controller.printList(self.__string_to_list(self.currentAdvisor.toString()))

        elif currentUserSelection != 7: 
            while True :
                messageList = ["", ""]
                
                messageMenuList = []
                messageMenuList.append("Select an action.")
                messageMenuList.append("1) See sent messages.")
                messageMenuList.append("2) See received messages.")
                messageMenuList.append("3) Send message to your advisor.")
                messageMenuList.append("4) Go back.")
                
                actionNumber = controller.printListReturnSelection(messageMenuList, -1)
                
                if actionNumber == 4:
                    return
            
                elif actionNumber == 2:
                    while True :
                        receivedMessagesList = ["Received messages:"]
                        
                        
                        if self.receivedMessages.len() != 0 :
                            i = 0
                            for currentRecievedMessage in self.receivedMessages:
                                receivedMessagesList.append(i + ") " + currentRecievedMessage.str())
                                i = i+1

                            receivedMessagesList.append(") Go back.")
                            actionNumber = controller.printListReturnSelection(receivedMessagesList, -1)
                            if actionNumber == (receivedMessagesList.len() - 1):
                                break
                            else :
                                messageList[0] = self.receivedMessages.index(actionNumber-1).str() + "\n\n" + self.receivedMessages.index(actionNumber-1).str()
                                messageList[1] = "1) Go back."
                                self.receivedMessages.index(actionNumber-1).readMessage()
                                actionNumber = controller.printListReturnSelection(messageList, -1)
                                if actionNumber == 1:
                                    continue
                        else :
                            receivedMessagesList[0] = "There is no received messages."
                            actionNumber = controller.printListReturnSelection(receivedMessagesList, -1)
                            break
                    continue
                elif actionNumber == 1: 
                    while True:
                        sentMessagesList = ["Sent messages:"]

                        if self.sentMessages.len() != 0 : 
                            i = 0
                            for currentSentMessage in self.sentMessages:
                                sentMessagesList.append(i + ") " + currentSentMessage.str())
                                i = i+1
                            
                            actionNumber = controller.printListReturnSelection(sentMessagesList, -1)
                            if actionNumber == (sentMessagesList.len() - 1) :
                                break
                            else :
                                messageList[0] = self.sentMessages.index(actionNumber-1).str() + "\n\n" + self.sentMessages.index(actionNumber-1).str()
                                messageList[1] = "1) Go back."
                                actionNumber = controller.printListReturnSelection(messageList, -1)
                                if actionNumber == 1 :
                                    continue
                        else :
                            sentMessagesList[0] = "There is no sent messages."
                            actionNumber = controller.printListReturnSelection(sentMessagesList, -1)
                            break
                    continue
                else :
                    messageInfo = controller.requestMessageString()
                    message = Message(messageInfo[0], messageInfo[1], self, self.currentAdvisor)
                    self.send_message(message, self.currentAdvisor)

    def __get_course_info_string(self, course) :
        courseInfoString = []
        courseInfoString.append("Select your course action")
        courseInfoString.append("1-)Return back to available course menu page")
        courseInfoString.append("2-)Register to the " + self.course.str())
        courseInfoString.append("3-)See the course's Lecturer")
        courseInfoString.append("4-)See the course's assistant")
        courseInfoString.append("5-)Return back to first menu page")
        return courseInfoString
    
    def __get_courses_assistant_info(self, currentCourse) :
        assistantInfo = [""]
        if currentCourse.getAssistant() is None :
            assistantInfo[0] = "There is no assistant assigned for this course"
        else :
            assistantInfo[0] = currentCourse.getAssistant().str()
        return assistantInfo
    
    def __get_courses_lecturer_info(self, currentCourse) :
        lecturerInfo = [""]
        lecturerInfo[0] = currentCourse.getLecturer().str()
        return lecturerInfo
    
    def __string_to_list(self, giveString) :
        stringList = [""]
        stringList[0] = giveString
        return stringList
    
    def __get_course_return_list_string(self, titleString, coursesList) :
        # TODO NULL CHECK
        size = self.coursesList.size()
        courseListString = ["", ""]
        courseListString[0] = titleString
        courseListString[1] = "1-)Return back"
        i = 2
        for currentCourse in coursesList :
            courseListString.append(i + "-)" + currentCourse.str())
            i = i + 1

        return courseListString

    def __get_course_list_string(self, titleString, coursesList) :
        size = coursesList.len()
        courseListString = [""]
        courseListString[0] = titleString
        i = 1
        for currentCourse in coursesList :
            courseListString.append(i + "-)" + currentCourse.str())
            i = i + 1
        
        return courseListString
    
    def check_course_availablity(self, course) :
        isAvailable = True
        if course.getType().equals("nt") :
            isAvailable = course.checkPreRequisite((self.CURRENT_TRANSCRIPT.getListOfCourses(), self.CURRENT_TRANSCRIPT.getListOfGrades()
            and self.CURRENT_TRANSCRIPT.checkPassedCourses(course) 
            and (not self.__check_existence(course)) 
            and (not course.isFull())))
        
        else :
            isAvailable = course.checkYearMatching((self.CURRENT_TRANSCRIPT.getYear()
            and course.checkPreRequisite(self.CURRENT_TRANSCRIPT.getListOfCourses(),self. CURRENT_TRANSCRIPT.getListOfGrades()) 
            and self.CURRENT_TRANSCRIPT.checkPassedCourses(course) 
            and (not self.__check_existence(course)) 
            and (not course.isFull())))

        return isAvailable
    
    def __check_existence(self, course) :
        exists = False
        exists = (self.__check_list_for_course(self.cancelWaitingCourses, course) 
                or self.__check_list_for_course(self.registrationCompleteCourses, course) 
                or self.__check_list_for_course(self.registrationWaitingCourses, course))
        return exists
    
    # Returns true if it finds a course in the list
    def __check_list_for_course(self, courseList, course) :
        for current in courseList :
            if course.equals(current) : 
                return True
        return False
    
    def compare_credentials(self, username, password) :
        if self.userName is None or self.password is None :
            return False
        return self.userName == username and self.password == password

    
    def set_current_advisor(self, currentAdvisor) :
        self.currentAdvisor = currentAdvisor
    

    def get_student_id(self, ) :
        return self.STUDENT_ID
    
    # iteration 2
    def send_message(self, msg, advisor) :
        self.sentMessages.add(msg)
        advisor.receive_message(msg)


    def receive_message(self, msg) :
        self.receivedMessages.add(msg)
    


    def is_taking_course(self, course) :
        return self.registrationCompleteCourses.contains(course) or self.cancelWaitingCourses.contains(course)
    

    def set_registration_waiting_courses(self, registrationWaitingCourses) :
        self.registrationWaitingCourses = registrationWaitingCourses
    

    def set_registration_complete_courses(self, registrationCompleteCourses) :
        self.registrationCompleteCourses = registrationCompleteCourses
    

    def set_cancel_waiting_courses(self, cancelWaitingCourses) :
        self.cancelWaitingCourses = cancelWaitingCourses
    

    def get_current_advisor(self) :
        return self.currentAdvisor
    

    def set_current_available_courses(self, currentAvailableCourses) :
        self.currentAvailableCourses = currentAvailableCourses
    

    def get_current_transcript(self) :
        return self.CURRENT_TRANSCRIPT
    

    def get_registration_complete_courses(self) :
        return self.registrationCompleteCourses
    

    def get_user_name(self) :
        return self.userName
    

    def get_password(self) :
        return self.password
    


    def remove_element_from_current_available_courses(self, course) :
        return self.currentAvailableCourses.remove(course)
    

    def remove_element_from_registration_waiting_courses(self, course) :
        return self.registrationWaitingCourses.remove(course)
    

    def remove_element_from_registration_complete_courses(self, course) :
        return self.registrationCompleteCourses.remove(course)
    

    def remove_element_from_cancel_waiting_courses(self, course) :
        return self.cancelWaitingCourses.remove(course)
    

    def add_element_to_current_available_courses(self, course) :
        self.currentAvailableCourses.append(course)
    

    def addElementToRegistrationWaitingCourses(self, course) :
        self.registrationWaitingCourses.append(course)
    

    def add_element_to_registration_complete_courses(self, course) :
        self.registrationCompleteCourses.append(course)
    

    def add_element_to_cancel_waiting_courses(self, course) :
        self.cancelWaitingCourses.append(course)
    

    def get_cancel_waiting_courses(self) :
        return self.cancelWaitingCourses
    

    def get_registration_waiting_courses(self) :
        return self.registrationWaitingCourses

    def get_available_courses(self, system_courses):
        for course in system_courses:
            if self.check_course_availability(course):
                self.available_courses.append(course)


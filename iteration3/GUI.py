import PySimpleGUI as sg


class GUI:
    _instance = None

    def __init__(self):
        pass

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = GUI()
        return cls._instance

    def main_menu(self):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        main_layout = [[sg.Button(button_text="Register"), sg.Button(button_text="Your Registered Courses")
                           , sg.Button(button_text="Accept Waiting Courses"),
                        sg.Button(button_text="Cancel Waiting Courses")],
                       [sg.Button(button_text="Your Transcript"), sg.Button(button_text="Your Advisor")
                           , sg.Button(button_text="Mailbox"),
                        sg.Button(button_text="Log out")]
                       ]

        # Create the Window
        main_window = sg.Window('Menu', main_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = main_window.read()
            if event == sg.WIN_CLOSED or event == 'Log out':  # if user closes window or clicks cancel
                main_window.close()
                return 8
            elif event == "Register":  # Should be done better, frankly idk how rn
                main_window.close()
                return 1
            elif event == "Your Registered Courses":
                main_window.close()
                return 2
            elif event == "Accept Waiting Courses":
                main_window.close()
                return 3
            elif event == "Cancel Waiting Courses":
                main_window.close()
                return 4
            elif event == "Your Transcript":
                main_window.close()
                return 5
            elif event == "Your Advisor":
                main_window.close()
                return 6
            elif event == "Mailbox":
                main_window.close()
                return 7

    def register_menu(self, course_list):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        course_listbox = sg.Listbox(values=course_list, select_mode="LISTBOX_SELECT_MODE_SINGLE"
                                    , pad=10, size=(60, 30), enable_events=True)
        register_layout = [[sg.Button(button_text="Back", pad=10), sg.Button(button_text="Confirm", pad=10)],
                           [course_listbox]]

        # Create the Window
        register_window = sg.Window('Your Courses', register_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        selected_course = None
        while True:
            event, values = register_window.read()
            if course_listbox.get_indexes() is not None and len(course_listbox.get_indexes()) != 0:
                selected_course = course_listbox.get_indexes()[
                                      0] + 2  # i dont know why this 2 is here but it fixes an offset
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                register_window.close()
                return 8
            elif event == "Back":
                register_window.close()
                return 1
            elif event == "Confirm":
                register_window.close()
                return selected_course

    def course_menu(self, course: str):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        course_layout = [[sg.Button(button_text="Back", pad=10, expand_x=True),
                          sg.Button(button_text="Main Menu", pad=10, expand_x=True)],
                         [sg.Button(button_text="Send for Registration", expand_x=True, pad=10)],
                         [sg.Button(button_text="Courses Lecturer", pad=10, expand_x=True),
                          sg.Button(button_text="Courses Assistant", pad=10, expand_x=True)]]

        # Create the Window
        course_window = sg.Window(course[2][19:], course_layout, size=(400, 150))
        # Event Loop to process "events" and get the "values" of the inputs
        selected_course = None
        while True:
            event, values = course_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                course_window.close()
                break
            elif event == "Back":
                course_window.close()
                return 1
            elif event == "Main Menu":
                course_window.close()
                return 5
            elif event == "Send for Registration":
                course_window.close()
                return 2
            elif event == "Courses Lecturer":
                course_window.close()
                return 3
            elif event == "Courses Assistant":
                course_window.close()
                return 4

    def initialize(self):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        layout = [[sg.Text('Account Information')],
                  [sg.Text('User Name'), sg.InputText()],
                  [sg.Text('Password'), sg.InputText()],
                  [sg.Button('Sign in'), sg.Button('Quit')]]

        # Create the Window
        welcome_window = sg.Window('Course Registration System', layout)
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = welcome_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                welcome_window.close()
                return 8
            if event == 'Quit':
                return 2
            if event == 'Sign in':
                welcome_window.close()
                return [values[0], values[1]]

    def course_popup(self, course):
        pop_layout = [[sg.Text(text=course, expand_x=True, pad=10)],
                      [sg.Button(button_text="OK", expand_x=True, pad=10)]]
        pop_window = sg.Window("Notification", pop_layout)
        while True:
            event, values = pop_window.read()
            if event == sg.WIN_CLOSED:
                pop_window.close()
                return 8
            elif event == "OK":
                pop_window.close()
                break

    def registered_courses_menu(self, course_list):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        registered_courses_listbox = sg.Listbox(values=course_list, select_mode=None
                                                , pad=10, size=(60, 30), enable_events=False)
        registered_courses_layout = [[sg.Button(button_text='Back', expand_x=True)
                                         , sg.Button(button_text='Confirm', expand_x=True)],
                                     [registered_courses_listbox]]

        # Create the Window
        registered_courses_window = sg.Window('Your registered courses', registered_courses_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        selected_course = None
        while True:
            event, values = registered_courses_window.read()
            if registered_courses_listbox.get_indexes() is not None and len(
                    registered_courses_listbox.get_indexes()) != 0:
                selected_course = registered_courses_listbox.get_indexes()[
                                      0] + 2  # i dont know why this 2 is here but it fixes an offset
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                registered_courses_window.close()
                return 8
            if event == 'Back':
                registered_courses_window.close()
                return 1
            elif event == "Confirm":
                registered_courses_window.close()
                return selected_course

    def registeration_waiting_courses_menu(self, course_list):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        register_waiting_courses_listbox = sg.Listbox(values=course_list, select_mode=None
                                                      , pad=10, size=(60, 30), enable_events=False)
        register_waiting_courses_layout = [[sg.Button(button_text='Back', expand_x=True)],
                                           [register_waiting_courses_listbox]]

        # Create the Window
        register_waiting_courses_window = sg.Window('Courses waiting to be registered', register_waiting_courses_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        selected_course = None
        while True:
            event, values = register_waiting_courses_window.read()
            if register_waiting_courses_listbox.get_indexes() is not None and len(
                    register_waiting_courses_listbox.get_indexes()) != 0:
                selected_course = register_waiting_courses_listbox.get_indexes()[
                                      0] + 2  # i dont know why this 2 is here but it fixes an offset
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                register_waiting_courses_window.close()
                return 8
            if event == 'Back':
                register_waiting_courses_window.close()
                return 1

    def cancel_waiting_courses_menu(self, course_list):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        cancel_waiting_courses_listbox = sg.Listbox(values=course_list, select_mode=None
                                                    , pad=10, size=(60, 30), enable_events=False)
        cancel_waiting_courses_layout = [[sg.Button(button_text='Back', expand_x=True)],
                                         [cancel_waiting_courses_listbox]]

        # Create the Window
        register_waiting_courses_window = sg.Window('Courses waiting to be registered', cancel_waiting_courses_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        selected_course = None
        while True:
            event, values = register_waiting_courses_window.read()
            if cancel_waiting_courses_listbox.get_indexes() is not None and len(
                    cancel_waiting_courses_listbox.get_indexes()) != 0:
                selected_course = cancel_waiting_courses_listbox.get_indexes()[
                                      0] + 2  # i dont know why this 2 is here but it fixes an offset
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                register_waiting_courses_window.close()
                return 8
            if event == 'Back':
                register_waiting_courses_window.close()
                return 1

    def lecturer_info_menu(self, course_string):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        lecturer_layout = [[[sg.Button(button_text='Back', expand_x=True, pad=10)]],
                           [sg.Text(text=course_string[0], justification='center', auto_size_text=True
                                    , expand_x=True, expand_y=True, pad=10)]]

        # Create the Window
        lecturer_window = sg.Window('Course lecturer information', lecturer_layout, size=(600, 100))
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = lecturer_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                lecturer_window.close()
                return 8
            if event == 'Back':
                lecturer_window.close()
                break

    def transcript_info_menu(self, course_string):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        lecturer_layout = [[[sg.Button(button_text='Back', expand_x=True, pad=10)]],
                           [sg.Text(text=course_string[0], justification='left', auto_size_text=True
                                    , expand_x=True, expand_y=True, pad=10)]]

        # Create the Window
        lecturer_window = sg.Window('Course lecturer information', lecturer_layout, size=(200, 200))
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = lecturer_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                lecturer_window.close()
                return 8
            if event == 'Back':
                lecturer_window.close()
                break

    def message_menu(self):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        lecturer_layout = [[[sg.Button(button_text='Back', expand_x=True, pad=10)]],
                           [sg.Button(button_text='Inbox',
                                      expand_x=True, expand_y=True, pad=10),
                            sg.Button(button_text='Outbox',
                                      expand_x=True, expand_y=True, pad=10)],
                           [sg.Button(button_text='Write Message',
                                      expand_x=True, expand_y=True, pad=10)]]

        # Create the Window
        lecturer_window = sg.Window('Course lecturer information', lecturer_layout, size=(600, 300))
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = lecturer_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                lecturer_window.close()
                return 8
            if event == 'Back':
                lecturer_window.close()
                return 4
            if event == 'Outbox':
                lecturer_window.close()
                return 1
            if event == 'Inbox':
                lecturer_window.close()
                return 2
            if event == 'Write Message':
                lecturer_window.close()
                return 3

    def message_send_menu(self):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        title = sg.InputText(size=(100, 50))
        message = sg.InputText(expand_y=True, expand_x=True)
        message_send_layout = [[sg.Button(button_text='Back', expand_x=True, pad=10),
                                sg.Button(button_text='Send', expand_x=True, pad=10)],
                               [sg.Text(text='Title  '), title],
                               [sg.Text(text='Message', ), message]]

        # Create the Window
        message_send_window = sg.Window('Course lecturer information', message_send_layout, size=(600, 300))
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = message_send_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                message_send_window.close()
                return 8
            if event == 'Back':
                message_send_window.close()
                return 4
            if event == 'Send':
                message_send_window.close()
                self.message_send_popup()
                return [title.get(), message.get()]

    def inbox_menu(self, inbox):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        inbox_listbox = sg.Listbox(values=inbox[1:], select_mode=None
                                   , pad=10, size=(90, 40), enable_events=False)
        inbox_layout = [[sg.Button(button_text='Back', expand_x=True), sg.Button(button_text='Read', expand_x=True)],
                        [inbox_listbox]]

        # Create the Window
        inbox_window = sg.Window('Inbox messages', inbox_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        selected_message = None
        while True:
            event, values = inbox_window.read()
            if inbox_listbox.get_indexes() is not None and len(
                    inbox_listbox.get_indexes()) != 0:
                selected_message = inbox_listbox.get_indexes()[
                    0]  # i dont know why this 2 is here but it fixes an offset
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                inbox_window.close()
                return 8
            if event == 'Back':
                inbox_window.close()
                return -1
            if event == 'Read':
                inbox_window.close()
                return selected_message

    def outbox_menu(self, inbox):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        outbox_listbox = sg.Listbox(values=inbox[1:], select_mode=None
                                    , pad=10, size=(90, 40), enable_events=False)
        outbox_layout = [[sg.Button(button_text='Back', expand_x=True), sg.Button(button_text='Read', expand_x=True)],
                         [outbox_listbox]]

        # Create the Window
        outbox_window = sg.Window('Outbox messages', outbox_layout)
        # Event Loop to process "events" and get the "values" of the inputs
        selected_message = None
        while True:
            event, values = outbox_window.read()
            if outbox_listbox.get_indexes() is not None and len(
                    outbox_listbox.get_indexes()) != 0:
                selected_message = outbox_listbox.get_indexes()[
                    0]  # i dont know why this 2 is here but it fixes an offset
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                outbox_window.close()
                return 8
            if event == 'Back':
                outbox_window.close()
                return -1
            if event == 'Read':
                outbox_window.close()
                return selected_message

    def message_read_menu(self, course_string):
        sg.theme('DarkAmber')  # Add a touch of color
        # All the stuff inside your window.
        message_read_layout = [[[sg.Button(button_text='Back', expand_x=True, pad=10)]],
                               [sg.Text(text=course_string[0], justification='left', auto_size_text=True
                                        , expand_x=True, expand_y=True, pad=10)]]

        # Create the Window
        message_read_window = sg.Window('Message Contents', message_read_layout, size=(600, 100))
        # Event Loop to process "events" and get the "values" of the inputs
        while True:
            event, values = message_read_window.read()
            if event == sg.WIN_CLOSED:  # if user closes window or clicks cancel
                message_read_window.close()
                return 8
            if event == 'Back':
                message_read_window.close()
                return 1

    def message_send_popup(self):
        pop_layout = [[sg.Text(text="Message has been sent!", expand_x=True, pad=10)],
                      [sg.Button(button_text="OK", expand_x=True, pad=10)]]
        pop_window = sg.Window("Notification", pop_layout)
        while True:
            event, values = pop_window.read()
            if event == sg.WIN_CLOSED:
                pop_window.close()
                return 8
            elif event == "OK":
                pop_window.close()
                break

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
        main_layout = [[sg.Text('Account Information')],
                       [sg.Button(button_text="Register"), sg.Button(button_text="Your Registered Courses")
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
                break
            elif event == 'Log out':
                main_window.close()
                return 2
            else:
                main_window.close()
                return event

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
            if event == sg.WIN_CLOSED or event == 'Cancel':  # if user closes window or clicks cancel
                break
            if event == 'Sign in':
                welcome_window.close()
                return [values[0], values[1]]

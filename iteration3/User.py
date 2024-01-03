from abc import ABC, abstractmethod


class User(ABC):
    @abstractmethod
    def get_action_list(self):
        pass

    @abstractmethod
    def run_user_action(self, action_number, controller):
        pass

    @abstractmethod
    def compare_credentials(self, username, password):
        pass

    @abstractmethod
    def start_actions(self, controller):
        pass

    # Methods added in iteration 2
    @abstractmethod
    def send_message(self, message, user):
        pass

    @abstractmethod
    def receive_message(self, message):
        pass

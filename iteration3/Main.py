from Controller import Controller
import os

def main():
    controller = Controller.get_instance()
    controller.start()


if __name__ == "__main__":
    main()


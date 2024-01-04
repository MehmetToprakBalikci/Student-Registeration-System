from Controller import Controller


def main():
    controller = Controller.get_instance()
    controller.start()


if __name__ == "__main__":
    main()


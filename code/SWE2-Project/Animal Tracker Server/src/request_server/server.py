import zmq
import json

from model.Animal import Animal
from model.AnimalClass import AnimalClass
from model.Project import Project
from model.Role import Role
from model.User import User
from model.data.ServerStorage import ServerStorage
from model.protocol.RequestHandler import RequestHandler


def build_storage():
    print("Creating User")
    storage = ServerStorage()
    bob = User("Bob", "1234", Role.SCIENTIST)
    Joe = User("Joe", "1234", Role.SCIENTIST)
    billy = User("Billy", "6767", Role.ADMIN)
    storage.add_user(bob)
    storage.add_user(billy)
    storage.add_user(Joe)
    animal = Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "Subject is a very aggressive bird DANGER!!!")
    animals = {animal}
    users = {bob}
    project = Project("Wildlife Migration Study", users, animals, 1)
    print(project.get_id())
    storage.add_project(project)

def main():
    context = zmq.Context()
    socket = context.socket(zmq.REP)

    socket.bind("tcp://127.0.0.1:5555")
    print("Server listening on port 5555...")

    build_storage()

    while True:
        raw = socket.recv()
        message = raw.decode("utf-8")

        if message == "exit":
            print("Server - Received exit, shutting down.")
            break

        handled_request = RequestHandler.handle_request(message)

        socket.send(json.dumps(handled_request).encode("utf-8"))

    socket.close()
    context.term()


if __name__ == "__main__":
    main()
from datetime import datetime

import zmq
import json

from model.Animal import Animal
from model.AnimalClass import AnimalClass
from model.Project import Project
from model.Role import Role
from model.Sighting import Sighting
from model.User import User
from model.data.ServerStorage import ServerStorage
from model.protocol.RequestHandler import RequestHandler


def build_prepopulated_storage_for_testing():
    print("Creating User")
    storage = ServerStorage()
    bob = User("Bob", "1234", Role.SCIENTIST)
    joe = User("Joe", "1234", Role.SCIENTIST)
    billy = User("Billy", "6767", Role.ADMIN)
    carl = User("Carl", "0987", Role.CONTRIBUTOR)
    storage.add_user(bob)
    storage.add_user(billy)
    storage.add_user(joe)
    storage.add_user(carl)
    animal = Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "Subject is a very aggressive bird DANGER!!!")
    sighting0 = Sighting(122345, "Tim", "Florida", 10.6, 20.6, datetime.now(), "Bird is flying over a lake.")
    sighting1 = Sighting(122345, "Bob", "Florida", 10.6, 20.6, datetime.now(), "Bird is drinking water.")
    animal2 = Animal(AnimalClass.BIRD, 21.0, 18.0, 20.0, 123456, "Subject is tall and travels in a pack")
    sighting2 = Sighting(123456, "Jimmy", "Florida", 12, 21, datetime.now(),
                         "Bird is traveling in a pack with similar birds.")
    storage.add_sighting(sighting0)
    storage.add_sighting(sighting1)
    storage.add_sighting(sighting2)
    animals = {animal, animal2}
    users = {bob}
    project = Project("Bird Wildlife Migration Study", users, animals, 1)
    project1 = Project("Atlantic Ocean Behavior Study", users, {}, 2)
    storage.add_project(project)
    storage.add_project(project1)


def main():
    context = zmq.Context()
    socket = context.socket(zmq.REP)

    socket.bind("tcp://127.0.0.1:5555")
    print("Server listening on port 5555...")

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
    build_prepopulated_storage_for_testing()
    main()

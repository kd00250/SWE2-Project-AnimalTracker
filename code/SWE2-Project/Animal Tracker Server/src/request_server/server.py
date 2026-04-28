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
    sarah = User("Sarah", "333", Role.SCIENTIST)
    kevin = User("Kevin", "777", Role.SCIENTIST)
    carl = User("Carl", "0987", Role.CONTRIBUTOR)
    storage.add_user(bob)
    storage.add_user(billy)
    storage.add_user(joe)
    storage.add_user(carl)
    storage.add_user(sarah)
    storage.add_user(kevin)
    #animal1 bird study

    animal = Animal(AnimalClass.BIRD, 11.0, 15.0, 17.0, 122345, "Subject is a very aggressive bird DANGER!!!")
    sighting0 = Sighting(122345, "Tim", "Florida", 10.6, 20.6, datetime.now(), "Bird is flying over a lake.")
    sighting1 = Sighting(122345, "Bob", "Florida", 10.6, 20.6, datetime.now(), "Bird is drinking water.")
    #animal2 bird study
    animal2 = Animal(AnimalClass.BIRD, 21.0, 18.0, 20.0, 123456, "Subject is tall and travels in a pack")
    sighting2 = Sighting(123456, "Jimmy", "Florida", 12, 21, datetime.now(),
                         "Bird is traveling in a pack with similar birds.")
    #animal3 bird study
    animal3 = Animal(AnimalClass.BIRD, 22.0, 15.6, 10.4, 121211, "Subject was found with four scars on it's left side.")
    sighting30 = Sighting(121211, "Billy", "Florida", 12.1, 21.1, datetime.now(), "Bird found alone.")
    sighting31 = Sighting(121211, "Tim", "Florida", 12.2, 21.3, datetime.now(), "Bird found eating fish.")
    sighting32 = Sighting(121211, "Elmer", "Florida", 12.0, 21.0, datetime.now(), "Bird looks hungry.")

    atlantic_animal0 = Animal(AnimalClass.FISH, 12.6, 50.4, 10.1, 61122, "Large atlantic cod with tan dots.")
    atl_sighting0 = Sighting(61122, "Tim", "Atlantic", 10.5, 60.4, datetime.now(),"Caught in the day off the shore.")
    atlantic_animal1 = Animal(AnimalClass.MAMMAL, 60.4, 500.45, 80.1, 786123, "Large manatee in perfect health.")
    atl_sighting10 = Sighting(786123, "Billy", "Atlantic", 40.5, 60.8, datetime.now(),"Found in a herd of manatees")
    storage.add_sighting(sighting0)
    storage.add_sighting(sighting1)
    storage.add_sighting(sighting2)
    storage.add_sighting(sighting30)
    storage.add_sighting(sighting31)
    storage.add_sighting(sighting32)
    storage.add_sighting(atl_sighting0)
    storage.add_sighting(atl_sighting10)

    animals0 = {animal, animal2, animal3}
    animals1 = {atlantic_animal0, atlantic_animal1}
    users = {bob}
    project = Project("Bird Wildlife Migration Study", users, animals0, 1)
    project1 = Project("Atlantic Ocean Behavior Study", users, animals1, 2)
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

import unittest

from model.Animal import Animal
from model.AnimalClass import AnimalClass
from model.Role import Role
from model.Project import Project
from model.User import User


class TestConstructor(unittest.TestCase):
    def test_none_name(self):
        with self.assertRaises(Exception):
            Project(None, [User("Casey", "Winkle", Role.ADMIN)], [], 0)

    def test_none_users(self):
        with self.assertRaises(Exception):
            Project("test", None, [], 0)

    def test_none_animals(self):
        with self.assertRaises(Exception):
            Project("1234", [User("Casey", "Winkle", Role.ADMIN)], None, 2)

    def test_negative_id(self):
        with self.assertRaises(Exception):
            Project("1234", [User("Casey", "Winkle", Role.ADMIN)], [], -5)

    def test_empty_name(self):
        with self.assertRaises(Exception):
            Project("", [User("Casey", "Winkle", Role.ADMIN)], [], 5)

    def test_create_valid_project(self):
        user = User("Casey", "Winkle", Role.ADMIN)
        animal = Animal(AnimalClass.BIRD, 10, 20, 20, 1, "A flying bird")
        project = Project("test", [user], [animal], 1)
        self.assertEqual("test", project.get_name())
        self.assertEqual(user, project.get_users()[0])
        first_animal = project.get_animals()[0]
        self.assertEqual(animal, first_animal)
        self.assertEqual(project.get_id(), 1)

import unittest

from model.Animal import Animal
from model.AnimalClass import AnimalClass
from model.Project import Project


class TestAddAnimal(unittest.TestCase):
    def test_add_none_animal(self):
        project = Project("1234", set(), set(), 1)
        with self.assertRaises(Exception):
            project.add_animal(None)

    def test_add_duplicate_animal(self):
        project = Project("1234", set(), set(), 1)
        animal = Animal(AnimalClass.BIRD, 20, 20, 20, 20, "1234")
        project.add_animal(animal)
        with self.assertRaises(Exception):
            project.add_animal(animal)

    def test_add_one_animal(self):
        project = Project("1234", set(), set(), 1)
        animal = Animal(AnimalClass.BIRD, 20, 20, 20, 20, "1234")
        project.add_animal(animal)
        self.assertEqual(len(project.get_animals()), 1)
        self.assertTrue(animal in project.get_animals())

    def test_multiple_animals(self):
        project = Project("1234", set(), set(), 1)
        animal = Animal(AnimalClass.BIRD, 20, 20, 20, 20, "1234")
        second_animal = Animal(AnimalClass.BIRD, 223, 15, 260, 2123, "1234")
        project.add_animal(animal)
        project.add_animal(second_animal)
        self.assertEqual(len(project.get_animals()), 2)
        self.assertTrue(animal in project.get_animals())
        self.assertTrue(second_animal in project.get_animals())


import unittest

from model.Animal import Animal
from model.Project import Project
from model.data.ProjectStorage import ProjectStorage


class TestUpdateAddAnimal(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage.reset()

    def test_update_add_animal(self):
        animal0 = Animal(10, 10, 10, 10, 10, "Test Desc")
        project0 = Project("Test", [], [animal0], 0)
        self._project_storage.add_project(project0)
        animal1 = Animal(20, 104, 150, 106, 110, "Another Desc")
        self._project_storage.add_project(project0)
        self.assertTrue(self._project_storage.update_add_animal(0, animal1))

        self.assertTrue(animal1 in self._project_storage.get_project(0).get_animals())

    def test_update_with_invalid_id(self):
        self.assertFalse(self._project_storage.update_add_animal(0, Animal(10, 10, 10, 10, 10, "Test Desc")))
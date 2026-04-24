import unittest
from unittest.mock import MagicMock

from model.data.ServerStorage import ServerStorage


class TestIsAnimalTagInServer(unittest.TestCase):

    def setUp(self):
        self.storage = ServerStorage()

    def test_tag_exists(self):
        animal = MagicMock()
        animal.get_tag_id.return_value = "1234"
        project = MagicMock()
        project.get_animals.return_value = [animal]

        self.storage._project_storage._projects = [project]

        result = self.storage.is_animal_tag_in_server("1234")

        self.assertTrue(result)

    def test_tag_does_not_exist(self):
        animal = MagicMock()
        animal.get_tag_id.return_value = "1234"
        project = MagicMock()
        project.get_animals.return_value = [animal]

        self.storage._projects = [project]

        result = self.storage.is_animal_tag_in_server("234")

        self.assertFalse(result)

    def test_no_projects(self):
        self.storage._projects = []

        result = self.storage.is_animal_tag_in_server("1234")

        self.assertFalse(result)

    def test_project_with_no_animals(self):
        project = MagicMock()
        project.get_animals.return_value = []

        self.storage._projects = [project]

        result = self.storage.is_animal_tag_in_server("1234")

        self.assertFalse(result)
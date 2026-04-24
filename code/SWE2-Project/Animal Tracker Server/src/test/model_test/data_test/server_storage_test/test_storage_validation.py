import unittest
from unittest.mock import MagicMock
from model.data.ServerStorage import ServerStorage


class TestIsAnimalTagInServer(unittest.TestCase):

    def setUp(self):
        self.storage = ServerStorage()
        self.storage._project_storage = MagicMock()

    def test_is_animal_tag_in_server_returns_true(self):
        self.storage._project_storage.is_animal_tag_in_server.return_value = True

        result = self.storage.is_animal_tag_in_server("1234")

        self.assertTrue(result)
        self.storage._project_storage.is_animal_tag_in_server.assert_called_once_with("1234")

    def test_is_animal_tag_in_server_returns_false(self):
        self.storage._project_storage.is_animal_tag_in_server.return_value = False

        result = self.storage.is_animal_tag_in_server("1234")

        self.assertFalse(result)
        self.storage._project_storage.is_animal_tag_in_server.assert_called_once_with("1234")
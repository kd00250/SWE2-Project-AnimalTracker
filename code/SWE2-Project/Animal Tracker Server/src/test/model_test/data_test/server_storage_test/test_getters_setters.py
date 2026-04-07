import unittest

from model.Animal import Animal
from model.Project import Project
from model.Role import Role
from model.User import User
from model.data.ServerStorage import ServerStorage


class TestServerStorageConstructor(unittest.TestCase):
    def setUp(self):
        self._server_storage = ServerStorage()
        self._server_storage._reset()

    def test_add_project(self):
        project = Project("test", [], [], 0)
        self._server_storage.add_project(project)

    def test_remove_project(self):
        project = Project("test", [], [], 0)
        self._server_storage.add_project(project)
        self._server_storage.remove_project(project)
        self.assertEqual(len(self._server_storage._project_storage._projects), 0)

    def test_get_project(self):
        project = Project("test", [], [], 0)
        self._server_storage.add_project(project)
        project1 = self._server_storage.get_project(0)
        self.assertEqual(project1.get_name(), project.get_name())

    def test_update_project_name(self):
        project = Project("test", [], [], 0)
        self._server_storage.add_project(project)
        self._server_storage.update_project_name(0, "Wildlife")
        project_returned = self._server_storage.get_project(0)
        self.assertEqual(project_returned.get_name(), "Wildlife")

    def test_update_add_animal(self):
        project = Project("test", set(), set(), 0)
        self._server_storage.add_project(project)
        animal1 = Animal(12, 12, 12, 12, 12, "desc")
        self._server_storage.update_add_animal(0, animal1)
        self.assertTrue(animal1 in self._server_storage.get_project(0).get_animals())

    def test_add_project_user(self):
        project = Project("test", set(), set(), 0)
        self._server_storage.add_project(project)
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_project_user(0, user)
        self.assertTrue(user in self._server_storage.get_project(0).get_users())

    def test_add_user(self):
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_user(user)
        self.assertTrue(user in self._server_storage._user_storage._users)

    def test_create_token(self):
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_user(user)
        token = self._server_storage.create_token(user.get_username())
        self.assertIsNotNone(token)

    def test_token_valid(self):
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_user(user)
        token = self._server_storage.create_token(user.get_username())
        self.assertTrue(self._server_storage.token_valid(token))

    def test_get_user(self):
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_user(user)
        token = self._server_storage.create_token(user.get_username())
        user_returned = self._server_storage.get_user(token)
        self.assertEqual(user, user_returned)

    def test_contains_user(self):
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_user(user)
        result = self._server_storage.contains_user(user)
        self.assertTrue(result)

    def test_contains_username(self):
        user = User("tim", "bob", Role.SCIENTIST)
        self._server_storage.add_user(user)
        result = self._server_storage.contains_username(user.get_username())
        self.assertTrue(result)





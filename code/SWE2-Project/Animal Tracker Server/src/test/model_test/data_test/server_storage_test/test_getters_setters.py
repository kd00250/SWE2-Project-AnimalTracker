import unittest
from datetime import datetime

from model.Animal import Animal
from model.Project import Project
from model.Role import Role
from model.Sighting import Sighting
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

    def test_retrieve_projects_from_user(self):
        user = User("Tim", "1234", Role.SCIENTIST)
        project = Project("Test", {user}, {}, 0)
        self._server_storage.add_project(project)
        projects = list(self._server_storage.retrieve_projects_from_user(user))
        self.assertEqual(len(projects), 1)
        self.assertEqual(projects[0].get_name(), project.get_name())

    def test_get_all_users(self):
        user = user = User("Tim", "1234", Role.SCIENTIST)
        self._server_storage.add_user(user)
        users = self._server_storage.get_all_users()
        self.assertEqual(len(users), 1)
        self.assertEqual(users[0].get_username(), user.get_username())

    def test_retrieve_projects_in_server(self):
        project = Project("Test", set(), set(), 0)
        self._server_storage.add_project(project)
        projects = self._server_storage.retrieve_projects_in_server()
        self.assertEqual(len(projects), 1)
        self.assertEqual(projects[0].get_name(), project.get_name())

    def test_get_user_with_username(self):
        original_user = User("Tim", "1234", Role.SCIENTIST)
        self._server_storage.add_user(original_user)
        user = self._server_storage.get_user_with_username(original_user.get_username())
        self.assertEqual(original_user, user)

    def test_contains_users_password(self):
        user = User("Tim", "1234", Role.SCIENTIST)
        self._server_storage.add_user(user)
        result = self._server_storage.contains_users_password(user.get_username(), user.get_password())
        self.assertTrue(result)

    def test_remove_user(self):
        user = User("Tim", "1234", Role.SCIENTIST)
        self._server_storage.add_user(user)
        self._server_storage.remove_user(user.get_username())
        self.assertTrue(len(self._server_storage.get_all_users()) == 0)

    def test_add_sighting(self):
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        has_added = self._server_storage.add_sighting(sighting)
        self.assertTrue(has_added)

    def test_remove_sighting(self):
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        has_added = self._server_storage.add_sighting(sighting)
        self.assertTrue(has_added)
        has_removed = self._server_storage.remove_sighting(sighting)
        self.assertTrue(has_removed)

    def test_retrieve_all_sightings(self):
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        sighting2 = Sighting("1235", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        self._server_storage.add_sighting(sighting)
        self._server_storage.add_sighting(sighting2)
        sightings = self._server_storage.retrieve_all_sightings()
        self.assertEqual(len(sightings), 2)

    def test_retrieve_sightings_by_animal_id(self):
        animal_tag = "1234"
        sighting = Sighting("1234", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        sighting2 = Sighting("1234", "user", "Forest", 10.0, 20.0, datetime.now(), "Notes")
        sighting3 = Sighting("1235", "user", "Park", 10.0, 20.0, datetime.now(), "Notes")
        self._server_storage.add_sighting(sighting)
        self._server_storage.add_sighting(sighting2)
        self._server_storage.add_sighting(sighting3)
        sightings = list(self._server_storage.retrieve_sightings_by_animal_id(animal_tag))
        self.assertEqual(len(sightings), 2)
        self.assertEqual([sighting, sighting2], sightings)





import unittest

from model.Project import Project
from model.Role import Role
from model.User import User
from model.data.ProjectStorage import ProjectStorage


class TestUpdateAddUser(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage.reset()

    def test_update_add_valid_user(self):
        user0 = User("Bob","1234",Role.ADMIN)
        project0 = Project("Test", [user0], [], 0)
        self._project_storage.add_project(project0)
        user1 = User("Tim", "12341234", Role.SCIENTIST)
        self.assertTrue(self._project_storage.update_add_user(0, user1))
        self.assertTrue(user1 in self._project_storage.get_project(0).get_users())

    def test_update_add_invalid_project(self):
        user0 = User("Bob","1234",Role.ADMIN)
        user1 = User("Tim", "12341234", Role.SCIENTIST)

        project0 = Project("Test", [user0], [], 0)
        self._project_storage.add_project(project0)
        self._project_storage.update_add_user(123, user1)
        self.assertFalse(user1 in self._project_storage.get_project(0).get_users())
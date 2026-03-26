import unittest

from model.Project import Project
from model.Role import Role
from model.User import User


class TestAddUser(unittest.TestCase):
    def test_add_one_user(self):
        admin = User("Tom", "1234", Role.ADMIN)
        project = Project("1234", [], [], 1)
        project.add_user(admin)
        returned_users = project.get_users()
        self.assertEqual(len(returned_users), 1)
        self.assertEqual(admin, returned_users[0])

    def test_add_multiple_user(self):
        admin = User("Tom", "1234", Role.ADMIN)
        scientist = User("Bob", "1234", Role.SCIENTIST)
        project = Project("test", [], [], 1)
        project.add_user(admin)
        project.add_user(scientist)
        returned_users = project.get_users()
        self.assertEqual(len(returned_users), 2)
        self.assertTrue(scientist in returned_users)
        self.assertTrue(scientist in returned_users)

    def test_add_none_user(self):
        project = Project("test", [], [], 1)
        with self.assertRaises(Exception):
            project.add_user(None)

    def test_add_duplicate_user(self):
        project = Project("test", [], [], 1)
        admin = User("Tom", "1234", Role.ADMIN)
        project.add_user(admin)
        with self.assertRaises(Exception):
            project.add_user(admin)


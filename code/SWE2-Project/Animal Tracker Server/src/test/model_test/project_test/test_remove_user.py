import unittest

from model.Project import Project
from model.Role import Role
from model.User import User


class TestRemoveUser(unittest.TestCase):
    def test_remove_none_does_nothing(self):
        project = Project("1234", set(), set(), 1)
        user_one = User("Tom", "1234", Role.SCIENTIST)
        project.add_user(user_one)
        project.remove_user(None)
        current_users = project.get_users()
        self.assertEqual(len(current_users), 1)

    def test_remove_one_user(self):
        project = Project("1234", set(), set(), 1)
        user_one = User("Tom", "1234", Role.SCIENTIST)
        project.add_user(user_one)
        project.remove_user(user_one)
        current_users = project.get_users()
        self.assertEqual(len(current_users), 0)

    def test_remove_one_user_multiple_in_list(self):
        project = Project("1234", set(), set(), 1)
        user_one = User("Tom", "1234", Role.SCIENTIST)
        user_two = User("tim", "1231244", Role.SCIENTIST)
        project.add_user(user_one)
        project.add_user(user_two)
        project.remove_user(user_two)
        current_users = project.get_users()
        self.assertEqual(len(current_users), 1)
        self.assertTrue(user_one in current_users)
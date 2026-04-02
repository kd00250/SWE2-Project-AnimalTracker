import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestContainsPassword(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_contains_password(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_users_password(user.get_username(), user.get_password())
        self.assertTrue(contains)

    def test_none_username(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_users_password(None, user.get_password())
        self.assertFalse(contains)

    def test_none_password(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_users_password(user.get_username(), None)
        self.assertFalse(contains)

    def test_does_not_contain(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_users_password(user.get_username(), "!!!!")
        self.assertFalse(contains)
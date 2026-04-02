import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestContainsUser(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_contains_user(self):
        user = User("Bob", "1234", Role.ADMIN)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_user(user)
        self.assertEqual(contains, True)

    def test_does_not_contain_user(self):
        user = User("Bob", "1234", Role.ADMIN)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_user("!!!!!")
        self.assertEqual(contains, False)
import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestContainsUsername(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_name_in_storage(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_username(user.get_username())
        self.assertEqual(contains, True)

    def test_None_username(self):
        contains = self._user_storage.contains_username(None)
        self.assertEqual(contains, False)

    def test_username_not_in_storage(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        contains = self._user_storage.contains_username("!!!!!")
        self.assertEqual(contains, False)


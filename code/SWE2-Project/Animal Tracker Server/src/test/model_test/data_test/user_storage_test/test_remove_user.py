import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestRemoveUser(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_remove_with_None_username(self):
        with self.assertRaises(Exception):
            self._user_storage.remove_user(None)

    def test_remove_existing_user(self):
        user = User("Bob", "1234", Role.ADMIN)
        self._user_storage.add_user(user)
        self._user_storage.remove_user(user.get_username())
        username_count = len(self._user_storage._username_map.keys())
        self.assertEqual(username_count, 0)
        self.assertEqual(len(self._user_storage._users), 0)

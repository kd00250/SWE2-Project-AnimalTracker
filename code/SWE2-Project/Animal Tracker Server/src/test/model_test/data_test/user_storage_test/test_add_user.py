import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestAddUser(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_add_None_user(self):
        user = None
        with self.assertRaises(Exception):
            self._user_storage.add_user(user)

    def test_add_valid_user(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        servers_user = self._user_storage._users[0]
        self.assertEqual(servers_user, user)
        self.assertEqual(self._user_storage._username_map["Bob"], user)

    def test_user_exist_already(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        server_user_count = len(self._user_storage._users)
        self._user_storage.add_user(user)
        self.assertEqual(server_user_count, 1)
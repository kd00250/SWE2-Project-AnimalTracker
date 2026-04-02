import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestGetUser(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_get_user(self):
        user = User("Bob", "1234", Role.ADMIN)
        self._user_storage.add_user(user)
        token = self._user_storage.create_token(user.get_username())
        returned_user = self._user_storage.get_user(token)
        self.assertEqual(returned_user, user)

    def test_invalid_token_get_user(self):
        with self.assertRaises(Exception):
            self._user_storage.get_user("!!!!!!!!!")

    

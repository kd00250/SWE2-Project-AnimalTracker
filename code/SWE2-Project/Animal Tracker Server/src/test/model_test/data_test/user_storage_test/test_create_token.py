import unittest

from model.Role import Role
from model.User import User
from model.data.UserStorage import UserStorage


class TestCreateToken(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_throws_when_username_missing(self):
        with self.assertRaises(Exception):
            self._user_storage.create_token("test")

    def test_create_valid_token(self):
        user = User("Bob", "1234", Role.SCIENTIST)
        self._user_storage.add_user(user)
        token = self._user_storage.create_token(user.get_username())
        associated_user = self._user_storage._token_map[token]
        self.assertEqual(associated_user, user)
        self.assertTrue(self._user_storage.token_valid(token))


import unittest

from model.Role import Role
from model.User import User


class TestUserConstructor(unittest.TestCase):

    def test_username_none(self):
        with self.assertRaises(Exception):
            User(None, "1234", Role.SCIENTIST)

    def test_password_none(self):
        with self.assertRaises(Exception):
            User("1234", None, Role.SCIENTIST)

    def test_password_empty(self):
        with self.assertRaises(Exception):
            User("1234", "", Role.SCIENTIST)

    def test_username_empty(self):
        with self.assertRaises(Exception):
            User("", "12346", Role.SCIENTIST)

    def test_valid_constructor(self):
        user = User("Tim", "1234", Role.SCIENTIST)

        self.assertEqual("Tim", user.get_username())
        self.assertEqual("1234", user.get_password())
        self.assertEqual(Role.SCIENTIST, user.get_role())
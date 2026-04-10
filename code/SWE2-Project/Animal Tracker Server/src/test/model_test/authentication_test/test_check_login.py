import unittest

from model.Role import Role
from model.User import User
from model.authentication.Authenticator import Authenticator
from model.data.ServerStorage import ServerStorage


class TestCheckLogin(unittest.TestCase):

    def test_invalid_request(self):
        request = {"action": "something"}
        result = Authenticator.check_login(request)
        self.assertIsNone(result)

    def test_request_no_username_or_password(self):
        request = {"action": "login"}
        result = Authenticator.check_login(request)
        self.assertIsNone(result)

    def test_request_username_only(self):
        request = {"action": "login", "username": "Bob"}
        result = Authenticator.check_login(request)
        self.assertIsNone(result)

    def test_request_password_only(self):
        request = {"action": "login", "password": "1234"}
        result = Authenticator.check_login(request)
        self.assertIsNone(result)

    def test_request_invalid_password(self):
        storage = ServerStorage()
        user = User("MarkRockers", "1234568907", Role.SCIENTIST)
        storage.add_user(user)
        request = {"action": "login", "password": "wrong", "username": "MarkRockers"}
        result = Authenticator.check_login(request)
        self.assertIsNone(result)

    def test_request_invalid_username(self):
        storage = ServerStorage()
        user = User("MarkRockers", "1234568907", Role.SCIENTIST)
        storage.add_user(user)
        request = {"action": "login", "password": "1234568907", "username": "Mark Rockers"}
        result = Authenticator.check_login(request)
        self.assertIsNone(result)

    def test_request_valid_password_and_username(self):
        storage = ServerStorage()
        user = User("MarkRockers", "1234568907", Role.SCIENTIST)
        storage.add_user(user)
        request = {"action": "login", "password": "1234568907", "username": "MarkRockers"}
        result = Authenticator.check_login(request)
        self.assertIsNotNone(result)


if __name__ == '__main__':
    unittest.main()

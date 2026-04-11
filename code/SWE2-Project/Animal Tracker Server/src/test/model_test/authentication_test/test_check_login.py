import unittest
from unittest.mock import patch, MagicMock

from model.authentication.Authenticator import Authenticator


class TestCheckLogin(unittest.TestCase):

    @patch("model.authentication.Authenticator.ServerStorage")
    def test_valid_login(self, mock_storage_class):
        mock_storage = MagicMock()
        mock_storage.contains_username.return_value = True
        mock_storage.contains_users_password.return_value = True
        mock_storage.create_token.return_value = "abc123"
        mock_storage_class.return_value = mock_storage

        request = {
            "action": "login",
            "username": "Bob",
            "password": "1234"
        }

        result = Authenticator.check_login(request)

        self.assertEqual("abc123", result)

    @patch("model.authentication.Authenticator.ServerStorage")
    def test_invalid_login(self, mock_storage_class):
        mock_storage = MagicMock()
        mock_storage.contains_username.return_value = False
        mock_storage_class.return_value = mock_storage

        request = {
            "action": "login",
            "username": "John",
            "password": "1234"
        }

        result = Authenticator.check_login(request)

        self.assertIsNone(result)

    @patch("model.authentication.Authenticator.ServerStorage")
    def test_invalid_action_type(self, mock_storage_class):
        request = {
            "action": "get_user",
            "username": "Bob",
            "password": "1234"
        }

        result = Authenticator.check_login(request)

        self.assertIsNone(result)

    @patch("model.authentication.Authenticator.ServerStorage")
    def test_username_exists_but_wrong_password(self, mock_storage_class):
        mock_storage = MagicMock()
        mock_storage.contains_username.return_value = True
        mock_storage.contains_users_password.return_value = False
        mock_storage_class.return_value = mock_storage

        request = {
            "action": "login",
            "username": "Bob",
            "password": "wrong"
        }

        result = Authenticator.check_login(request)

        self.assertIsNone(result)

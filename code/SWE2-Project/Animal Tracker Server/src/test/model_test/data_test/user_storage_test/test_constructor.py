import unittest

from model.data.UserStorage import UserStorage


class TestUserStorageConstructor(unittest.TestCase):
    def setUp(self):
        self._user_storage = UserStorage()

    def test_storage_constructed(self):
        self.assertEqual(0, len(self._user_storage._username_map))
        self.assertEqual(0, len(self._user_storage._token_map))
        self.assertEqual(0, len(self._user_storage._users))
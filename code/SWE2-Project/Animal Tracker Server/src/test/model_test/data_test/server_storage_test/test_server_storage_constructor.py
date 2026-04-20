import unittest

from model.data.ServerStorage import ServerStorage


class TestServerStorageConstructor(unittest.TestCase):
    def setUp(self):
        self._server_storage = ServerStorage()

    def test_initialized(self):
        self.assertIsNotNone(self._server_storage._project_storage)
        self.assertIsNotNone(self._server_storage._user_storage)
        self.assertIsNotNone(self._server_storage._sighting_storage)

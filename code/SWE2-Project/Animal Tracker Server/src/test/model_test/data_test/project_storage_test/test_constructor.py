import unittest

from model.data.ProjectStorage import ProjectStorage


class TestProjectStorageConstructor(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage._reset()

    def test_storage_initialized(self):
        self.assertEqual(0, len(self._project_storage._projects))
        self.assertEqual(0, len(self._project_storage._id_to_project))

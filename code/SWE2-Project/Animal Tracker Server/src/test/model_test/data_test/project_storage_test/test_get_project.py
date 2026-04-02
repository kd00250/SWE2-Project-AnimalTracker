import unittest

from model.Project import Project
from model.data.ProjectStorage import ProjectStorage


class TestProjectStorageConstructor(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage.reset()

    def test_get_valid_project(self):
        project0 = Project("Test", [], [], 0)
        self._project_storage.add_project(project0)
        project = self._project_storage.get_project(0)
        self.assertEqual(project, project0)
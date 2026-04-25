import unittest

from model.Project import Project
from model.data.ProjectStorage import ProjectStorage


class TestUpdateProjectName(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage._reset()

    def test_update_valid_project(self):
        project = Project("Test", [], [], 0)
        self._project_storage.add_project(project)
        self._project_storage.update_project_name(0, "Wildlife")
        project1 = self._project_storage.get_project(0)
        self.assertEqual(project1.get_name(), "Wildlife")

    def test_update_invalid_project(self):
        project = Project("Test", [], [], 0)
        self._project_storage.add_project(project)
        self._project_storage.update_project_name(10, "Wildlife")
        project1 = self._project_storage.get_project(0)
        self.assertEqual(project1.get_name(), "Test")

    
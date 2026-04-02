import unittest

from model.Project import Project
from model.data.ProjectStorage import ProjectStorage


class TestRemoveProject(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage.reset()

    def test_remove_valid_project(self):
        project = Project("Test", [], [], 0)
        project.set_id(self._project_storage.add_project(project))
        self._project_storage.remove_project(project)
        self.assertEqual(0, len(self._project_storage._projects))
        self.assertEqual(None, self._project_storage.get_project(project.get_id()))

    def test_remove_project_does_not_exist(self):
        project = Project("Test", [], [], 0)
        self._project_storage.add_project(project)
        self._project_storage.remove_project(Project("1234", [], [], 20))
        self.assertEqual(1, len(self._project_storage._projects))
        
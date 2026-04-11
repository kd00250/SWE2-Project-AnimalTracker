import unittest

from model.Project import Project
from model.data.ProjectStorage import ProjectStorage


class TestAddProject(unittest.TestCase):
    def setUp(self):
        self._project_storage = ProjectStorage()
        self._project_storage.reset()

    def test_add_valid_project(self):
        project = Project("Name", [], [], 0)
        id = self._project_storage.add_project(project)
        self.assertEqual(1, len(self._project_storage._projects))
        self.assertEqual(project, self._project_storage._projects[0])
        self.assertEqual(len(self._project_storage._projects) - 1, id)

    def test_add_already_exist(self):
        project = Project("Name", [], [], 0)
        self._project_storage.add_project(project)
        self._project_storage.add_project(project)
        self.assertEqual(1, len(self._project_storage._projects))

    def test_none_project(self):
        with self.assertRaises(Exception):
            self._project_storage.add_project(None)



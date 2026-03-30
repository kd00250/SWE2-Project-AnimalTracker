import unittest

from model.Project import Project


class TestSetters(unittest.TestCase):
    def test_none_name(self):
        project = Project("1234", [], [], 1)
        with self.assertRaises(Exception):
            project.set_name(None)

    def test_empty_name(self):
        project = Project("1234", [], [], 1)
        with self.assertRaises(Exception):
            project.set_name("")

    def test_negative_id(self):
        project = Project("1234", [], [], 10)
        with self.assertRaises(Exception):
            project.set_id(-1)

    def test_none_id(self):
        project = Project("1234", [], [], 1)
        with self.assertRaises(Exception):
            project.set_id(None)

    def test_set_valid_name(self):
        project = Project("1234", [], [], 1)
        project.set_name("Valid")
        self.assertEqual(project.get_name(), "Valid")

    def test_valid_id(self):
        project = Project("1234", [], [], 1)
        project.set_id(5)
        self.assertEqual(project.get_id(), 5)
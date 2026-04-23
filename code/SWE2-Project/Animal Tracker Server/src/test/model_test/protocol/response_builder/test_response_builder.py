import unittest
from unittest.mock import MagicMock

from model.Role import Role
from model.protocol.ResponseBuilder import ResponseBuilder


class TestResponseBuilder(unittest.TestCase):

    def test_build_login_response_success(self):
        result = ResponseBuilder.build_login_response("abc123")
        self.assertEqual({"token": "abc123"}, result)

    def test_build_login_response_error(self):
        result = ResponseBuilder.build_login_response(None)
        self.assertEqual({"status": "error"}, result)

    def test_build_get_role_response_success(self):
        result = ResponseBuilder.build_get_role_response("SCIENTIST")
        self.assertEqual({"role": "SCIENTIST"}, result)

    def test_build_get_role_response_error(self):
        result = ResponseBuilder.build_get_role_response(None)
        self.assertEqual({"status": "error"}, result)

    def test_build_add_user_response_success(self):
        mock_user = MagicMock()
        result = ResponseBuilder.build_add_user_response(mock_user)
        self.assertEqual({"status": "success"}, result)

    def test_build_add_user_response_error(self):
        result = ResponseBuilder.build_add_user_response(None)
        self.assertEqual({"status": "error"}, result)

    def test_build_user_exists_response(self):
        result = ResponseBuilder.build_user_exists_response()
        self.assertEqual({"status": "error"}, result)

    def test_build_retrieved_projects_response_none(self):
        result = ResponseBuilder.build_retrieved_projects_response(None)
        self.assertEqual({"status": "error", "projects": []}, result)

    def test_build_retrieved_projects_response_with_projects(self):
        mock_project1 = MagicMock()
        mock_project1.get_name.return_value = "Project1"
        mock_project1.get_id.return_value = 1

        mock_project2 = MagicMock()
        mock_project2.get_name.return_value = "Project2"
        mock_project2.get_id.return_value = 2

        result = ResponseBuilder.build_retrieved_projects_response([mock_project1, mock_project2])

        self.assertEqual(
            {
                "projects": [
                    {"name": "Project1", "id": 1},
                    {"name": "Project2", "id": 2},
                ]
            },
            result,
        )

    def test_build_user_does_not_have_permission_response(self):
        result = ResponseBuilder.build_user_does_not_have_permission_response()
        self.assertEqual({"status": "error"}, result)

    def test_build_could_not_find_project(self):
        result = ResponseBuilder.build_could_not_find_project()
        self.assertEqual({"status": "error"}, result)

    def test_build_get_project_response_animals_none(self):
        mock_project = MagicMock()
        mock_project.get_animals.return_value = None

        result = ResponseBuilder.build_get_project_response(mock_project)

        self.assertEqual(
            {
                "animals": [
                    {
                        "Class": "",
                        "Height": "",
                        "Weight": "",
                        "Length": "",
                        "TagID": "",
                        "Description": "",
                    }
                ]
            },
            result,
        )

    def test_build_get_project_response_with_animals(self):
        mock_class = MagicMock()
        mock_class.name = "MAMMAL"

        mock_animal = MagicMock()
        mock_animal.get_animal_class.return_value = mock_class
        mock_animal.get_height.return_value = 10.5
        mock_animal.get_weight.return_value = 25.0
        mock_animal.get_length.return_value = 30.0
        mock_animal.get_tag_id.return_value = 101
        mock_animal.get_description.return_value = "Test animal"

        mock_project = MagicMock()
        mock_project.get_animals.return_value = [mock_animal]

        result = ResponseBuilder.build_get_project_response(mock_project)

        self.assertEqual(
            {
                "animals": [
                    {
                        "Class": "MAMMAL",
                        "Height": 10.5,
                        "Weight": 25.0,
                        "Length": 30.0,
                        "TagID": 101,
                        "Description": "Test animal",
                    }
                ]
            },
            result,
        )

    def test_build_removed_project_success(self):
        result = ResponseBuilder.build_removed_project(True)
        self.assertEqual({"status": "success"}, result)

    def test_build_removed_project_error(self):
        result = ResponseBuilder.build_removed_project(False)
        self.assertEqual({"status": "error"}, result)

    def test_build_token_does_not_exist(self):
        result = ResponseBuilder.build_token_does_not_exist()
        self.assertEqual({"status": "error-TokenDoesNotExist"}, result)

    def test_build_get_scientist_response_filters_users(self):
        project_creator = MagicMock()
        project_creator.get_username.return_value = "creator"

        scientist_user = MagicMock()
        scientist_user.get_role.return_value = Role.SCIENTIST
        scientist_user.get_username.return_value = "alice"

        creator_user = MagicMock()
        creator_user.get_role.return_value = Role.SCIENTIST
        creator_user.get_username.return_value = "creator"

        admin_user = MagicMock()
        admin_user.get_role.return_value = Role.ADMIN
        admin_user.get_username.return_value = "admin"

        result = ResponseBuilder.build_get_scientist_response(
            [scientist_user, creator_user, admin_user],
            project_creator,
        )

        self.assertEqual(
            {
                "users": [
                    {
                        "username": "alice",
                        "password": "******",
                    }
                ]
            },
            result,
        )

    def test_build_get_scientist_response_no_matching_users(self):
        project_creator = MagicMock()
        project_creator.get_username.return_value = "creator"

        admin_user = MagicMock()
        admin_user.get_role.return_value = Role.ADMIN
        admin_user.get_username.return_value = "admin"

        creator_user = MagicMock()
        creator_user.get_role.return_value = Role.SCIENTIST
        creator_user.get_username.return_value = "creator"

        result = ResponseBuilder.build_get_scientist_response(
            [admin_user, creator_user],
            project_creator,
        )

        self.assertEqual({"users": []}, result)

    def test_build_create_project_response_error(self):
        result = ResponseBuilder.build_create_project_response(None)
        self.assertEqual({"status": "error"}, result)

    def test_build_create_project_response_success(self):
        result = ResponseBuilder.build_create_project_response(1)
        self.assertEqual({"status": "success"}, result)

    def test_build_add_animal_request_error(self):
        result = ResponseBuilder.build_add_animal_request(None)
        self.assertEqual({"status": "error"}, result)

    def test_build_add_animal_request_success(self):
        mock_animal = MagicMock()
        result = ResponseBuilder.build_add_animal_request(mock_animal)
        self.assertEqual({"status": "success"}, result)

    def test_build_user_is_not_in_system(self):
        result = ResponseBuilder.build_user_is_not_in_system()
        self.assertEqual({"status": "error"}, result)

    def test_build_add_sighting_request_success(self):
        mock_sighting = MagicMock()
        result = ResponseBuilder.build_add_sighting_request(mock_sighting)
        self.assertEqual({"status": "success"}, result)

    def test_build_add_sighting_request_error(self):
        result = ResponseBuilder.build_add_sighting_request(None)
        self.assertEqual({"status": "error"}, result)

    def test_build_tag_does_not_exist(self):
        result = ResponseBuilder.build_tag_does_not_exist()

        self.assertEqual({
            "status": "error",
        }, result)

    def test_build_get_sighting_response_with_none(self):
        result = ResponseBuilder.build_get_sighting_response(None)

        self.assertEqual({
            "sightings": [
                {
                    "Animal": "",
                    "User": "",
                    "Location": "",
                    "Latitude": "",
                    "Longitude": "",
                    "Time": "",
                    "Notes": "",
                }
            ]
        }, result)

    def test_build_get_sighting_response_with_empty_list(self):
        result = ResponseBuilder.build_get_sighting_response([])

        self.assertEqual({
            "sightings": [
                {
                    "Animal": "",
                    "User": "",
                    "Location": "",
                    "Latitude": "",
                    "Longitude": "",
                    "Time": "",
                    "Notes": "",
                }
            ]
        }, result)

    def test_build_get_sighting_response_with_sightings(self):
        mock_sighting = MagicMock()
        mock_sighting.get_animal_tag.return_value = "122345"
        mock_sighting.get_username.return_value = "Bob"
        mock_sighting.get_location.return_value = "Forest"
        mock_sighting.get_latitude.return_value = "33.1"
        mock_sighting.get_longitude.return_value = "-84.2"
        mock_sighting.get_time.return_value = "2026-04-21T08:00:00"
        mock_sighting.get_notes.return_value = "Spotted near trail"

        result = ResponseBuilder.build_get_sighting_response([mock_sighting])

        self.assertEqual({
            "sightings": [
                {
                    "Animal": "122345",
                    "User": "Bob",
                    "Location": "Forest",
                    "Latitude": "33.1",
                    "Longitude": "-84.2",
                    "Time": "2026-04-21T08:00:00",
                    "Notes": "Spotted near trail",
                }
            ]
        }, result)
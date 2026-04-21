import unittest
from unittest.mock import patch, MagicMock

from model.protocol.RequestHandler import RequestHandler
from model.Role import Role


class TestRequestHandler(unittest.TestCase):

    def setUp(self):
        RequestHandler.storage = MagicMock()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_login_response")
    @patch("model.protocol.RequestHandler.Authenticator.check_login")
    def test_handle_request_login(self, mock_check_login, mock_build_login_response):
        message = '{"action": "login", "username": "Bob", "password": "1234"}'
        request = {
            "action": "login",
            "username": "Bob",
            "password": "1234"
        }

        mock_check_login.return_value = "abc123"
        mock_build_login_response.return_value = {"token": "abc123"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"token": "abc123"}, result)
        mock_check_login.assert_called_once_with(request)
        mock_build_login_response.assert_called_once_with("abc123")

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_get_role_response")
    def test_handle_request_get_user_role_request(self, mock_build_get_role_response):
        mock_user = MagicMock()
        mock_role = MagicMock()
        mock_role.name = "SCIENTIST"
        mock_user.get_role.return_value = mock_role
        RequestHandler.storage.get_user.return_value = mock_user

        message = '{"action": "user_role_request", "token": "abc123"}'

        mock_build_get_role_response.return_value = {"role": "SCIENTIST"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"role": "SCIENTIST"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        mock_build_get_role_response.assert_called_once_with("SCIENTIST")

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_add_user_response")
    def test_handle_request_add_user_request_success(self, mock_build_add_user_response):
        RequestHandler.storage.contains_username.return_value = False
        message = '{"action": "add_user_request", "username": "John", "password": "1234", "role": "SCIENTIST"}'

        mock_build_add_user_response.return_value = {"status": "success"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "success"}, result)
        RequestHandler.storage.contains_username.assert_called_once_with("John")
        RequestHandler.storage.add_user.assert_called_once()
        mock_build_add_user_response.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_user_exists_response")
    def test_handle_request_add_user_request_user_exists(self, mock_build_user_exists_response):
        RequestHandler.storage.contains_username.return_value = True
        message = '{"action": "add_user_request", "username": "John", "password": "1234", "role": "SCIENTIST"}'

        mock_build_user_exists_response.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.contains_username.assert_called_once_with("John")
        mock_build_user_exists_response.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_retrieved_projects_response")
    def test_handle_request_get_project_list_request_success(self, mock_build_retrieved_projects_response):
        mock_user = MagicMock()
        mock_user.get_role.return_value = Role.SCIENTIST
        mock_projects = [MagicMock(), MagicMock()]

        RequestHandler.storage.get_user.return_value = mock_user
        RequestHandler.storage.retrieve_projects_from_user.return_value = mock_projects

        message = '{"action": "get_project_list_request", "token": "abc123"}'
        mock_build_retrieved_projects_response.return_value = {"projects": []}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"projects": []}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        RequestHandler.storage.retrieve_projects_from_user.assert_called_once_with(mock_user)
        mock_build_retrieved_projects_response.assert_called_once_with(mock_projects)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_user_does_not_have_permission_response")
    def test_handle_request_get_project_list_request_no_permission(self, mock_build_no_permission):
        mock_user = MagicMock()
        mock_user.get_role.return_value = Role.ADMIN
        RequestHandler.storage.get_user.return_value = mock_user

        message = '{"action": "get_project_list_request", "token": "abc123"}'
        mock_build_no_permission.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        mock_build_no_permission.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_get_project_response")
    @patch("model.protocol.RequestHandler.ResponseBuilder.build_could_not_find_project")
    def test_handle_request_get_project_request_success(self, mock_build_not_found, mock_build_get_project_response):
        mock_project = MagicMock()
        mock_project.get_id.return_value = 1
        mock_project.get_name.return_value = "Project1"
        RequestHandler.storage.get_project.return_value = mock_project

        message = '{"action": "get_project_request", "project id": "1", "project name": "Project1"}'
        mock_build_get_project_response.return_value = {"animals": []}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"animals": []}, result)
        RequestHandler.storage.get_project.assert_called_once_with("1")
        mock_build_get_project_response.assert_called_once_with(mock_project)
        mock_build_not_found.assert_not_called()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_could_not_find_project")
    def test_handle_request_get_project_request_project_not_found(self, mock_build_not_found):
        RequestHandler.storage.get_project.return_value = None

        message = '{"action": "get_project_request", "project id": "1", "project name": "Project1"}'
        mock_build_not_found.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.get_project.assert_called_once_with("1")
        mock_build_not_found.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_could_not_find_project")
    def test_handle_request_get_project_request_name_mismatch(self, mock_build_not_found):
        mock_project = MagicMock()
        mock_project.get_id.return_value = 1
        mock_project.get_name.return_value = "WrongName"
        RequestHandler.storage.get_project.return_value = mock_project

        message = '{"action": "get_project_request", "project id": "1", "project name": "Project1"}'
        mock_build_not_found.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        mock_build_not_found.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_removed_project")
    def test_handle_request_delete_project_request_user_none(self, mock_build_removed_project):
        RequestHandler.storage.get_user.return_value = None

        message = '{"action": "delete_project_request", "project id": "1", "token": "abc123"}'
        mock_build_removed_project.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        mock_build_removed_project.assert_called_once_with(False)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_removed_project")
    def test_handle_request_delete_project_request_success(self, mock_build_removed_project):
        mock_project = MagicMock()
        mock_project.get_id.return_value = 1
        mock_user = MagicMock()

        RequestHandler.storage.get_user.return_value = mock_user
        RequestHandler.storage.retrieve_projects_from_user.return_value = [mock_project]
        RequestHandler.storage.remove_project.return_value = True

        message = '{"action": "delete_project_request", "project id": "1", "token": "abc123"}'
        mock_build_removed_project.return_value = {"status": "success"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "success"}, result)
        RequestHandler.storage.retrieve_projects_from_user.assert_called_once_with(mock_user)
        RequestHandler.storage.remove_project.assert_called_once_with(mock_project)
        mock_build_removed_project.assert_called_once_with(True)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_removed_project")
    def test_handle_request_delete_project_request_project_not_owned(self, mock_build_removed_project):
        mock_project = MagicMock()
        mock_project.get_id.return_value = 2
        mock_user = MagicMock()

        RequestHandler.storage.get_user.return_value = mock_user
        RequestHandler.storage.retrieve_projects_from_user.return_value = [mock_project]

        message = '{"action": "delete_project_request", "project id": "1", "token": "abc123"}'
        mock_build_removed_project.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        mock_build_removed_project.assert_called_once_with(False)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_token_does_not_exist")
    def test_handle_request_get_scientist_request_token_missing(self, mock_build_token_does_not_exist):
        RequestHandler.storage.get_all_users.return_value = []

        message = '{"action": "get_scientist_request"}'
        mock_build_token_does_not_exist.return_value = {"status": "error-TokenDoesNotExist"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error-TokenDoesNotExist"}, result)
        RequestHandler.storage.get_all_users.assert_called_once()
        mock_build_token_does_not_exist.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_get_scientist_response")
    def test_handle_request_get_scientist_request_success(self, mock_build_get_scientist_response):
        mock_users = [MagicMock()]
        mock_creator = MagicMock()

        RequestHandler.storage.get_all_users.return_value = mock_users
        RequestHandler.storage.get_user.return_value = mock_creator

        message = '{"action": "get_scientist_request", "token": "abc123"}'
        mock_build_get_scientist_response.return_value = {"users": []}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"users": []}, result)
        RequestHandler.storage.get_all_users.assert_called_once()
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        mock_build_get_scientist_response.assert_called_once_with(mock_users, mock_creator)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_create_project_response")
    @patch("model.protocol.RequestHandler.Project")
    def test_handle_request_create_project_request_success_with_found_user(self, mock_project_class,
                                                                           mock_build_create_project_response):
        mock_creator = MagicMock()
        mock_project = MagicMock()
        mock_found_user = MagicMock()

        mock_project_class.return_value = mock_project
        RequestHandler.storage.get_user.return_value = mock_creator
        RequestHandler.storage.retrieve_projects_in_server.return_value = []
        RequestHandler.storage.get_user_with_username.return_value = mock_found_user
        RequestHandler.storage.add_project.return_value = 1

        message = '{"action": "create_project_request", "token": "abc123", "project name": "Project1", "users": ["John"]}'
        mock_build_create_project_response.return_value = {"status": "success"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "success"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        RequestHandler.storage.retrieve_projects_in_server.assert_called_once()
        RequestHandler.storage.get_user_with_username.assert_called_once_with("John")
        mock_project.add_user.assert_called_once_with(mock_found_user)
        RequestHandler.storage.add_project.assert_called_once_with(mock_project)
        mock_build_create_project_response.assert_called_once_with(1)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_create_project_response")
    @patch("model.protocol.RequestHandler.Project")
    def test_handle_request_create_project_request_skips_none_user(self, mock_project_class,
                                                                   mock_build_create_project_response):
        mock_creator = MagicMock()
        mock_project = MagicMock()

        mock_project_class.return_value = mock_project
        RequestHandler.storage.get_user.return_value = mock_creator
        RequestHandler.storage.retrieve_projects_in_server.return_value = []
        RequestHandler.storage.get_user_with_username.return_value = None
        RequestHandler.storage.add_project.return_value = 1

        message = '{"action": "create_project_request", "token": "abc123", "project name": "Project1", "users": ["Ghost"]}'
        mock_build_create_project_response.return_value = {"status": "success"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "success"}, result)
        RequestHandler.storage.get_user_with_username.assert_called_once_with("Ghost")
        mock_project.add_user.assert_not_called()
        RequestHandler.storage.add_project.assert_called_once_with(mock_project)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_add_animal_request")
    @patch("model.protocol.RequestHandler.Animal")
    def test_handle_request_add_animal_request_success(self, mock_animal_class, mock_build_add_animal_request):
        mock_project = MagicMock()
        mock_animal = MagicMock()
        updated_animal = MagicMock()

        mock_animal_class.return_value = mock_animal
        RequestHandler.storage.get_project.return_value = mock_project
        RequestHandler.storage.update_add_animal.return_value = updated_animal

        message = (
            '{"action": "add_animal_request", "project id": "1", '
            '"Class": "MAMMAL", "Height": "10.0", "Weight": "25.0", '
            '"Length": "15.0", "TagID": "100", "Description": "Test animal"}'
        )
        mock_build_add_animal_request.return_value = {"status": "success"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "success"}, result)
        RequestHandler.storage.get_project.assert_called_once_with(1)
        RequestHandler.storage.update_add_animal.assert_called_once_with(1, mock_animal)
        mock_build_add_animal_request.assert_called_once_with(updated_animal)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_could_not_find_project")
    def test_handle_request_add_animal_request_project_not_found(self, mock_build_not_found):
        RequestHandler.storage.get_project.return_value = None

        message = (
            '{"action": "add_animal_request", "project id": "1", '
            '"Class": "MAMMAL", "Height": "10.0", "Weight": "25.0", '
            '"Length": "15.0", "TagID": "100", "Description": "Test animal"}'
        )
        mock_build_not_found.return_value = {"status": "error"}

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.get_project.assert_called_once_with(1)
        mock_build_not_found.assert_called_once()

    def test_handle_request_unknown_action_returns_none(self):
        message = '{"action": "unknown_request"}'

        result = RequestHandler.handle_request(message)

        self.assertIsNone(result)

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_user_is_not_in_system")
    def test_handle_request_add_sighting_request_username_is_none(self, mock_build_user_not_in_system):
        RequestHandler.storage.get_user.return_value = None
        mock_build_user_not_in_system.return_value = {"status": "error"}

        message = (
            '{"action": "add_sighting_request", "token": "abc123", '
            '"animal": "122345", "location": "Forest", "latitude": "33.1", '
            '"longitude": "-84.2", "time": "2026-04-21T08:00:00", "notes": "Spotted near trail"}'
        )

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        RequestHandler.storage.contains_username.assert_not_called()
        RequestHandler.storage.add_sighting.assert_not_called()
        mock_build_user_not_in_system.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_user_is_not_in_system")
    def test_handle_request_add_sighting_request_username_not_in_system(self, mock_build_user_not_in_system):
        RequestHandler.storage.get_user.return_value = "Bob"
        RequestHandler.storage.contains_username.return_value = False
        mock_build_user_not_in_system.return_value = {"status": "error"}

        message = (
            '{"action": "add_sighting_request", "token": "abc123", '
            '"animal": "122345", "location": "Forest", "latitude": "33.1", '
            '"longitude": "-84.2", "time": "2026-04-21T08:00:00", "notes": "Spotted near trail"}'
        )

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "error"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        RequestHandler.storage.contains_username.assert_called_once_with("Bob")
        RequestHandler.storage.add_sighting.assert_not_called()
        mock_build_user_not_in_system.assert_called_once()

    @patch("model.protocol.RequestHandler.ResponseBuilder.build_add_sighting_request")
    @patch("model.protocol.RequestHandler.Sighting")
    def test_handle_request_add_sighting_request_success(self, mock_sighting_class, mock_build_add_sighting_request):
        RequestHandler.storage.get_user.return_value = "Bob"
        RequestHandler.storage.contains_username.return_value = True

        mock_sighting = MagicMock()
        mock_sighting_class.return_value = mock_sighting
        mock_build_add_sighting_request.return_value = {"status": "success"}

        message = (
            '{"action": "add_sighting_request", "token": "abc123", '
            '"animal": "122345", "location": "Forest", "latitude": "33.1", '
            '"longitude": "-84.2", "time": "2026-04-21T08:00:00", "notes": "Spotted near trail"}'
        )

        result = RequestHandler.handle_request(message)

        self.assertEqual({"status": "success"}, result)
        RequestHandler.storage.get_user.assert_called_once_with("abc123")
        RequestHandler.storage.contains_username.assert_called_once_with("Bob")
        mock_sighting_class.assert_called_once_with(
            "122345",
            "Bob",
            "Forest",
            "33.1",
            "-84.2",
            "2026-04-21T08:00:00",
            "Spotted near trail"
        )
        RequestHandler.storage.add_sighting.assert_called_once_with(mock_sighting)
        mock_build_add_sighting_request.assert_called_once_with(mock_sighting)

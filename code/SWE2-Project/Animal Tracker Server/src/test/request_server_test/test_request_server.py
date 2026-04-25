import unittest
from unittest.mock import patch, MagicMock, call
from request_server.server import build_prepopulated_storage_for_testing, main


class TestServer(unittest.TestCase):

    @patch("request_server.server.Project")
    @patch("request_server.server.Sighting")
    @patch("request_server.server.Animal")
    @patch("request_server.server.User")
    @patch("request_server.server.ServerStorage")
    def test_build_prepopulated_storage_for_testing(
            self,
            mock_server_storage_class,
            mock_user_class,
            mock_animal_class,
            mock_sighting_class,
            mock_project_class):
        mock_storage = MagicMock()
        mock_server_storage_class.return_value = mock_storage

        mock_bob = MagicMock()
        mock_joe = MagicMock()
        mock_billy = MagicMock()
        mock_carl = MagicMock()

        mock_animal = MagicMock()
        mock_animal2 = MagicMock()

        mock_sighting0 = MagicMock()
        mock_sighting1 = MagicMock()
        mock_sighting2 = MagicMock()

        mock_project = MagicMock()
        mock_project1 = MagicMock()

        mock_user_class.side_effect = [
            mock_bob,
            mock_joe,
            mock_billy,
            mock_carl
        ]

        mock_animal_class.side_effect = [
            mock_animal,
            mock_animal2
        ]

        mock_sighting_class.side_effect = [
            mock_sighting0,
            mock_sighting1,
            mock_sighting2
        ]

        mock_project_class.side_effect = [
            mock_project,
            mock_project1
        ]

        build_prepopulated_storage_for_testing()

        self.assertEqual(4, mock_user_class.call_count)
        self.assertEqual(2, mock_animal_class.call_count)
        self.assertEqual(3, mock_sighting_class.call_count)
        self.assertEqual(2, mock_project_class.call_count)

        mock_storage.add_user.assert_has_calls([
            call(mock_bob),
            call(mock_billy),
            call(mock_joe),
            call(mock_carl)
        ])

        mock_storage.add_sighting.assert_has_calls([
            call(mock_sighting0),
            call(mock_sighting1),
            call(mock_sighting2)
        ])

        mock_storage.add_project.assert_has_calls([
            call(mock_project),
            call(mock_project1)
        ])

    @patch("request_server.server.RequestHandler.handle_request")
    @patch("request_server.server.zmq.Context")
    def test_main_handles_one_request_then_exit(
            self,
            mock_context_class,
            mock_handle_request
    ):
        mock_context = MagicMock()
        mock_socket = MagicMock()
        mock_context_class.return_value = mock_context
        mock_context.socket.return_value = mock_socket

        mock_raw_message = MagicMock()
        mock_raw_message.decode.return_value = '{"action": "login"}'

        mock_exit_message = MagicMock()
        mock_exit_message.decode.return_value = "exit"

        mock_socket.recv.side_effect = [mock_raw_message, mock_exit_message]
        mock_handle_request.return_value = {"status": "success"}

        main()

        mock_context.socket.assert_called_once()
        mock_socket.bind.assert_called_once_with("tcp://127.0.0.1:5555")
        mock_handle_request.assert_called_once_with('{"action": "login"}')
        mock_socket.send.assert_called_once_with(b'{"status": "success"}')
        mock_socket.close.assert_called_once()
        mock_context.term.assert_called_once()

    @patch("request_server.server.RequestHandler.handle_request")
    @patch("request_server.server.zmq.Context")
    def test_main_exits_immediately_on_exit_message(
            self,
            mock_context_class,
            mock_handle_request
    ):
        mock_context = MagicMock()
        mock_socket = MagicMock()
        mock_context_class.return_value = mock_context
        mock_context.socket.return_value = mock_socket

        mock_exit_message = MagicMock()
        mock_exit_message.decode.return_value = "exit"
        mock_socket.recv.return_value = mock_exit_message

        main()

        mock_socket.bind.assert_called_once_with("tcp://127.0.0.1:5555")
        mock_handle_request.assert_not_called()
        mock_socket.send.assert_not_called()
        mock_socket.close.assert_called_once()
        mock_context.term.assert_called_once()

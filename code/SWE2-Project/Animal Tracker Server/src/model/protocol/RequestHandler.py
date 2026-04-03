import json

from model.Role import Role
from model.User import User
from model.authentication.Authenticator import Authenticator
from model.data.ServerStorage import ServerStorage
from model.protocol.ResponseBuilder import ResponseBuilder


class RequestHandler:

    @staticmethod
    def handle_request(message):
        storage = ServerStorage()

        request = json.loads(message)
        print(f"Server - Received request: {request}")

        if request.get("action") == "login":
            has_token = Authenticator.check_login(request)
            response = ResponseBuilder.build_login_response(has_token)
            return response

        if request.get("action") == "user_role_request":
            token = request.get("token")
            user = storage.get_user(token)
            role = user.get_role().name
            response = ResponseBuilder.build_get_role_response(role)
            return response

        if request.get("action") == "add_user_request":
            username = request.get("username")
            if not storage.contains_username(username):
                password = request.get("password")
                role = Role[request.get("role")]
                user = User(username, password, role)
                response = ResponseBuilder.build_add_user_response(user)
                return response
            return ResponseBuilder.build_user_exists_response()

        if request.get("action") == "get_project_list_request":
            token = request.get("token")
            user = storage.get_user(token)
            role = user.get_role()

            if role == Role.SCIENTIST:
                projects = storage.retrieve_projects_from_user(user)
                response = ResponseBuilder.build_retrieved_projects_response(projects)
                return response
            return ResponseBuilder.build_user_does_not_have_permission_response()


        return None



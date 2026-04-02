import json

from model.authentication.Authenticator import Authenticator
from model.data.ServerStorage import ServerStorage


class RequestHandler:

    @staticmethod
    def handle_request(message):
        storage = ServerStorage()

        request = json.loads(message)
        print(f"Server - Received request: {request}")

        if request.get("action") == "login":
            has_token = Authenticator.check_login(message)
            return has_token

        if request.get("action") == "user_role_request":
            token = request.get("token")
            user = storage.get_user(token)
            role = user.get_role()
            return role
        return None



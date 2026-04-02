from model.data.ServerStorage import ServerStorage
import json


class Authenticator:

    @staticmethod
    def check_login(message):
        storage = ServerStorage()
        request = json.loads(message)
        print(f"In Authenticator")

        if request["action"] != "login":
            return None
        print("Server - Login Action!")

        username = request["username"]
        password = request["password"]

        if not storage.contains_username(request["username"]):
            return None
        print(f"Server - Login {username}!")

        if not storage.contains_users_password(username, password):
            return None
        print(f"Server - Login {password}!")
        token = storage.create_token(username)
        return token

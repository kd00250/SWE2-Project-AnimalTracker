from model.data.ServerStorage import ServerStorage


class Authenticator:

    @staticmethod
    def check_login(request):
        storage = ServerStorage()
        print(f"In Authenticator")

        if request["action"] != "login":
            return None
        print("Server - Login Action!")

        username = request.get("username")
        password = request.get("password")

        if not storage.contains_username(username):
            return None
        print(f"Server - Login {username}!")

        if not storage.contains_users_password(username, password):
            return None
        print(f"Server - Login {password}!")
        token = storage.create_token(username)
        return token

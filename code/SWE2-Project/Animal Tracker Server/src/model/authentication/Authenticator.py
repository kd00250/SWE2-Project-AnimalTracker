from model.data.ServerStorage import ServerStorage


class Authenticator:
    """
    Provides authentication-related functionality for validating user login
        requests and generating authentication tokens.
    """

    @staticmethod
    def check_login(request):

        """
        Validates user login requests and generates authentication tokens.
        :param request: A JSON-like dictionary containing login data.
                        Expected keys:
                        "action" (str): The type of request to perform
                        "username" (str): The username of the user
                        "password" (str): The password of the user
        :return: str | None: A generated authentication token if the login is successful;
                        otherwise, None if authentication fails.
        """
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

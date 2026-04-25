import uuid


class UserStorage:
    """
    The storage class that manages users for the server.
    """

    def __init__(self):
        """
        Initialize the user storage object.
        """
        self._username_map = {}
        self._users = []
        self._token_map = {}

    def add_user(self, user):
        """
        Add a user to the user storage.
        :param user: The user to add.
        """
        if user is None:
            raise Exception('User is required')
        username = user.get_username()
        if username not in self._username_map:
            self._users.append(user)
            self._username_map[username] = user

    def create_token(self, username):
        """
        Create a new token for a user.
        :param username: The username of the user.
        :return: The new token.
        """
        if username not in self._username_map:
            raise Exception("Invalid token creation")

        user_token = uuid.uuid4()
        user = self._username_map[username]
        self._token_map[str(user_token)] = user

        return str(user_token)

    def token_valid(self, token):
        """
        Check if a token is valid.
        :param token: The token to check.
        :return: True if the token is valid, False otherwise.
        """
        return token in self._token_map.keys()

    def get_user(self, token):
        """
        Gets a user from the user storage using a token.
        :param token: The token to use to get the user.
        :return: The user.
        """
        if token not in self._token_map:
            raise Exception("Invalid user access token")

        return self._token_map[token]

    def remove_user(self, username):
        """
        Removes a user from the user storage.
        :param username: The username of the user.
        """
        if username is None:
            raise Exception("Invalid username")
        self._username_map.pop(username)

        user = None
        for current_user in self._users:
            if current_user.get_username() == username:
                user = current_user

        self._users.remove(user)

    def contains_user(self, user):
        """
        Checks if a user exists in the user storage.
        :param user: The user to check.
        :return: True if the user exists, False otherwise.
        """
        if user in self._users:
            return True
        return False

    def contains_username(self, username):
        """
        Checks if a username exists in the user storage.
        :param username: The username to check.
        :return: True if the username exists, False otherwise.
        """
        if username in self._username_map.keys():
            return True
        return False

    def contains_users_password(self, username, password):
        """
        Checks if a username exists in the user storage.
        :param username: The username to check.
        :param password: The password to check.
        :return: True if the username exists, False otherwise.
        """
        if username is None or password is None:
            return False
        user = self._username_map[username]
        if user.get_password() == password:
            return True
        return False

    def get_user_with_username(self, username):
        """
        Gets a user from the user storage using a username.
        :param username: The username to get.
        :return: The user.
        """
        if username is None:
            return None
        return self._username_map.get(username)

    def get_all_users(self):
        """
        Gets all users in the user storage.
        :return: The list of users.
        """
        return list(self._users)

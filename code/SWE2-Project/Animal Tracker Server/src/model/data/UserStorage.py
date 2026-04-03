import uuid
"""
The user storage which stores and updates user information.
"""
class UserStorage:

    """
    Instantiates a new user storage.
    """
    def __init__(self):
        self._username_map = {}
        self._users = []
        self._token_map = {}

    def add_user(self, user):
        if user is None:
            raise Exception('User is required')
        username = user.get_username()
        if username not in self._username_map:
            self._users.append(user)
            self._username_map[username] = user

    def create_token(self, username):
        if username not in self._username_map:
            raise Exception("Invalid token creation")

        user_token = uuid.uuid4()
        user = self._username_map[username]
        self._token_map[str(user_token)] = user

        return str(user_token)

    def token_valid(self, token):
        return token in self._token_map.keys()

    def get_user(self, token):
        if token not in self._token_map:
            raise Exception("Invalid user access token")

        return self._token_map[token]

    def remove_user(self, username):
        if username is None:
            raise Exception("Invalid username")
        self._username_map.pop(username)

        user = None
        for current_user in self._users:
            if current_user.get_username() == username:
                user = current_user

        self._users.remove(user)
    def contains_user(self, user):
        if user in self._users:
            return True
        return False

    def contains_username(self, username):
        if username in self._username_map.keys():
            return True
        return False

    def contains_users_password(self, username, password):
        if username is None or password is None:
            return False
        user = self._username_map[username]
        if user.get_password() == password:
            return True
        return False

    def get_user_with_username(self, username):
        if username is None:
            return None
        return self._username_map.get(username)

    def get_all_users(self):
        return list(self._users)


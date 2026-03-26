import uuid
class UserStorage:
    def __init__(self):
        self._username_map = {}
        self._users = []
        self._token_map = {}

    def add_user(self, user):
        if user not in self._username_map:
            self._users.append(user)
            self._username_map[user] = user.username

    def create_token(self, username):
        if username not in self._token_map:
            raise Exception("Invalid token creation")

        user_token = uuid.uuid4()
        user = self._username_map[username]
        self._token_map[str(user_token)] = user

        return user_token

    def token_valid(self, token):
        return token in self._token_map

    def get_user(self, token):
        if token not in self._token_map:
            raise Exception("Invalid user access token")

        return self._token_map[token]

    def remove_user(self, username):
        self._username_map.pop(username)

        user = None
        for current_user in self._users:
            if user.username == username:
                user = current_user

        self._users.remove(user)

    def _remove_token(self, username):
        token_to_delete = None
        for token in self._token_map.values():
            found_user = self._username_map[token]
            if found_user.username == username:
                token_to_delete = token

        if token_to_delete is not None:
            self._token_map.pop(token_to_delete)

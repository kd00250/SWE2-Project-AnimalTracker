
class User:
    def __init__(self, username, password, role):
        if username is None:
            raise Exception("Username cannot be None")
        if password is None:
            raise Exception("Password cannot be None")
        if not username.strip():
            raise Exception("Username cannot be empty")
        if not password.strip():
            raise Exception("Password cannot be empty")

        self._username = username
        self._password = password
        self._role = role

    def get_username(self):
        return self._username
    def get_password(self):
        return self._password
    def get_role(self):
        return self._role
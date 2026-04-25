class User:
    """
    Class representing a user.
    """

    def __init__(self, username, password, role):
        """
        Initializes a new user.
        :param username: The username of the user.
        :param password: The password of the user.
        :param role: The role of the user.
        """
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
        """
        Gets the username of the user.
        :return: The username of the user.
        """
        return self._username

    def get_password(self):
        """
        Gets the password of the user.
        :return: The password of the user.
        """
        return self._password

    def get_role(self):
        """
        Gets the role of the user.
        :return: The role of the user.
        """
        return self._role

from model.data.UserStorage import UserStorage


class Storage:
    def __init__(self, username, password, role):
        self._user_storage = UserStorage()
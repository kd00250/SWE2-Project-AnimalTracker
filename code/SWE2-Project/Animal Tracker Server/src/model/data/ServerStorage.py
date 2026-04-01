from model.data.UserStorage import UserStorage

"""
A singleton server used to store model objects for this server.
"""
class ServerStorage:
    instance = None

    """
    Modifies the creation of this server to only allow once instance.
    """
    def __new__(cls):
        if cls.instance is None:
            cls.instance = super().__new__(cls)
        return cls.instance

    """
    Instantiates a server storage.
    """
    def __init__(self):
        self._user_storage = UserStorage()

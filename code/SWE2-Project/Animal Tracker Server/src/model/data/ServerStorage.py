from model.data.ProjectStorage import ProjectStorage
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
        self._project_storage = ProjectStorage()

    def add_project(self, project):
        self._project_storage.add_project(project)
    """
    Removes a project from the storage
    """
    def remove_project(self, project):
        self._project_storage.remove_project(project)
    """
    Gets a project from the storage
    """
    def get_project(self, project_id):
        self._project_storage.get_project(project_id)
    """
    Updates a projects name inside the storage.
    """
    def update_project_name(self, project_id, name):
        self._project_storage.get_project(project_id).set_name(name)

    def update_add_animal(self, project_id, animal):
        self._project_storage.get_project(project_id).add_animal(animal)

    def add_project_user(self, project_id, user):
        if self._user_storage.contains_user(user):
            is_added = self._project_storage.get_project(project_id).add_user(user)
            return is_added
        return False

    def project_remove_user(self, project_id, user):
        self._project_storage.get_project(project_id).remove_user(user)

    def add_user(self, user):
        self._user_storage.add_user(user)

    def create_token(self, username):
        self._user_storage.create_token(username)

    def token_valid(self, token):
        self._user_storage.token_valid(token)

    def get_user(self, token):
        self._user_storage.get_user(token)

    def remove_user(self, username):
        self._user_storage.remove_user(username)




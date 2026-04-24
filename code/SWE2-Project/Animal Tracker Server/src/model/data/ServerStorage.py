from model.data.ProjectStorage import ProjectStorage
from model.data.SightingStorage import SightingStorage
from model.data.UserStorage import UserStorage


class ServerStorage:
    """
    The server storage class responsible for handling storage for the server.
    """
    instance = None

    def __new__(cls):
        if cls.instance is None:
            cls.instance = super().__new__(cls)
        return cls.instance

    def __init__(self):
        """
        Initializes the server storage class if no instance exist.
        """
        if hasattr(self, "_initialized"):
            return
        self._user_storage = UserStorage()
        self._project_storage = ProjectStorage()
        self._sighting_storage = SightingStorage()
        self._initialized = True

    def add_project(self, project):
        """
        Adds a project to the server storage.
        :param project: The project to add.
        """
        self._project_storage.add_project(project)

    def remove_project(self, project):
        """
        Removes a project from the server storage.
        :param project: The project to remove.
        :return: True if the project was removed, False otherwise.
        """
        return self._project_storage.remove_project(project)

    def get_project(self, project_id):
        """
        Gets a project from the server storage.
        :param project_id: The project id.
        :return: The project if it was found, None otherwise.
        """
        return self._project_storage.get_project(project_id)

    def update_project_name(self, project_id, name):
        """
        Updates a project name within the server storage.
        :param project_id: The project id.
        :param name: The new name.
        """
        self._project_storage.get_project(project_id).set_name(name)

    def update_add_animal(self, project_id, animal):
        """
        Updates an animal in the server storage.
        :param project_id: The project id.
        :param animal: The new animal.
        """
        self._project_storage.get_project(project_id).add_animal(animal)

    def add_project_user(self, project_id, user):
        """
        Adds a user to the server storage.
        :param project_id: The project id.
        :param user: The new user.
        """
        self._project_storage.update_add_user(project_id, user)

    def add_user(self, user):
        """
        Adds a user to the server storage.
        :param user: The new user.
        """
        self._user_storage.add_user(user)

    def create_token(self, username):
        """
        Creates a new token for a user based on username.
        :param username: The username.
        :return: The new token.
        """
        return self._user_storage.create_token(username)

    def token_valid(self, username):
        """
        Checks if a token is valid.
        :param username: The username.
        :return: True if the token is valid, False otherwise.
        """
        return self._user_storage.token_valid(username)

    def get_user(self, token):
        """
        Gets a user from the server storage using a token.
        :param token: The token.
        :return: The user.
        """
        return self._user_storage.get_user(token)

    def remove_user(self, username):
        """
        Removes a user from the server storage.
        :param username: The username.
        """
        self._user_storage.remove_user(username)

    def contains_username(self, username):
        """
        Checks if a username exists in the server storage.
        :param username: The username.
        :return: True if the username exists, False otherwise.
        """
        return self._user_storage.contains_username(username)

    def contains_users_password(self, username, password):
        """
        Checks if a users password exists in the server storage.
        :param username: The username.
        :param password: The password.
        :return: True if the password exists, False otherwise.
        """
        return self._user_storage.contains_users_password(username, password)

    def contains_user(self, user):
        """
        Checks if a user exists in the server storage.
        :param user: The user.
        :return: True if the user exists, False otherwise.
        """
        return self._user_storage.contains_user(user)

    def retrieve_projects_from_user(self, user):
        """
        Retrieves projects associated with a user.
        :param user: The user.
        :return: The projects.
        """
        projects = self._project_storage.retrieve_projects_from_user(user)
        return projects

    def get_all_users(self):
        """
        Retrieves all users in the system.
        :return: The users.
        """
        return self._user_storage.get_all_users()

    def retrieve_projects_in_server(self):
        """
        Retrieves all projects in the system.
        :return: The projects.
        """
        return self._project_storage.retrieve_projects_in_server()

    def get_user_with_username(self, username):
        """
        Gets a user from the server storage using a username.
        :param username: The username.
        :return: The user.
        """
        return self._user_storage.get_user_with_username(username)

    def add_sighting(self, sighting):
        """
        Adds a sighting to the server storage.
        :param sighting: Sighting object.
        :return: True or False depending on if the sighting was added.
        """
        return self._sighting_storage.add_sighting(sighting)

    def remove_sighting(self, sighting):
        """
        Removes a sighting from the server storage.
        :param sighting: Sighting object to be removed.
        :return: True or False depending on if the sighting was removed.
        """
        return self._sighting_storage.remove_sighting(sighting)

    def retrieve_all_sightings(self):
        """
        Retrieves all sightings in the system.
        :return: All sightings in the system.
        """
        return self._sighting_storage.retrieve_all_sightings()

    def retrieve_sightings_by_animal_id(self, animal_tag):
        """
        Retrieves all sightings from the sighting list by animal tag.
        :param animal_tag: Id of an animal.
        :return: All sightings from the sighting list by animal tag.
        """
        result = self._sighting_storage.retrieve_sightings_by_animal_id(animal_tag)
        return result

    def is_animal_tag_in_server(self, animal_tag):
        return self._project_storage.is_animal_tag_in_server(animal_tag)

    def _reset(self):
        self._user_storage = UserStorage()
        self._project_storage = ProjectStorage()
        self._sighting_storage = SightingStorage()
        self._initialized = True

class Project:
    def __init__(self, name, users, animals, project_id):
        """
        Initializes a new project.
        :param name: The name of the project.
        :param users: The users of the project.
        :param animals: The animals of the project.
        :param project_id: The ID of the project.
        """
        if name is None:
            raise Exception("Project name cannot be None")
        if users is None:
            raise Exception("Project users cannot be None")
        if project_id < 0:
            raise Exception("Project project_id cannot be negative")
        if name.strip() == "":
            raise Exception("Project name cannot be an empty string")
        if animals is None:
            raise Exception("Project animals cannot be None")
        self._name = name
        self._users = users
        self._animals = animals
        self._id = project_id

    def get_name(self):
        """
        Gets the name of the project.
        :return: The name of the project.
        """
        return self._name
    def get_users(self):
        """
        Gets the users in the project.
        :return: The users in the project.
        """
        return self._users
    def get_animals(self):
        """
        Gets the animals in the project.
        :return: The animals in the project.
        """
        return self._animals

    def get_id(self):
        """
        Gets the id of the project.
        :return: The id of the project.
        """
        return self._id
    def set_name(self, name):
        """
        Sets the name of the project with a new name.
        :param name: The new name of the project.
        """
        if name is None:
            raise Exception("Project name cannot be None")
        if name.strip() == "":
            raise Exception("Project name cannot be an empty string")
        self._name = name

    def set_id(self, id):
        """
        Sets the id of the project with a new id.
        :param id: The new id of the project.
        """
        if id is None:
            raise Exception("Project id cannot be None")
        if id < 0:
            raise Exception("Project id cannot be negative")
        self._id = id

    def add_user(self, user):
        """
        Adds a user to the project.
        :param user: The user to add.
        """
        if user is None:
            raise Exception("User cannot be None")
        if self.contains_user(user):
            raise Exception("User already exists")
        self._users.add(user)

    def contains_user(self, user):
        """
        Checks if the user is in the project.
        :param user: The user to check.
        """
        return user in self._users

    def add_animal(self, animal):
        """
        Adds a new animal to the project.
        :param animal: The animal to add.
        """
        if animal is None:
            raise Exception("Animal cannot be None")
        if self.contains_animal(animal):
            raise Exception("Animal already exists")
        self._animals.add(animal)
    def contains_animal(self, animal):
        """
        Checks if the project contains the animal.
        :param animal: The animal to check.
        :return: True if the project contains the animal.
        """
        return animal in self._animals

    def remove_user(self, user):
        """
        Removes a user from the project.
        :param user: The user to remove.
        """
        if user is None:
            return
        self._users.remove(user)